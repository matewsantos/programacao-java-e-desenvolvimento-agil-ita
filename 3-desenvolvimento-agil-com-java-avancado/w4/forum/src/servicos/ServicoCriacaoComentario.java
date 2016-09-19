package servicos;

import dominio.daos.ComentarioDao;
import dominio.daos.IComentarioDao;
import dominio.daos.IUsuarioDao;
import dominio.daos.UsuarioDao;
import dominio.entidades.Comentario;

public class ServicoCriacaoComentario {
    private IComentarioDao comentarioDao;
    private IUsuarioDao usuarioDao;

    public ServicoCriacaoComentario() {
        this.comentarioDao = new ComentarioDao();
        this.usuarioDao = new UsuarioDao();
    }

    public void criar(String comentario, int idTopico, String loginAutor) {
        comentarioDao.inserir(new Comentario(loginAutor, idTopico, comentario));
        pontuarUsuario(loginAutor);
    }

    private void pontuarUsuario(String login) {
        final int PONTOS_POR_COMENTARIO = 3;
        usuarioDao.adicionarPontos(login, PONTOS_POR_COMENTARIO);
    }
}
