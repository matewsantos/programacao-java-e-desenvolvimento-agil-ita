import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ServicoRemotoMock implements ServicoRemoto {
    private String numeroContaCorrente;
    private ContaCorrente contaCorrente;
    private ContaCorrente contaCorrentePersistida;
    private boolean chamouMetodoPersistirConta;

    @Override
    public ContaCorrente recuperaConta(String numeroContaCorrente) {
        if (this.numeroContaCorrente == numeroContaCorrente) {
            return contaCorrente;
        }

        return null;
    }

    @Override
    public void persistirConta(ContaCorrente contaCorrente) {
        this.contaCorrentePersistida = contaCorrente;
    }

    public ServicoRemotoMock quandoChamarRecupaContaCom(String numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
        return this;
    }

    public ServicoRemotoMock quandoChamarRecupaContaRetornar(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
        return this;
    }

    public ServicoRemotoMock retornar(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
        return this;
    }

    public void chamouPersistirContaCom(ContaCorrente contaCorrenteEsperada) {
        assertEquals(contaCorrenteEsperada, contaCorrentePersistida);
    }

    public void naoChamouPersistirConta() {
        assertNull(contaCorrentePersistida);
    }
}
