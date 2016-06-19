import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlacarArmazenamentoIntegracao {
    private final String FILE_NAME = "usuariosTeste";

    private UsuarioRepositorio usuarioRepositorio;
    private Placar placar;

    @Before
    public void setup() {
        usuarioRepositorio = new UsuarioRepositorio(FILE_NAME);
        Armazenamento armazenamento = new Armazenamento(usuarioRepositorio);
        placar = new Placar(armazenamento);
    }

    @After
    public void limparArquivo() throws IOException {
        usuarioRepositorio.limparRepositorio();
    }

    @Test
    public void pontosPorUsuario() {
        placar.armazenaPontuacao("mario", "estrela", 10);
        placar.armazenaPontuacao("mario", "moeda", 20);

        Set<Ponto> resultado = placar.pontosPorUsuario("mario");

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(
                ponto -> ponto.getTipo().equals("moeda") && ponto.getQuantidade() == 20)
        );
        assertTrue(resultado.stream().anyMatch(
                ponto -> ponto.getTipo().equals("estrela") && ponto.getQuantidade() == 10)
        );
    }

    @Test
    public void pontosPorUsuarioComPontuacaoZero() {
        placar.armazenaPontuacao("mario", "estrela", 0);

        assertTrue(placar.pontosPorUsuario("mario").isEmpty());
    }

    @Test
    public void pontoPorUsuarioComUsuarioInexistente() {
        placar.armazenaPontuacao("mario", "estrela", 10);

        assertTrue(placar.pontosPorUsuario("sonic").isEmpty());
    }

    @Test
    public void rankingPorTipo() {
        placar.armazenaPontuacao("mario", "estrela", 10);
        placar.armazenaPontuacao("yoshi", "estrela", 5);
        placar.armazenaPontuacao("luigi", "estrela", 20);

        List<Usuario> resultado = placar.rankingPorTipo("estrela");

        assertEquals(3, resultado.size());
        assertEquals("luigi", resultado.get(0).getNome());
        assertEquals("mario", resultado.get(1).getNome());
        assertEquals("yoshi", resultado.get(2).getNome());
    }

    @Test
    public void rankingPorTipoComUsuarioSemPontuacaoNoTipo() {
        placar.armazenaPontuacao("mario", "estrela", 10);
        placar.armazenaPontuacao("yoshi", "estrela", 0);

        List<Usuario> resultado = placar.rankingPorTipo("estrela");

        assertEquals(1, resultado.size());
        assertEquals("mario", resultado.get(0).getNome());
    }
}
