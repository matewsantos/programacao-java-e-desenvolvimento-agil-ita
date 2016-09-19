package servicos;

import dominio.daos.ITopicoDao;
import dominio.daos.IUsuarioDao;
import dominio.daos.TopicoDao;
import dominio.daos.UsuarioDao;
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
