import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Pizza {
    private static Map<String, Integer> ingredientes = new HashMap<>();
    private int quantidadeIngredientes = 0;

    public static String relatorioIngredientes() {
        return ingredientes.entrySet().stream().map(Map.Entry::toString).collect(Collectors.joining(", "));
    }

    public void adicionaIngredientes(String...  ingredientes) {
        for (String ingrediente: ingredientes) {
            this.adicionaIngrediente(ingrediente);
        }
    }

    public void adicionaIngrediente(String ingrediente) {
        contabilizaIngrediente(ingrediente);
        quantidadeIngredientes++;
    }

    public double getPreco() {
        if (quantidadeIngredientes <= 2) {
            return 15;
        } else if (quantidadeIngredientes <= 5) {
            return 20;
        } else {
            return 23;
        }
    }
    public int getQuantidadeIngredientes() {
        return quantidadeIngredientes;
    }

    public static void zeraRegistroIngredientes() {
        Pizza.ingredientes = new HashMap<>();
    }

    public static Map<String, Integer> getIngredientes() {
        return Pizza.ingredientes;
    }

    private static void contabilizaIngrediente(String ingrediente) {
        int quantidadeIngredientesAnterior = ingredientes.containsKey(ingrediente) ? ingredientes.get(ingrediente) : 0;
        int quantidadeIngredientesAtual = quantidadeIngredientesAnterior + 1;

        ingredientes.put(ingrediente, quantidadeIngredientesAtual);
    }
}
