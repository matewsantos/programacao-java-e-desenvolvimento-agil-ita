import java.util.Set;

public class Placar {
    private ArmazenadorPontuacao armazenamento;

    public Placar(ArmazenadorPontuacao armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void armazenaPontuacao(String nomeUsuario, String tipoPontuacao, int quantidadePontuacao) {
        this.armazenamento.armazenarPontuacao(nomeUsuario, tipoPontuacao, quantidadePontuacao);
    }

    public Set<Ponto> pontosPorUsuario(String nomeUsuario) {
        return armazenamento.pontosPorUsuario(nomeUsuario);
    }
}
