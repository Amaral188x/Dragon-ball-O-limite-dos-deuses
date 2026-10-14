import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;

public class Som {

    private Clip clipLoop;
    private Clip clip;
    private float volume = 1.0f;
    private String caminhoClip;
    private String caminhoClipLoop;

    public synchronized void tocarSom(String caminho) {
        try {
            if (clip == null || !clip.isOpen() || !caminho.equals(caminhoClip)) {
                if (clip != null && clip.isOpen()) {
                    clip.stop();
                    clip.close();
                }

                Clip novoClip = AudioSystem.getClip();
                try (AudioInputStream audioStream = abrirAudio(caminho)) {
                    novoClip.open(audioStream);
                } catch (Exception e) {
                    novoClip.close();
                    throw e;
                }

                clip = novoClip;
                caminhoClip = caminho;
            } else if (clip.isRunning()) {
                clip.stop();
            }

            clip.setFramePosition(0);
            aplicarVolume(clip);
            clip.start();
        } catch (Exception e) {
            System.out.println("ERRO NO MÉTODO TOCAR SOM NA CLASSE SOM. CÓDIGO DE ERRO: " + e);
        }
    }

    public synchronized void tocarLoop(String caminho) {
        try {
            if (clipLoop == null || !clipLoop.isOpen() || !caminho.equals(caminhoClipLoop)) {
                if (clipLoop != null && clipLoop.isOpen()) {
                    clipLoop.stop();
                    clipLoop.close();
                }

                Clip novoClip = AudioSystem.getClip();
                try (AudioInputStream audioStream = abrirAudio(caminho)) {
                    novoClip.open(audioStream);
                } catch (Exception e) {
                    novoClip.close();
                    throw e;
                }

                clipLoop = novoClip;
                caminhoClipLoop = caminho;
            } else if (clipLoop.isRunning()) {
                clipLoop.stop();
            }

            clipLoop.setFramePosition(0);
            aplicarVolume(clipLoop);
            clipLoop.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("ERRO NO MÉTODO TOCARLOOP DA CLASSE SOM. CÓDIGO DE ERRO: " + e);
        }
    }

    // Interrompe a reproducao sem descarregar o WAV da memoria.
    public synchronized void parar() {
        if (clipLoop != null && clipLoop.isOpen()) {
            if (clipLoop.isRunning()) {
                clipLoop.stop();
            }
            clipLoop.setFramePosition(0);
        }

        if (clip != null && clip.isOpen()) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
        }
    }

    public synchronized void setVolume(float volume) {
        this.volume = Math.max(0.0f, volume);
        aplicarVolume(clip);
        aplicarVolume(clipLoop);
    }

    private void aplicarVolume(Clip clipParaAjustar) {
        if (clipParaAjustar == null || !clipParaAjustar.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            return;
        }

        try {
            FloatControl gainControl = (FloatControl) clipParaAjustar.getControl(FloatControl.Type.MASTER_GAIN);
            float db;

            if (volume == 0.0f) {
                db = gainControl.getMinimum();
            } else {
                db = (float) (20.0 * Math.log10(volume));
                db = Math.max(gainControl.getMinimum(), Math.min(gainControl.getMaximum(), db));
            }

            gainControl.setValue(db);
        } catch (Exception e) {
            System.out.println(
              "ERRO NO SETVOLUME DA CLASSE SOM! CÓDIGO DE ERRO: " + e
            );  
        }
    }

    // RETORNA TRUE QUANDO O SOM TERMINAR
    public synchronized boolean terminou() {
        return clip != null && !clip.isRunning();
    }

    public synchronized long getPosicaoMicrosegundos() {
        return clip != null ? clip.getMicrosecondPosition() : 0;
    }

    public synchronized long getDuracaoMicrosegundos() {
        return clip != null ? clip.getMicrosecondLength() : 0;
    }

    public synchronized boolean estaTocando() {
        return clip != null && clip.isRunning();
    }

    private AudioInputStream abrirAudio(String caminho) throws Exception {
        URL recurso = getClass().getResource(caminho);
        if (recurso != null) {
            return AudioSystem.getAudioInputStream(recurso);
        }

        String caminhoRelativo = caminho.startsWith("/") ? caminho.substring(1) : caminho;
        File arquivo = new File("src", caminhoRelativo);
        return AudioSystem.getAudioInputStream(arquivo);
    }
}
