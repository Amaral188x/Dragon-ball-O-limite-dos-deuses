import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Dragon Ball: O limite dos deuses");

        janela.setSize(1920, 1080);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        TelaIntro intro = new TelaIntro(janela);

        janela.setContentPane(intro);

        janela.setVisible(true);
        SwingUtilities.invokeLater(() -> {
            intro.requestFocusInWindow();
        });
    }
}