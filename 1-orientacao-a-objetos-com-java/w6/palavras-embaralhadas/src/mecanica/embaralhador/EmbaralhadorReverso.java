package mecanica.embaralhador;

public class EmbaralhadorReverso implements Embaralhador
{
    @Override
    public String embaralhar(String palavra) {
        StringBuffer resultado = new StringBuffer();
        for (int i = palavra.length() - 1; i >= 0; i--) {
            resultado.append(palavra.charAt(i));
        }

        return resultado.toString();
    }
}
