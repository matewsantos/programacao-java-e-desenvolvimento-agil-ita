import java.util.Set;

public class ArmazenamentoMock implements ArmazenadorPontuacao  {
    @Override
    public void armazenarPontuacao(String nomeUsuario, String tipoPontuacao, int pontuacao) {

    }

    @Override
    public int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao) {
        return 0;
    }

    @Override
    public Set<String> usuariosPontuadores() {
        return null;
    }

    @Override
    public Set<String> pontosPorUsuario(String nomeUsuario) {
        return null;
    }
}
