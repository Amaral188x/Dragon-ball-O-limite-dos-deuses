import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TelaIntro extends JPanel implements KeyListener {

    private final JFrame janela;
    private final Som som = new Som();

    private BufferedImage frameAtual;
    private Timer timer;

    private final int TOTAL_FRAMES = 1944;
    private final int FPS_TELA = 60;

    public TelaIntro(JFrame janela) {
        this.janela = janela;
        setFocusable(true);
        addKeyListener(this);

        setDoubleBuffered(true);

        som.tocarSom("/sons/intro.wav");

        timer = new Timer(1000 / FPS_TELA, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               atualizarIntro();
            }});
        timer.start();
    }

    private void atualizarIntro() {
        long duracao = som.getDuracaoMicrosegundos();
        long posicao = som.getPosicaoMicrosegundos();

        if (duracao <= 0) {
            return;
        }

        double progresso = posicao / (double) duracao;

        int frame = (int) (progresso * TOTAL_FRAMES) + 1;

        if (frame > TOTAL_FRAMES) {
            frame = TOTAL_FRAMES;
        }

        frameAtual = carregarFrame(frame);

        repaint();

        if (posicao >= duracao) {
            finalizarIntro();
        }
    }

    private BufferedImage carregarFrame(int numero) {
        String caminho = "/intro/intro/(" + numero + ").jpg";

        URL recurso = getClass().getResource(caminho);

        if (recurso == null) {
            System.out.println("Frame não encontrado: " + caminho);
            return null;
        }

        try {
            BufferedImage original = ImageIO.read(recurso);
            if (original == null) {
                return null;
            }

            // Os frames da intro sao exibidos em tela cheia 1920x1080;
            // qualquer imagem maior que isso e reduzida para economizar memoria.
            int maxLado = 1920;
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

        } catch (IOException e) {
            System.out.println("Erro ao carregar frame: " + caminho);
            return null;
        }
    }

    private void finalizarIntro() {
        timer.stop();
        som.parar();

        MenuPrincipal menu = new MenuPrincipal(janela);

        janela.setContentPane(menu);
        janela.revalidate();
        janela.repaint();

        SwingUtilities.invokeLater(() -> {
            menu.requestFocusInWindow();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (frameAtual != null) {
            g.drawImage(frameAtual, 0, 0, getWidth(), getHeight(), null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() ==KeyEvent.VK_ENTER){
            finalizarIntro();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
     
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
