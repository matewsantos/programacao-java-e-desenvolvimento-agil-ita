package servicos;

import dominio.daos.IUsuarioDao;
import dominio.daos.UsuarioDao;
import dominio.entidades.Usuario;

public class ServicoCadastroUsuario {
    private IUsuarioDao usuarioDao;

    public ServicoCadastroUsuario() {
        this.usuarioDao = new UsuarioDao();
    }

    public void cadastrar(String nome, String login, String email, String senha) {
        usuarioDao.inserir(new Usuario(login, email, nome, senha, 0));
    }
}
