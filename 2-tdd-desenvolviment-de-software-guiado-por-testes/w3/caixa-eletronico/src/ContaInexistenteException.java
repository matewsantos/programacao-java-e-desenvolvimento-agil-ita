public class ContaInexistenteException extends Exception {
    public ContaInexistenteException(String numeroConta) {
        super("Não foi encontrada conta com numero " + numeroConta);
    }
}
