import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CaixaEletronicoOperacoesSemLogarTest {
    private CaixaEletronico caixa;
    private ServicoRemotoMock servicoRemotoMock;

    @Before
    public void setUp() {
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(new HardwareMock(), servicoRemotoMock);
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void saldoDeslogado() throws UsuarioNaoLogadoException {
        caixa.saldo();
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void sacarSemLogar() throws UsuarioNaoLogadoException, ProblemaHardwareException {
        caixa.sacar(new BigDecimal("10.00"));

        servicoRemotoMock.naoChamouPersistirConta();
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void depositarSemLogar() throws UsuarioNaoLogadoException, ProblemaHardwareException {
        caixa.depositar(new BigDecimal("10.00"));

        servicoRemotoMock.naoChamouPersistirConta();
    }
}
