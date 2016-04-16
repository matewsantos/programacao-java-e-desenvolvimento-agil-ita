import org.junit.Test;

import static org.junit.Assert.*;

public class AutoridadeTeste {

    @Test
    public void testGetTratamentoInformal() throws Exception {
        Autoridade autoridade = new Autoridade("Alan", "Turing", new Informal());

        assertEquals("retorna apenas primeiro nome", "Alan", autoridade.getTratamento());
    }

    @Test
    public void testGetTratamentoRespeitosoMasculino() throws Exception {
        Autoridade autoridade = new Autoridade("Alan", "Turing", new Respeitoso(Respeitoso.Genero.MASCULINO));

        assertEquals("retorna pronome de tratamento masculino com sobrenome", "Sr. Turing", autoridade.getTratamento());
    }

    @Test
    public void testGetTratamentoRespeitosoFeminino() throws Exception {
        Autoridade autoridade = new Autoridade("Ada", "Lovelace", new Respeitoso(Respeitoso.Genero.FEMININO));

        assertEquals("retorna pronome de tratamento feminimo com sobrenome", "Sra. Lovelace", autoridade.getTratamento());
    }

    @Test
    public void testGetTratamentoComTitulo() throws Exception {
        Autoridade autoridade = new Autoridade("Ada", "Lovelace", new ComTitulo("Mestre"));

        assertEquals("retorna titulo com nome completo", "Mestre Ada Lovelace", autoridade.getTratamento());
    }
}

