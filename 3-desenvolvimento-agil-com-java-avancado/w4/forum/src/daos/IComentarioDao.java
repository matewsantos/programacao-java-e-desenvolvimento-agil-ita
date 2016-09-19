package daos;

import dominio.entidades.Comentario;

import java.util.List;

public interface IComentarioDao {
    public List<Comentario> recuperaPorIdTopico(int idTopico);
    public void inserir(Comentario comentario);
}
