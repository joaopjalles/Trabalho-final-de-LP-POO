import java.util.ArrayList;
import java.util.List;
public class GerenciadorUsuarios {

    private List<Usuario> usuarios;

    public GerenciadorUsuarios(){

        usuarios = new ArrayList<>();
        //inicializar com usuarios de testes
        usuarios.add(new Usuario("admin", "admin", "Vendedor"));
        usuarios.add(new Usuario("cliente1", "1234", "Cliente"));
    }

    public boolean validarLogin(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public String getTipoUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario.getTipo();
            }
        }
        return null;
    }
}


