import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.image.BufferedImage;

public class MenuPrincipal extends JPanel implements KeyListener {

    private int indiceFundo = 0;
    private Timer timer;
    private JFrame janela;
    private Som som;
    private boolean jogoIniciado;
    // O menu so mantem UM frame em memoria por vez, como a intro;
    // manter os 93 em memoria consome mais de 500 MB de heap.
    private final int totalFramesFundo = 93;
    private Image frameAtual = carregarImagemReduzida("/fundo/fundoMenu/(1).jpg", 1920);

    public MenuPrincipal(JFrame janela) {
        this.janela = janela;
        som = new Som();
        som.tocarSom("/sons/fundoMenuSom.wav");
        setFocusable(true);
        addKeyListener(this);
        SwingUtilities.invokeLater(() -> requestFocusInWindow());

    timer = new Timer(16, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Carrega o proximo frame antes de avancar o indice,
            // para nao gastar memoria com o anterior.
            Image proximo = carregarImagemReduzida("/fundo/fundoMenu/(" + (indiceFundo + 1) + ").jpg", 1920);
            if (proximo != null) {
                if (frameAtual != null) {
                    frameAtual.flush();
                }
                frameAtual = proximo;
            }
            indiceFundo++;
            if (indiceFundo >= totalFramesFundo) {
                indiceFundo = 0;
            }
            repaint();
        }
    });
    timer.start();
    timer.start();
}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(frameAtual, 0, 0, getWidth(), getHeight(), this);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !jogoIniciado) {
            // Impede a repeticao do Enter de criar varias telas do jogo.
            jogoIniciado = true;
            timer.stop();
            removeKeyListener(this);
            som.parar();


            if (frameAtual != null) {
                frameAtual.flush();
                frameAtual = null;
            }


            TelaJogo jogo = new TelaJogo();

            janela.setContentPane(jogo);
            janela.revalidate();
            janela.repaint();
            jogo.requestFocusInWindow();
        }

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Carrega a imagem e, se for maior que maxLado, redimensiona
    // proporcionalmente para o maior lado ficar igual a maxLado.
    private Image carregarImagemReduzida(String caminho, int maxLado) {
        try {
            java.net.URL url = getClass().getResource(caminho);
            if (url == null) {
                return null;
            }

            BufferedImage original = ImageIO.read(url);
            if (original == null) {
                return null;
            }

            int largura = original.getWidth();
            int altura = original.getHeight();
            int maiorLado = Math.max(largura, altura);

            if (maiorLado <= maxLado) {
                return original;
            }

            double fator = (double) maxLado / maiorLado;
            int novaLargura = Math.max(1, (int) Math.round(largura * fator));
            int novaAltura = Math.max(1, (int) Math.round(altura * fator));

            BufferedImage reduzida = new BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = reduzida.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(original, 0, 0, novaLargura, novaAltura, null);
            g2.dispose();
            return reduzida;
        } catch (Exception e) {
            return new ImageIcon(getClass().getResource(caminho)).getImage();
        }
    }
}
