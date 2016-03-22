import org.junit.Test;

import static org.junit.Assert.*;

public class CarrinhoDeComprasTeste {

    @Test
    public void testeAdicionaProduto() {
        Produto ventilador = new Produto("ventilador", 2, 100);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(ventilador, 2);

        assertEquals(2, carrinho.getQuantidade(ventilador));
    }

    @Test
    public void testeAdicionaProdutosTamanhosDiferentes() {
        Produto ventiladorPequeno = new ProdutoComTamanho("ventilador", 2, 100, 30);
        Produto ventiladorGrande = new ProdutoComTamanho("ventilador", 2, 150, 45);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(ventiladorPequeno, 2);
        carrinho.adicionaProduto(ventiladorGrande, 1);

        assertEquals(2, carrinho.getQuantidade(ventiladorPequeno));
        assertEquals(1, carrinho.getQuantidade(ventiladorGrande));
    }

    @Test
    public void testeRemoveProduto() {
        Produto ventilador = new Produto("ventilador", 2, 100);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(ventilador, 2);
        carrinho.removeProduto(ventilador, 2);

        assertEquals(0, carrinho.getQuantidade(ventilador));
    }

    @Test
    public void testeRemoveProdutosTamanhosDiferentes() {
        Produto ventiladorPequeno = new ProdutoComTamanho("ventilador", 2, 100, 30);
        Produto ventiladorGrande = new ProdutoComTamanho("ventilador", 2, 150, 45);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(ventiladorPequeno, 2);
        carrinho.adicionaProduto(ventiladorGrande, 2);
        carrinho.removeProduto(ventiladorPequeno, 1);
        carrinho.removeProduto(ventiladorGrande, 2);

        assertEquals(1, carrinho.getQuantidade(ventiladorPequeno));
        assertEquals(0, carrinho.getQuantidade(ventiladorGrande));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testeRemoveMuitosProdutos() {
        Produto ventilador = new Produto("ventilador", 2, 100);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(ventilador, 2);
        carrinho.removeProduto(ventilador, 3);
    }

    @Test
    public void testeValorTotalCompra() {
        Produto ventiladorPequeno = new ProdutoComTamanho("ventilador", 2, 100, 30);
        Produto ventiladorGrande = new ProdutoComTamanho("ventilador", 1, 150, 45);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaProduto(ventiladorPequeno, 2);
        carrinho.adicionaProduto(ventiladorGrande, 1);

        assertEquals(350, carrinho.valorTotalCompra(), 0.001);
    }
}