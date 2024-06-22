public class Pessoa {
    String nome;
    String endereco;
    String CPF;
    String Telefone;


    public Pessoa(String nome,String endereco, String CPF, String Telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.CPF = CPF;
        this.Telefone = Telefone;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}