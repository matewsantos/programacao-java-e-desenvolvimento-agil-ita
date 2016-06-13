import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Armazenamento implements ArmazenadorPontuacao {
    private Map<String, Map<String, Integer>> usuarios;

    public Armazenamento() {
        usuarios = new HashMap<>();
    }

    public void armazenarPontuacao(String nomeUsuario, String tipoPontuacao, int pontuacao) {
        if (pontuacao <= 0) {
            return;
        }

        Map<String, Integer> usuario = getUsuario(nomeUsuario);

        usuario.put(tipoPontuacao, pontuacao);
    }

    public int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao) {
        Map<String, Integer> usuario = getUsuario(nomeUsuario);

        return usuario.get(tipoPontuacao);
    }


    public Set<String> usuariosPontuadores() {
        return usuarios.keySet();
    }

    public Set<String> pontosPorUsuario(String nomeUsuario) {
        return getUsuario(nomeUsuario).keySet();
    }

    private Map<String, Integer> getUsuario(String nomeUsuario) {
        Map<String, Integer> usuario = usuarios.get(nomeUsuario);
        if (usuario == null) {
            usuario = new HashMap<String, Integer>();
            usuarios.put(nomeUsuario, usuario);
        }

        return usuario;
    }
}
