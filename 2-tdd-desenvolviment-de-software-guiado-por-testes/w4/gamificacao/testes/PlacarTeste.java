import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PlacarTeste {
    @Test
    public void registrarPontuacao() {
        ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
        Placar placar = new Placar(armazenamentoMock);

        placar.armazenaPontuacao("mario", "estrela", 10);

        armazenamentoMock.armazenouPontuacaoCom("mario", "estrela", 10);
    }

    @Test
    public void pontosPorUsuario() {
        ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
        Placar placar = new Placar(armazenamentoMock);
        Set<Ponto> pontosDoMario = new HashSet<>(Arrays.asList(new Ponto("estrela", 1)));
        armazenamentoMock.quandoChamarPontosPorUsuarioCom("mario").retornar(pontosDoMario);

        Set<Ponto> resultado = placar.pontosPorUsuario("mario");

        assertEquals(pontosDoMario, resultado);
    }
}
