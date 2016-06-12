import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CaixaEletronico {
    private Hardware hardware;
    private ServicoRemoto servicoRemoto;
    private ContaCorrente contaCorrente;

    private NumberFormat formatador = NumberFormat.getInstance().getCurrencyInstance(new Locale("pt", "BR"));

    public CaixaEletronico(Hardware hardware, ServicoRemoto servicoRemoto) {
        this.hardware = hardware;
        this.servicoRemoto = servicoRemoto;
    }

    public String logar(String senha) throws ContaInexistenteException, SenhaErradaException, ProblemaHardwareException {
        String numeroConta = hardware.pegarNumeroDaContaCartao();
        this.contaCorrente = servicoRemoto.recuperaConta(numeroConta);
        if (contaCorrente == null) {
            throw new ContaInexistenteException("Não foi encontrada conta com numero " + numeroConta);
        }

        if (!contaCorrente.senhaConfere(senha)) {
            throw new SenhaErradaException("Senha incorreta");
        }

        return "Usuário Autenticado";
    }

    public String saldo() throws UsuarioNaoLogadoException {
        validarLogin("saldo");

        return "O saldo é " + formatador.format(contaCorrente.saldo());
    }

    public String sacar(BigDecimal valorASacar) throws UsuarioNaoLogadoException, ProblemaHardwareException {
        validarLogin("sacar");

        if (!contaCorrente.sacar(valorASacar)) {
            return "Saldo Insuficiente";
        }

        servicoRemoto.persistirConta(contaCorrente);
        hardware.entregarDinheiro();
        return "Retire seu dinheiro";
    }

    public String depositar(BigDecimal valorADepositar) throws UsuarioNaoLogadoException, ProblemaHardwareException {
        validarLogin("depositar");

        hardware.lerEnvelope();
        contaCorrente.depositar(valorADepositar);
        servicoRemoto.persistirConta(contaCorrente);

        return "Depósito recebido com sucesso";
    }

    private void validarLogin(String acao) throws UsuarioNaoLogadoException {
        if (contaCorrente == null) {
            throw new UsuarioNaoLogadoException(acao);
        }
    }
}
