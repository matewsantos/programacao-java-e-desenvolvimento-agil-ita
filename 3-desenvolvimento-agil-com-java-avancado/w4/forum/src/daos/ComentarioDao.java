package daos;

import dominio.entidades.Comentario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDao implements IComentarioDao {
    private final Conexao conexao;

    public ComentarioDao() {
        this.conexao = new Conexao();
    }

    @Override
    public List<Comentario> recuperaPorIdTopico(int idTopico) {
        List<Comentario> resultado = new ArrayList<>();
        String sql = "SELECT * FROM comentario WHERE id_topico = ?";

        try(PreparedStatement stm = conexao.getConexao().prepareStatement(sql)) {
            stm.setInt(1, idTopico);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                resultado.add(constroiComentario(rs));
            }
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }

        return resultado;
    }

    @Override
    public void inserir(Comentario comentario) {
        String sql = "INSERT INTO comentario(login, id_topico, comentario) VALUES (?, ?, ?)";

        try (PreparedStatement stm = conexao.getConexao().prepareStatement(sql)){
            stm.setString(1, comentario.getLogin());
            stm.setInt(2, comentario.getIdTopico());
            stm.setString(3, comentario.getComentario());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }
    }

    private Comentario constroiComentario(ResultSet rs) {
        try {
            return new Comentario(
                    rs.getString("login"),
                    rs.getInt("id_topico"),
                    rs.getString("comentario")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar entidade do banco", e);
        }
    }
}
