import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame {
    private JButton btnCarrinho;
    private JButton btnLogin;
    private List<Produto> carrinhoDeCompras = new ArrayList<>();

    public Home() {
        setTitle("Adega Saudável");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel vitrinePanel = new JPanel(new GridLayout(2, 2));

        Produto produto1 = new Produto("Whisky Black Label", 900.00, "Bebida", 10);
        Produto produto2 = new Produto("Coca Cola", 12.00, "Bebida", 15);
        Produto produto3 = new Produto("Spaten", 70.00, "Bebida", 20);
        Produto produto4 = new Produto("Guarana Jesus", 10.00, "Bebida", 8);

        // Carregue as imagens usando ImageIcon
        ImageIcon imagemProduto1 = new ImageIcon(getClass().getResource("Imagens/bebida2.png"));
        ImageIcon imagemProduto2 = new ImageIcon(getClass().getResource("Imagens/cocacola.png"));
        ImageIcon imagemProduto3 = new ImageIcon(getClass().getResource("Imagens/spaten-removebg-preview.png"));
        ImageIcon imagemProduto4 = new ImageIcon(getClass().getResource("Imagens/guaranajesus.png"));

        // Redimensione as imagens para o tamanho desejado
        Image imgProduto1 = imagemProduto1.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        Image imgProduto2 = imagemProduto2.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        Image imgProduto3 = imagemProduto3.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        Image imgProduto4 = imagemProduto4.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);

        // Crie novos Icones com as imagens redimensionadas
        ImageIcon imgRedimensionada1 = new ImageIcon(imgProduto1);
        ImageIcon imgRedimensionada2 = new ImageIcon(imgProduto2);
        ImageIcon imgRedimensionada3 = new ImageIcon(imgProduto3);
        ImageIcon imgRedimensionada4 = new ImageIcon(imgProduto4);

        // Crie os labels com as imagens e informações
        JLabel labelProduto1 = new JLabel(imgRedimensionada1);
        labelProduto1.setText("<html><center><h2>" + produto1.getNome() + "</h2><br>R$" + produto1.getPreco() + "</center></html>");
        labelProduto1.setHorizontalTextPosition(JLabel.CENTER);
        labelProduto1.setVerticalTextPosition(JLabel.BOTTOM);
        labelProduto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionarAoCarrinho(produto1);
            }
        });

        JLabel labelProduto2 = new JLabel(imgRedimensionada2);
        labelProduto2.setText("<html><center><h2>" + produto2.getNome() + "</h2><br>R$" + produto2.getPreco() + "</center></html>");
        labelProduto2.setHorizontalTextPosition(JLabel.CENTER);
        labelProduto2.setVerticalTextPosition(JLabel.BOTTOM);
        labelProduto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionarAoCarrinho(produto2);
            }
        });

        JLabel labelProduto3 = new JLabel(imgRedimensionada3);
        labelProduto3.setText("<html><center><h2>" + produto3.getNome() + "</h2><br>R$" + produto3.getPreco() + "</center></html>");
        labelProduto3.setHorizontalTextPosition(JLabel.CENTER);
        labelProduto3.setVerticalTextPosition(JLabel.BOTTOM);
        labelProduto3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionarAoCarrinho(produto3);
            }
        });

        JLabel labelProduto4 = new JLabel(imgRedimensionada4);
        labelProduto4.setText("<html><center><h2>" + produto4.getNome() + "</h2><br>R$" + produto4.getPreco() + "</center></html>");
        labelProduto4.setHorizontalTextPosition(JLabel.CENTER);
        labelProduto4.setVerticalTextPosition(JLabel.BOTTOM);
        labelProduto4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionarAoCarrinho(produto4);
            }
        });

        // Adicione os labels ao painel
        vitrinePanel.add(labelProduto1);
        vitrinePanel.add(labelProduto2);
        vitrinePanel.add(labelProduto3);
        vitrinePanel.add(labelProduto4);

        // Crie o ícone do carrinho
        ImageIcon iconeCarrinho = new ImageIcon(getClass().getResource("Imagens/carrinho.png"));
        Image imageCarrinho = iconeCarrinho.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconeCarrinho = new ImageIcon(imageCarrinho);
        JLabel carrinhoIcon = new JLabel(iconeCarrinho);
        carrinhoIcon.setToolTipText("Ver Carrinho");
        carrinhoIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirCarrinho();
            }
        });

        // Crie um painel para o botão Login e o ícone do carrinho
        JPanel botaoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login login = new Login();
                login.setVisible(true);
                dispose(); // Fecha a tela Home
            }
        });
        botaoPanel.add(btnLogin);
        botaoPanel.add(new JLabel("Adega Saudável"));
        botaoPanel.add(carrinhoIcon);

        btnCarrinho = new JButton("Carrinho");
        btnCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirCarrinho();
            }
        });

        // Adicione os painéis à janela
        add(botaoPanel, BorderLayout.NORTH);
        add(vitrinePanel, BorderLayout.CENTER);
        add(btnCarrinho, BorderLayout.SOUTH);
        updateCarrinhoButton(); // Chama a função para atualizar o botão inicialmente

        // Mensagem com fundo colorido
        JPanel mensagemPanel = new JPanel();
        mensagemPanel.setBackground(Color.YELLOW);
        mensagemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel mensagemLabel = new JLabel("Para adicionar ao carrinho, basta clicar na imagem do seu produto desejado");
        mensagemPanel.add(mensagemLabel);
        add(mensagemPanel, BorderLayout.SOUTH);
    }

    private void adicionarAoCarrinho(Produto produto) {
        carrinhoDeCompras.add(produto);
        JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho com sucesso!");
        updateCarrinhoButton();
    }

    private void updateCarrinhoButton() {
        if (carrinhoDeCompras.isEmpty()) {
            btnCarrinho.setText("Carrinho");
        } else {
            StringBuilder text = new StringBuilder("Carrinho (");
            for (int i = 0; i < carrinhoDeCompras.size(); i++) {
                text.append(carrinhoDeCompras.get(i).getNome());
                if (i < carrinhoDeCompras.size() - 1) {
                    text.append(", ");
                }
            }
            text.append(")");
            btnCarrinho.setText(text.toString());
        }
    }

    private void abrirCarrinho() {
        Carrinho carrinho = new Carrinho(carrinhoDeCompras);
        carrinho.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
}