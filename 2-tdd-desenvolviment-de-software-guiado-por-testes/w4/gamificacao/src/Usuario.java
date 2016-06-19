import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class Usuario {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        
        return Objects.equals(nome, usuario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
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
