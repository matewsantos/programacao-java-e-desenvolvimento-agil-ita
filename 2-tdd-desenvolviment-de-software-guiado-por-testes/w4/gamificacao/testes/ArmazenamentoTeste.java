import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ArmazenamentoTeste {
    private Armazenamento armazenamento;

    @Before
    public void setup() {
        armazenamento = new Armazenamento();
    }

    @Test
    public void recuperarPontosPorUsuarioETipo() {
        armazenamento.armazenarPontuacao("mario", "estrela", 10);
        armazenamento.armazenarPontuacao("mario", "moeda", 1);

        int resultado = armazenamento.pontosPorUsuarioETipo("mario", "estrela");

        assertEquals(10, resultado);
    }

    @Test
    public void recuperarUsuariosComPontos() {
        armazenamento.armazenarPontuacao("mario", "estrela", 10);
        armazenamento.armazenarPontuacao("mario", "moeda", 1);
        armazenamento.armazenarPontuacao("luigi", "moeda", 1);
        armazenamento.armazenarPontuacao("yoshi", "moeda", 0);

        Set<String> resultado = armazenamento.usuariosPontuadores();

        assertEquals(set("mario", "luigi"), resultado);
    }

    @Test
    public void recuperarTodosPontosPorUsuario() {
        armazenamento.armazenarPontuacao("mario", "estrela", 10);
        armazenamento.armazenarPontuacao("mario", "moeda", 1);

        Set<String> resultado = armazenamento.pontosPorUsuario("mario");

        assertEquals(set("moeda", "estrela"), resultado);
    }

    public Set<String> set(String ... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}
