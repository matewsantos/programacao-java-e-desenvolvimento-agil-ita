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
        Usuario usuario = buscaOuCriaUsuario(nomeUsuario);
        usuario.adicionaPontos(tipoPonto, quantidade);
    }

    public int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao) {
        Usuario usuario = buscaOuCriaUsuario(nomeUsuario);

        return usuario.quantidadePontosPorTipo(tipoPontuacao);
    }

    public Set<Usuario> usuariosPontuadores() {
        return usuarios.values().stream()
                .filter(usuario -> usuario.temPontos())
                .collect(Collectors.toSet());
    }

    public Set<Ponto> pontosPorUsuario(String nomeUsuario) {
        Usuario usuario = buscaOuCriaUsuario(nomeUsuario);

        return usuario.getPontos();
    }

    private Usuario buscaOuCriaUsuario(String nomeUsuario) {
        Usuario usuario = usuarios.getOrDefault(nomeUsuario, new Usuario(nomeUsuario));
        usuarios.put(nomeUsuario, usuario);

        return usuario;
    }
}
