package mecanica;

import banco.BancoDePalavras;
import mecanica.embaralhador.FabricaEmbaralhadores;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FabricaMecanicaDoJogo {
    private List<MecanicaDoJogo> mecanicasDosJogos;
    private Random random;

    public FabricaMecanicaDoJogo() throws IOException {
        random = new Random();
        FabricaEmbaralhadores fabricaEmbaralhadores = new FabricaEmbaralhadores();
        BancoDePalavras bancoDePalavras = new BancoDePalavras("palavras.txt");
        mecanicasDosJogos = Arrays.asList(
                new MorteSubita(fabricaEmbaralhadores, bancoDePalavras),
                new CincoTentativas(fabricaEmbaralhadores, bancoDePalavras)
        );
    }

    public MecanicaDoJogo getMecanicaDoJogoAleatorio() {
        return mecanicasDosJogos.get(getIndiceAleatorio());
    }

    private int getIndiceAleatorio() {
        return random.nextInt(mecanicasDosJogos.size());
    }
}
