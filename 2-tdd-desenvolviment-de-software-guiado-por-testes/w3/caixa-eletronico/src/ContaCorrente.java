import java.math.BigDecimal;

public class ContaCorrente {
    private String numero;
    private String senha;
    private BigDecimal saldo;

    public ContaCorrente(String numero, String senha, BigDecimal saldo) {
        this.numero = numero;
        this.senha = senha;
        this.saldo = saldo;
    }

    public boolean senhaConfere(String senha) {
        return this.senha == senha;
    }

    public BigDecimal saldo() {
        return saldo;
    }
}
