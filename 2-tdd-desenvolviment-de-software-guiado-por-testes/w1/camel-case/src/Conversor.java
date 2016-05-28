import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Conversor {

    public List<String> converterCamelCase(String original) {
        validarParametro(original);
        String grupos = "(" +
            sequenciaDeMinusculasNoComeco() + "|" +
            sequenciaDeNumeros() + "|" +
            maiusculaSeguidaDeMinusculas() + "|" +
            maiusculasConsecutivasSemAUltimaSeAProximaForMiniscula() +
        ")";

        String gruposSeparadoPorEspaco = original.replaceAll(grupos, "$1 ");
        List<String> gruposEmLista = Arrays.asList(gruposSeparadoPorEspaco.split(" "));
        return transformarEmMinusculaExcetoSigla(gruposEmLista);
    }

    public void validarParametro(String parametro) throws IllegalArgumentException {
        if (comecaComDigito(parametro)) {
            throw new IllegalArgumentException("Não pode começar com número");
        } else if (possuiCaractereEspecial(parametro)) {
            throw new IllegalArgumentException("Não pode conter caractere(s) especial(is)");
        }
    }

    private boolean possuiCaractereEspecial(String string) {
        return !string.matches("([A-Za-z0-9]])*");
    }

    public boolean comecaComDigito(String string) {
        return Character.isDigit(string.charAt(0));
    }

    public String sequenciaDeMinusculasNoComeco() {
        return "(^[a-z]+)";
    }

    public String sequenciaDeNumeros() {
        return "([0-9]+)";
    }

    public String maiusculaSeguidaDeMinusculas() {
        return "([A-Z]{1}[a-z]+)";
    }

    public String maiusculasConsecutivasSemAUltimaSeAProximaForMiniscula() {
        return "([A-Z]+(?=([A-Z][a-z])|($)|([0-9])))";
    }

    private List<String> transformarEmMinusculaExcetoSigla(List<String> palavras) {
        List<String> resultado = new ArrayList<>();
        for (String palavra : palavras) {
            if (!ehSigla(palavra)) {
                palavra = palavra.toLowerCase();
            }
            resultado.add(palavra);
        }

        return resultado;
    }

    private boolean ehSigla(String string) {
        for (Character caractere : string.toCharArray()) {
            if (ehMinusculo(caractere)) {
                return false;
            }
        }

        return true;
    }

    private boolean ehMinusculo(Character caractere) {
        return Character.isLowerCase(caractere);
    }
}
