public class Usuario {

    private String login;
    private String senha;
    private String tipo; //"Cliente" ou "Vendedor'

    public Usuario(String login,String senha,String tipo) {
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}