import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PizzaTeste {
    @Before
    public void setUp() {
        Pizza.zeraRegistroIngredientes();
    }

    @Test
    public void testePrecoPizzaAteDoisIngredientes() throws Exception {
        Pizza pizza = new Pizza();
        pizza.adicionaIngredientes("queijo", "presunto");

        assertEquals("pizzas com dois ingredientes ou menos custam 15 reais", 15, pizza.getPreco(), 0.001);
    }

    @Test
    public void testePrecoPizzaAteCincoIngredientes() throws Exception {
        Pizza pizza = new Pizza();
        pizza.adicionaIngredientes("queijo", "presunto", "azeitona", "tomate", "ervilha");

        assertEquals("pizzas com 3 a 5 ingredientes custam 20 reais", 20, pizza.getPreco(), 0.001);
    }

    @Test
    public void testePrecoPizzaMaisDeCincoIngredientes() throws Exception {
        Pizza pizza = new Pizza();
        pizza.adicionaIngredientes("queijo", "presunto", "azeitona", "tomate", "ervilha", "oregano");

        assertEquals("pizzas com mais de 5 ingredientes custam 23 reais", 23, pizza.getPreco(), 0.001);
    }

    @Test
    public void testeRegistroIngredientesVazio() {
        assertTrue("lista de ingredientes comeca vazia", Pizza.getIngredientes().isEmpty());
    }

    @Test
    public void testeRegistroIngredientes() {
        Pizza pizza = new Pizza();
        pizza.adicionaIngredientes("queijo", "presunto");

        Map<String, Integer> ingredientes = Pizza.getIngredientes();

        assertEquals("possui dois ingredientes", 2, ingredientes.size());
        assertEquals("possui 1 porcao de queijo", Integer.valueOf(1), ingredientes.get("queijo"));
        assertEquals("possui 1 porcao de presunto", Integer.valueOf(1), ingredientes.get("presunto"));
    }

    @Test
    public void testeRegistroIngredientesDuasPizzas() {
        Pizza queijo= new Pizza();
        queijo.adicionaIngredientes("queijo");

        Pizza misto= new Pizza();
        misto.adicionaIngredientes("queijo", "presunto");

        Map<String, Integer> ingredientes = Pizza.getIngredientes();

        assertEquals(2, ingredientes.size());
        assertEquals(Integer.valueOf(2), ingredientes.get("queijo"));
        assertEquals(Integer.valueOf(1), ingredientes.get("presunto"));
    }
}