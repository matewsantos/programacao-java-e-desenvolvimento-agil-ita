public interface ServicoRemoto {
    ContaCorrente recuperaConta(String numeroContaCorrente);
    void persistirConta(ContaCorrente contaCorrente);
}
