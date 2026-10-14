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

public class Vegeta {

    //efeitos
    //anel do especial
    private ArrayList<Image> anel;

    //poeira ao soltar especial
    private ArrayList<Image> poeiraEspecial;

    //poeira do ki

    private ArrayList<Image> poeiraKi;
    private ArrayList<Image> golpes;
    private int indiceGolpe;
    private int contadorGolpe;
    private boolean mostrarGolpe;

    //listas de sequencias

    private ArrayList<Image> sequencia1;
    private ArrayList<Image> sequencia2;
    private ArrayList<Image> sequencia3;
    private ArrayList<Image> sequencia4;
    private ArrayList<Image> sequencia5;
    private ArrayList<Image> sequencia6;
    private ArrayList<Image> sequencia7;
    private ArrayList<Image> sequencia8;
    private ArrayList<Image> explosao;
    private ArrayList<Image> transformacao;
    private ArrayList<Image> elevarKiSprites;
    private ArrayList<Image> especial;

    // Sprites da forma base.
    private ArrayList<Image> baseSequencia1, baseSequencia2, baseSequencia3, baseSequencia4, baseSequencia5, baseSequencia6, baseSequencia7, baseSequencia8;
    private ArrayList<Image> baseKi, baseEspecial, baseTransformacao;
    private Image frenteBase, trasBase, cimaBase;

    // Sprites da forma Super Saiyajin.
    private ArrayList<Image> ssjSequencia1, ssjSequencia2, ssjSequencia3, ssjSequencia4, ssjSequencia5, ssjSequencia6, ssjSequencia7, ssjSequencia8;
    private ArrayList<Image> ssjKi, ssjEspecial, ssjTransformacao;
    private Image frenteSsj, trasSsj, cimaSsj;

    // Sprites da forma Deus.
    private ArrayList<Image> deusSequencia1, deusSequencia2, deusSequencia3, deusSequencia4, deusSequencia5, deusSequencia6, deusSequencia7, deusSequencia8;
    private ArrayList<Image> deusKi, deusEspecial, deusTransformacao, deusExplosao;
    private Image frenteDeus, trasDeus, cimaDeus;

    // Sprites da forma Blue.
    private ArrayList<Image> blueSequencia1, blueSequencia2, blueSequencia3, blueSequencia4, blueSequencia5, blueSequencia6, blueSequencia7, blueSequencia8;
    private ArrayList<Image> blueKi, blueEspecial, blueTransformacao;
    private Image frenteBlue, trasBlue, cimaBlue;

    // Sprites da forma Ultra Ego.
    private ArrayList<Image> egoSequencia1, egoSequencia2, egoSequencia3, egoSequencia4, egoSequencia5, egoSequencia6, egoSequencia7, egoSequencia8;
    private ArrayList<Image> egoKi, egoEspecial, egoTransformacao;
    private Image frenteEgo, trasEgo, cimaEgo;

    // Booleans simples para saber a forma atual.
    private boolean vegetaBase;
    private boolean vegetaSsj;
    private boolean vegetaDeus;
    private boolean vegetaBlue;
    private boolean vegetaUltraEgo;


    private int formaDestinoTransformacao;


    private int contadorKi = 0;


    private Image frente;
    private Image tras;
    private Image cima;


    private Image spriteAtual;


    private int dimensoesVegetaX = 150;
    private int dimensoesVegetaY = 170;

    private int posXGoku;
    private int posYGoku;

    public int posX;
    public int posY;

    private Image barraDeVida = load("bVVegeta.png");
    private Image barraKiImg = load("barraKi.png");

    private int pontos;

    public boolean vegetaAparecendo;
    public boolean elevarKi;
    private boolean atacar;
    public boolean ativarEspecial;
    private final Som finalFlash;
    private final Som finalflashVoz;
    private final Som finalFlashCarregar;
    private final Som soco;



    private boolean indiceKiNormal;

    private boolean finalizandoKi;
    private int contadorFimKi;

    private int indiceEspecial;
    private boolean especialEnergia = false;
    private boolean flashVoz;
    private int indiceAtaque;
    private int sequencia;
    private boolean especialAcertou = false;
    private boolean mostrarAnel = true;
    private boolean mostrarPoeiraEspecial = true;

    private int indiceAnel = 0;
    private int indicePoeiraEspecial = 0;
    private int indicePoeiraKi = 0;

    private final Image energiaEspecialInicioImg = load("/vegeta/vegeta base/especial/energia/inicio.png");
    private final Image energiaEspecialMeioImg = load("/vegeta/vegeta base/especial/energia/meio.png");
    private final Image energiaEspecialFimImg = load("/vegeta/vegeta base/especial/energia/fim.png");
    private final Image energiaEspecialDeusImg = load("/vegeta/vegetaDeus/especial/energia/energia.png");
    private ArrayList<Image> explosaoEspecial = new ArrayList<>();

    public int vegetaVida;
    private int forca = 2;
    private double especialDano = 20;
    private boolean tirarVidaGoku;
    public int pontosEspecial;

    private int indiceExplosao;
    private Som somExplosao;
    public boolean explodindo;
    private boolean explosaoAoSerAtingido;
    private int indiceExplosaoAoSerAtingido;
    private int posXExplosaoAoSerAtingido;
    private int posYExplosaoAoSerAtingido;
    private boolean transformando;
    private int indiceTransformacao;
    private int XEnergia;
    private int energiaY;


    private boolean podeExplodir = true;


