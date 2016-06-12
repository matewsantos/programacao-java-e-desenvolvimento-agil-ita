import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CaixaEletronicoOperacoesSemLogarTest {
    private CaixaEletronico caixa;
    private HardwareMock hardwareMock;
    private ServicoRemotoMock servicoRemotoMock;
    private ContaCorrente contaCorrente;

    @Before
    public void setUp() {
        hardwareMock = new HardwareMock();
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(hardwareMock, servicoRemotoMock);
        contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void saldoDeslogado() throws UsuarioNaoLogadoException {
        caixa.saldo();
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void sacarSemLogar() throws UsuarioNaoLogadoException, ProblemaHardwareException {
        caixa.sacar(new BigDecimal("100.01"));

        assertFalse(servicoRemotoMock.chamouMetodoPersistirConta());
    }

    @Test(expected = UsuarioNaoLogadoException.class)
    public void depositarSemLogar() throws UsuarioNaoLogadoException, ProblemaHardwareException {
        caixa.depositar(new BigDecimal("10.00"));

        assertEquals(new BigDecimal("100.00"), contaCorrente.saldo());
        assertFalse(servicoRemotoMock.chamouMetodoPersistirConta());
    }
}
