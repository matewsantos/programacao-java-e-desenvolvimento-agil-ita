package banco;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BancoDePalavras {
    private List<String> palavras;
    private Random random;

     public BancoDePalavras(String filePath) throws IOException {
        this.random = new Random();
        inicializaBanco(filePath);
    }

    public String getPalavraAleatoria() {
        return palavras.get(getIndiceAleatorio());
    }

    private int getIndiceAleatorio() {
        return random.nextInt(palavras.size());
    }

    private void inicializaBanco(String filePath) throws IOException {
        Path caminho = Paths.get(filePath);

        Stream<String> linhas = Files.lines(caminho);
        palavras = linhas.collect(Collectors.toList());
    }
}

