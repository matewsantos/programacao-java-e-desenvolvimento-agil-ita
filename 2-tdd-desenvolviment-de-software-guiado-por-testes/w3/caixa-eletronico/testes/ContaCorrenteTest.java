import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContaCorrenteTest {
    ContaCorrente contaCorrente;

    @Before
    public void setup() {
        contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
    }

    @Test
    public void senhaConfereSenhaCorreta() {
        assertTrue(contaCorrente.senhaConfere("senhasecreta"));
    }

    @Test
    public void senhaConfereSenhaIncorreta() {
        assertFalse(contaCorrente.senhaConfere("senhaerrada"));
    }

    @Test
    public void saldo() {
        assertEquals(new BigDecimal("100.00"), contaCorrente.saldo());
    }

    @Test
    public void sacarComSucesso() {
        boolean resultado = contaCorrente.sacar(new BigDecimal("10.00"));

        assertTrue(resultado);
        assertEquals(new BigDecimal("90.00"), contaCorrente.saldo());
    }

    @Test
    public void sacarMaisDoQuePermitido() {
        boolean resultado = contaCorrente.sacar(new BigDecimal("100.01"));

        assertFalse(resultado);
        assertEquals(new BigDecimal("100.00"), contaCorrente.saldo());
    }

    @Test
    public void depositar() {
        contaCorrente.depositar(new BigDecimal("10.00"));

        assertEquals(new BigDecimal("110.00"), contaCorrente.saldo());
    }
}
