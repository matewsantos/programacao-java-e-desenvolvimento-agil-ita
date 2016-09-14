package dominio;

import infraestrutura.FileReader;

import java.util.Map;

public class Tradutor {
    String FILE_PATH = "recursos/traducoes.txt";

    public String traduzir(String palavra) {
        return traducoes().getOrDefault(palavra, palavra);
    }

    private Map<String, String> traducoes () {
        FileReader reader = new FileReader(FILE_PATH);

        return reader.pegarTudo();
    }
}
