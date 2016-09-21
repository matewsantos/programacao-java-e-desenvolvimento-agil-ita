package daos;

import dominio.entidades.Topico;

import java.util.List;

public interface ITopicoDao {
    public void inserir(Topico topico);
    public Topico recuperar(int id);
    public List<Topico> topicos();
}
