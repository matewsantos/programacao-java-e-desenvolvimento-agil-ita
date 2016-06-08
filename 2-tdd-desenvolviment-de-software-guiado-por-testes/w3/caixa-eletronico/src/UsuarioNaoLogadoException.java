public class UsuarioNaoLogadoException extends Exception {
    public UsuarioNaoLogadoException(String operacao) {
        super("Usuário precisa estar logado para efetuar esta operação:" + operacao);
    }
}
