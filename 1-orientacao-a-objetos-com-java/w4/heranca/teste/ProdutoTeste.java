import org.junit.Test;

import static org.junit.Assert.*;

public class ProdutoTeste {

    @Test
    public void testeGetCodigo() {
        Produto p = new Produto("celular", 1, 1_000);

        assertEquals(1, p.getCodigo());
    }

    @Test
    public void testeGetPreco() {
        Produto p = new Produto("celular", 1, 1_000);

        assertEquals(1_000, p.getPreco(), 0.001);
    }

    @Test
    public void testeGetNome() {
        Produto p = new Produto("celular", 1, 1_000);

        assertEquals("celular", p.getNome() );
    }

    @Test
    public void testeHashCodeMesmoCodigo() {
        Produto p1 = new Produto("celular", 1, 1_000);
        Produto p2 = new Produto("tablet", 1, 500);

        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testeHashCodeCodigosDiferentes() {
        Produto p1 = new Produto("celular", 1, 1_000);
        Produto p2 = new Produto("celular", 2, 1_000);

        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testeEqualsMesmoCodigo() {
        Produto p1 = new Produto("celular", 1, 1_000);
        Produto p2 = new Produto("tablet", 1, 500);

        assertTrue(p1.equals(p2));
    }

    @Test
    public void testeEqualsCodigosDiferentes() {
        Produto p1 = new Produto("celular", 1, 1_000);
        Produto p2 = new Produto("celular", 2, 1_000);

        assertFalse(p1.equals(p2));
    }
}