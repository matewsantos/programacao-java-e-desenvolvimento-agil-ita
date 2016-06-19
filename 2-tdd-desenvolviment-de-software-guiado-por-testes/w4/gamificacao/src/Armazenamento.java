import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Armazenamento implements ArmazenadorPontuacao {
    private Map<String, Usuario> usuarios;

    public Armazenamento() {
        usuarios = new HashMap<>();
    }

    public void armazenarPontuacao(String nomeUsuario, String tipoPonto, int quantidade) {
        buscaOuCriaUsuario(nomeUsuario).adicionaPontos(tipoPonto, quantidade);
    }

    public int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao) {
        return buscaOuCriaUsuario(nomeUsuario).quantidadePontosPorTipo(tipoPontuacao);
    }

    public Set<Usuario> usuariosPontuadores() {
        return usuarios.values().stream()
                .filter(usuario -> usuario.temPontos())
                .collect(Collectors.toSet());
    }

    public Set<Ponto> pontosPorUsuario(String nomeUsuario) {
        return buscaOuCriaUsuario(nomeUsuario).getPontos().stream()
                .collect(Collectors.toSet());
    }

    private Usuario buscaOuCriaUsuario(String nomeUsuario) {
        Usuario usuario = usuarios.getOrDefault(nomeUsuario, new Usuario(nomeUsuario));
        usuarios.put(nomeUsuario, usuario);

        return usuario;
    }
}
