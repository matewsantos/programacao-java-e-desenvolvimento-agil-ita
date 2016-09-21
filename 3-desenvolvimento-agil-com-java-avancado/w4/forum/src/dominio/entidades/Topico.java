package dominio.entidades;

import daos.ComentarioDao;
import daos.IComentarioDao;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;
        return getId() == topico.getId() &&
                Objects.equals(getLogin(), topico.getLogin()) &&
                Objects.equals(getTitulo(), topico.getTitulo()) &&
                Objects.equals(getConteudo(), topico.getConteudo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getTitulo(), getConteudo(), comentarioDao);
    }

    public List<Comentario> getComentarios() {
        return comentarioDao.recuperaPorIdTopico(id);
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
