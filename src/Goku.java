import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Goku {

    // Efeitos usados nas formas Base, SSJ, Deus e Blue.
    private final ArrayList<Image> anelEspecial = new ArrayList<>();
    private final ArrayList<Image> poeiraEspecial = new ArrayList<>();
    private final ArrayList<Image> poeiraKi = new ArrayList<>();
    private final ArrayList<Image> golpes = new ArrayList<>();
    private int indiceAnelEspecial = 0;
    private int indicePoeiraEspecial = 0;
    private int indicePoeiraKi = 0;
    private int indiceGolpe = 0;
    private int contadorGolpe = 0;
    private int contadorPoeiraKi = 0;
    private boolean mostrarAnelEspecial = true;
    private boolean mostrarPoeiraEspecial = true;
    // Contador de frames que anima o anel e a poeira no ritmo do desenho.
    private int contadorAnelEspecial = 0;
    private boolean mostrarGolpe = false;
    private boolean elevandoKi = false;

    public int posX;
    public int posY;

    public int vida = 100;
    // Sprites (movimentação)
    private Image parado;
    private Image frente;
    private Image tras;
    private Image cima;
    private Image barraVida = load("bv.png");

    //GOKU SSJ MOVIMENTOS
    private Image ParadoSsj;
    private Image FrenteSsj;
    private Image TrasSsj;
    private Image CimaSsj;

    //GOKU DEUS MOVIMENTOS
    private Image ParadoDeus;
    private Image FrenteDeus;
    private Image TrasDeus;
    private Image CimaDeus;

    //GOKU BLUE MOVIMENTOS
    private Image ParadoBlue;
    private Image FrenteBlue;
    private Image TrasBlue;
    private Image CimaBlue;

    //GOKU INSTINTO INCOMPLETO MOVIMENTO
    private Image ParadoInstintoIncompleto;
    private Image FrenteInstintoIncompleto;
    private Image TrasInstintoIncompleto;
    private Image CimaInstintoIncompleto;

    //GOKU ULTRA INSTINTO MOVIMENTO
    private Image ParadoUltraInstinto;
    private Image FrenteUltraInstinto;
    private Image TrasUltraInstinto;
    private Image CimaUltraInstinto;

    //BASE PARA SSJ - TRANSFORMAÇÃO
    private int IndiceTransformacao = 0;
    private int ContadorTransformacao = 0;
    private final int AtrasoTransformacao = 5;
    public boolean Transformando = false;
    public boolean Transformar() {
        return Transformando;
    }
    private boolean SsjAtivo = false;

    //SSJ PARA DEUS - TRANSFORMAÇÃO
    private final List<Image> TransformacaoSsjParaDeus = new ArrayList<>();
    private int IndiceTransformacaoDeus = 0;
    private int ContadorTransformacaoDeus = 0;
    private final int AtrasoTransformacaoDeus = 5;
    public boolean TransformandoDeus = false;
    public boolean TransformarDeus() {
        return TransformandoDeus;
    }
    private boolean DeusAtivo = false;

    private int NivelKiDeus = 0;
    private final int NivelKiMaximoDeus = 800;

    //ATAQUES
    private int IndiceSeq = 0;
    private int IndiceFrame = 0;
    private boolean Atacando = false;
    public boolean Atacando() {
        return Atacando;
    }
    private int ContadorAtaque = 0;
    private final int AtrasoAtaque = 6;

    // Controle de dano do ataque
    private boolean tirarVidaVegeta = false;
    private int forcaAtaque = 1; // Dano base do soco
    private double especialDano = 20; // Dano base do especial

    // Ki (elevar)
    private final List<Image> Elevar = new ArrayList<>();
    private int IndiceKi = 0;
    private int NivelKi = 0;
    private final int NivelKiMaximo = 600;

    //SSJ KI
    private final List<Image> ElevarSsj = new ArrayList<>();

    //DEUS KI
    private final List<Image> ElevarDeus = new ArrayList<>();

    //BLUE KI
    private final List<Image> ElevarKiBlue = new ArrayList<>();

    //INSTINTO COMPLETO KI
    private final List<Image> ElevarKiInstintoIncompleto = new ArrayList<>();

    //ULTRA INSTINTO KI
    private final List<Image> ElevarKiUltraInstinto = new ArrayList<>();

    // Ataques (ataques/sequenciaX/(i).png)
    private final List<Image> sequencia1 = new ArrayList<>();
    private final List<Image> sequencia2 = new ArrayList<>();
    private final List<Image> sequencia3 = new ArrayList<>();
    private final List<Image> sequencia4 = new ArrayList<>();
    private final List<Image> sequencia5 = new ArrayList<>();
    private final List<Image> sequencia6 = new ArrayList<>();

    // ATAQUES SSJ
    private final List<Image> SsjSequencia1 = new ArrayList<>();
    private final List<Image> SsjSequencia2 = new ArrayList<>();
    private final List<Image> SsjSequencia3 = new ArrayList<>();
    private final List<Image> SsjSequencia4 = new ArrayList<>();
    private final List<Image> SsjSequencia5 = new ArrayList<>();
    private final List<Image> SsjSequencia6 = new ArrayList<>();
    private final List<Image> SsjSequencia7 = new ArrayList<>();

    //ATAQUES DEUS
    private final List<Image> DeusSequencia1 = new ArrayList<>();
    private final List<Image> DeusSequencia2 = new ArrayList<>();
    private final List<Image> DeusSequencia3 = new ArrayList<>();
    private final List<Image> DeusSequencia4 = new ArrayList<>();
    private final List<Image> DeusSequencia5 = new ArrayList<>();
    private final List<Image> DeusSequencia6 = new ArrayList<>();

    //ATAQUES BLUE
    private final List<Image> BlueSequencia1 = new ArrayList<>();
    private final List<Image> BlueSequencia2 = new ArrayList<>();
    private final List<Image> BlueSequencia3 = new ArrayList<>();
    private final List<Image> BlueSequencia4 = new ArrayList<>();

    //ATAQUES INSTINTO INCOMPLETO
    private final List<Image> InstintoIncompletoSequencia1 = new ArrayList<>();
    private final List<Image> InstintoIncompletoSequencia2 = new ArrayList<>();
    private final List<Image> InstintoIncompletoSequencia3 = new ArrayList<>();
    private final List<Image> InstintoIncompletoSequencia4 = new ArrayList<>();
    private final List<Image> InstintoIncompletoSequencia5 = new ArrayList<>();
    private final List<Image> InstintoIncompletoSequencia6 = new ArrayList<>();
    private final List<Image> InstintoIncompletoSequencia7 = new ArrayList<>();

    //ATAQUES ULTRA INSTINTO
    private final List<Image> UltraInstintoSequencia1 = new ArrayList<>();
    private final List<Image> UltraInstintoSequencia2 = new ArrayList<>();
    private final List<Image> UltraInstintoSequencia3 = new ArrayList<>();
    private final List<Image> UltraInstintoSequencia4 = new ArrayList<>();
    private final List<Image> UltraInstintoSequencia5 = new ArrayList<>();
    private final List<Image> UltraInstintoSequencia6 = new ArrayList<>();
    private final List<Image> UltraInstintoSequencia7 = new ArrayList<>();
    private final List<Image> UltraInstintoSequencia8 = new ArrayList<>();

    //GOKU BASE PARA SSJ - TRANSFORMAÇÃO
    private final List<Image> TransformacaoBaseParaSsj = new ArrayList<>();

    //DEUS PARA BLUE - TRANSFORMAÇÃO
    private final List<Image> TransformacaoDeusParaBlue = new ArrayList<>();
    private int IndiceTransformacaoBlue = 0;
    private int ContadorTransformacaoBlue = 0;
    private final int AtrasoTransformacaoBlue = 5;
    public boolean TransformandoBlue = false;
    public boolean TransformarBlue() {
        return TransformandoBlue;
    }
    private int NivelKiBlue = 0;
    private final int NivelKiMaximoBlue = 800;
    private boolean BlueAtivo = false;

    //BLUE PARA INSTINTO INCOMPLETO - TRANSFORMAÇÃO
    private final List<Image> TransformacaoBlueParaInstintoIncompleto = new ArrayList<>();
    private int IndiceTransformacaoInstintoIncompleto = 0;
    private int ContadorTransformacaoInstintoIncompleto = 0;

    private final int AtrasoTransformacaoInstintoIncompleto = 5;
    public boolean TransformandoInstintoIncompleto = false;
    public boolean TransformarInstintoIncompleto() {
        return TransformandoInstintoIncompleto;
    }
    private int NivelKiInstintoIncompleto = 0;
    private final int NivelKiMaximoInstintoIncompleto = 800;
    public boolean InstintoIncompletoAtivo = false;


    //INSTINTO INCOMPLETO PARA ULTRA INSTINTO - TRANSFORMAÇÃO
    private final List<Image> TransformacaoInstintoIncompletoParaUltraInstinto = new ArrayList<>();
    private int IndiceTransformacaoUltraInstinto = 0;
    private int ContadorTransformacaoUltraInstinto = 0;
    private final int AtrasoTransformacaoUltraInstinto = 5;
    public boolean TransformandoUltraInstinto = false;
    public boolean TransformarUltraInstinto() {
        return TransformandoUltraInstinto;
    }
    private int NivelKiUltraInstinto = 0;
    private final int NivelKiMaximoUltraInstinto = 600;
    public boolean UltraInstintoAtivo = false;

    // Controle velocidade
    private int velocidade = 5;

    // Sprite atual
    private Image spriteAtual;

    // SONS
    private final Som somSoco;
    private final Som somKi;
    private final Som somKiInicio;
    private boolean somKiTocando = false;


    //Especial
    private ArrayList<Image> especialSpritesBase = new ArrayList<>();
    private ArrayList<Image> especialSpritesSsj = new ArrayList<>();
    private ArrayList<Image> especialSpritesDeus = new ArrayList<>();
    private ArrayList<Image> especialSpritesBlue = new ArrayList<>();
    private ArrayList<Image> especialSpritesInstintoIncompleto = new ArrayList<>();
    private final ArrayList<Image> especialEnergiaInstintoIncompleto = new ArrayList<>();
    private int indiceEspecialInstintoIncompleto = 0;
    private boolean especialInstintoIncompletoAcertou = false;

    public Timer timerEspecialInstintoIncompleto;
    public Timer timerEspecial;

    public boolean ativarEspecial = false;
    public boolean especialEnergia = false;
    private boolean pararSom = true;

    private Som kameVoz;
    private Som kameSom;
    private Som kameFim;

    private Image barraKi;

    private boolean podeTocarSonsEspecial = true;
    private boolean podeTocarFimKame = false;
    // O especial so avanca para os frames finais (e solta a energia)
    // quando a voz do kamehameha chega nesse tempo (em microsegundos).
    private static final long TEMPO_DISPARO_KAME = 2700000L; // 2,7 segundos
    private boolean vozIniciada = false;

    private int energiaX = 0;

    private int indiceEspecial = 0;
    //energia
    private ArrayList<Image> especialSprites = new ArrayList<>();
    private Image energiaInicio;
    private Image energiaMeio;
    private Image energiaFim;
    public Timer timerEnergia;
    private Image energiaFundo;

    // ESPECIAL ULTRA INSTINTO COMPLETO
    private final List<Image> EspecialUltraInstinto = new ArrayList<>();
    private int IndiceEspecialUltraInstinto = 0;
    private int ContadorEspecialUltraInstinto = 0;
    private final int AtrasoEspecialUltraInstinto = 4;

    public boolean AtivarEspecialUltraInstinto = false;
    private boolean IndoParaEspecialUltraInstinto = false;
    private boolean PosicionadoEspecialUltraInstinto = false;
    private final int VelocidadeEspecialUltraInstinto = 18;
    private final int DistanciaEspecialUltraInstinto = 140;




    public int especialKi = 0;

    // Getter para verificar se está no Ultra Instinto
    public boolean isUltraInstintoAtivo() {
        return UltraInstintoAtivo;
    }

    public Goku(int posX, int posY) {

        this.posX = posX;
        this.posY = posY;

        // Inicializa os sons
        kameFim = new Som();
        kameSom = new Som();
        kameVoz = new Som();


        kameFim.tocarSom("/sons/goku/kamehamehaSomFim.wav");
        kameSom.tocarSom("/sons/goku/kamehamehaSom.wav");
        kameVoz.tocarSom("/sons/goku/kamehamehaVoz.wav");


        somSoco = new Som();
        somKi = new Som();
        somKiInicio = new Som();
        somKi.setVolume(0.0f);
        somKi.tocarSom("/sons/ki.wav");
        somKi.parar();
        somKi.setVolume(1.5f);
        somKiInicio.setVolume(0.0f);
        somKiInicio.tocarSom("/sons/kiInicio.wav");
        somKiInicio.parar();
        somKiInicio.setVolume(1.0f);


        kameVoz.setVolume(0f);
        kameFim.setVolume(0f);

        especialSprites = especialSpritesBase;
        //carregar especiais

        energiaMeio = load("goku/gokuBase/especial/energia/meio1.png");
        energiaInicio = load("goku/gokuBase/especial/energia/inicio1.png");
        energiaFim = load("goku/gokuBase/especial/energia/Fim1.png");

        barraKi = load("barraKi.png");

        //base
        for(int i = 1; i <= 9; i++ ){
            especialSpritesBase.add(load("goku/gokuBase/especial/(" + i + ").png"));
        }

        //ssj
        for(int i = 1; i <= 8; i++ ){
            especialSpritesSsj.add(load("goku/gokuSsj/especial/(" + i + ").png"));
        }

        //deus
        for(int i = 1; i <= 6; i++ ){
            especialSpritesDeus.add(load("goku/gokuDeus/especial/(" + i + ").png"));
        }

        //blue
        for(int i = 1; i <= 6; i++ ){
            especialSpritesBlue.add(load("goku/gokuBlue/especial/(" + i + ").png"));
        }

        //INSTINTO INCOMPLETO ESPECIAL
        for(int i = 1; i <= 3; i++ ){
            especialSpritesInstintoIncompleto.add(load("goku/gokuInstintoIncompleto/especial/(" + i + ").png"));
        }

        //Energia
        for(int i = 1; i <= 5;i++){
            especialEnergiaInstintoIncompleto.add(load("goku/gokuInstintoIncompleto/especial/energia/(" + i + ").png"));
        }

        // ESPECIAL ULTRA INSTINTO COMPLETO
        for (int i = 1; i <= 23; i++) {
            EspecialUltraInstinto.add(load("/goku/gokuUltraInstinto/especial/(" + i + ").png"));
        }

        for (int i = 1; i <= 4; i++) {
            anelEspecial.add(load("/efeitosGraficos/naoSeiONomeDisso/(" + i + ").png"));
        }
        for (int i = 1; i <= 5; i++) {
            poeiraEspecial.add(load("/efeitosGraficos/poeiraEspecial/(" + i + ").png"));
        }
        for (int i = 1; i <= 6; i++) {
            poeiraKi.add(load("/efeitosGraficos/poeiraKi/(" + i + ").png"));
        }
        for (int i = 1; i <= 4; i++) {
            golpes.add(load("/efeitosGraficos/golpes/(" + i + ").png"));
        }
        // ===== TIMER DO ESPECIAL (APENAS UM) =====
        timerEspecial = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se o especial já foi parado, não faz nada
                if (!ativarEspecial && !especialEnergia ) {
                    return;
                }

                if (pararSom) {
                    kameFim.parar();
                    kameSom.parar();

                    kameVoz.setVolume(2.0f);
                    kameFim.setVolume(1.0f);
                    pararSom = false;
                }

                if (ativarEspecial) {
                    if (indiceEspecial < especialSprites.size()) {
                        indiceEspecial++;
                    }
                } else {
                    indiceEspecial = 0;
                    especialEnergia = false;
                }

                if (especialEnergia && !InstintoIncompletoAtivo && !UltraInstintoAtivo) {
                    // A animacao do anel e da poeira agora acontece em
                    // desenharEspecialEnergia, no ritmo do desenho.
                }

                if (!especialEnergia) {
                    energiaX = 0;
                    if (timerEnergia != null) {
                        timerEnergia.stop();
                    }
                }
            }
        });
        timerEspecial.start();

        // ===== TIMER DA ENERGIA =====
        timerEnergia = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                energiaX += 50;
            }
        });

        // ===== TIMER INSTINTO INCOMPLETO =====
        timerEspecialInstintoIncompleto = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ativarEspecial && InstintoIncompletoAtivo) {
                    if (indiceEspecialInstintoIncompleto < especialEnergiaInstintoIncompleto.size() - 1) {
                        indiceEspecialInstintoIncompleto++;
                    } else {
                        ativarEspecial = false;
                        especialEnergia = false;
                        especialInstintoIncompletoAcertou = false;
                        timerEspecialInstintoIncompleto.stop();
                        indiceEspecialInstintoIncompleto = 0;
                    }
                }
            }
        });

        // Carregar os sprites

        energiaFundo = load("/goku/gokuBase/especial/energia/fundoKame.png");
        parado = load ("/goku/gokuBase/movimentos/parado.png");

        frente = load("/goku/gokuBase/movimentos/frente.png");

        tras = load("/goku/gokuBase/movimentos/tras.png");

        cima = load("/goku/gokuBase/movimentos/cima.png");

        //GOKU BASE PARA SSJ - TRANSFORMAÇÃO
        for (int i = 1; i <= 24; i++) {TransformacaoBaseParaSsj.add(load("/goku/gokuSsj/transformar/(" + i + ").png"));
        }

        //GOKU SSJ - MOVIMENTOS
        ParadoSsj = load("/goku/gokuSsj/movimentos/parado.png");

        FrenteSsj = load("/goku/gokuSsj/movimentos/frente.png");

        TrasSsj = load("/goku/gokuSsj/movimentos/tras.png");

        CimaSsj = load("/goku/gokuSsj/movimentos/cima.png");

        //DEUS MOVIMENTOS
        ParadoDeus = load("/goku/gokuDeus/movimentos/parado.png");

        FrenteDeus = load("/goku/gokuDeus/movimentos/frente.png");

        TrasDeus = load("/goku/gokuDeus/movimentos/tras.png");

        CimaDeus = load("/goku/gokuDeus/movimentos/cima.png");

        //BLUE MOVIMENTOS
        ParadoBlue = load("/goku/gokuBlue/movimentos/parado.png");

        FrenteBlue = load("/goku/gokuBlue/movimentos/frente.png");

        TrasBlue = load("/goku/gokuBlue/movimentos/tras.png");

        CimaBlue = load("/goku/gokuBlue/movimentos/cima.png");

        //INSTINTO INCOMPLETO MOVIMENTOS
        ParadoInstintoIncompleto = load("/goku/gokuInstintoIncompleto/movimentos/parado.png");

        FrenteInstintoIncompleto = load("/goku/gokuInstintoIncompleto/movimentos/frente.png");

        TrasInstintoIncompleto = load("/goku/gokuInstintoIncompleto/movimentos/tras.png");

        CimaInstintoIncompleto = load("/goku/gokuInstintoIncompleto/movimentos/cima.png");

        //ULTRA INSTINTO MOVIMENTOS
        ParadoUltraInstinto = load("/goku/gokuUltraInstinto/movimentos/parado.png");

        FrenteUltraInstinto = load("/goku/gokuUltraInstinto/movimentos/frente.png");

        TrasUltraInstinto = load("/goku/gokuUltraInstinto/movimentos/tras.png");

        CimaUltraInstinto = load("/goku/gokuUltraInstinto/movimentos/cima.png");

        // Ki (frames)
        for (int i = 1; i <= 10; i++) {
            Elevar.add(load("/goku/gokuBase/ki/(" + i + ").png"));
        }

        // Ataques base (sequencia1..6)
        for (int i = 1; i <= 3; i++) sequencia1.add(load("/goku/gokuBase/ataques/sequencia1/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) sequencia2.add(load("/goku/gokuBase/ataques/sequencia2/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) sequencia3.add(load("/goku/gokuBase/ataques/sequencia3/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) sequencia4.add(load("/goku/gokuBase/ataques/sequencia4/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) sequencia5.add(load("/goku/gokuBase/ataques/sequencia5/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) sequencia6.add(load("/goku/gokuBase/ataques/sequencia6/(" + i + ").png"));

        //SSJ ATAQUES (FRAMES)
        for (int i = 1; i <= 3; i++) SsjSequencia1.add(load("/goku/gokuSsj/ataques/sequencia1/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) SsjSequencia2.add(load("/goku/gokuSsj/ataques/sequencia2/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) SsjSequencia3.add(load("/goku/gokuSsj/ataques/sequencia3/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) SsjSequencia4.add(load("/goku/gokuSsj/ataques/sequencia4/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) SsjSequencia5.add(load("/goku/gokuSsj/ataques/sequencia5/(" + i + ").png"));
        for (int i = 1; i <= 5; i++) SsjSequencia6.add(load("/goku/gokuSsj/ataques/sequencia6/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) SsjSequencia7.add(load("/goku/gokuSsj/ataques/sequencia7/(" + i + ").png"));

        //INSTINTO INCOMPLETO ATAQUES
        for (int i = 1; i <= 4; i++) InstintoIncompletoSequencia1.add(load("/goku/gokuInstintoIncompleto/ataques/sequencia1/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) InstintoIncompletoSequencia2.add(load("/goku/gokuInstintoIncompleto/ataques/sequencia2/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) InstintoIncompletoSequencia3.add(load("/goku/gokuInstintoIncompleto/ataques/sequencia3/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) InstintoIncompletoSequencia4.add(load("/goku/gokuInstintoIncompleto/ataques/sequencia4/(" + i + ").png"));
        for (int i = 1; i <= 4; i++) InstintoIncompletoSequencia5.add(load("/goku/gokuInstintoIncompleto/ataques/sequencia5/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) InstintoIncompletoSequencia6.add(load("/goku/gokuInstintoIncompleto/ataques/sequencia6/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) InstintoIncompletoSequencia7.add(load("/goku/gokuInstintoIncompleto/ataques/sequencia7/(" + i + ").png"));

        //SSJ PARA DEUS - TRANSFORMAÇÃO
        for (int i = 1; i <= 15; i++) {
            TransformacaoSsjParaDeus.add(load("/goku/gokuDeus/transformar/(" + i + ").png"));
        }

        //DEUS PARA BLUE - TRANSFORMAÇÃO
        for (int i = 1; i <= 13; i++) {
            TransformacaoDeusParaBlue.add(load("/goku/gokuBlue/transformar/(" + i + ").png"));
        }

        //BLUE PARA INSTINTO INCOMPLETO - TRANSFORMAÇÃO
        for (int i = 1; i <= 17; i++) {
            TransformacaoBlueParaInstintoIncompleto.add(load("/goku/gokuInstintoIncompleto/transformar/(" + i + ").png"));
        }

        //INSTINTO INCOMPLETO PARA ULTRA INSTINTO - TRANSFORMAÇÃO
        for (int i = 1; i <= 40; i++) {
            TransformacaoInstintoIncompletoParaUltraInstinto.add(load("/goku/gokuUltraInstinto/transformar/(" + i + ").png"));
        }

        //DEUS ATAQUES
        for (int i = 1; i <= 3; i++) DeusSequencia1.add(load("/goku/gokuDeus/ataques/sequencia1/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) DeusSequencia2.add(load("/goku/gokuDeus/ataques/sequencia2/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) DeusSequencia3.add(load("/goku/gokuDeus/ataques/sequencia3/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) DeusSequencia4.add(load("/goku/gokuDeus/ataques/sequencia4/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) DeusSequencia5.add(load("/goku/gokuDeus/ataques/sequencia5/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) DeusSequencia6.add(load("/goku/gokuDeus/ataques/sequencia6/(" + i + ").png"));

        //BLUE ATAQUES
        for (int i = 1; i <= 3; i++) BlueSequencia1.add(load("/goku/gokuBlue/ataques/sequencia1/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) BlueSequencia2.add(load("/goku/gokuBlue/ataques/sequencia2/(" + i + ").png"));
        for (int i = 1; i <= 2; i++) BlueSequencia3.add(load("/goku/gokuBlue/ataques/sequencia3/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) BlueSequencia4.add(load("/goku/gokuBlue/ataques/sequencia4/(" + i + ").png"));

        //ULTRA INSTINTO ATAQUES
        for (int i = 1; i <= 6; i++) UltraInstintoSequencia1.add(load("/goku/gokuUltraInstinto/ataques/sequencia1/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) UltraInstintoSequencia2.add(load("/goku/gokuUltraInstinto/ataques/sequencia2/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) UltraInstintoSequencia3.add(load("/goku/gokuUltraInstinto/ataques/sequencia3/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) UltraInstintoSequencia4.add(load("/goku/gokuUltraInstinto/ataques/sequencia4/(" + i + ").png"));
        for (int i = 1; i <= 3; i++) UltraInstintoSequencia5.add(load("/goku/gokuUltraInstinto/ataques/sequencia5/(" + i + ").png"));
        for (int i = 1; i <= 5; i++) UltraInstintoSequencia6.add(load("/goku/gokuUltraInstinto/ataques/sequencia6/(" + i + ").png"));
        for (int i = 1; i <= 5; i++) UltraInstintoSequencia7.add(load("/goku/gokuUltraInstinto/ataques/sequencia7/(" + i + ").png"));
        for (int i = 1; i <= 4; i++) UltraInstintoSequencia8.add(load("/goku/gokuUltraInstinto/ataques/sequencia8/(" + i + ").png"));

        //SSJ KI
        for (int i = 1; i <= 5; i++) ElevarSsj.add(load("/goku/gokuSsj/ki/(" + i + ").png"));

        //DEUS KI
        for (int i = 1; i <= 4; i++) ElevarDeus.add(load("/goku/gokuDeus/ki/(" + i + ").png"));

        //BLUE KI
        for (int i = 1; i <= 2; i++) ElevarKiBlue.add(load("/goku/gokuBlue/ki/(" + i + ").png"));

        //INCOMPLETO KI
        for (int i = 1; i <= 3; i++) ElevarKiInstintoIncompleto.add(load("/goku/gokuInstintoIncompleto/ki/(" + i + ").png"));

        //ULTRA INSTINTO KI
        for (int i = 1; i <= 4; i++) ElevarKiUltraInstinto.add(load("/goku/gokuUltraInstinto/ki/(" + i + ").png"));

        spriteAtual = parado;
    }

    // Métodos para alterar o sprite atual
    public void spriteParado() {

        if (UltraInstintoAtivo) {
            spriteAtual = ParadoUltraInstinto;
        } else if (InstintoIncompletoAtivo) {
            spriteAtual = ParadoInstintoIncompleto;
        } else if (BlueAtivo) {
            spriteAtual = ParadoBlue;

        } else if (DeusAtivo) {
            spriteAtual = ParadoDeus;

        } else if (SsjAtivo) {
            spriteAtual = ParadoSsj;

        } else {
            spriteAtual = parado;
        }
    }

    public void spriteFrente() {
        posX += velocidade;

        if (UltraInstintoAtivo) {
            spriteAtual = FrenteUltraInstinto;
        } else if (InstintoIncompletoAtivo) {
            spriteAtual = FrenteInstintoIncompleto;

        } else if (BlueAtivo) {
            spriteAtual = FrenteBlue;

        } else if (DeusAtivo) {
            spriteAtual = FrenteDeus;

        } else if (SsjAtivo) {
            spriteAtual = FrenteSsj;

        } else {
            spriteAtual = frente;
        }
    }

    public void spriteTras() {
        posX -= velocidade;

        if (UltraInstintoAtivo) {
            spriteAtual = TrasUltraInstinto;
        } else if (InstintoIncompletoAtivo) {
            spriteAtual = TrasInstintoIncompleto;

        } else if (BlueAtivo) {
            spriteAtual = TrasBlue;

        } else if (DeusAtivo) {
            spriteAtual = TrasDeus;

        } else if (SsjAtivo) {
            spriteAtual = TrasSsj;

        } else {
            spriteAtual = tras;
        }
    }

    public void spriteCima() {
        posY -= velocidade;

        if (UltraInstintoAtivo) {
            spriteAtual = CimaUltraInstinto;
        } else if (InstintoIncompletoAtivo) {
            spriteAtual = CimaInstintoIncompleto;

        } else if (BlueAtivo) {
            spriteAtual = CimaBlue;

        } else if (DeusAtivo) {
            spriteAtual = CimaDeus;

        } else if (SsjAtivo) {
            spriteAtual = CimaSsj;

        } else {
            spriteAtual = cima;
        }
    }

    public void PararEspecial() {
        // Para o timer principal do especial
        if (timerEspecial != null && timerEspecial.isRunning()) {
            timerEspecial.stop();
            // Reinicia o timer para poder ser usado novamente
            timerEspecial.start();
        }

        // Para o timer da energia
        if (timerEnergia != null && timerEnergia.isRunning()) {
            timerEnergia.stop();
        }

        // Para o timer do instinto incompleto
        if (timerEspecialInstintoIncompleto != null && timerEspecialInstintoIncompleto.isRunning()) {
            timerEspecialInstintoIncompleto.stop();
        }

        ativarEspecial = false;
        especialEnergia = false;
        indiceEspecial = 0;
        energiaX = 0;
        podeTocarSonsEspecial = true;
        pararSom = true;
        vozIniciada = false;
        especialInstintoIncompletoAcertou = false;
        indiceEspecialInstintoIncompleto = 0;
        mostrarAnelEspecial = true;
        mostrarPoeiraEspecial = true;
        indiceAnelEspecial = 0;
        indicePoeiraEspecial = 0;
        contadorAnelEspecial = 0;

        if (!podeTocarFimKame) {
            podeTocarFimKame = true;
        }

        // Volta ao sprite normal
        spriteParado();
    }

    public void desenharEspecialEnergia(Graphics g, Vegeta vegeta) {
        final int LARGURA_TELA = 1920;

        // BASE - ESPECIAL NORMAL (Base, SSJ, Deus, Blue)
        if (especialEnergia && !UltraInstintoAtivo && !InstintoIncompletoAtivo) {
            if (mostrarPoeiraEspecial
                    && indicePoeiraEspecial >= 0
                    && indicePoeiraEspecial < poeiraEspecial.size()) {
                g.drawImage(poeiraEspecial.get(indicePoeiraEspecial), posX, posY,256,256, null);
            }
            if (mostrarAnelEspecial
                    && indiceAnelEspecial >= 0
                    && indiceAnelEspecial < anelEspecial.size()) {
                g.drawImage(anelEspecial.get(indiceAnelEspecial), posX + 400, posY, null);
            }

            // O anel e a poeira sao animados aqui
          
            contadorAnelEspecial++;
            if (contadorAnelEspecial >= 5) {
                contadorAnelEspecial = 0;

                if (mostrarAnelEspecial) {
                    if (indiceAnelEspecial < anelEspecial.size() - 1) {
                        indiceAnelEspecial++;
                    } else {
                        mostrarAnelEspecial = false;
                        indiceAnelEspecial = 0;
                    }
                }

                if (mostrarPoeiraEspecial) {
                    if (indicePoeiraEspecial < poeiraEspecial.size() - 1) {
                        indicePoeiraEspecial++;
                    } else {
                        mostrarPoeiraEspecial = false;
                        indicePoeiraEspecial = 0;
                    }
                }
            }

            // A hitbox inclui o meio e a ponta do Kamehameha.
            Rectangle boundsKame = getBoundsKame();
            Rectangle boundsVegeta = vegeta.getBounds();

            if (boundsKame.intersects(boundsVegeta)) {
                Rectangle areaImpacto = boundsKame.intersection(boundsVegeta);
                vegeta.receberDano(especialDano);
                vegeta.iniciarExplosaoAoSerAtingido(
                    areaImpacto.x + (areaImpacto.width / 2),
                    areaImpacto.y + (areaImpacto.height / 2)
                );
                podeTocarFimKame = true;
                PararEspecial();
                return;
            }

            // Verifica se o Kamehameha saiu da tela
            int inicioPontaKame = posX + 350 + energiaX;
            if (inicioPontaKame > LARGURA_TELA) {
                PararEspecial();
                return;
            }

            g.drawImage(energiaInicio, posX + 150, posY + 53, 128, 128, null);
            g.drawImage(energiaFim, posX + 350 + energiaX, posY + 50, 128, 128, null);
            g.drawImage(energiaFundo,posX + 250, posY  , 100 + energiaX,200,null);
            g.drawImage(energiaMeio, posX + 250, posY + 35 + 50, 100 + energiaX, 68, null);



        }

        // INSTINTO INCOMPLETO
        if (especialEnergia && InstintoIncompletoAtivo) {
            g.drawImage(especialEnergiaInstintoIncompleto.get(indiceEspecialInstintoIncompleto),
                        posX + 150, posY + 30, 1300, 128, null);

            if (!especialInstintoIncompletoAcertou &&
                getBoundsEspecialInstintoIncompleto().intersects(vegeta.getBounds()) &&
                indiceEspecialInstintoIncompleto >= especialEnergiaInstintoIncompleto.size() - 1) {

                vegeta.receberDano(especialDano);
                especialInstintoIncompletoAcertou = true;
                podeTocarFimKame = true;
                PararEspecial();
                return;
            }

            int posicaoFinalEspecial = posX + 150 + 1300;
            if (posicaoFinalEspecial > LARGURA_TELA) {
                PararEspecial();
                return;
            }
        }
    }

    public Rectangle getBoundsEspecialInstintoIncompleto(){
        return new Rectangle(posX + 150, posY + 30, 1300, 128);
    }

    public void desenharBarraKi(Graphics g){
        g.drawImage(barraKi,200,60,especialKi,50,null);
    }

    public void desenharGoku(Graphics g,TelaJogo jogo) {
        if (AtivarEspecialUltraInstinto) {
            return;
        }

        if (ativarEspecial) {
            // Cadeia de sons: a energia so aparece quando a voz do kamehameha
            // chega em ~2,7s, e o som do disparo toca junto com a energia.
            if (!InstintoIncompletoAtivo) {
                if (podeTocarSonsEspecial) {
                    if (!vozIniciada) {
                        // Passo 1: toca a voz ("haaaaa") uma unica vez.
                        kameVoz.tocarSom("/sons/goku/kamehamehaVoz.wav");
                        vozIniciada = true;
                    } else if (kameVoz.getPosicaoMicrosegundos() >= TEMPO_DISPARO_KAME) {
                        // Passo 2: a voz chegou em ~2,7s: solta a energia e
                        // o som do especial toca junto com ela.
                        especialEnergia = true;
                        kameSom.tocarSom("/sons/goku/kamehamehaSom.wav");
                        kameSom.setVolume(1.0f);
                        podeTocarSonsEspecial = false;
                        timerEnergia.start();
                    }
                }
            }

            // A animacao so avanca para o antepenultimo frame em diante depois
            // do disparo (podeTocarSonsEspecial = false significa que aconteceu).
            int limiteAnimacao = podeTocarSonsEspecial
                    ? especialSprites.size() - 2
                    : especialSprites.size() - 1;

            if (indiceEspecial >= limiteAnimacao) {
                indiceEspecial = limiteAnimacao;

                // O Instinto Incompleto segue o fluxo proprio dele: quando a
                // animacao chega no limite, a energia dele e ativada e o
                // timer dele comeca a rodar os frames da energia.
                if (InstintoIncompletoAtivo && !timerEspecialInstintoIncompleto.isRunning()) {
                    especialEnergia = true;
                    timerEspecialInstintoIncompleto.start();
                }
            }

            if (!especialSprites.isEmpty()) {
                int indiceSeguro = indiceEspecial;
                if (indiceSeguro > limiteAnimacao) {
                    indiceSeguro = limiteAnimacao;
                }
                spriteAtual = especialSprites.get(indiceSeguro);
            }
        }

        if ((kameSom.terminou() || !ativarEspecial) && podeTocarFimKame && kameFim.terminou()) {
            kameFim.tocarSom("/sons/goku/kamehamehaSomFim.wav");
            kameFim.setVolume(1.0f);
            podeTocarFimKame = false;
        }

        g.drawImage(spriteAtual, posX, posY, 200, 210, null);

        if (elevandoKi && indicePoeiraKi >= 0 && indicePoeiraKi < poeiraKi.size()) {
            g.drawImage(poeiraKi.get(indicePoeiraKi), posX + 10, posY + 45,180,180, null);
        }

        if (mostrarGolpe && !golpes.isEmpty()) {
            if(jogo.baixo || jogo.cima || jogo.esquerda || jogo.direita){
                g.drawImage(golpes.get(indiceGolpe), posX - 100, posY - 170, 700, 600, null);
            }else{
                g.drawImage(golpes.get(indiceGolpe), posX, posY + 90, 200, 100, null);
            }
            contadorGolpe++;

            if (contadorGolpe >= 5) {
                contadorGolpe = 0;
                indiceGolpe++;

                if (indiceGolpe >= golpes.size()) {
                    indiceGolpe = 0;
                    mostrarGolpe = false;
                }
            }
        }
    }

    public Rectangle getBoundsKame(){
        return new Rectangle(posX + 250, posY + 50, 228 + energiaX, 128);
    }

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, 160, 160);
    }

    public void receberDano(double dano) {
        vida -= dano;
        indiceGolpe = 0;
        contadorGolpe = 0;
        mostrarGolpe = true;
    }

    public Rectangle getBoundsAtaque() {
        return new Rectangle(posX - 20, posY + 70, 160, 160);
    }

    public void ElevarKi() {
        elevandoKi = true;
        if (elevandoKi && !poeiraKi.isEmpty()) {
            contadorPoeiraKi++;
            if (contadorPoeiraKi >= 5) {
                contadorPoeiraKi = 0;
                indicePoeiraKi++;
                if (indicePoeiraKi >= poeiraKi.size()) {
                    indicePoeiraKi = 0;
                }
            }
        }

        // O inicio toca uma vez; depois, o som principal se repete.
        // Ambos ja foram carregados no construtor, portanto nao ha I/O aqui.
        if (!somKiTocando) {
            somKiInicio.tocarSom("/sons/kiInicio.wav");
            somKiTocando = true;
        } else if (somKiInicio.terminou() && somKi.terminou()) {
            somKi.tocarSom("/sons/ki.wav");
        }

        // BASE
        if (!SsjAtivo && !DeusAtivo && !BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo) {

            if (IndiceKi < 6) {
                spriteAtual = Elevar.get(IndiceKi);
                IndiceKi++;
            } else {
                IndiceKi = 3;
            }

            if (NivelKi < NivelKiMaximo)
                NivelKi++;
                especialKi ++;

            if (NivelKi >= NivelKiMaximo && !Transformando) {
                Transformando = true;
                IndiceTransformacao = 0;
            }
        }

        // SSJ
        else if (SsjAtivo && !DeusAtivo && !BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo) {

            if (IndiceKi < 4) {
                spriteAtual = ElevarSsj.get(IndiceKi);
                IndiceKi++;
            } else {
                IndiceKi = 2;
            }

            if (NivelKiDeus < NivelKiMaximoDeus)
                NivelKiDeus++;
                especialKi++;

            if (NivelKiDeus >= NivelKiMaximoDeus && !TransformandoDeus) {
                TransformandoDeus = true;
                IndiceTransformacaoDeus = 0;
            }
        }

        // DEUS
        else if (DeusAtivo && !BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo) {

            if (IndiceKi < ElevarDeus.size()) {
                spriteAtual = ElevarDeus.get(IndiceKi);
                IndiceKi++;
            } else {
                IndiceKi = ElevarDeus.size() - 2;
            }

            if (NivelKiBlue < NivelKiMaximoBlue)
                NivelKiBlue++;
                especialKi++;

            if (NivelKiBlue >= NivelKiMaximoBlue && !TransformandoBlue) {
                TransformandoBlue = true;
                IndiceTransformacaoBlue = 0;
            }
        }

        // BLUE
        else if (BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo) {

            if (IndiceKi < ElevarKiBlue.size()) {
                spriteAtual = ElevarKiBlue.get(IndiceKi);
                IndiceKi++;
            } else {
                IndiceKi = 0;
            }

            if (NivelKiInstintoIncompleto < NivelKiMaximoInstintoIncompleto)
                NivelKiInstintoIncompleto++;
                especialKi++;

            if (NivelKiInstintoIncompleto >= NivelKiMaximoInstintoIncompleto
                    && !TransformandoInstintoIncompleto) {

                TransformandoInstintoIncompleto = true;
                IndiceTransformacaoInstintoIncompleto = 0;
            }
        }

        // INSTINTO INCOMPLETO
        else if (InstintoIncompletoAtivo && !UltraInstintoAtivo) {

            if (IndiceKi < ElevarKiInstintoIncompleto.size()) {
                spriteAtual = ElevarKiInstintoIncompleto.get(IndiceKi);
                IndiceKi++;
            } else {
                IndiceKi = 0;
            }

            if (NivelKiUltraInstinto < NivelKiMaximoUltraInstinto)
                NivelKiUltraInstinto++;
                especialKi++;

            if (NivelKiUltraInstinto >= NivelKiMaximoUltraInstinto
                    && !TransformandoUltraInstinto) {

                TransformandoUltraInstinto = true;
                IndiceTransformacaoUltraInstinto = 0;
            }
        }

        // ULTRA INSTINTO
        else {
            if (IndiceKi < ElevarKiUltraInstinto.size()) {
                spriteAtual = ElevarKiUltraInstinto.get(IndiceKi);
                IndiceKi++;
                especialKi++;
            } else {
                IndiceKi = 0;
            }
        }

        if(especialKi >= 400
        ){
            especialKi = 400;
        }

        if (NivelKi >= NivelKiMaximo && !SsjAtivo && !DeusAtivo && !BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo && !Transformando) {

            Transformando = true;
            IndiceTransformacao = 0;
            velocidade += 2;

        } else if (NivelKiDeus >= NivelKiMaximoDeus && SsjAtivo && !DeusAtivo && !BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo && !TransformandoDeus) {

            TransformandoDeus = true;
            IndiceSeq = 0;
            IndiceTransformacaoDeus = 0;
            velocidade += 2;

        } else if (NivelKiBlue >= NivelKiMaximoBlue && DeusAtivo && !BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo && !TransformandoBlue) {

            TransformandoBlue = true;
            IndiceTransformacaoBlue = 0;
            velocidade += 2;

        } else if (NivelKiInstintoIncompleto >= NivelKiMaximoInstintoIncompleto && BlueAtivo && !InstintoIncompletoAtivo && !UltraInstintoAtivo && !TransformandoInstintoIncompleto) {

            TransformandoInstintoIncompleto = true;
            IndiceTransformacaoInstintoIncompleto = 0;
            velocidade += 2;
        } else if (NivelKiUltraInstinto >= NivelKiMaximoUltraInstinto && InstintoIncompletoAtivo && !UltraInstintoAtivo && !TransformandoUltraInstinto) {

            TransformandoUltraInstinto = true;
            IndiceTransformacaoUltraInstinto = 0;
            velocidade += 2;
        }
    }

    public void SoltarKi() {
        elevandoKi = false;
        indicePoeiraKi = 0;
        contadorPoeiraKi = 0;
        somKiTocando = false;
        somKi.parar();
        somKiInicio.parar();
        IndiceKi = 0;
    }

    public void ExecutarAtaque(Vegeta vegeta) {


        List<Image> SequenciaAtual = null;

        if (UltraInstintoAtivo) {

            switch (IndiceSeq) {
                case 0: SequenciaAtual = UltraInstintoSequencia1; break;
                case 1: SequenciaAtual = UltraInstintoSequencia2; break;
                case 2: SequenciaAtual = UltraInstintoSequencia3; break;
                case 3: SequenciaAtual = UltraInstintoSequencia4; break;
                case 4: SequenciaAtual = UltraInstintoSequencia5; break;
                case 5: SequenciaAtual = UltraInstintoSequencia6; break;
                case 6: SequenciaAtual = UltraInstintoSequencia7; break;
                case 7: SequenciaAtual = UltraInstintoSequencia8; break;
            }

        } else if (InstintoIncompletoAtivo) {

            switch (IndiceSeq) {
                case 0: SequenciaAtual = InstintoIncompletoSequencia1; break;
                case 1: SequenciaAtual = InstintoIncompletoSequencia2; break;
                case 2: SequenciaAtual = InstintoIncompletoSequencia3; break;
                case 3: SequenciaAtual = InstintoIncompletoSequencia4; break;
                case 4: SequenciaAtual = InstintoIncompletoSequencia5; break;
                case 5: SequenciaAtual = InstintoIncompletoSequencia6; break;
                case 6: SequenciaAtual = InstintoIncompletoSequencia7; break;
            }

        } else if (BlueAtivo) {

            switch (IndiceSeq) {
                case 0: SequenciaAtual = BlueSequencia1; break;
                case 1: SequenciaAtual = BlueSequencia2; break;
                case 2: SequenciaAtual = BlueSequencia3; break;
                case 3: SequenciaAtual = BlueSequencia4; break;
            }

        } else if (DeusAtivo) {

            switch (IndiceSeq) {
                case 0: SequenciaAtual = DeusSequencia1; break;
                case 1: SequenciaAtual = DeusSequencia2; break;
                case 2: SequenciaAtual = DeusSequencia3; break;
                case 3: SequenciaAtual = DeusSequencia4; break;
                case 4: SequenciaAtual = DeusSequencia5; break;
                case 5: SequenciaAtual = DeusSequencia6; break;
            }

        } else if (SsjAtivo) {

            switch (IndiceSeq) {
                case 0: SequenciaAtual = SsjSequencia1; break;
                case 1: SequenciaAtual = SsjSequencia2; break;
                case 2: SequenciaAtual = SsjSequencia3; break;
                case 3: SequenciaAtual = SsjSequencia4; break;
                case 4: SequenciaAtual = SsjSequencia5; break;
                case 5: SequenciaAtual = SsjSequencia6; break;
                case 6: SequenciaAtual = SsjSequencia7; break;
            }

        } else {

            switch (IndiceSeq) {
                case 0: SequenciaAtual = sequencia1; break;
                case 1: SequenciaAtual = sequencia2; break;
                case 2: SequenciaAtual = sequencia3; break;
                case 3: SequenciaAtual = sequencia4; break;
                case 4: SequenciaAtual = sequencia5; break;
                case 5: SequenciaAtual = sequencia6; break;
            }
        }

        if (SequenciaAtual != null && !SequenciaAtual.isEmpty()) {

            ContadorAtaque++;

            if (ContadorAtaque >= AtrasoAtaque) {

                ContadorAtaque = 0;
                IndiceFrame++;

                if (IndiceFrame >= SequenciaAtual.size()) {

                    IndiceFrame = 0;
                    IndiceSeq++;

                   // Verifica colisão com o Vegeta ao finalizar cada sequência
                    if (vegeta != null && getBoundsAtaque().intersects(new Rectangle(vegeta.posX, vegeta.posY, 150, 170))) {
                        somSoco.tocarSom("/sons/soco.wav");
                        vegeta.receberDano(forcaAtaque);
                        especialKi += 6;
                    }

                    int limiteSeq;

                    if (UltraInstintoAtivo)
                        limiteSeq = 8;
                    else if (InstintoIncompletoAtivo)
                        limiteSeq = 7;
                    else if (BlueAtivo)
                        limiteSeq = 4;
                    else if (DeusAtivo)
                        limiteSeq = 6;
                    else if (SsjAtivo)
                        limiteSeq = 7;
                    else
                        limiteSeq = 6;

                    if (IndiceSeq >= limiteSeq)
                        IndiceSeq = 0;

                    Atacando = false;
                }
            }

            spriteAtual = SequenciaAtual.get(IndiceFrame);
        }
    }


    public void ExecutarAtaque() {
        ExecutarAtaque(null);
    }

    public void InciarAtaque() {

        if (!Atacando) {

            Atacando = true;
            IndiceFrame = 0;
            tirarVidaVegeta = true; // Permite causar dano no próximo golpe
        }
    }

    public void AtivarSsj() {
        SsjAtivo = true;
        forcaAtaque += 1;
        especialDano += 10; // Aumenta o dano do especial
        spriteAtual = ParadoSsj;
        especialSprites = especialSpritesSsj;
    }

    public void DesativarSsj() {
        SsjAtivo = false;
        forcaAtaque -= 5;
        especialDano -= 20;
        spriteAtual = parado;
    }

    public void AtivarDeus() {
        DeusAtivo = true;
        SsjAtivo = false;
        forcaAtaque += 1;
        especialDano += 10; // Aumenta o dano do especial no Deus
        spriteAtual = ParadoDeus;
        especialSprites = especialSpritesDeus;
    }

    public void AtivarBlue() {
        BlueAtivo = true;
        SsjAtivo = false;
        DeusAtivo = false;
        forcaAtaque += 1;
        especialDano += 10; // Aumenta o dano do especial no Blue
        IndiceSeq = 0;
        IndiceFrame = 0;
        spriteAtual = ParadoBlue;
        especialSprites = especialSpritesBlue;
    }

    public void AtivarInstintoIncompleto() {

        InstintoIncompletoAtivo = true;
        UltraInstintoAtivo = false;
        BlueAtivo = false;
        DeusAtivo = false;
        SsjAtivo = false;
        forcaAtaque += 1;
        especialDano += 10; // Aumenta o dano do especial no Instinto Incompleto

        IndiceSeq = 0;
        IndiceFrame = 0;

        spriteAtual = ParadoInstintoIncompleto;
        especialSprites = especialSpritesInstintoIncompleto;

    }

    public void AtivarUltraInstinto() {

        UltraInstintoAtivo = true;
        InstintoIncompletoAtivo = false;
        BlueAtivo = false;
        DeusAtivo = false;
        SsjAtivo = false;
        forcaAtaque += 1;
        especialDano += 10; // Aumenta o dano do especial no Ultra Instinto

        IndiceSeq = 0;
        IndiceFrame = 0;

        spriteAtual = ParadoUltraInstinto;
    }

    public void ExecutarTransformacao() {
        // Toca som de transformação uma vez

        if (IndiceTransformacao < TransformacaoBaseParaSsj.size()) {
            spriteAtual = TransformacaoBaseParaSsj.get(IndiceTransformacao);

            ContadorTransformacao++;
            if (ContadorTransformacao >= AtrasoTransformacao) {
                ContadorTransformacao = 0;
                IndiceTransformacao++;
            }
        } else {
            IndiceTransformacao = 0;
            ContadorTransformacao = 0;
            Transformando = false;
            vida = 200;
            AtivarSsj();
        }
    }

    public void ExecutarTransformacaoDeus() {
        // Toca som de transformação uma vez


        if (IndiceTransformacaoDeus < TransformacaoSsjParaDeus.size()) {
            spriteAtual = TransformacaoSsjParaDeus.get(IndiceTransformacaoDeus);

            ContadorTransformacaoDeus++;
            if (ContadorTransformacaoDeus >= AtrasoTransformacaoDeus) {
                ContadorTransformacaoDeus = 0;
                IndiceTransformacaoDeus++;
            }
        } else {
            IndiceTransformacaoDeus = 0;
            ContadorTransformacaoDeus = 0;
            TransformandoDeus = false;

            vida = 300;
            AtivarDeus();
        }
    }

    public void ExecutarTransformacaoBlue() {
        // Toca som de transformação uma vez



        if (IndiceTransformacaoBlue < TransformacaoDeusParaBlue.size()) {
            spriteAtual = TransformacaoDeusParaBlue.get(IndiceTransformacaoBlue);

            ContadorTransformacaoBlue++;
            if (ContadorTransformacaoBlue >= AtrasoTransformacaoBlue) {
                ContadorTransformacaoBlue = 0;
                IndiceTransformacaoBlue++;
            }
        } else {
            IndiceTransformacaoBlue = 0;
            ContadorTransformacaoBlue = 0;
            TransformandoBlue = false;

            vida = 400;
            AtivarBlue();
        }
    }

    public void ExecutarTransformacaoInstintoIncompleto() {
        // Toca som de transformação uma vez


        if (IndiceTransformacaoInstintoIncompleto < TransformacaoBlueParaInstintoIncompleto.size()) {
            spriteAtual = TransformacaoBlueParaInstintoIncompleto.get(IndiceTransformacaoInstintoIncompleto);

            ContadorTransformacaoInstintoIncompleto++;
            if (ContadorTransformacaoInstintoIncompleto >= AtrasoTransformacaoInstintoIncompleto) {
                ContadorTransformacaoInstintoIncompleto = 0;
                IndiceTransformacaoInstintoIncompleto++;
            }
        } else {
            IndiceTransformacaoInstintoIncompleto = 0;
            ContadorTransformacaoInstintoIncompleto = 0;
            TransformandoInstintoIncompleto = false;

            vida = 500;
            AtivarInstintoIncompleto();
        }
    }

    public void ExecutarTransformacaoUltraInstinto() {


        if (IndiceTransformacaoUltraInstinto < TransformacaoInstintoIncompletoParaUltraInstinto.size()) {
            spriteAtual = TransformacaoInstintoIncompletoParaUltraInstinto.get(IndiceTransformacaoUltraInstinto);

            ContadorTransformacaoUltraInstinto++;
            if (ContadorTransformacaoUltraInstinto >= AtrasoTransformacaoUltraInstinto) {
                ContadorTransformacaoUltraInstinto = 0;
                IndiceTransformacaoUltraInstinto++;
            }
        } else {
            IndiceTransformacaoUltraInstinto = 0;
            ContadorTransformacaoUltraInstinto = 0;
            TransformandoUltraInstinto = false;

            vida = 600;
            AtivarUltraInstinto();
        }
    }

    public void IniciarEspecialUltraInstinto() {
        if (UltraInstintoAtivo && !AtivarEspecialUltraInstinto) {
            AtivarEspecialUltraInstinto = true;
            IndoParaEspecialUltraInstinto = true;
            PosicionadoEspecialUltraInstinto = false;
            IndiceEspecialUltraInstinto = 0;
            ContadorEspecialUltraInstinto = 0;
            somKi.parar();
            somKiInicio.parar();
            somKiTocando = false;
        }
    }

    public void ExecutarEspecialUltraInstinto(Graphics g, Vegeta vegeta) {
        if (!AtivarEspecialUltraInstinto || vegeta == null) return;

        //Goku anda até ficar de frente pro Vegeta
        if (IndoParaEspecialUltraInstinto) {

            boolean VegetaEstaDireita = vegeta.posX >= posX;
            int DestinoX = VegetaEstaDireita
                    ? vegeta.posX - DistanciaEspecialUltraInstinto
                    : vegeta.posX + DistanciaEspecialUltraInstinto;

            if (VegetaEstaDireita) {
                if (posX < DestinoX) {
                    posX += VelocidadeEspecialUltraInstinto;
                    spriteAtual = FrenteUltraInstinto;
                } else {
                    posX = DestinoX;
                    IndoParaEspecialUltraInstinto = false;
                    PosicionadoEspecialUltraInstinto = true;
                }
            } else {
                if (posX > DestinoX) {
                    posX -= VelocidadeEspecialUltraInstinto;
                    spriteAtual = TrasUltraInstinto;
                } else {
                    posX = DestinoX;
                    IndoParaEspecialUltraInstinto = false;
                    PosicionadoEspecialUltraInstinto = true;
                }
            }

        // Aqui ele já está de frente pro Vegeta e vai executar a animação
        } else if (PosicionadoEspecialUltraInstinto) {

            if (IndiceEspecialUltraInstinto < EspecialUltraInstinto.size()) {

                spriteAtual = EspecialUltraInstinto.get(IndiceEspecialUltraInstinto);

                ContadorEspecialUltraInstinto++;
                if (ContadorEspecialUltraInstinto >= AtrasoEspecialUltraInstinto) {
                    ContadorEspecialUltraInstinto = 0;
                    IndiceEspecialUltraInstinto++;
                }

            } else {
                // Dano do especial do Ultra Instinto
                if (getBoundsAtaque().intersects(new Rectangle(vegeta.posX, vegeta.posY, 150, 170))) {
                    vegeta.receberDano(especialDano * 3); // Usa o especialDano atual
                }

                AtivarEspecialUltraInstinto = false;
                PosicionadoEspecialUltraInstinto = false;
                IndiceEspecialUltraInstinto = 0;
                ContadorEspecialUltraInstinto = 0;
                spriteParado();
            }
        }

        g.drawImage(spriteAtual, posX, posY, 200, 210, null);

    }

    public void desenharVida(Graphics g){
        int tamanhoBarraVidaX = vida;
        g.drawImage(barraVida,200,10,tamanhoBarraVidaX ,50,null);
    }

    private void limparListas(ArrayList<Image>... listas) {

    for (int i = 0; i < listas.length; i++) {
        listas[i].clear();
    }
}

    private Image load(String caminho) {
        // Sprites do Goku tambem sao desenhados com no maximo ~200px;
        // reduzir na carga evita manter imagens gigantes na memoria.
        return load(caminho, 220);
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
