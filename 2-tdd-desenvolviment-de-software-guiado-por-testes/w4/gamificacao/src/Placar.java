import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Placar {
    private ArmazenadorPontuacao armazenamento;

    public Placar(ArmazenadorPontuacao armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void armazenaPontuacao(String nomeUsuario, String tipoPontuacao, int quantidadePontuacao) {
        this.armazenamento.armazenarPontuacao(nomeUsuario, tipoPontuacao, quantidadePontuacao);
    }

    public Set<Ponto> pontosPorUsuario(String nomeUsuario) {
        return armazenamento.pontosPorUsuario(nomeUsuario).stream()
                .filter(ponto -> ponto.getQuantidade() > 0)
                .collect(Collectors.toSet());
    }

    public List<Usuario> rankingPorTipo(String tipoPonto) {
        return armazenamento.usuariosPontuadores().stream()
                .filter(usuario -> usuario.quantidadePontosPorTipo(tipoPonto) > 0)
                .sorted(comparadorPorMaiorPontuacao(tipoPonto))
                .collect(Collectors.toList());
    }

    private Comparator<Usuario> comparadorPorMaiorPontuacao(String tipoPonto) {
        return (usuario1, usuario2) -> Integer.compare(
                usuario2.quantidadePontosPorTipo(tipoPonto), usuario1.quantidadePontosPorTipo(tipoPonto));
    }

}
