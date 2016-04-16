package mecanica.embaralhador;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FabricaEmbaralhadores {
    private List<Embaralhador> embaralhadores;
    private Random random;

    public FabricaEmbaralhadores() {
        embaralhadores = Arrays.asList(
            new EmbaralhadorReverso(),
            new EmbaralhadorCortaNoMeio()
        );
        random = new Random();
    }

    public Embaralhador getEmbaralhadorAleatorio() {
        return embaralhadores.get(getIndiceAleatorio());
    }

    private int getIndiceAleatorio() {
        return random.nextInt(embaralhadores.size());
    }
}

