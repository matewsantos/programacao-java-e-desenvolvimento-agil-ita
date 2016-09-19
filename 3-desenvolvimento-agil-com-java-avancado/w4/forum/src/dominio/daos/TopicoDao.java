package dominio.daos;

import dominio.entidades.Topico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicoDao implements ITopicoDao {
    private Conexao conexao;

    public TopicoDao() {
        this.conexao = new Conexao();
    }

    @Override
    public void inserir(Topico topico) {
        String sql = "INSERT INTO topico(login, titulo, conteudo) VALUES (?, ?, ?)";

        try (PreparedStatement stm = conexao.getConexao().prepareStatement(sql)){
            stm.setString(1, topico.getLogin());
            stm.setString(2, topico.getTitulo());
            stm.setString(3, topico.getConteudo());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }
    }

    @Override
    public Topico recuperar(int id) {
        Topico resultado = null;
        String sql = "SELECT * FROM topico WHERE id_topico= ?";

        try(PreparedStatement stm = conexao.getConexao().prepareStatement(sql)) {
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                resultado = constroiTopico(rs);
            }
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }

        return resultado;
    }

    @Override
    public List<Topico> topicos() {
        List<Topico> resultado = new ArrayList<>();
        String sql = "SELECT * FROM topico";

        try(PreparedStatement stm = conexao.getConexao().prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                resultado.add(constroiTopico(rs));
            }
        } catch (SQLException e) {
            throw  new RuntimeException("Não foi possível executar o acesso ao banco de dados", e);
        }

        return resultado;
    }

    private Topico constroiTopico(ResultSet rs) {
        try {
            return new Topico(
                    rs.getInt("id_topico"),
                    rs.getString("login"),
                    rs.getString("titulo"),
                    rs.getString("conteudo")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar entidade do banco", e);
        }
    }
}
