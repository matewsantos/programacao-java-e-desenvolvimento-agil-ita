import java.util.HashSet;
import java.util.Set;

public class HardwareMock implements Hardware {

    private String numeroDaContaRetorno;
    private Set<String> metodosQueLancamException;

    public HardwareMock() {
        metodosQueLancamException  = new HashSet<>();
    }

    @Override
    public String pegarNumeroDaContaCartao() throws ProblemaHardwareException {
        if (metodosQueLancamException.contains("pegarNumeroDaContaCartao")) {
            throw new ProblemaHardwareException();
        }

        return numeroDaContaRetorno;
    }

    @Override
    public void entregarDinheiro() throws ProblemaHardwareException {
        if (metodosQueLancamException.contains("entregarDinheiro")) {
            throw new ProblemaHardwareException();
        }
    }

    @Override
    public void lerEnvelope() throws ProblemaHardwareException {
        if (metodosQueLancamException.contains("lerEnvelope")) {
            throw new ProblemaHardwareException();
        }
    }

    public void quandoPegarNumeroDaContaRetornar(String numeroDaConta) {
        this.numeroDaContaRetorno = numeroDaConta;
    }

    public void lancarExceptionQuandoChamar(String nomeMetodo) {
        metodosQueLancamException.add(nomeMetodo);
    }
}
