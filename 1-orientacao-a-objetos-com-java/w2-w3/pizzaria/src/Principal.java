public class Principal {
    public static void main(String[] args) {
        Pizza pizzaCalabresa = new Pizza();
        pizzaCalabresa.adicionaIngrediente("calabresa");
        pizzaCalabresa.adicionaIngrediente("cebola");

        Pizza pizzaPortuguesa = new Pizza();
        pizzaPortuguesa.adicionaIngrediente("presunto");
        pizzaPortuguesa.adicionaIngrediente("queijo");
        pizzaPortuguesa.adicionaIngrediente("tomate");
        pizzaPortuguesa.adicionaIngrediente("cebola");
        pizzaPortuguesa.adicionaIngrediente("ervilha");
        pizzaPortuguesa.adicionaIngrediente("azeitona");

        Pizza pizzaDoisQueijos = new Pizza();
        pizzaDoisQueijos.adicionaIngrediente("parmesao");
        pizzaDoisQueijos.adicionaIngrediente("provolone");
        pizzaDoisQueijos.adicionaIngrediente("azeitona");
        pizzaDoisQueijos.adicionaIngrediente("oregano");

        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adicionaPizza(pizzaCalabresa);
        carrinho.adicionaPizza(pizzaPortuguesa);
        carrinho.adicionaPizza(pizzaDoisQueijos);

        System.out.println("total carrinho de compra: " + carrinho.precoTotal());
        System.out.println("quantidade de cada ingrediente: " + Pizza.relatorioIngredientes());
    }
}
