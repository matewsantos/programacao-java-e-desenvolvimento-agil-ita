import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ConversorTeste {
    private Conversor conversor;

    @Before
    public void setup() {
        conversor = new Conversor();
    }

    @Test
    public void umaPalavraTodaMinuscula() {;
        assertEquals(
                Arrays.asList("nome"),
                conversor.converterCamelCase("nome")
        );
    }

    @Test
    public void umaPalavraComecaComMaiuscula() {
        assertEquals(
                Arrays.asList("nome"),
                conversor.converterCamelCase("Nome")
        );
    }

    @Test
    public void camelCaseComecaMinuscula() {
        assertEquals(
                Arrays.asList("nome", "composto"),
                conversor.converterCamelCase("nomeComposto")
        );
    }

    @Test
    public void CamelCaseComecaMaiuscula() {
        assertEquals(
                Arrays.asList("nome", "composto"),
                conversor.converterCamelCase("NomeComposto")
        );
    }

    @Test
    public void apenasSigla() {
        assertEquals(
                Arrays.asList("CPF"),
                conversor.converterCamelCase("CPF")
        );
    }

    @Test
    public void siglaNoComeco() {
        assertEquals(
                Arrays.asList("CPF", "novo"),
                conversor.converterCamelCase("CPFNovo")
        );
    }

    @Test
    public void siglaNoFim() {
        assertEquals(
                Arrays.asList("numero", "CPF"),
                conversor.converterCamelCase("numeroCPF")
        );
    }

    @Test
    public void siglaNoMeio() {
        assertEquals(
                Arrays.asList("numero", "CPF", "contribuinte"),
                conversor.converterCamelCase("numeroCPFContribuinte")
        );
    }

    @Test
    public void comDigitoNoMeio() {
        assertEquals(
                Arrays.asList("recupera", "10", "primeiros"),
                conversor.converterCamelCase("recupera10primeiros")
        );
    }

    @Test
    public void comDigitoNoFim() {
        assertEquals(
                Arrays.asList("recupera", "10"),
                conversor.converterCamelCase("recupera10")
        );
    }

    @Test(expected=IllegalArgumentException.class)
    public void comecaComDigito() {
        conversor.converterCamelCase("10Primeiros");
    }

    @Test(expected=IllegalArgumentException.class)
    public void possuiCaractereEspecial() {
        conversor.converterCamelCase("nome#Composto");
    }
}