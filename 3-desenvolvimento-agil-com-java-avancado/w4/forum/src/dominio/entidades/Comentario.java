package dominio.entidades;

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
