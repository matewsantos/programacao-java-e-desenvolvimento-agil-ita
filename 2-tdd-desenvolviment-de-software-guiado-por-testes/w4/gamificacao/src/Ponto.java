import java.io.Serializable;

public class Ponto implements Serializable {
    private static final long serialVersionUID = -7213733517682362315L;

    private String tipo;
    private int quantidade;

    public Ponto(String tipo) {
        this.tipo = tipo;
        this.quantidade = 0;
    }

    public Ponto(String tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void adicionaPontos(int quantidade) {
        this.quantidade += quantidade;
    }
}
