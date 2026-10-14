import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TelaJogo extends JPanel implements ActionListener, KeyListener {

    private final Vegeta vegeta;
    private final Goku goku;

    private final Timer timer;
    private int musicaASerTocada;
    private Image fundo = load("/fundo/fundo.png");
    private Image fundoNormal = fundo;
    private Image fundoEspecial = load("/fundo/fundo2.png");
    private Som somFundo;
    private boolean esquerda;
    private boolean direita;
    private boolean cima;
    private boolean baixo;
    private boolean elevarKiGoku;
    private boolean espacoPressionado;
    private boolean pararMusica = true;
    private boolean mutar = false;
    private boolean podeGritar = true;
    private boolean podeMover = true;
    public Som grito;
    private ArrayList<Image> fimJogo;
    private boolean rodarFimJogo = false;
    private boolean carregar = false;
    private boolean finalizarOponente = true;
    private Som vitoriaSom;
    private int indiceFimJogo;
    private Timer timerFimJogo;
    private boolean vitoria = false;
    private boolean derrota = false;
    private boolean vitoriaSom1 = true;
    private boolean vitoriaSom2 = false;
    private int gokuposY;

    public TelaJogo() {
        setFocusable(true);
        addKeyListener(this);


        vitoriaSom = new Som();
        grito = new Som();
        grito.setVolume(0.0f);
        grito.tocarSom("/sons/grito.wav");
        grito.parar();
        grito.setVolume(1.0f);
        somFundo = new Som();
        musicaASerTocada = 1;
        somFundo.tocarSom("/fundo/musicasFundo/fundo1.wav");
        SwingUtilities.invokeLater(() -> requestFocusInWindow());


        goku = new Goku(500, 1000);
        vegeta = new Vegeta(1700, 850,gokuposY);

        timer = new Timer(10, this);
        timer.start();

        timerFimJogo = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(rodarFimJogo){
                    if(vitoriaSom1){
                        vitoriaSom.tocarSom("/sons/goku/kamehamehaSom.wav");
                        vitoriaSom1 = false;
                        vitoriaSom2 = true;
                    }
                    if(vitoriaSom.terminou() && vitoriaSom2){
                        vitoriaSom.tocarSom("/sons/goku/kamehamehaSomFim.wav");
                        vitoriaSom2 = false;
                    }
                    if(indiceFimJogo >= fimJogo.size() - 1){
                        timerFimJogo.stop();
                    }else{
                        indiceFimJogo ++;
                    }
                }
            }

        });
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);



        // Desenha o Goku (se não estiver no especial do Ultra Instinto)
        if (!goku.AtivarEspecialUltraInstinto) {
            goku.desenharGoku(g);
        }

        vegeta.desenharVegeta(g);
        vegeta.desenharVida(g);
        goku.desenharVida(g);

        vegeta.desenharEspecial(g, goku);

        goku.desenharEspecialEnergia(g,vegeta);
        // O impacto fica por cima do Kamehameha e permanece no ponto do acerto.
        vegeta.desenharExplosaoAoSerAtingido(g);
        goku.desenharBarraKi(g);
        vegeta.desenharBarraKi(g);

        // Desenha o especial do Ultra Instinto (se ativo)
        if (goku.AtivarEspecialUltraInstinto) {
            goku.ExecutarEspecialUltraInstinto(g, vegeta);
        }

        if(rodarFimJogo){
            mutar = true;
            g.drawImage(fimJogo.get(indiceFimJogo),0,0,1920,1080,null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if(e.getKeyCode() == KeyEvent.VK_1){

            if (musicaASerTocada > 6) {
                musicaASerTocada = 1;
            }else{
                musicaASerTocada++;
                somFundo.parar();
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            if(!mutar){
                mutar = true;
                somFundo.parar();
            }else{
                mutar = false;
            }
        }

        if( goku.Transformando || goku.TransformandoBlue || goku.TransformandoDeus || goku.TransformandoInstintoIncompleto  || goku.TransformandoUltraInstinto || goku.ativarEspecial || !podeMover || goku.vida <= 0 || vegeta.vegetaVida <= 0){
            return;
        }

        // Goku
        if (e.getKeyCode() == KeyEvent.VK_D)
            direita = true;

        if (e.getKeyCode() == KeyEvent.VK_A)
            esquerda = true;

        if (e.getKeyCode() == KeyEvent.VK_W)
            cima = true;

        if (e.getKeyCode() == KeyEvent.VK_S)
            baixo = true;

        // Goku - Ki
        if (e.getKeyCode() == KeyEvent.VK_E ) {
            if(grito.terminou() && podeGritar){
                grito.tocarSom("/sons/grito.wav");
                podeGritar = false;
            }
            elevarKiGoku = true;
            vegeta.iniciarKi();

        }

        // Goku - Ataque normal
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!espacoPressionado) {
                espacoPressionado = true;
                goku.InciarAtaque();
            }

        }

        // Goku - Especial (tecla F para todas as formas)
        if(e.getKeyCode() == KeyEvent.VK_F){
            if(goku.especialKi >= 400){

                if (goku.isUltraInstintoAtivo()){
                    goku.IniciarEspecialUltraInstinto();
                } else {
                    goku.ativarEspecial = true;
                }
                goku.especialKi = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // Goku
        if (e.getKeyCode() == KeyEvent.VK_D)
            direita = false;

        if (e.getKeyCode() == KeyEvent.VK_A)
            esquerda = false;

        if (e.getKeyCode() == KeyEvent.VK_W)
            cima = false;

        if (e.getKeyCode() == KeyEvent.VK_S)
            baixo = false;

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            espacoPressionado = false;
        }

        // Goku
        if (e.getKeyCode() == KeyEvent.VK_E) {
            podeGritar = true;
            elevarKiGoku = false;
            goku.SoltarKi();
            vegeta.soltarKi();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não usado
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(goku.ativarEspecial || goku.AtivarEspecialUltraInstinto || vegeta.ativarEspecial ){
            fundo = fundoEspecial;
        }else{
            fundo = fundoNormal;
        }


        if(goku.vida <= 0 && goku.vida >= -9 && finalizarOponente){
            podeMover = false;
            vegeta.ativarEspecial = true;
            finalizarOponente = false;
        }else if(goku.vida > 0){
            podeMover = true;
        }

        if(vegeta.vegetaVida <= 0 && vegeta.vegetaVida >= -9 && finalizarOponente){
            podeMover = false;
            goku.ativarEspecial = true;
            finalizarOponente = false;
        }else if(vegeta.vegetaVida > 0){
            podeMover = true;
        }

        if(goku.vida <= 0 && vegeta.vegetaVida <= 0){
            goku.vida += 100;
            vegeta.vegetaVida += 100;

        }else if(vegeta.vegetaVida <= -10){
            derrota = false;
            vitoria = true;
            carregar = true;

        }else if(goku.vida <= -10){
            derrota = true;
            vitoria = false;
            carregar = true;

        }

        if(pararMusica){
            somFundo.parar();
            grito.parar();
            pararMusica = false;
        }

        if(carregar){
            if(vitoria){
                fimJogo = carregarFrames("/fundo/ganha",224);
            }else if(derrota){
                fimJogo = carregarFrames("/fundo/perde",141);
            }
            carregar = false;
            rodarFimJogo = true;
            goku.timerEspecialInstintoIncompleto.stop();
            goku.timerEspecialInstintoIncompleto.stop();
            somFundo.parar();
            mutar = true;
            vegeta.timer.stop();
            timerFimJogo.start();
        }

        if(!mutar){
            if(musicaASerTocada == 1 && somFundo.terminou()){
                somFundo.parar();
                somFundo.tocarSom("/fundo/musicasFundo/fundo1.wav");
                somFundo.setVolume(1.0f);
            }else if(musicaASerTocada == 2 && somFundo.terminou()){
                somFundo.parar();
                somFundo.tocarSom("/fundo/musicasFundo/fundo2.wav");
                somFundo.setVolume(1.5f);

            }else if(musicaASerTocada == 3 && somFundo.terminou()) {
                somFundo.parar();
                somFundo.tocarSom("/fundo/musicasFundo/fundo3.wav");
                somFundo.setVolume(1.5f);

            }else if(musicaASerTocada == 4 && somFundo.terminou()){
                somFundo.parar();
                somFundo.tocarSom("/fundo/musicasFundo/fundo4.wav");
                somFundo.setVolume(2.0f);

            }else if(musicaASerTocada == 5 && somFundo.terminou()){
                somFundo.parar();
                somFundo.tocarSom("/fundo/musicasFundo/fundo5.wav");
                somFundo.setVolume(1.0f);

            }else if(musicaASerTocada == 6 && somFundo.terminou()){
                somFundo.parar();
                somFundo.tocarSom("/fundo/musicasFundo/fundo6.wav");
                somFundo.setVolume(1.5f);
            }
        }

        if(goku.ativarEspecial || vegeta.ativarEspecial || vegeta.explodindo){
            somFundo.setVolume(0.5f);
        }else{
            somFundo.setVolume(1.5f);
        }
        // Atualiza Vegeta
        vegeta.atualizar();



    // Movimento do Goku
        if (direita){
            goku.posX += 20;
        }

        if (esquerda){
            goku.posX -= 20;
        }

        if (cima){
            goku.posY -= 20;
        }

        if (baixo){
            goku.posY += 20;
        }

            // Limites
        if (goku.posX < 0)
            goku.posX = 0;


        if (goku.posX > getWidth() - 200){
            goku.posX = getWidth() - 200;
        }

        if (goku.posY < 0){
            goku.posY = 0;}

        if (goku.posY > getHeight() - 200){
            goku.posY = getHeight() - 200;
        }
        // Animação do Goku
        if (goku.TransformarUltraInstinto()) {
            goku.ExecutarTransformacaoUltraInstinto();

        } else if (goku.TransformarInstintoIncompleto()) {
            goku.ExecutarTransformacaoInstintoIncompleto();

        } else if (goku.TransformarBlue()) {
            goku.ExecutarTransformacaoBlue();

        } else if (goku.TransformarDeus()) {
            goku.ExecutarTransformacaoDeus();

        } else if (goku.Transformar()) {
            goku.ExecutarTransformacao();

        } else if (goku.Atacando()) {
            goku.ExecutarAtaque(vegeta);

        } else if (elevarKiGoku) {
            goku.ElevarKi();

        } else if (direita) {
            goku.spriteFrente();

        } else if (esquerda) {
            goku.spriteTras();

        } else if (cima) {
            goku.spriteCima();

        } else if (baixo) {
            goku.spriteCima();

        } else {
            goku.spriteParado();
        }

        // IA do Vegeta
        vegeta.atualizarIa(goku);

        repaint();
    }

    private ArrayList<Image> carregarFrames(String pasta, int total) {
        ArrayList<Image> lista = new ArrayList<Image>();

        for (int i = 1; i <= total; i++) {
            // Os frames de fim de jogo sao de tela cheia e muitos; ImageIcon
            // carrega de forma preguicosa, o que evita estourar a heap.
            lista.add(new ImageIcon(getClass().getResource(pasta + "/(" + i + ").jpg")).getImage());
        }

        return lista;
    }

    private Image load(String caminho) {
        // Imagens de tela cheia (fundo, fim de jogo) ficam em 1920 de largura;
        // barras e imagens pequenas em 256. Reduzir na carga economiza memoria.
        return load(caminho, caminho.contains("fundo") ? 1920 : 256);
    }

    // Carrega a imagem e, se for maior que maxLado, redimensiona
    // proporcionalmente para o maior lado ficar igual a maxLado.
    private Image load(String caminho, int maxLado) {
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

            BufferedImage reduzida = new BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = reduzida.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(original, 0, 0, novaLargura, novaAltura, null);
            g2.dispose();
            return reduzida;
        } catch (Exception e) {
            // Se algo falhar, volta ao carregamento simples para nao travar o jogo.
            return new ImageIcon(getClass().getResource(caminho)).getImage();
        }
    }
}
