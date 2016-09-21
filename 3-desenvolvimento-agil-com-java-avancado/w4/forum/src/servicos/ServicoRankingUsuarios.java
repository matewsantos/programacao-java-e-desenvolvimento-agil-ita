package servicos;

import daos.IUsuarioDao;
import daos.UsuarioDao;
import dominio.entidades.Usuario;

import java.util.List;

public class ServicoRankingUsuarios {
    private IUsuarioDao usuarioDao;

    public ServicoRankingUsuarios() {
        this.usuarioDao = new UsuarioDao();
    }

    public List<Usuario> getRanking() {
        return usuarioDao.ranking();
    }
}
