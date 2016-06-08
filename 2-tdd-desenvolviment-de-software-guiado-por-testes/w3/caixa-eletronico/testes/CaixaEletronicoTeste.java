import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

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
    public void loginComSucesso() throws ContaInexistenteException, SenhaErradaException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("0")));

        assertEquals("Usuário Autenticado", caixa.login("senhasecreta"));
    }

    @Test(expected = ContaInexistenteException.class)
    public void loginComErroContaCorrenteNaoEncontrada() throws ContaInexistenteException, SenhaErradaException {
        servicoRemotoMock.quandoChamarRecupaContaRetornar(null);

        caixa.login("senhasecreta");
    }

    @Test(expected = SenhaErradaException.class)
    public void loginComErroSenhaIncorreta() throws ContaInexistenteException, SenhaErradaException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("0")));

        caixa.login("senhaerrada");
    }

    @Test
    public void saldoComSucesso() throws ContaInexistenteException, SenhaErradaException, UsuarioNaoLogadoException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00")));
        caixa.login("senhasecreta");

        assertEquals("O saldo é R$ 100,00", caixa.saldo());
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void saldoDeslogado() throws ContaInexistenteException, SenhaErradaException, UsuarioNaoLogadoException {
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00")));

        caixa.saldo();
    }
}
