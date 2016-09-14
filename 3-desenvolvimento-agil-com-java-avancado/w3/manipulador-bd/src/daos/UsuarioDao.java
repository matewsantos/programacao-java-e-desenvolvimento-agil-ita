package daos;

import entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements IUsuarioDao {
    private final String DATABASE_URL = "jdbc:postgresql://localhost/coursera";
    private final String DATABASE_USUARIO = "postgres";
    private final String DATABASE_SENHA = "admin";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Usuario u) {
        try(Connection c = DriverManager.getConnection(DATABASE_URL, DATABASE_USUARIO, DATABASE_SENHA)) {
            String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getEmail());
            stm.setString(3, u.getNome());
            stm.setString(4, u.getSenha());
            stm.setInt(5, u.getPontos());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }
    }

    @Override
    public Usuario recuperar(String login) {
        Usuario resultado = null;

        try(Connection c = DriverManager.getConnection(DATABASE_URL, DATABASE_USUARIO, DATABASE_SENHA)) {
            String sql = "SELECT * FROM usuario WHERE login = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, login);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                resultado = constroiUsuario(rs);
            }
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }

        return resultado;
    }

    @Override
    public void adicionarPontos(String login, int pontos) {
        try(Connection c = DriverManager.getConnection(DATABASE_URL, DATABASE_USUARIO, DATABASE_SENHA)) {
            String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, pontos);
            stm.setString(2, login);

            stm.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }
    }

    @Override
    public List<Usuario> ranking() {
        List<Usuario> resultado = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(DATABASE_URL, DATABASE_USUARIO, DATABASE_SENHA)) {
            String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
            PreparedStatement stm = c.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                resultado.add(constroiUsuario(rs));
            }
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }


        return resultado;
    }

    private Usuario constroiUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("login"),
                rs.getString("email"),
                rs.getString("nome"),
                rs.getString("senha"),
                rs.getInt("pontos")
        );
    }
}
