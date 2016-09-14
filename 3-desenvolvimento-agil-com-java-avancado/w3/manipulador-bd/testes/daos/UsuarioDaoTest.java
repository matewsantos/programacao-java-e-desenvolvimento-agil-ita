package daos;

import entidades.Usuario;
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
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost/coursera", "postgres", "admin");
        loader = new FlatXmlDataFileLoader();
        IDataSet dataSet = loader.load("/database-images/usuarios-inicio.xml");
        jdt.setDataSet(dataSet);
        jdt.onSetup();
        dao = new UsuarioDao();
    }

    @Test
    public void testeRecuperarPorLogin() {
        Usuario usuarioEsperado = new Usuario("maria", "maria@gmail.com", "Maria de Souza", "maria123", 30);

        Usuario resposta = dao.recuperar("maria");

        assertEquals(usuarioEsperado, resposta);
    }

    @Test
    public void testeRanking() {
        Usuario usuarioEsperadoPrimeiro = new Usuario("maria", "maria@gmail.com", "Maria de Souza", "maria123", 30);
        Usuario usuarioEsperadoSegundo = new Usuario("luquinhas", "lucas@hotmail.com", "Lucas Pereira", "lucca", 20);

        List<Usuario> resultado = dao.ranking();

        assertEquals(2, resultado.size());
        assertEquals(usuarioEsperadoPrimeiro, resultado.get(0));
        assertEquals(usuarioEsperadoSegundo, resultado.get(1));
    }

    @Test
    public void testeInsercao() throws Exception {
        dao.inserir(new Usuario("pedro", "pedro@gmail.com", "Pedro Silva", "pedro123", 10));

        ITable tabelaCorrente = jdt.getConnection().createDataSet().getTable("usuario");
        ITable tabelaEsperada = loader.load("/database-images/usuarios-apos-insercao.xml").getTable("usuario");

        Assertion.assertEquals(tabelaEsperada, tabelaCorrente);
    }

    @Test
    public void testeAdicionaPontos() throws Exception {
        dao.adicionarPontos("maria", 5);

        ITable tabelaCorrente = jdt.getConnection().createDataSet().getTable("usuario");
        ITable tabelaEsperada= loader.load("/database-images/usuarios-apos-adicao-de-pontos.xml").getTable("usuario");

        Assertion.assertEquals(tabelaCorrente, tabelaEsperada);
    }
}
