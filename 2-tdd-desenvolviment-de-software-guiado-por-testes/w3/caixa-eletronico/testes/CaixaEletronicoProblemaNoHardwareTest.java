import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CaixaEletronicoProblemaNoHardwareTest {
    private CaixaEletronico caixa;
    private HardwareMock hardwareMock;
    private ServicoRemotoMock servicoRemotoMock;

    @Before
    public void setUp() {
        hardwareMock = new HardwareMock();
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(hardwareMock, servicoRemotoMock);
    }

    @Test(expected = SenhaIncorretaException.class)
    public void logarComErroProblemaHardware()
            throws ContaInexistenteException, SenhaIncorretaException, ProblemaHardwareException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("0"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        hardwareMock.lancarExceptionQuandoChamar("pegarNumeroCartao");

        caixa.logar("senhaerrada");
    }

    @Test(expected = ProblemaHardwareException.class)
    public void sacarComProbleaDeHardware() throws SenhaIncorretaException, ContaInexistenteException,
            ProblemaHardwareException, UsuarioNaoLogadoException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        hardwareMock.lancarExceptionQuandoChamar("entregarDinheiro");
        caixa.logar("senhasecreta");

        caixa.sacar(new BigDecimal("10.00"));
    }


    @Test(expected = ProblemaHardwareException.class)
    public void depositarComProblemaNoHardware() throws SenhaIncorretaException, ContaInexistenteException,
            ProblemaHardwareException, UsuarioNaoLogadoException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        hardwareMock.lancarExceptionQuandoChamar("lerEnvelope");
        caixa.logar("senhasecreta");

        caixa.depositar(new BigDecimal("10.00"));
    }
}
