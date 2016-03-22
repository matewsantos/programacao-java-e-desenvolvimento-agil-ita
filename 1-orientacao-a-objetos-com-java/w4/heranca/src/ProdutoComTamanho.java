public class ProdutoComTamanho extends Produto {
    private int tamanho;

    public ProdutoComTamanho(String nome, int codigo, double preco, int tamanho) {
        super(nome, codigo, preco);

        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    @Override
    public int hashCode() {
        return this.getCodigo() ^ this.getTamanho();
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }
        if (outro == null || getClass() != outro.getClass()) {
            return false;
        }

        ProdutoComTamanho outroProduto = (ProdutoComTamanho) outro;
        return this.getCodigo() == outroProduto.getCodigo() && this.getTamanho() == outroProduto.getTamanho();
    }
}