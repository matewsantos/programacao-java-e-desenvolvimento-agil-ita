import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Usuario implements Serializable {
    private static final long serialVersionUID = -4004695228169636141L;

    private String nome;
    private Map<String, Ponto> pontos;

    public Usuario(String nome) {
        this.nome = nome;
        this.pontos = new HashMap<>();
    }

    public void adicionaPontos(String tipo, int quantidade) {
        buscaOuCriaPonto(tipo).adicionaPontos(quantidade);
    }

    public int quantidadePontosPorTipo(String tipoPonto) {
        return buscaOuCriaPonto(tipoPonto).getQuantidade();
    }

    public boolean temPontos() {
        return totalPontos() > 0;
    }

    public String getNome() {
        return this.nome;
    }

    public Set<Ponto> getPontos() {
        return new HashSet<>(pontos.values());
    }

    private int totalPontos() {
        return pontos.values().stream()
                .mapToInt(ponto -> ponto.getQuantidade())
                .sum();
    }

    private Ponto buscaOuCriaPonto(String tipo) {
        Ponto ponto = pontos.getOrDefault(tipo, new Ponto(tipo));
        pontos.put(tipo, ponto);

        return ponto;
    }
}
