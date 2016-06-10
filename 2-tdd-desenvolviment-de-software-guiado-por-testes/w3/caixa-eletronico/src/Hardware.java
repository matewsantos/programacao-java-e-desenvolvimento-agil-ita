public interface Hardware {
    String pegarNumeroDaContaCartao() throws ProblemaHardwareException;
    void entregarDinheiro() throws ProblemaHardwareException;
    void lerEnvelope() throws ProblemaHardwareException;
}
