package entidades;

import java.util.Objects;

public class Usuario {
    private String login;
    private String email;
    private String nome;
    private String senha;
    private int pontos;

    public Usuario(String login, String email, String nome, String senha, int pontos) {
        this.login = login;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.pontos = pontos;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public int getPontos() {
        return pontos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return getPontos() == usuario.getPontos() &&
                Objects.equals(getLogin(), usuario.getLogin()) &&
                Objects.equals(getEmail(), usuario.getEmail()) &&
                Objects.equals(getNome(), usuario.getNome()) &&
                Objects.equals(getSenha(), usuario.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getEmail(), getNome(), getSenha(), getPontos());
    }
}
