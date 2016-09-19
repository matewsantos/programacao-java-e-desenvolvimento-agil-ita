package servicos;

import daos.ITopicoDao;
import daos.IUsuarioDao;
import daos.TopicoDao;
import daos.UsuarioDao;
import dominio.entidades.Usuario;

public class ServicoLogin {
    IUsuarioDao usuarioDao;
    ITopicoDao topicoDao;

    public ServicoLogin() {
        this.usuarioDao = new UsuarioDao();
        this.topicoDao = new TopicoDao();
    }

    public Usuario logar(String login, String senha) {
        Usuario usuario = usuarioDao.recuperar(login);

        if (usuario == null || !usuario.getSenha().equals(senha)) {
            return null;
        }

        return usuario;
    }
}
