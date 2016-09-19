package dominio.entidades;

import daos.ComentarioDao;
import daos.IComentarioDao;

import java.util.List;

public class Topico {
    private int id;
    private String login;
    private String titulo;
    private String conteudo;

    private IComentarioDao comentarioDao;

    public Topico(String login, String titulo, String conteudo) {
        this.login = login;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.comentarioDao = new ComentarioDao();
    }

    public Topico(int id, String login, String titulo, String conteudo) {
        this.id = id;
        this.login = login;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.comentarioDao = new ComentarioDao();
    }

    public List<Comentario> getComentarios() {
        return comentarioDao.recuperaPorIdTopico(id);
//        return new UsuarioDao().ranking();
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }
}