    private final ArrayList<Image> aparecer = new ArrayList<>();

    public Timer timer;
    public Timer timerEnergiaEspecial;

    public Vegeta(int posX, int posY,int posyGoku) {

        this.posX = posX;
        this.posY = posY;
        this.posYGoku = posyGoku;



        // Inicializa os sons usados nas acoes do Vegeta.
        finalFlash = new Som();
        finalFlash.tocarSom("/sons/vegeta/finalFlash.wav");
        finalflashVoz = new Som();
        finalflashVoz.tocarSom("/sons/vegeta/finalFlashVoz.wav");
        finalFlashCarregar = new Som();
        finalFlashCarregar.tocarSom("/sons/vegeta/finalFlashCarregar.wav");

        finalFlash.setVolume(2.0f);
        finalflashVoz.setVolume(2.0f);
        finalFlashCarregar.setVolume(2.0f);

        somExplosao = new Som();
        somExplosao.tocarSom("sons/explosao.wav");
        soco = new Som();



        pontos = 0;


        //efeitos
        poeiraEspecial = new ArrayList<>();
        poeiraKi = new ArrayList<>();
        golpes = new ArrayList<>();
        anel = new ArrayList<>();



        // Estado inicial: aparecendo, sem atacar e sem especial.
        vegetaAparecendo = true;
        elevarKi = false;
        atacar = false;
        ativarEspecial = false;

        // Zera os controles das animacoes.
        indiceKiNormal = true;
        finalizandoKi = false;
        flashVoz = true;
        contadorFimKi = 0;
        indiceEspecial = 0;
        indiceAtaque = 0;
        sequencia = 1;

        vegetaVida = 100;

        vegetaBase = true;
        vegetaSsj = false;
        vegetaDeus = false;
        vegetaBlue = false;
        vegetaUltraEgo = false;

        // Nenhuma transformacao esta rodando no inicio.
        transformando = false;
        formaDestinoTransformacao = 0;
        indiceTransformacao = 0;

        // Carrega TODOS os sprites uma vez e usa a forma base no inicio.
        carregarTodosSprites();
        usarBase();

        timerEnergiaEspecial = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // XEnergia e usado no desenho, na hitbox e no teste de saida da tela,
                // entao esse incremento move
                // tanto a imagem quanto a hitbox do raio.
                if (especialEnergia) {
                    XEnergia += 50;
                } else {
                    // Se a energia acabou, para esse timer.
                    timerEnergiaEspecial.stop();
                }
            }
        });

        timer = new Timer(100, new ActionListener() {
            // Controla em qual frame da entrada o Vegeta esta.
            private int indiceEntrada = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (explosaoAoSerAtingido) {
                    if (indiceExplosaoAoSerAtingido < explosaoEspecial.size() - 1) {
                        indiceExplosaoAoSerAtingido++;
                    } else {
                        explosaoAoSerAtingido = false;
                        indiceExplosaoAoSerAtingido = 0;
                    }
                }

                if(especialEnergia){
                    if(mostrarAnel){
                        if(indiceAnel < anel.size() - 1){
                            indiceAnel ++;
                        }else{
                            mostrarAnel = false;
                            indiceAnel = 0;
                        }
                    }

                    if(mostrarPoeiraEspecial){
                        if(indicePoeiraEspecial < poeiraEspecial.size() - 1){
                            indicePoeiraEspecial ++;
                        }else{
                            mostrarPoeiraEspecial = false;
                            indicePoeiraEspecial = 0;
                        }
                    }

                }

                if(explodindo){

                    if(indiceExplosao < explosao.size() - 1){
                        indiceExplosao++;
                    }else{
                        finalizarEspecial();

                    }
                    return;
                }

                if (vegetaAparecendo) {
                    // Durante a entrada, nenhum som de especial fica tocando.
                    // Esse return evita que transformacao, ki, ataque ou especial

                    somExplosao.parar();
                    finalFlash.parar();
                    finalflashVoz.parar();
                    finalFlashCarregar.parar();
                    if (indiceEntrada < aparecer.size()) {
                        // Mostra o proximo frame da animacao de entrada.
                        spriteAtual = aparecer.get(indiceEntrada);
                        indiceEntrada++;
                    } else {
                        // Entrada terminou; limpa os frames para economizar memoria.
                        vegetaAparecendo = false;
                        aparecer.clear();

                    }
                    return;
                }

                if (ativarEspecial && !elevarKi && !transformando) {


                    // ativarEspecial e ligado por iniciarEspecialSeCarregado().

                    boolean energiaJaEstavaAtiva = especialEnergia;
                    atualizarEspecial();

                    if (energiaJaEstavaAtiva) {

                        return;
                    }
                } else {

                    flashVoz = true;
                    especialEnergia = false;
                    indiceEspecial = 0;
                }


                if (!transformando && vegetaBase && pontos >= 130 && pontos <= 140) {
                    iniciarTransformacaoSsj();
                }


                if (!transformando && vegetaSsj && pontos >= 260 && pontos < 270) {
                    iniciarTransformacaoDeus();
                }

                if(!transformando && vegetaDeus && pontos >= 400 && pontos <= 410){
                    iniciarTransformacaoBlue();
                }

                if(!transformando && vegetaBlue && pontos >= 530 && pontos <= 540){
                    iniciarTransformacaoUltraEgo();
                }

                // Enquanto transforma, o Vegeta nao ataca,
                // nao carrega ki e nao usa especial.
                if (transformando) {
                    atualizarTransformacao();

                    return;
                }

                // A animacao do ki e controlada no metodo atualizar().
                if (elevarKi) {
                    // O ki e animado em atualizar(), chamado pela TelaJogo.
                    // Aqui o timer principal evita iniciar ataque ao mesmo tempo.
                    return;
                }

                if (atacar && !elevarKi && !ativarEspecial && !transformando) {
                    atacarForma(totalSequenciasAtaque());
                }
            }
        });

        timer.start();
    }

    private void atualizarEspecial() {

        posX = 1700;
        // So entra aqui se o especial estiver ligado
        // e o Vegeta nao estiver carregando ki nem transformando.
        if (especial.isEmpty()) {
            return;
        }
        if(finalflashVoz.terminou() && flashVoz && !vegetaDeus){

            finalflashVoz.tocarSom("/sons/vegeta/finalFlashVoz.wav");
            flashVoz = false;
        }else{
            if(finalFlashCarregar.terminou()){
            // Toca o som de carregar quando ele estiver livre.
            // Esse som nao trava a animacao; ele so acompanha o especial.Explosao
            finalFlashCarregar.tocarSom("/sons/vegeta/finalFlashCarregar.wav");
            }
        }
        int ultimoIndice = especial.size() - 1;

        if (especialEnergia) {

            spriteAtual = especial.get(especial.size() - 1);
            return;
        }



        spriteAtual = especial.get(indiceEspecial);

        if (indiceEspecial < ultimoIndice) {

            indiceEspecial++;
        } else {
            // Chegou no ultimo frame: agora a energia comeca.
            // especialEnergia ativa desenharEspecial().

            // especialAcertou volta para false para permitir novo dano.
            especialEnergia = true;
            XEnergia = 0;
            especialAcertou = false;
            timerEnergiaEspecial.start();
            indiceEspecial = ultimoIndice;
        }
    }

    private void atualizarTransformacao() {
        // Transformando bloqueia outras acoes.
        // Isso protege spriteAtual, porque transformacao, ki, especial
        // e ataque tentam escrever nele.
        atacar = false;
        elevarKi = false;
        ativarEspecial = false;

        if (indiceTransformacao < transformacao.size()) {
            // Mostra o proximo frame da transformacao.
            spriteAtual = transformacao.get(indiceTransformacao);
            indiceTransformacao++;
            return;
        }

        // Terminou a animacao de transformacao.
        transformando = false;
        indiceTransformacao = 0;

        // A forma so muda aqui, no fim da animacao.
        aplicarFormaDestino();

    }

    private void carregarTodosSprites() {

        explosaoEspecial = carregarFrames("/efeitosGraficos/esppecialAcertou", 4, 600);
        // Entrada do Vegeta.
        for (int i = 1; i <= 17; i++) {
            aparecer.add(load("/vegeta/vegeta base/aparecer/(" + i + ").png"));
        }

        //efeitos
        for(int i = 1; i <= 4;i++){
            anel.add(load("/efeitosGraficos/naoSeiONomeDisso/(" + i + ").png"));
        }

        for(int i = 1; i <= 5;i++){
            poeiraEspecial.add(load("/efeitosGraficos/poeiraEspecial/(" + i + ").png"));
        }

        for(int i = 1; i <= 6; i++){
            poeiraKi.add(load("/efeitosGraficos/poeiraKi/(" + i + ").png"));
        }
        for(int i = 1; i <= 4; i++){
            golpes.add(load("/efeitosGraficos/golpes/(" + i + ").png"));
        }
        // Forma base.
        baseSequencia1 = carregarFrames("/vegeta/vegeta base/ataques/sequencia1", 4);
        baseSequencia2 = carregarFrames("/vegeta/vegeta base/ataques/sequencia2", 12);
        baseSequencia3 = carregarFrames("/vegeta/vegeta base/ataques/sequencia3", 4);
        baseSequencia4 = carregarFrames("/vegeta/vegeta base/ataques/sequencia4", 5);
        baseSequencia5 = carregarFrames("/vegeta/vegeta base/ataques/sequencia5", 3);
        baseSequencia6 = novaListaVazia();
        baseSequencia7 = novaListaVazia();
        baseSequencia8 = novaListaVazia();
        baseKi = carregarFrames("/vegeta/vegeta base/elevarKi", 4);
        baseEspecial = carregarFrames("/vegeta/vegeta base/especial", 10);
        baseTransformacao = novaListaVazia();
        frenteBase = load("/vegeta/vegeta base/movimentos/frente.png");
        trasBase = load("/vegeta/vegeta base/movimentos/tras.png");
        cimaBase = load("/vegeta/vegeta base/movimentos/cima.png");

        // Super Saiyajin.
        ssjTransformacao = carregarFrames("/vegeta/vegetaSsj/transformar", 20);
        ssjSequencia1 = carregarFrames("/vegeta/vegetaSsj/ataques/sequencia1", 3);
        ssjSequencia2 = carregarFrames("/vegeta/vegetaSsj/ataques/sequencia2", 8);
        ssjSequencia3 = carregarFrames("/vegeta/vegetaSsj/ataques/sequencia3", 3);
        ssjSequencia4 = carregarFrames("/vegeta/vegetaSsj/ataques/sequencia4", 2);
        ssjSequencia5 = carregarFrames("/vegeta/vegetaSsj/ataques/sequencia5", 5);
        ssjSequencia6 = carregarFrames("/vegeta/vegetaSsj/ataques/sequencia6", 6);
        ssjSequencia7 = novaListaVazia();
        ssjSequencia8 = novaListaVazia();
        ssjKi = carregarFrames("/vegeta/vegetaSsj/elevarKi", 12);
        ssjEspecial = carregarFrames("/vegeta/vegetaSsj/especial", 12);
        frenteSsj = load("/vegeta/vegetaSsj/movimentos/frente.png");
        trasSsj = load("/vegeta/vegetaSsj/movimentos/tras.png");
        cimaSsj = load("/vegeta/vegetaSsj/movimentos/cima.png");

        // Deus.
        deusExplosao = carregarFrames("/vegeta/vegetaDeus/especial/explosao", 11);
        deusTransformacao = carregarFrames("/vegeta/vegetaDeus/transformar", 19);
        deusSequencia1 = carregarFrames("/vegeta/vegetaDeus/ataques/sequencia1", 4);
        deusSequencia2 = carregarFrames("/vegeta/vegetaDeus/ataques/sequencia2", 3);
        deusSequencia3 = carregarFrames("/vegeta/vegetaDeus/ataques/sequencia3", 2);
        deusSequencia4 = carregarFrames("/vegeta/vegetaDeus/ataques/sequencia4", 3);
        deusSequencia5 = carregarFrames("/vegeta/vegetaDeus/ataques/sequencia5", 4);
        deusSequencia6 = carregarFrames("/vegeta/vegetaDeus/ataques/sequencia6", 3);
        deusSequencia7 = carregarFrames("/vegeta/vegetaDeus/ataques/sequencia7", 4);
        deusSequencia8 = novaListaVazia();
        deusKi = carregarFrames("/vegeta/vegetaDeus/ki", 2);
        deusEspecial = carregarFrames("/vegeta/vegetaDeus/especial", 9);
        frenteDeus = load("/vegeta/vegetaDeus/movimentos/frente.png");
        trasDeus = load("/vegeta/vegetaDeus/movimentos/tras.png");
        cimaDeus = load("/vegeta/vegetaDeus/movimentos/cima.png");

        // Blue.
        blueTransformacao = carregarFrames("/vegeta/vegetaBlue/transformar", 18);
        blueSequencia1 = carregarFrames("/vegeta/vegetaBlue/ataques/sequencia1", 3);
        blueSequencia2 = carregarFrames("/vegeta/vegetaBlue/ataques/sequencia2", 3);
        blueSequencia3 = carregarFrames("/vegeta/vegetaBlue/ataques/sequencia3", 4);
        blueSequencia4 = carregarFrames("/vegeta/vegetaBlue/ataques/sequencia4", 3);
        blueSequencia5 = carregarFrames("/vegeta/vegetaBlue/ataques/sequencia5", 3);
        blueSequencia6 = carregarFrames("/vegeta/vegetaBlue/ataques/sequencia6", 3);
        blueSequencia7 = carregarFrames("/vegeta/vegetaBlue/ataques/sequencia7", 4);
        blueSequencia8 = novaListaVazia();
        blueKi = carregarFrames("/vegeta/vegetaBlue/ki", 2);
        blueEspecial = carregarFrames("/vegeta/vegetaBlue/especial", 14);
        frenteBlue = load("/vegeta/vegetaBlue/movimentos/frente.png");
        trasBlue = load("/vegeta/vegetaBlue/movimentos/tras.png");
        cimaBlue = load("/vegeta/vegetaBlue/movimentos/cima.png");

        // Ultra Ego.
        egoTransformacao = carregarFrames("/vegeta/VegetaUltraEgo/transformar", 22);
        egoSequencia1 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia1", 4);
        egoSequencia2 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia2", 3);
        egoSequencia3 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia3", 4);
        egoSequencia4 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia4", 4);
        egoSequencia5 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia5", 4);
        egoSequencia6 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia6", 3);
        egoSequencia7 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia7", 4);
        egoSequencia8 = carregarFrames("/vegeta/VegetaUltraEgo/ataques/sequencia8", 8);
        egoKi = carregarFrames("/vegeta/VegetaUltraEgo/ki", 2);
        egoEspecial = carregarFrames("/vegeta/VegetaUltraEgo/especial", 8);
        frenteEgo = load("/vegeta/VegetaUltraEgo/movimentos/frente.png");
        trasEgo = load("/vegeta/VegetaUltraEgo/movimentos/tras.png");
        cimaEgo = load("/vegeta/VegetaUltraEgo/movimentos/cima.png");
    }

    private ArrayList<Image> novaListaVazia() {
        return new ArrayList<Image>();
    }

    private ArrayList<Image> carregarFrames(String pasta, int total) {
        return carregarFrames(pasta, total, 220);
    }

    // Carrega os frames ja redimensionados para o maior lado informado.
    private ArrayList<Image> carregarFrames(String pasta, int total, int maxLado) {
        ArrayList<Image> lista = new ArrayList<Image>();

        for (int i = 1; i <= total; i++) {
            lista.add(load(pasta + "/(" + i + ").png", maxLado));
        }

        return lista;
    }

    private void trocarAtaques(ArrayList<Image> s1, ArrayList<Image> s2, ArrayList<Image> s3, ArrayList<Image> s4,
                               ArrayList<Image> s5, ArrayList<Image> s6, ArrayList<Image> s7, ArrayList<Image> s8) {
        sequencia1 = s1;
        sequencia2 = s2;
        sequencia3 = s3;
        sequencia4 = s4;
        sequencia5 = s5;
        sequencia6 = s6;
        sequencia7 = s7;
        sequencia8 = s8;
    }

    private void desligarTodasAsFormas() {
        vegetaBase = false;
        vegetaSsj = false;
        vegetaDeus = false;
        vegetaBlue = false;
        vegetaUltraEgo = false;
    }

    private void usarBase() {
        desligarTodasAsFormas();
        vegetaBase = true;
        dimensoesVegetaX = 150;
        dimensoesVegetaY = 170;
        trocarAtaques(baseSequencia1, baseSequencia2, baseSequencia3, baseSequencia4, baseSequencia5, baseSequencia6, baseSequencia7, baseSequencia8);
        transformacao = baseTransformacao;
        elevarKiSprites = baseKi;
        especial = baseEspecial;
        explosao = explosaoEspecial;
        frente = frenteBase;
        tras = trasBase;
        cima = cimaBase;
        spriteAtual = frente;
    }

    private void usarSsj() {
        limparListas(baseSequencia1, baseSequencia2, baseSequencia3, baseSequencia4, baseSequencia5, baseSequencia6, baseSequencia7, baseSequencia8,baseKi, baseEspecial, baseTransformacao);
        desligarTodasAsFormas();
        vegetaSsj = true;
        dimensoesVegetaX = 200;
        dimensoesVegetaY = 200;
        trocarAtaques(ssjSequencia1, ssjSequencia2, ssjSequencia3, ssjSequencia4, ssjSequencia5, ssjSequencia6, ssjSequencia7, ssjSequencia8);
        elevarKiSprites = ssjKi;
        especial = ssjEspecial;
        explosao = explosaoEspecial;
        frente = frenteSsj;
        tras = trasSsj;
        cima = cimaSsj;
        spriteAtual = frente;
    }

    private void usarDeus() {
        limparListas(ssjSequencia1, ssjSequencia2, ssjSequencia3, ssjSequencia4, ssjSequencia5, ssjSequencia6, ssjSequencia7, ssjSequencia8,ssjKi, ssjEspecial, ssjTransformacao);
        desligarTodasAsFormas();
        vegetaDeus = true;
        dimensoesVegetaX = 200;
        dimensoesVegetaY = 200;
        trocarAtaques(deusSequencia1, deusSequencia2, deusSequencia3, deusSequencia4, deusSequencia5, deusSequencia6, deusSequencia7, deusSequencia8);
        elevarKiSprites = deusKi;
        especial = deusEspecial;
        explosao = deusExplosao;
        frente = frenteDeus;
        tras = trasDeus;
        cima = cimaDeus;
        spriteAtual = frente;
    }

    private void usarBlue() {
        limparListas(deusSequencia1, deusSequencia2, deusSequencia3, deusSequencia4, deusSequencia5, deusSequencia6, deusSequencia7, deusSequencia8,deusKi, deusEspecial, deusTransformacao, deusExplosao);
        desligarTodasAsFormas();
        vegetaBlue = true;
        dimensoesVegetaX = 200;
        dimensoesVegetaY = 200;
        trocarAtaques(blueSequencia1, blueSequencia2, blueSequencia3, blueSequencia4, blueSequencia5, blueSequencia6, blueSequencia7, blueSequencia8);
        elevarKiSprites = blueKi;
        especial = blueEspecial;
        explosao = explosaoEspecial;
        frente = frenteBlue;
        tras = trasBlue;
        cima = cimaBlue;
        spriteAtual = frente;
    }

    private void usarUltraEgo() {
        limparListas(blueSequencia1, blueSequencia2, blueSequencia3, blueSequencia4, blueSequencia5, blueSequencia6, blueSequencia7, blueSequencia8,blueKi, blueEspecial, blueTransformacao);
        desligarTodasAsFormas();
        vegetaUltraEgo = true;
        dimensoesVegetaX = 200;
        dimensoesVegetaY = 200;
        trocarAtaques(egoSequencia1, egoSequencia2, egoSequencia3, egoSequencia4, egoSequencia5, egoSequencia6, egoSequencia7, egoSequencia8);
        elevarKiSprites = egoKi;
        especial = egoEspecial;
        explosao = explosaoEspecial;
        frente = frenteEgo;
        tras = trasEgo;
        cima = cimaEgo;
        spriteAtual = frente;
    }

    private void aplicarFormaDestino() {
        if (formaDestinoTransformacao == 1) {
            usarSsj();
        } else if (formaDestinoTransformacao == 2) {
            usarDeus();
        } else if (formaDestinoTransformacao == 3) {
            usarBlue();
        } else if (formaDestinoTransformacao == 4) {
            usarUltraEgo();
        }

        formaDestinoTransformacao = 0;
    }

    private void iniciarTransformacaoSsj() {
        forca += 1;
        vegetaVida = 200;
        dimensoesVegetaX = 200;
        dimensoesVegetaY = 200;
        especialDano += 10;

        transformacao = ssjTransformacao;
        indiceTransformacao = 0;
        transformando = true;
        formaDestinoTransformacao = 1;
    }

    private void iniciarTransformacaoDeus() {
        vegetaVida = 300;
        forca += 1;
        especialDano += 20;

        transformacao = deusTransformacao;
        indiceTransformacao = 0;
        transformando = true;
        formaDestinoTransformacao = 2;
    }

    private void iniciarTransformacaoBlue() {
        vegetaVida = 400;
        forca += 1;
        especialDano += 10;
        dimensoesVegetaX = 200;
        dimensoesVegetaY = 200;

        transformacao = blueTransformacao;
        indiceTransformacao = 0;
        transformando = true;
        formaDestinoTransformacao = 3;
    }

    private void iniciarTransformacaoUltraEgo() {
        vegetaVida = 1000;
        forca += 1;
        especialDano += 10;
        dimensoesVegetaX = 200;
        dimensoesVegetaY = 200;

        transformacao = egoTransformacao;
        indiceTransformacao = 0;
        transformando = true;
        formaDestinoTransformacao = 4;
    }

    public void desenharEspecial(Graphics g, Goku goku) {
        if (explodindo) {
            if (!explosao.isEmpty()) {
                g.drawImage(explosao.get(indiceExplosao), posXGoku - 200, posYGoku - 200, 600, 600, null);
                if(somExplosao.terminou() && podeExplodir){
                    somExplosao.tocarSom("/sons/explosao.wav");
                    somExplosao.setVolume(2.0f);
                    podeExplodir = false;
                }
            }
            return;
        }else{podeExplodir = true;}

        if (!especialEnergia) {
            return;
        }
        // Desenha a energia e verifica colisao com o Goku.

        desenharEnergiaEspecial(g);
        int larguraEspecial = XEnergia + 128;
        if (larguraEspecial < 128) {
            larguraEspecial = 128;
        }
        Rectangle boundsEspecial = new Rectangle(posX - XEnergia, posY + energiaY, larguraEspecial, 100);

        if (!especialAcertou && boundsEspecial.intersects(goku.getBounds())) {
            if(vegetaUltraEgo){
                vegetaVida += 100;
            }
            goku.receberDano(especialDano);
            especialAcertou = true;

            // Guarda a posicao somente no instante do acerto. Cada forma usa
            // sua lista de explosao; a forma Deus usa apenas deusExplosao.
            posXGoku = goku.posX;
            posYGoku = goku.posY;
            indiceExplosao = 0;
            explodindo = true;

            return;
        }

        int larguraPontaEnergia = vegetaDeus ? 300 : 128;
        if (posX - XEnergia + larguraPontaEnergia <= 0) {
            finalizarEspecial();
        }
    }

    private void desenharEnergiaEspecial(Graphics g) {


        if (vegetaDeus) {
            energiaY = 0;
            g.drawImage(energiaEspecialDeusImg, posX - XEnergia, posY + energiaY, 300, 200, null);
            if (finalFlash.terminou()){
                finalFlash.tocarSom("/sons/vegeta/finalFlash.wav");
            }
            return;
        }

        if (pontos < 30) {
            energiaY = 0;
        } else if (pontos >= 30 && pontos < 40) {
            energiaY = 20;
        } else {
            energiaY = 0;
        }

        // Largura cresce conforme XEnergia aumenta.
        // O timerEnergiaEspecial aumenta XEnergia de 50 em 50.
        int larguraEnergia = XEnergia;
        if (larguraEnergia < 0) {
            larguraEnergia = 0;
        }

        if (mostrarPoeiraEspecial
                && indicePoeiraEspecial >= 0
                && indicePoeiraEspecial < poeiraEspecial.size()) {
            g.drawImage(poeiraEspecial.get(indicePoeiraEspecial), posX, posY + 20, 256, 256, null);
        }

        if (mostrarAnel && indiceAnel >= 0 && indiceAnel < anel.size()) {
            g.drawImage(anel.get(indiceAnel), posX - 300, posY + 20, 256, 256, null);
        }
        g.drawImage(energiaEspecialMeioImg, posX - larguraEnergia, posY + energiaY, larguraEnergia, 200, null);
        g.drawImage(energiaEspecialInicioImg, posX - 100, posY + energiaY, 128, 200, null);
        g.drawImage(energiaEspecialFimImg, posX - larguraEnergia - 20, posY + energiaY, 128, 200, null);
        if(finalFlash.terminou()){
            finalFlash.tocarSom("/sons/vegeta/finalFlash.wav");
        }
    }

    private int totalSequenciasAtaque() {
        if (vegetaBase) {
            return 5;
        }

        if (vegetaSsj) {
            return 6;
        }

        if (vegetaUltraEgo) {
            return 8;
        }

        return 7;
    }

    private void atacarForma(int totalSequencias) {
        // Pega a lista de frames da sequencia atual.
        ArrayList<Image> sequenciaAtual = null;
        switch (sequencia) {
            case 1:
                sequenciaAtual = sequencia1;
                break;
            case 2:
                sequenciaAtual = sequencia2;
                break;
            case 3:
                sequenciaAtual = sequencia3;
                break;
            case 4:
                sequenciaAtual = sequencia4;
                break;
            case 5:
                sequenciaAtual = sequencia5;
                break;
            case 6:
                sequenciaAtual = sequencia6;
                break;
            case 7:
                sequenciaAtual = sequencia7;
                break;
            case 8:
                sequenciaAtual = sequencia8;
                break;
            default:
                break;
        }

        if (sequenciaAtual == null || sequencia > totalSequencias) {
            // Se a sequencia acabou ou nao existe, encerra o golpe.
            finalizarAtaque(totalSequencias);
            return;
        }

        if (indiceAtaque >= sequenciaAtual.size()) {
            // No fim da animacao, libera a chance de causar dano.
            tirarVidaGoku = true;
            finalizarAtaque(totalSequencias);
            return;
        }

        // Mostra o frame atual do ataque.
        spriteAtual = sequenciaAtual.get(indiceAtaque);
        indiceAtaque++;
    }

    private void finalizarAtaque(int totalSequencias) {
        // Toca som de soco ao terminar uma sequencia.
        soco.tocarSom("/sons/soco.wav");

        // Para de atacar ate a IA mandar atacar de novo.
        atacar = false;
        sequencia++;

        if (sequencia > totalSequencias) {
            // Volta para a primeira sequencia depois da ultima.
            sequencia = 1;
        }

        // Reinicia o frame e recompensa o Vegeta com pontos.
        // pontosEspecial alimenta iniciarEspecialSeCarregado().
        indiceAtaque = 0;
        pontos++;
        pontosEspecial += 6;
    }

    public void atualizarIa(Goku goku) {
        // Se tiver pontos, tenta iniciar o especial automaticamente.

        iniciarEspecialSeCarregado();

        if(goku.ativarEspecial && !goku.UltraInstintoAtivo && !goku.InstintoIncompletoAtivo){
            if(posX >= 1800){
                return;
            }
            posX += 15;
            spriteAtual = tras;
            return;
        }

        if (!podeAgir(goku)) {
            // Se estiver ocupado, nao move nem ataca.
            return;
        }

        // Persegue o Goku e tenta bater no infeliz, se estiver perto.
        moverAte(goku);
        atualizarAtaqueContra(goku);
    }

    public void iniciarKi() {
        // Quando o especial ja esta carregado, nao continua carregando ki.
        // Isso evita passar muito de 30 pontosEspecial antes de soltar o especial.
        if(pontosEspecial >= 400){
            elevarKi = false;
            return;
        }


        // Marca que o Vegeta esta carregando ki.
        // atualizar() le essa flag para trocar sprites e aumentar pontos.
        elevarKi = true;
    }

    public void iniciarEspecialSeCarregado() {

        if (pontosEspecial >= 400 && !ativarEspecial && !finalizandoKi && !transformando && !vegetaAparecendo) {
            soltarKi();
            ativarEspecial = true;
            atacar = false;
        }
    }

    public void desenharBarraKi(Graphics g){
        g.drawImage(barraKiImg,1800,60, - pontosEspecial, 50,null);
    }

    private boolean podeAgir(Goku goku) {

        return !vegetaAparecendo && !elevarKi && !finalizandoKi && !ativarEspecial && !transformando && !goku.ativarEspecial;
    }

    private void moverAte(Goku goku) {
        if(!goku.Transformando && !goku.TransformandoDeus && !goku.TransformandoBlue &&
            !goku.TransformandoInstintoIncompleto &&
            !goku.TransformandoUltraInstinto && !goku.TransformandoInstintoIncompleto){

            if (posX < goku.posX + 90) {
                posX += 15;
                spriteAtual = tras;
            } else if (posX > goku.posX + 100) {

                posX -= 15;
                spriteAtual = frente;
            }


            if (posY < goku.posY - 5) {
                posY += 15;
                spriteAtual = cima;
            } else if (posY > goku.posY + 50) {
                posY -= 15;
                spriteAtual = cima;
            }

            // Limites de tela: impede o Vegeta de sair da area visivel
            // enquanto persegue o Goku (a tela e 1920x1080).
            if (posX < 0) {
                posX = 0;
            }
            if (posX > 1700) {
                posX = 1500;
            }
            if (posY < 0) {
                posY = 0;
            }
            if (posY > 830) {
                posY = 830;
            }
        }
    }

    private void atualizarAtaqueContra(Goku goku) {
        if(goku.AtivarEspecialUltraInstinto){
            return;
        }
        int distanciaX = goku.posX - posX;
        int distanciaY = goku.posY - posY;
        atacar = (distanciaX * distanciaX + distanciaY * distanciaY) <= 40000;

        Rectangle boundsAtaque = new Rectangle(posX - 20, posY + 70, 16, 16);
        if (atacar && tirarVidaGoku && boundsAtaque.intersects(goku.getBounds())) {

            goku.receberDano(forca);
            tirarVidaGoku = false;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(posX,posY,dimensoesVegetaX,dimensoesVegetaY);
    }

    public void iniciarExplosaoAoSerAtingido(int centroXImpacto, int centroYImpacto) {
        if (explosaoEspecial.isEmpty()) {
            return;
        }

        // Mantem o efeito no ponto exato em que a energia encontrou o Vegeta.
        posXExplosaoAoSerAtingido = centroXImpacto - 300;
        posYExplosaoAoSerAtingido = centroYImpacto - 300;
        indiceExplosaoAoSerAtingido = 0;
        explosaoAoSerAtingido = true;
    }

    public void desenharExplosaoAoSerAtingido(Graphics g) {
        if (!explosaoAoSerAtingido || explosaoEspecial.isEmpty()) {
            return;
        }

        g.drawImage(explosaoEspecial.get(indiceExplosaoAoSerAtingido),posXExplosaoAoSerAtingido, posYExplosaoAoSerAtingido, 600,600,null
        );
    }

    // Atualiza a animacao do ki.
    public void atualizar() {
        if (vegetaAparecendo) {
            return;
        }

        if (transformando) {
            return;
        }

        if (finalizandoKi) {

            contadorFimKi++;

            // Depois de um curto tempo, libera o Vegeta para voltar ao normal.
            if (contadorFimKi >= 15) {
                finalizandoKi = false;
                contadorFimKi = 0;
            }

            return;
        }


        if (!elevarKi) {
            contadorKi = 0;
            indicePoeiraKi = 0;
            return;
        }

        // Este bloco faz a animacao trocar de imagem a cada 5 chamadas.
        contadorKi++;
        if (contadorKi < 5) {
            return;
        }

        contadorKi = 0;

        if (!poeiraKi.isEmpty()) {
            indicePoeiraKi++;
            if (indicePoeiraKi >= poeiraKi.size()) {
                indicePoeiraKi = 0;
            }
        }

        // Escolhe quais dois frames vao piscar enquanto carrega ki.
        int frameA;
        int frameB;

        if (vegetaDeus) {

            pontosEspecial += 6;
            frameA = 0;
            frameB = 1;
            pontos += 1;
        } else if (vegetaSsj) {

            //dependem da lista carregada em iniciarTransformacaoSsj().
            pontosEspecial++;
            frameA = 8;
            frameB = 9;
            pontos += 1;
            pontosEspecial += 6;
        } else if(vegetaBlue || vegetaUltraEgo) {
            frameA = 0;
            frameB = 1;
            pontos += 1;
            pontosEspecial += 6;

        }else {
            // Base alterna entre frames simples.
            // Esses indicies dependem da lista carregada em carregarBase().
            pontosEspecial += 6;
            pontos += 1;
            frameA = 1;
            frameB = 2;
        }

        if (indiceKiNormal) {
            mostrarSpriteKi(frameA);
        } else {
            mostrarSpriteKi(frameB);

        }

        // Inverte para na proxima vez, usar o outro frame.
        indiceKiNormal = !indiceKiNormal;
    }

    public void soltarKi() {
        // finalizandoKi fica true no fim para mostrar o ultimo sprite
        // por alguns ticks e impedir que a IA aja imediatamente.
        elevarKi = false;

        indiceKiNormal = true;
        contadorKi = 0;
        indicePoeiraKi = 0;

        // Indice 3 = quarta imagem da lista.

        mostrarSpriteKi(3);

        finalizandoKi = true;
        contadorFimKi = 0;
    }

    private void mostrarSpriteKi(int indice) {
        // Evita erro se nao houver sprites de ki carregados.
        if (elevarKiSprites.isEmpty()) {
            return;
        }
        if (indice < 0) {

            indice = 0;
        }
        if (indice >= elevarKiSprites.size()) {

            indice = elevarKiSprites.size() - 1;
        }

        spriteAtual = elevarKiSprites.get(indice);
    }

    public void cancelarEspecialAntesDaEnergia() {
        // Cancela apenas se a animacao comecou mas a energia ainda nao saiu.
        if (ativarEspecial && !especialEnergia) {
            finalizarEspecial();
        }
    }

    private void finalizarEspecial() {
        // Reseta tudo que controla o especial.
        // Esse metodo e chamado quando a energia sai da tela, quando o
        // especial Deus acerta ou quando o jogador cancela antes da energia.
        ativarEspecial = false;
        especialEnergia = false;
        explodindo = false;
        mostrarAnel = true;
        mostrarPoeiraEspecial = true;
        indicePoeiraEspecial = 0;
        indiceAnel = 0;
        indiceExplosao = 0;
        indiceEspecial = 0;
        XEnergia = 0;
        pontosEspecial = 0;
        especialAcertou = false;
        timerEnergiaEspecial.stop();
    }

    public void desenharVida(Graphics g) {
        // Barra negativa desenha da direita para a esquerda.
        // drawImage nao desenha com largura negativa, entao a barra e
        // desenhada com largura positiva, terminando (ancorada) em x = 1800.
        // A imagem tem margem transparente, entao dobra o tamanho para
        // o quadrado vermelho preencher toda a barra.
        int tamBarraVidaX = vegetaVida / 2;
        if(vegetaUltraEgo){
            tamBarraVidaX = vegetaVida / 4;
        }
        if (tamBarraVidaX < 0) {
            tamBarraVidaX = 0;
        }
        g.drawImage(barraDeVida, 1800 - tamBarraVidaX * 2, 10, tamBarraVidaX * 2, 50, null);
    }

    public void desenharVegeta(Graphics g) {
        // Desenha o sprite atual no tamanho da forma atual.
        g.drawImage(spriteAtual, posX, posY, dimensoesVegetaX, dimensoesVegetaY, null);

        if (elevarKi && indicePoeiraKi >= 0 && indicePoeiraKi < poeiraKi.size()) {
            g.drawImage(poeiraKi.get(indicePoeiraKi), posX - 20, posY + 45, 180,180,null);
        }

        if (mostrarGolpe && !golpes.isEmpty()) {
            int centroX = posX + (dimensoesVegetaX / 2);
            int centroY = posY + (dimensoesVegetaY / 2);
            g.drawImage(golpes.get(indiceGolpe), centroX - 100, centroY - 10, 200, 100, null);
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

    // Importa uma imagem da pasta de recursos.
    private Image load(String caminho) {
        // Sprites de personagem sao desenhados com no maximo 200px;
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

    private void limparListas(ArrayList<Image>... listas) {

    for (int i = 0; i < listas.length; i++) {
        listas[i].clear();
    }

}

    public void receberDano(double dano) {
        this.vegetaVida -= dano;
        indiceGolpe = 0;
        contadorGolpe = 0;
        mostrarGolpe = true;
    }
}
