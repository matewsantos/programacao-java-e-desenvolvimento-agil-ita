import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Armazenamento implements ArmazenadorPontuacao {
    private UsuarioRepositorio usuarioRepositorio;

    public Armazenamento(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public void armazenarPontuacao(String nomeUsuario, String tipoPonto, int quantidade) {
        Usuario usuario = buscarOuCriarUsuario(nomeUsuario);
        usuario.adicionaPontos(tipoPonto, quantidade);

        salvarUsuario(usuario);
    }

    public int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao) {
        return buscarOuCriarUsuario(nomeUsuario).quantidadePontosPorTipo(tipoPontuacao);
    }

    public Set<Usuario> usuariosPontuadores() {
        return buscarUsuarios().values().stream()
                .filter(usuario -> usuario.temPontos())
                .collect(Collectors.toSet());
    }

    public Set<Ponto> pontosPorUsuario(String nomeUsuario) {
        return buscarOuCriarUsuario(nomeUsuario).getPontos();
    }

    private Usuario buscarOuCriarUsuario(String nomeUsuario) {
        return usuarioRepositorio.buscarOuCriar(nomeUsuario);
    }

    private Map<String, Usuario> buscarUsuarios() {
        return usuarioRepositorio.listar();
    }

    private void salvarUsuario(Usuario usuario) {
        usuarioRepositorio.salvar(usuario);
    }
}
