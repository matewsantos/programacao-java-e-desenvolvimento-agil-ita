import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CaixaEletronicoLogarTest {
    private CaixaEletronico caixa;
    private ServicoRemotoMock servicoRemotoMock;
    private HardwareMock hardwareMock;

    @Before
    public void setUp() {
        String numeroConta = "1";
        hardwareMock = new HardwareMock();
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(hardwareMock, servicoRemotoMock);
        ContaCorrente contaCorrente = new ContaCorrente(numeroConta, "senhasecreta", new BigDecimal("100.00"));
        hardwareMock.quandoPegarNumeroDaContaRetornar(numeroConta);
        servicoRemotoMock.quandoChamarRecupaContaCom(numeroConta).retornar(contaCorrente);
    }

    @Test
    public void logarComSucesso() throws ContaInexistenteException, SenhaIncorretaException, ProblemaHardwareException {
        assertEquals("Usuário Autenticado", caixa.logar("senhasecreta"));
    }

    @Test(expected = ContaInexistenteException.class)
    public void logarComErroContaCorrenteNaoEncontrada()
            throws ContaInexistenteException, SenhaIncorretaException, ProblemaHardwareException {
        servicoRemotoMock.quandoChamarRecupaContaRetornar(null);

        caixa.logar("senhasecreta");
    }

    @Test(expected = SenhaIncorretaException.class)
    public void logarComErroSenhaIncorreta()
            throws ContaInexistenteException, SenhaIncorretaException, ProblemaHardwareException {
        caixa.logar("senhaerrada");
    }

    @Test(expected = ProblemaHardwareException.class)
    public void logarComErroProblemaHardware()
            throws ContaInexistenteException, SenhaIncorretaException, ProblemaHardwareException {
        hardwareMock.lancarExceptionQuandoChamar("pegarNumeroDaContaCartao");

        caixa.logar("senhasecreta");
    }
}
