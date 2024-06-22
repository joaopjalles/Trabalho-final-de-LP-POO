import java.util.ArrayList;
import java.util.List;
class Cliente extends Pessoa {

    private String dataNascimento;
    private String email;
    private List<Venda> historicoCompras;

    public Cliente(String nome, String CPF, String endereco, String telefone, String dataNascimento, String email) {
        super(nome, endereco, CPF, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.historicoCompras = new ArrayList<>();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Métodos específicos para o sistema de vendas

    public boolean cadastrarCliente(String nome, String CPF, String endereco, String telefone, String DataNascimento, String email) {
        //validação dos dados
        if (CPF.length() != 11 || !CPF.matches("\\d+")) {
            return false; //CPF inválido
        }
        //Verifica se o CPF já existe


        Cliente[] listaDeClientes = new Cliente[0];

        for (Cliente cliente : listaDeClientes) {
            if (cliente.getCPF().equals(CPF)) {
                return false;
            }
        }


        return false;
    }
}