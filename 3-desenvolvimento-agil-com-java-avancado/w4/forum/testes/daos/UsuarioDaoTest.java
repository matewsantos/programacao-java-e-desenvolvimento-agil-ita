package daos;

import dominio.entidades.Usuario;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsuarioDaoTest {
    private UsuarioDao dao;
    private DataFileLoader loader;
    private JdbcDatabaseTester jdt;

    @Before
    public void database() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost/forum", "postgres", "admin");
        loader = new FlatXmlDataFileLoader();
        IDataSet dataSet = loader.load("/daos/database-images/inicio.xml");
        jdt.setDataSet(dataSet);
        jdt.onSetup();
        dao = new UsuarioDao();
    }

    @Test
    public void testeRecuperarPorLogin() {
        Usuario usuarioEsperado = new Usuario("mario", "mario@nintendo.com", "It's me, Mario!", "mario", 13);

        Usuario resposta = dao.recuperar("mario");

        assertEquals(usuarioEsperado, resposta);
    }

    @Test
    public void testeRanking() {
        Usuario usuarioEsperadoPrimeiro = new Usuario("mario", "mario@nintendo.com", "It's me, Mario!", "mario", 13);
        Usuario usuarioEsperadoSegundo = new Usuario("luigi", "luigi@naointendo.com", "Luigi", "marioverde", 10);
        Usuario usuarioEsperadoTerceiro = new Usuario("peach", "peach@nintendo.com", "Peach", "socorro", 6);

        List<Usuario> resultado = dao.ranking();

        assertEquals(3, resultado.size());
        assertEquals(usuarioEsperadoPrimeiro, resultado.get(0));
        assertEquals(usuarioEsperadoSegundo, resultado.get(1));
        assertEquals(usuarioEsperadoTerceiro, resultado.get(2));
    }

    @Test
    public void testeInsercao() throws Exception {
        dao.inserir(new Usuario("goomba", "goomba@nintendo.com", "Goomba", "", 0));

        ITable tabelaCorrente = jdt.getConnection().createDataSet().getTable("usuario");
        ITable tabelaEsperada = loader.load("/daos/database-images/apos-insercao-usuario.xml").getTable("usuario");

        Assertion.assertEquals(tabelaEsperada, tabelaCorrente);
    }

    @Test
    public void testeAdicionaPontos() throws Exception {
        dao.adicionarPontos("mario", 5);

        ITable tabelaCorrente = jdt.getConnection().createDataSet().getTable("usuario");
        ITable tabelaEsperada= loader.load("/daos/database-images/apos-adicao-de-pontos.xml").getTable("usuario");

        Assertion.assertEquals(tabelaCorrente, tabelaEsperada);
    }
}
