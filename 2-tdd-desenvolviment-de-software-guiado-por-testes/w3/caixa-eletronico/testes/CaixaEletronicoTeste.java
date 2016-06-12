import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CaixaEletronicoTeste {
    private CaixaEletronico caixa;
    private ServicoRemotoMock servicoRemotoMock;
    private HardwareMock hardwareMock;
    private ContaCorrente contaCorrente;

    @Before
    public void setUp() {
        String numeroConta = "1";
        hardwareMock = new HardwareMock();
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(hardwareMock, servicoRemotoMock);
        contaCorrente = new ContaCorrente(numeroConta, "senhasecreta", new BigDecimal("100.00"));
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

    @Test
    public void saldoComSucesso()
            throws ContaInexistenteException, SenhaIncorretaException, UsuarioNaoLogadoException, ProblemaHardwareException {
        caixa.logar("senhasecreta");

        assertEquals("O saldo é R$ 100,00", caixa.saldo());
    }

    @Test
    public void sacarComSucesso() throws SenhaIncorretaException, ContaInexistenteException, ProblemaHardwareException,
            UsuarioNaoLogadoException {
        caixa.logar("senhasecreta");

        String resultado = caixa.sacar(new BigDecimal("10.00"));
        assertEquals("Retire seu dinheiro", resultado);
        assertEquals(new BigDecimal("90.00"), contaCorrente.saldo());
        assertTrue(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test
    public void sacarMaisDoQuePode() throws SenhaIncorretaException, ContaInexistenteException, ProblemaHardwareException,
            UsuarioNaoLogadoException {
        caixa.logar("senhasecreta");

        String resultado = caixa.sacar(new BigDecimal("100.01"));
        assertEquals("Saldo Insuficiente", resultado);
        assertEquals(new BigDecimal("100.00"), contaCorrente.saldo());
        assertFalse(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test(expected = ProblemaHardwareException.class)
    public void sacarComProblemaDeHardware() throws SenhaIncorretaException, ContaInexistenteException,
            ProblemaHardwareException, UsuarioNaoLogadoException {
        hardwareMock.lancarExceptionQuandoChamar("entregarDinheiro");

        caixa.logar("senhasecreta");

        caixa.sacar(new BigDecimal("10.00"));
    }

    @Test
    public void depositarComSucesso() throws SenhaIncorretaException, ContaInexistenteException, ProblemaHardwareException,
            UsuarioNaoLogadoException {
        caixa.logar("senhasecreta");

        String resultado = caixa.depositar(new BigDecimal("10.00"));
        assertEquals("Depósito recebido com sucesso", resultado);
        assertEquals(new BigDecimal("110.00"), contaCorrente.saldo());
        assertTrue(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test(expected = ProblemaHardwareException.class)
    public void depositarComProblemaNoHardware() throws SenhaIncorretaException, ContaInexistenteException,
            ProblemaHardwareException, UsuarioNaoLogadoException {
        hardwareMock.lancarExceptionQuandoChamar("lerEnvelope");

        caixa.logar("senhasecreta");

        caixa.depositar(new BigDecimal("10.00"));
    }
}
