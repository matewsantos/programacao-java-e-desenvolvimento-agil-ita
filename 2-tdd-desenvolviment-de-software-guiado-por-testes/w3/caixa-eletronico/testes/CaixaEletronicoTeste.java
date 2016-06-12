import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CaixaEletronicoTeste {
    private CaixaEletronico caixa;
    private HardwareMock hardwareMock;
    private ServicoRemotoMock servicoRemotoMock;

    @Before
    public void setUp() {
        hardwareMock = new HardwareMock();
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(hardwareMock, servicoRemotoMock);
    }

    @Test
    public void logarComSucesso() throws ContaInexistenteException, SenhaErradaException, ProblemaHardwareException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("0")));

        assertEquals("Usuário Autenticado", caixa.logar("senhasecreta"));
    }

    @Test(expected = ContaInexistenteException.class)
    public void logarComErroContaCorrenteNaoEncontrada() throws ContaInexistenteException, SenhaErradaException, ProblemaHardwareException {
        servicoRemotoMock.quandoChamarRecupaContaRetornar(null);

        caixa.logar("senhasecreta");
    }

    @Test(expected = SenhaErradaException.class)
    public void logarComErroSenhaIncorreta() throws ContaInexistenteException, SenhaErradaException, ProblemaHardwareException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("0")));

        caixa.logar("senhaerrada");
    }

    @Test(expected = SenhaErradaException.class)
    public void logarComErroProblemaHardware() throws ContaInexistenteException, SenhaErradaException, ProblemaHardwareException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("0")));
        hardwareMock.lancarExceptionQuandoChamar("pegarNumeroCartao");

        caixa.logar("senhaerrada");
    }

    @Test
    public void saldoComSucesso() throws ContaInexistenteException, SenhaErradaException, UsuarioNaoLogadoException, ProblemaHardwareException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00")));
        caixa.logar("senhasecreta");

        assertEquals("O saldo é R$ 100,00", caixa.saldo());
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void saldoDeslogado() throws ContaInexistenteException, SenhaErradaException, UsuarioNaoLogadoException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00")));

        caixa.saldo();
    }

    @Test
    public void sacarComSucesso() throws SenhaErradaException, ContaInexistenteException, ProblemaHardwareException, UsuarioNaoLogadoException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        caixa.logar("senhasecreta");

        String resultado = caixa.sacar(new BigDecimal("10.00"));
        assertEquals("Retire seu dinheiro", resultado);
        assertEquals(new BigDecimal("90.00"), contaCorrente.saldo());
        assertTrue(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test
    public void sacarMaisDoQuePode() throws SenhaErradaException, ContaInexistenteException, ProblemaHardwareException, UsuarioNaoLogadoException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        caixa.logar("senhasecreta");

        String resultado = caixa.sacar(new BigDecimal("100.01"));
        assertEquals("Saldo Insuficiente", resultado);
        assertEquals(new BigDecimal("100.00"), contaCorrente.saldo());
        assertFalse(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void sacarSemLogar() throws UsuarioNaoLogadoException, ProblemaHardwareException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);

        String resultado = caixa.sacar(new BigDecimal("100.01"));
        assertFalse(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test(expected = ProblemaHardwareException.class)
    public void sacarComProbleaDeHardware() throws SenhaErradaException, ContaInexistenteException, ProblemaHardwareException, UsuarioNaoLogadoException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        hardwareMock.lancarExceptionQuandoChamar("entregarDinheiro");
        caixa.logar("senhasecreta");

        caixa.sacar(new BigDecimal("10.00"));
    }

    @Test
    public void depositarComSucesso() throws SenhaErradaException, ContaInexistenteException, ProblemaHardwareException, UsuarioNaoLogadoException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        caixa.logar("senhasecreta");

        String resultado = caixa.depositar(new BigDecimal("10.00"));
        assertEquals("Depósito recebido com sucesso", resultado);
        assertEquals(new BigDecimal("110.00"), contaCorrente.saldo());
        assertTrue(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test(expected = ProblemaHardwareException.class)
    public void depositarComProblemaNoHardware() throws SenhaErradaException, ContaInexistenteException, ProblemaHardwareException, UsuarioNaoLogadoException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
        hardwareMock.lancarExceptionQuandoChamar("lerEnvelope");
        caixa.logar("senhasecreta");

        caixa.depositar(new BigDecimal("10.00"));
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void depositarSemLogar() throws UsuarioNaoLogadoException, ProblemaHardwareException {
        ContaCorrente contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);

        caixa.depositar(new BigDecimal("10.00"));
        assertFalse(servicoRemotoMock.chamouMetodoPersistirConta());
    }
}