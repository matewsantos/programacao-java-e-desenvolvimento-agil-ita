package mecaninca.embaralhador;

import mecanica.embaralhador.Embaralhador;
import mecanica.embaralhador.EmbaralhadorReverso;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmbaralhadorReversoTeste {

    @Test
    public void testeEmbaralhar() throws Exception {
        Embaralhador embralhador = new EmbaralhadorReverso();

        assertEquals("avaj", embralhador.embaralhar("java"));
    }
}