import java.util.Objects;

public class Ponto {
    private String tipo;
    private int quantidade;

    public Ponto(String tipo) {
        this.tipo = tipo;
        this.quantidade = 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ponto ponto = (Ponto) o;
        return Objects.equals(tipo, ponto.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo);
    }
}
