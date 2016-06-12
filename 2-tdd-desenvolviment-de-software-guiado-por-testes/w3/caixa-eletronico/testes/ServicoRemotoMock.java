public class ServicoRemotoMock implements ServicoRemoto {
    private String numeroContaCorrente;
    private ContaCorrente conta;
    private boolean chamouMetodoPersistirConta;

    @Override
    public ContaCorrente recuperaConta(String numeroContaCorrente) {
        if (this.numeroContaCorrente == numeroContaCorrente) {
            return conta;
        }

        return null;
    }

    @Override
    public void persistirConta(ContaCorrente contaCorrente) {
        this.chamouMetodoPersistirConta = true;
    }

    public ServicoRemotoMock quandoChamarRecupaContaCom(String numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
        return this;
    }

    public ServicoRemotoMock quandoChamarRecupaContaRetornar(ContaCorrente conta) {
        this.conta = conta;
        return this;
    }

    public ServicoRemotoMock retornar(ContaCorrente conta) {
        this.conta = conta;
        return this;
    }

    public boolean chamouMetodoPersistirConta() {
        return this.chamouMetodoPersistirConta;
    }
}
