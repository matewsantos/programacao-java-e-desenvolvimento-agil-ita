import org.junit.Test;

import static org.junit.Assert.*;

public class ProdutoComTamanhoTeste {

    @Test
    public void testeHashCodeMesmoCodigoETamanho() {
        Produto p1 = new ProdutoComTamanho("celular", 1, 1_000, 10);
        Produto p2 = new ProdutoComTamanho("tablet", 1, 500, 10);

        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testeHashCodeCodigosDiferentes() {
        Produto p1 = new ProdutoComTamanho("celular", 1, 1_000, 10);
        Produto p2 = new ProdutoComTamanho("celular", 2, 1_000, 10);

        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testeHashCodeTamanhosDiferentes() {
        Produto p1 = new ProdutoComTamanho("celular", 1, 1_000, 1);
        Produto p2 = new ProdutoComTamanho("celular", 1, 1_000, 2);

        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testeEqualsMesmoCodigoETamanho() {
        Produto p1 = new ProdutoComTamanho("celular", 1, 1_000, 10);
        Produto p2 = new ProdutoComTamanho("tablet", 1, 500, 10);

        assertTrue(p1.equals(p2));
    }

    @Test
    public void testeEqualsCodigosDiferentes() {
        Produto p1 = new ProdutoComTamanho("celular", 1, 1_000, 10);
        Produto p2 = new ProdutoComTamanho("celular", 2, 1_000, 10);

        assertFalse(p1.equals(p2));
    }

    @Test
    public void testeEqualsTamanhosDiferentes() {
        Produto p1 = new ProdutoComTamanho("celular", 1, 1_000, 10);
        Produto p2 = new ProdutoComTamanho("celular", 1, 1_000, 20);

        assertFalse(p1.equals(p2));
    }
}