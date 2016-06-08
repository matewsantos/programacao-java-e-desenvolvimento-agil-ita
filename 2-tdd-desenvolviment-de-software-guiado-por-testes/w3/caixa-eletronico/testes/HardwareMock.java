public class HardwareMock implements Hardware {

    private String numeroDaContaRetorno;

    @Override
    public String pegarNumeroDaContaCartao() {
        return "1";
    }

    @Override
    public void entregarDinheiro() {

    }

    @Override
    public void lerEnvelope() {

    }

    public void quandoPegarNumeroDaContaRetornar(String numeroDaConta) {
        this.numeroDaContaRetorno = numeroDaConta;
    }
}
