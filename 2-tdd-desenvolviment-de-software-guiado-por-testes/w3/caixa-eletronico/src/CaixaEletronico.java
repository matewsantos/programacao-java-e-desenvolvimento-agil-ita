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

    public String login(String senha) throws ContaInexistenteException, SenhaErradaException {
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
        if (contaCorrente == null) {
            throw new UsuarioNaoLogadoException("saldo");
        }

        return "O saldo é " + formatador.format(contaCorrente.saldo());
    }
}
