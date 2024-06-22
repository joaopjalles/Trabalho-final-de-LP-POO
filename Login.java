import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnCadastrar;
    private JButton btnVoltar;

    // Dados de conexão com o banco de dados
    private static final String URL = "jdbc:sqlite:src/bd/BancodadosLoja.db"; // Caminho para o arquivo do banco de dados
    private static final String USUARIO = "root"; // Usuário do banco de dados
    private static final String SENHA = ""; // Senha do banco de dados

    public Login() {
        setTitle("Adega Saúdavel");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JLabel lblUsuario = new JLabel("Usuário:");
        txtUsuario = new JTextField();
        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        btnEntrar = new JButton("Entrar");
        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");

        // Ação do botão "Entrar"
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String senha = new String(txtSenha.getPassword());

                try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                     PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM users WHERE email = ? AND senha = ?")) {
                    stmt.setString(1, usuario);
                    stmt.setString(2, senha);
                    ResultSet resultado = stmt.executeQuery();
                    if (resultado.next()) {
                        System.out.println("Login realizado com sucesso!");
                        // Aqui você pode adicionar a lógica para o que acontece após o login
                        // Por exemplo, abrir a tela Home:
                        Home home = new Home();
                        home.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "Usuário ou senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    System.err.println("Erro ao fazer login: " + ex.getMessage());
                    JOptionPane.showMessageDialog(Login.this, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Cadastrar"
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cadastro cadastro = new Cadastro();
                cadastro.setVisible(true);
                dispose();
            }
        });

        // Ação do botão "Voltar"
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.setVisible(true);
                dispose();
            }
        });

        add(lblUsuario);
        add(txtUsuario);
        add(lblSenha);
        add(txtSenha);
        add(btnEntrar);
        add(btnCadastrar);
        add(btnVoltar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}

class Cadastro extends JFrame {
    private JTextField txtNomeCompleto;
    private JTextField txtEmail;
    private JTextField txtCEP;
    private JTextField txtCPF;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;
    private JButton btnVoltar;

    // Dados de conexão com o banco de dados
    private static final String URL = "jdbc:sqlite:src/bd/BancodadosLoja.db"; // Caminho para o arquivo do banco de dados
    private static final String USUARIO = "root"; // Usuário do banco de dados
    private static final String SENHA = ""; // Senha do banco de dados

    public Cadastro() {
        setTitle("Adega Saúdavel");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        JLabel lblNomeCompleto = new JLabel("Nome Completo:");
        txtNomeCompleto = new JTextField();
        JLabel lblEmail = new JLabel("E-mail:");
        txtEmail = new JTextField();
        JLabel lblCEP = new JLabel("CEP:");
        txtCEP = new JTextField();
        JLabel lblCPF = new JLabel("CPF:");
        txtCPF = new JTextField();
        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");

        // Ação do botão "Cadastrar"
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obter os valores dos campos de texto
                    String nomeCompleto = txtNomeCompleto.getText();
                    String email = txtEmail.getText();
                    String cep = txtCEP.getText();
                    String cpf = txtCPF.getText();
                    String senha = new String(txtSenha.getPassword());

                    // Verificar se algum campo está vazio
                    if (nomeCompleto.isEmpty() || email.isEmpty() || cep.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                        throw new IllegalArgumentException("Cadastro inválido: preencha todos os campos.");
                    }

                    // Lógica de cadastro (salve os dados do usuário em um banco de dados ou arquivo)
                    try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                         PreparedStatement stmt = conexao.prepareStatement("INSERT INTO users (nome, email, senha, cep, cpf) VALUES (?, ?, ?, ?, ?)")) {
                        stmt.setString(1, nomeCompleto);
                        stmt.setString(2, email);
                        stmt.setString(3, senha);
                        stmt.setString(4, cep);
                        stmt.setString(5, cpf);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(Cadastro.this, "Usuário cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                        // Após o cadastro, volta para a tela de Login
                        Login login = new Login();
                        login.setVisible(true);
                        dispose();
                    } catch (SQLException ex) {
                        System.err.println("Erro ao cadastrar usuário: " + ex.getMessage());
                        JOptionPane.showMessageDialog(Cadastro.this, "Erro ao cadastrar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Cadastro.this, ex.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Voltar"
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volta para a tela de login
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });

        add(lblNomeCompleto);
        add(txtNomeCompleto);
        add(lblEmail);
        add(txtEmail);
        add(lblCEP);
        add(txtCEP);
        add(lblCPF);
        add(txtCPF);
        add(lblSenha);
        add(txtSenha);
        add(btnCadastrar);
        add(btnVoltar);
    }
}