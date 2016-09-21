package daos;

import dominio.entidades.Comentario;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComentarioDaoTest {
    private ComentarioDao dao;
    private DataFileLoader loader;
    private JdbcDatabaseTester jdt;

    @Before
    public void database() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost/forum", "postgres", "admin");
        loader = new FlatXmlDataFileLoader();
        IDataSet dataSet = loader.load("/daos/database-images/inicio.xml");
        jdt.setDataSet(dataSet);
        jdt.onSetup();
        dao = new ComentarioDao();
    }

    @Test
    public void recuperaPorIdTopico() {
        List<Comentario> comentariosEsperados = Arrays.asList(
                new Comentario(2, "mario", 2, "Mario Verde!"),
                new Comentario(3, "peach", 2, "coitado =(")
        );

        List<Comentario> resposta = dao.recuperaPorIdTopico(2);

        assertEquals(comentariosEsperados, resposta);
    }

    @Test
    public void testeInsercao() throws Exception {
        dao.inserir(new Comentario(4, "luigi", 1, "Que Mario?"));

        ITable tabelaCorrente = jdt.getConnection().createDataSet().getTable("comentario");
        ITable tabelaEsperada = loader.load("/daos/database-images/apos-insercao-comentario.xml").getTable("comentario");

        Assertion.assertEqualsIgnoreCols(tabelaEsperada, tabelaCorrente, new String[] {"id_comentario"});
    }
}
