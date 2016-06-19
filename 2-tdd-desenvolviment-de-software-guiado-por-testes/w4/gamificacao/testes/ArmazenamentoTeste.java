import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArmazenamentoTeste {
    private final String FILE_NAME = "usuariosTeste";
    private Armazenamento armazenamento;

    @Before
    public void setup() {
        armazenamento = new Armazenamento(FILE_NAME);
    }

    @After
    public void limpaArquivo() throws IOException {
        RandomAccessFile arquivo = new RandomAccessFile(FILE_NAME, "rw");
        arquivo.setLength(0);
    }

    @Test
    public void recuperarPontosPorUsuarioETipo() {
        armazenamento.armazenarPontuacao("mario", "estrela", 10);
        armazenamento.armazenarPontuacao("mario", "moeda", 1);
        armazenamento.armazenarPontuacao("luigi", "estrela", 20);

        int resultado = armazenamento.pontosPorUsuarioETipo("mario", "estrela");

        assertEquals(10, resultado);
    }

    @Test
    public void recuperarUsuariosComPontos() {
        armazenamento.armazenarPontuacao("mario", "estrela", 10);
        armazenamento.armazenarPontuacao("mario", "moeda", 1);
        armazenamento.armazenarPontuacao("luigi", "moeda", 1);
        armazenamento.armazenarPontuacao("yoshi", "moeda", 0);

        Set<Usuario> resultado = armazenamento.usuariosPontuadores();

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(usuario -> usuario.getNome().equals("mario")));
        assertTrue(resultado.stream().anyMatch(usuario -> usuario.getNome().equals("luigi")));
    }

    @Test
    public void recuperarTodosPontosPorUsuario() {
        armazenamento.armazenarPontuacao("mario", "estrela", 10);
        armazenamento.armazenarPontuacao("mario", "moeda", 1);

        Set<Ponto> resultado = armazenamento.pontosPorUsuario("mario");

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(ponto -> ponto.getTipo().equals("estrela")));
        assertTrue(resultado.stream().anyMatch(ponto-> ponto.getTipo().equals("moeda")));
    }
}
