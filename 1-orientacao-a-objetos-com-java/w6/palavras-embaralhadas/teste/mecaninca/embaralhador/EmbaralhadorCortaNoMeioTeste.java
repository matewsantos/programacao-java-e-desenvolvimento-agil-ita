package mecaninca.embaralhador;

import mecanica.embaralhador.Embaralhador;
import mecanica.embaralhador.EmbaralhadorCortaNoMeio;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmbaralhadorCortaNoMeioTeste {

    @Test
    public void testeEmbaralharQuantidadeParDeLetras() throws Exception {
        Embaralhador embaralhador = new EmbaralhadorCortaNoMeio();

        assertEquals("primeira metade das letras ficam no final e vice-versa",
                "vaja", embaralhador.embaralhar("java")
        );
    }

    @Test
    public void testeEmbaralharQuantidadeImparDeLetras() throws Exception {
        Embaralhador embaralhador = new EmbaralhadorCortaNoMeio();

        assertEquals("primeira metade das letras ficam no final e vice-versa: letra do meio fica na primeira metade",
                "tranfor", embaralhador.embaralhar("fortran")
        );
    }
}