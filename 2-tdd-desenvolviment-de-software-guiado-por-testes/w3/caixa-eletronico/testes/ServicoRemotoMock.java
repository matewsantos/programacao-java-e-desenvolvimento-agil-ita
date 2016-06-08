public class ServicoRemotoMock implements ServicoRemoto {
    private String numeroContaCorrente;
    private ContaCorrente conta;

    @Override
    public ContaCorrente recuperaConta(String numeroContaCorrente) {
        if (this.numeroContaCorrente == numeroContaCorrente) {
            return conta;
        }

        return null;
    }

    @Override
    public void persistirConta() {

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
}
