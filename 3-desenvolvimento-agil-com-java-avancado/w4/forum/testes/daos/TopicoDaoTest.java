package daos;

import dominio.entidades.Topico;
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

public class TopicoDaoTest {
    private TopicoDao dao;
    private DataFileLoader loader;
    private JdbcDatabaseTester jdt;

    @Before
    public void database() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost/forum", "postgres", "admin");
        loader = new FlatXmlDataFileLoader();
        IDataSet dataSet = loader.load("/daos/database-images/inicio.xml");
        jdt.setDataSet(dataSet);
        jdt.onSetup();
        dao = new TopicoDao();
    }

    @Test
    public void testeRecuperarPorId() {
        Topico topicoEsperado = new Topico(1, "mario", "Corrida?", "Quem está com ânimo para uma corrida no Mario Kart?");

        Topico resposta = dao.recuperar(topicoEsperado.getId());

        assertEquals(topicoEsperado, resposta);
    }

    @Test
    public void testeListarTopicos() {
        Topico topicoEsperadoPrimeiro = new Topico(1, "mario", "Corrida?", "Quem está com ânimo para uma corrida no Mario Kart?");
        Topico topicoEsperadoSegundo = new Topico(2, "luigi", "Mario Verde uma ova!!!", "O próximo que me chamar de Mario Verde, está ferrado!");

        List<Topico> resultado = dao.topicos();

        assertEquals(2, resultado.size());
        assertEquals(topicoEsperadoPrimeiro, resultado.get(0));
        assertEquals(topicoEsperadoSegundo, resultado.get(1));
    }

    @Test
    public void testeInserirTopico() throws Exception {
        dao.inserir(new Topico(3, "peach", "socorro!!!", "O Bowser me capturou!"));

        ITable tabelaCorrente = jdt.getConnection().createDataSet().getTable("topico");
        ITable tabelaEsperada = loader.load("/daos/database-images/apos-insercao-topico.xml").getTable("topico");

        Assertion.assertEqualsIgnoreCols(tabelaEsperada, tabelaCorrente, new String[] {"id_topico"});
    }
}
