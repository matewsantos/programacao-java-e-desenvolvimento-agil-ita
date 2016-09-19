package servicos;

import daos.ComentarioDao;
import daos.IComentarioDao;
import daos.IUsuarioDao;
import daos.UsuarioDao;
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
