import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UsuarioRepositorio {
    private String fileName;

    public UsuarioRepositorio(String fileName) {
        this.fileName = fileName;
        inicializarArquivoSeEstiverVazio(fileName);
    }

    public void limparRepositorio() {
        salvarLista(new HashMap<>());
    }

    public Map<String, Usuario> listar() {
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

    public void salvar(Usuario usuario) {
        Map<String, Usuario> usuarios = listar();
        usuarios.put(usuario.getNome(), usuario);

        salvarLista(usuarios);
    }

    public Usuario buscarOuCriar(String nomeUsuario) {
        Map<String, Usuario> usuarios = listar();
        Usuario usuario = usuarios.getOrDefault(nomeUsuario, new Usuario(nomeUsuario));

        return usuario;
    }

    private void inicializarArquivoSeEstiverVazio(String fileName) {
        File file = new File(fileName);
        if (file.length() == 0) {
            limparRepositorio();
        }
    }

    private ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.fileName)));
    }

    private ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.fileName)));
    }

    private void salvarLista(Map<String, Usuario> usuarios) {
        try {
            ObjectOutputStream oos = getObjectOutputStream();
            oos.writeObject(usuarios);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
