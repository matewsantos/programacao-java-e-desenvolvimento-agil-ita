import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras {
    private Map<Produto, Integer> produtos;

    public CarrinhoDeCompras() {
        produtos = new HashMap<>();
    }

    public void adicionaProduto(Produto produto, int quantidade) {
        int quantidadeAtual = produtos.getOrDefault(produto, 0);
        produtos.put(produto, quantidadeAtual + quantidade);
    }

    public void removeProduto(Produto produto, int quantidade) {
        int quantidadeAtual = produtos.getOrDefault(produto, 0);

        if (quantidadeAtual - quantidade < 0) {
            String mensagem = "Há apenas " + quantidadeAtual + " unidades do produto " + produto.getCodigo();
            throw new IllegalArgumentException(mensagem);
        }

        produtos.put(produto, quantidadeAtual - quantidade);
    }

    public double valorTotalCompra() {
        double resultado = 0;

        for (Map.Entry<Produto, Integer> entry: produtos.entrySet()) {
            resultado += entry.getKey().getPreco() * entry.getValue();
        }

        return resultado;
    }

    public int getQuantidade(Produto p) {
        return produtos.getOrDefault(p, 0);
    }
}

