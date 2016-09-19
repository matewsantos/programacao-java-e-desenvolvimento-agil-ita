package servicos;

import dominio.daos.ITopicoDao;
import dominio.daos.TopicoDao;
import dominio.entidades.Topico;

import java.util.List;

public class ServicoListagemTopicos {
    private ITopicoDao topicoDao;

    public ServicoListagemTopicos() {
        this.topicoDao = new TopicoDao();
    }

    public List<Topico> getTopicos() {
        return topicoDao.topicos();
    }
}
