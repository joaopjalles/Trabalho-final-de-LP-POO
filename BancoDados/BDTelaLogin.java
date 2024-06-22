package BancoDados;

import java.util.Scanner;
import java.sql.*;

public class BDTelaLogin {

    // Dados de conexão com o banco de dados
    private static final String URL = "jdbc:sqlite:bd/BD.db"; // Caminho para o arquivo do banco de dados
    private static final String USUARIO = "root"; // Usuário do banco de dados
    private static final String SENHA = ""; // Senha do banco de dados

    public static void main(String[] args) {

        criarTabela();

        // Menu de opções:
        int opcao;
        do {
            opcao = mostrarMenu();
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    fazerLogin(); // Chama o login após o cadastro
                    break;
                case 2:
                    fazerLogin();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // Cria a tabela "users" se ela não existir
    private static void criarTabela() {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conexao.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) UNIQUE NOT NULL, " +
                    "senha VARCHAR(255) NOT NULL, " +
                    "cep VARCHAR(10) NOT NULL, " + // Adiciona a coluna "cep"
                    "cpf VARCHAR(11) NOT NULL)"); // Adiciona a coluna "cpf"
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // Exibe o menu de opções
    private static int mostrarMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Fazer login");
        System.out.println("0. Sair");
        System.out.print("Digite a opção desejada: ");
        return new Scanner(System.in).nextInt();
    }

    // Cadastra um novo usuário
    private static void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement("INSERT INTO Users (nome, email, senha, cep, cpf) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, cep);
            stmt.setString(5, cpf);
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // Faz o login do usuário (com preenchimento automático)
    private static void fazerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite 'a' para usar o último usuário cadastrado ou 'm' para inserir manualmente:");
        String opcao = scanner.nextLine();

        if (opcao.equalsIgnoreCase("a")) {
            // Obtém o último usuário cadastrado do banco de dados
            String email = obterUltimoEmailCadastrado();
            String senha = obterUltimaSenhaCadastrada();

            if (email != null && senha != null) {
                // Preenche os campos com os dados do último usuário
                System.out.println("Email: " + email);
                System.out.println("Senha: " + senha);

                // Tenta fazer login com os dados preenchidos
                try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                    try (PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM Users WHERE email = ? AND senha = ?")) {
                        stmt.setString(1, email);
                        stmt.setString(2, senha);
                        ResultSet resultado = stmt.executeQuery();
                        if (resultado.next()) {
                            System.out.println("Login realizado com sucesso!");
                            // Aqui você pode adicionar a lógica para o que acontece após o login
                        } else {
                            System.out.println("Usuário ou senha inválidos!");
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao fazer login: " + e.getMessage());
                }
            } else {
                System.out.println("Nenhum usuário cadastrado encontrado.");
                fazerLogin(); // Chama a função de login novamente para inserir dados manualmente
            }
        } else if (opcao.equalsIgnoreCase("m")) {
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                 PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM Users WHERE email = ? AND senha = ?")) {
                stmt.setString(1, email);
                stmt.setString(2, senha);
                ResultSet resultado = stmt.executeQuery();
                if (resultado.next()) {
                    System.out.println("Login realizado com sucesso!");
                    // Aqui você pode adicionar a lógica para o que acontece após o login
                } else {
                    System.out.println("Usuário ou senha inválidos!");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fazer login: " + e.getMessage());
            }
        } else {
            System.out.println("Opção inválida.");
            fazerLogin(); // Chama a função de login novamente
        }
    }

    // Obtem o último email cadastrado no banco de dados
    private static String obterUltimoEmailCadastrado() {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conexao.createStatement();
             ResultSet resultado = stmt.executeQuery("SELECT senha FROM Users ORDER BY id DESC LIMIT 1")) {
            if (resultado.next()) {
                return resultado.getString("email");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter último email: " + e.getMessage());
        }
        return null;
    }

    // Obtem a última senha cadastrada no banco de dados

    private static String obterUltimaSenhaCadastrada() {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conexao.createStatement();
             ResultSet resultado = stmt.executeQuery("SELECT senha FROM Users ORDER BY id DESC LIMIT 1")) {
            if (resultado.next()) {
                return resultado.getString("senha");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter última senha: " + e.getMessage());
        }
        return null;
    }
}