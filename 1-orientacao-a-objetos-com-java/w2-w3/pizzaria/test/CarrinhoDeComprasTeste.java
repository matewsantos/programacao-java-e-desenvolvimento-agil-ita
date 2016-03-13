import org.junit.Test;
import static org.junit.Assert.*;

public class CarrinhoDeComprasTeste {

    @Test(expected=IllegalArgumentException.class)
    public void testAdicionaPizzaSemIngrediente() throws Exception {
        Pizza vento = new Pizza();
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionaPizza(vento);
    }

    @Test
    public void testPrecoTotal() throws Exception {
        Pizza misto = new Pizza();
        misto.adicionaIngredientes("queijo", "presunto");

        Pizza bauru = new Pizza();
        bauru.adicionaIngredientes("queijo", "tomate", "presunto");

        Pizza especial = new Pizza();
        especial.adicionaIngredientes("queijo", "presunto", "azeitona", "tomate", "ervilha", "oregano");

        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionaPizza(misto);
        carrinho.adicionaPizza(bauru);
        carrinho.adicionaPizza(especial);

        double precoTotal = misto.getPreco() + bauru.getPreco() + especial.getPreco();

        assertEquals(precoTotal, carrinho.precoTotal(), 0.001);
    }
}