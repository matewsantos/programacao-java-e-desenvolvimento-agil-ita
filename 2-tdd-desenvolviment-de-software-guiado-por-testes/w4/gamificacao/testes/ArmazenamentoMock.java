import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ArmazenamentoMock implements ArmazenadorPontuacao  {
    private String nomeUsuarioArmazenado;
    private String tipoPontuacaoArmazenada;
    private int quantidadePontuacaoArmazenada;

    @Override
    public void armazenarPontuacao(String nomeUsuario, String tipoPontuacao, int quantidadePontuacao) {
        this.nomeUsuarioArmazenado = nomeUsuario;
        this.tipoPontuacaoArmazenada = tipoPontuacao;
        this.quantidadePontuacaoArmazenada = quantidadePontuacao;
    }

    @Override
    public int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao) {
        return 0;
    }

    @Override
    public Set<Usuario> usuariosPontuadores() {
        return null;
    }

    @Override
    public Set<Ponto> pontosPorUsuario(String nomeUsuario) {
        return null;
    }

    public void armazenouPontuacaoCom(String nomeUsuario, String tipoPontuacao, int quantidadePontuacao) {
        assertEquals(nomeUsuario, nomeUsuarioArmazenado);
        assertEquals(tipoPontuacao, tipoPontuacaoArmazenada);
        assertEquals(quantidadePontuacao, quantidadePontuacaoArmazenada);
    }
}
