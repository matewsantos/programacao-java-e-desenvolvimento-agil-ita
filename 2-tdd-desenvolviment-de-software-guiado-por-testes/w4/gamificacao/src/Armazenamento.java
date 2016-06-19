import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Armazenamento implements ArmazenadorPontuacao {
    private String fileName;

    public Armazenamento(String fileName) {
        this.fileName = fileName;
        inicializarArquivoSeEstiverVazio(fileName);
    }

    public void armazenarPontuacao(String nomeUsuario, String tipoPonto, int quantidade) {
        Usuario usuario = buscaOuCriaUsuario(nomeUsuario);
        usuario.adicionaPontos(tipoPonto, quantidade);

        Map<String, Usuario> usuarios = getUsuarios();
        usuarios.put(nomeUsuario, usuario);
        salvarUsuarios(usuarios);
    }

    public int pontosPorUsuarioETipo(String nomeUsuario, String tipoPontuacao) {
        return buscaOuCriaUsuario(nomeUsuario).quantidadePontosPorTipo(tipoPontuacao);
    }

    public Set<Usuario> usuariosPontuadores() {
        return getUsuarios().values().stream()
                .filter(usuario -> usuario.temPontos())
                .collect(Collectors.toSet());
    }

    public Set<Ponto> pontosPorUsuario(String nomeUsuario) {
        return buscaOuCriaUsuario(nomeUsuario).getPontos().stream()
                .collect(Collectors.toSet());
    }

    private Usuario buscaOuCriaUsuario(String nomeUsuario) {
        Map<String, Usuario> usuarios = getUsuarios();
        Usuario usuario = usuarios.getOrDefault(nomeUsuario, new Usuario(nomeUsuario));

        return usuario;
    }

    private Map<String, Usuario> getUsuarios() {
        try {
            ObjectInputStream ois = getObjectInputStream();
            Object obj = ois.readObject();
            ois.close();

            return (Map<String, Usuario>) obj;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void salvarUsuarios(Object obj) {
        try {
            ObjectOutputStream oos = getObjectOutputStream();
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.fileName)));
    }

    private ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.fileName)));
    }

    private void inicializarArquivoSeEstiverVazio(String fileName) {
        File file = new File(fileName);
        if (file.length() == 0) {
            salvarUsuarios(new HashMap<>());
        }
    }
}
