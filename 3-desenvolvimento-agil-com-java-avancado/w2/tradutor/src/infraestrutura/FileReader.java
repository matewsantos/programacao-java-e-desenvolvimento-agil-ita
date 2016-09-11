package infraestrutura;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    private String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, String> pegarTudo() {
        Map<String, String> resultado = new HashMap<>();

        getBufferedReader()
                .lines()
                .map(linha-> linha.split(":"))
                .forEach((palavras -> resultado.put(palavras[0], palavras[1])));

        return resultado;
    }

    private BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filePath)));
    }
}
