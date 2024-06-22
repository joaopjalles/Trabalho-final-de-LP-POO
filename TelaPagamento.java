import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPagamento extends JFrame {

    public TelaPagamento() {
        setTitle("Tela de Pagamento");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton btnPix = new JButton("Pagar com Pix");
        JButton btnBoleto = new JButton("Pagar com Boleto");
        JButton btnCartao = new JButton("Pagar com Cartão de Crédito");

        btnPix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPagamento("Pix");
            }
        });

        btnBoleto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPagamento("Boleto");
            }
        });

        btnCartao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPagamento("Cartão de Crédito");
            }
        });

        add(btnPix);
        add(btnBoleto);
        add(btnCartao);
    }

    private void realizarPagamento(String metodoPagamento) {
        JOptionPane.showMessageDialog(this, "Compra realizada com sucesso usando " + metodoPagamento);
        voltarParaHome();
    }

    private void voltarParaHome() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Home home = new Home();
                home.setVisible(true);
                dispose(); // Fecha a tela de pagamento
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPagamento().setVisible(true);
            }
        });
    }
}
