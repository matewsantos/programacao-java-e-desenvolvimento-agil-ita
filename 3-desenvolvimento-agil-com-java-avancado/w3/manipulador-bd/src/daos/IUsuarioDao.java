package daos;

import entidades.Usuario;
import java.util.List;

public interface IUsuarioDao {

    public void inserir(Usuario u);
    public Usuario recuperar(String login);
    public void adicionarPontos(String login, int pontos);
    public List<Usuario> ranking();
}