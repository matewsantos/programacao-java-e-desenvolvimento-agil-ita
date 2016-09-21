package dominio.entidades;

import java.util.Objects;

public class Comentario {
    private int id;
    private String login;
    private int idTopico;
    private String comentario;

    public Comentario(int id, String login, int idTopico, String comentario) {
        this.id = id;
        this.login = login;
        this.idTopico = idTopico;
        this.comentario = comentario;
    }

    public Comentario(String login, int idTopico, String comentario) {
        this.login = login;
        this.idTopico = idTopico;
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return getId() == that.getId() &&
                getIdTopico() == that.getIdTopico() &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getComentario(), that.getComentario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getIdTopico(), getComentario());
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getIdTopico() {
        return idTopico;
    }

    public String getComentario() {
        return comentario;
    }
}
