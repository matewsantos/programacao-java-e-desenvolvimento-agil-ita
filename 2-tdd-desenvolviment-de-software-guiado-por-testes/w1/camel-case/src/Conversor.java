import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Conversor {

    public List<String> converterCamelCase(String original) {
        validaParametro(original);
        String grupos = "(" + sequenciaDeMinusculasNoComeco() + "|" +
                              maiusculaSeguidaDeMinusculas() + "|" +
                              maiusculasConsecutivasSemAUltimaSeAProximaForMinuscula() + "|" +
                              sequenciaDeNumeros() + ")";

        String gruposSeparadoPorEspaco = original.replaceAll(grupos, "$1 ");
        List<String> gruposEmLista = Arrays.asList(gruposSeparadoPorEspaco.split(" "));
        return transformarEmMinusculaExcetoSigla(gruposEmLista);
    }

    private void validaParametro(String palavra) {
        if (comecaComDigito(palavra)) {
            throw new IllegalArgumentException("Não pode começar com número");
        } else if (possuiCaractereEspecial(palavra)) {
            throw new IllegalArgumentException("Não pode conter caractere(s) especial(is)");
        }
    }

    private boolean possuiCaractereEspecial(String string) {
        return !string.matches("([A-Za-z0-9])*");
    }

    private boolean comecaComDigito(String string) {
        return Character.isDigit(string.charAt(0));
    }

    private String sequenciaDeNumeros() {
        return "([0-9]+)";
    }

    private String maiusculasConsecutivasSemAUltimaSeAProximaForMinuscula() {
        return "([A-Z]+(?=([A-Z][a-z])|($)|([0-9])))";
    }

    private String sequenciaDeMinusculasNoComeco() {
        return "(^[a-z]+)";
    }

    private String maiusculaSeguidaDeMinusculas() {
        return "([A-Z]{1}[a-z]+)";
    }

    private List<String> transformarEmMinusculaExcetoSigla(List<String> palavras) {
        return palavras.stream()
                       .map(s -> transformaNaoSiglasEmMinuscula(s))
                       .collect(Collectors.toList());
    }

    private String transformaNaoSiglasEmMinuscula(String palavra) {
        return ehSigla(palavra) ? palavra : palavra.toLowerCase();
    }

    private boolean ehSigla(String string) {
        return string.matches("([A-Z])*");
    }
}
