// ControladorTelaLogin.java
public class ControladorTelaLogin {
    public boolean validarLogin(String login, String senha) {
        // Validação com a classe GerenciadorUsuarios
        GerenciadorUsuarios gerenciador = new GerenciadorUsuarios();
        return gerenciador.validarLogin(login, senha);
    }
}