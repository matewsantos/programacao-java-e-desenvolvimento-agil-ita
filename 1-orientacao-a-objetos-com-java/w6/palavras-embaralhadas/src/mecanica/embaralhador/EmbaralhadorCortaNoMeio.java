package mecanica.embaralhador;

public class EmbaralhadorCortaNoMeio implements Embaralhador {
    private int deslocamento;

    @Override
    public String embaralhar(String palavra) {
        int meio = palavra.length() / 2;
        String comeco = palavra.substring(0, meio);
        String fim = palavra.substring(meio, palavra.length());

        return fim + comeco;
    }
}
