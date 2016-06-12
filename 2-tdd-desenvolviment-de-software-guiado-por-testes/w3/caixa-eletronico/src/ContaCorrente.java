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

    public boolean sacar(BigDecimal valorASacar) {
        if (saldo().compareTo(valorASacar) < 0) {
            return false;
        }

        this.saldo = this.saldo.subtract(valorASacar);
        return true;
    }

    public void depositar(BigDecimal valorADepositar) {
        this.saldo = this.saldo.add(valorADepositar);
    }
}
