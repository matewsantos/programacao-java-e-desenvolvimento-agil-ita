import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CaixaEletronicoOperacoesLogadasTest {
    private CaixaEletronico caixa;
    private ServicoRemotoMock servicoRemotoMock;
    private HardwareMock hardwareMock;
    private ContaCorrente contaCorrente;

    @Before
    public void setUp() throws ProblemaHardwareException, ContaInexistenteException, SenhaIncorretaException {
        String numeroConta = "1";
        hardwareMock = new HardwareMock();
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(hardwareMock, servicoRemotoMock);
        contaCorrente = new ContaCorrente(numeroConta, "senhasecreta", new BigDecimal("100.00"));
        hardwareMock.quandoPegarNumeroDaContaRetornar(numeroConta);
        servicoRemotoMock.quandoChamarRecupaContaCom(numeroConta).retornar(contaCorrente);
        caixa.logar("senhasecreta");
    }

    @Test
    public void saldoComSucesso() throws UsuarioNaoLogadoException {
        assertEquals("O saldo é R$ 100,00", caixa.saldo());
    }

    @Test
    public void sacarComSucesso() throws  ProblemaHardwareException, UsuarioNaoLogadoException {
        String resultado = caixa.sacar(new BigDecimal("10.00"));

        assertEquals("Retire seu dinheiro", resultado);
        assertEquals(new BigDecimal("90.00"), contaCorrente.saldo());
        servicoRemotoMock.chamouPersistirContaCom(contaCorrente);
    }

    @Test
    public void sacarMaisDoQuePode() throws ProblemaHardwareException, UsuarioNaoLogadoException {
        String resultado = caixa.sacar(new BigDecimal("100.01"));

        assertEquals("Saldo Insuficiente", resultado);
        assertEquals(new BigDecimal("100.00"), contaCorrente.saldo());
        servicoRemotoMock.naoChamouPersistirConta();
    }

    @Test(expected = ProblemaHardwareException.class)
    public void sacarComProblemaDeHardware() throws ProblemaHardwareException, UsuarioNaoLogadoException {
        hardwareMock.lancarExceptionQuandoChamar("entregarDinheiro");

        caixa.sacar(new BigDecimal("10.00"));
    }

    @Test
    public void depositarComSucesso() throws ProblemaHardwareException, UsuarioNaoLogadoException {
        String resultado = caixa.depositar(new BigDecimal("10.00"));

        assertEquals("Depósito recebido com sucesso", resultado);
        assertEquals(new BigDecimal("110.00"), contaCorrente.saldo());
        servicoRemotoMock.chamouPersistirContaCom(contaCorrente);
    }

    @Test(expected = ProblemaHardwareException.class)
    public void depositarComProblemaNoHardware() throws ProblemaHardwareException, UsuarioNaoLogadoException {
        hardwareMock.lancarExceptionQuandoChamar("lerEnvelope");

        caixa.depositar(new BigDecimal("10.00"));
    }
}
