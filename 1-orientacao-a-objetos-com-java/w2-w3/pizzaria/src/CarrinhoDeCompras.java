import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    private List<Pizza> pizzas = new ArrayList<>();

    public void adicionaPizza( Pizza pizza) {
        if (!podeAdicionar(pizza)) {
            throw  new IllegalArgumentException("nao eh possivel adicionar uma pizza sem ingredientes");
        }

        pizzas.add(pizza);
    }

    public double precoTotal() {
        return pizzas.stream().mapToDouble(Pizza::getPreco).sum();
    }

    private boolean podeAdicionar( Pizza pizza) {
        return pizza.getQuantidadeIngredientes() > 0;
    }
}
