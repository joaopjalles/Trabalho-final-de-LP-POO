import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Carrinho extends JFrame {
    private List<Produto> produtosCarrinho;
    private DefaultListModel<String> listModel;

    public Carrinho(List<Produto> produtosCarrinho) {
        this.produtosCarrinho = produtosCarrinho;

        setTitle("Carrinho de Compras");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        for (Produto produto : produtosCarrinho) {
            listModel.addElement(produto.getNome() + " - R$" + produto.getPreco());
        }

        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        JButton btnRealizarCompra = new JButton("Realizar Compra");
        btnRealizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPagamento telaPagamento = new TelaPagamento();
                telaPagamento.setVisible(true);
                dispose(); // Fecha a tela do Carrinho ao abrir a tela de pagamento
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela do Carrinho e retorna à tela anterior
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRealizarCompra);
        buttonPanel.add(btnVoltar);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Simulação de produtos no carrinho para teste
                List<Produto> produtos = List.of(
                        new Produto("Whisky Black Label", 900.00, "Bebida", 10),
                        new Produto("Coca Cola", 12.00, "Bebida", 15)
                );
                new Carrinho(produtos).setVisible(true);
            }
        });
    }
}
