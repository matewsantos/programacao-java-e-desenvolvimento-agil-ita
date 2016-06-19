import java.util.Set;

public interface ArmazenadorPontuacao {
    void armazenarPontuacao(String nomeUsuario, String tipoPontuacao, int pontuacao);
    int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao);
    Set<Usuario> usuariosPontuadores();
    Set<Ponto> pontosPorUsuario(String nomeUsuario);
}
