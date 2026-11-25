package ejercicio4;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class ReproductorMusica {
    private Clip clip;
    private long posicionPausa;
    private final JLabel estado = new JLabel("Pista: musica.wav", JLabel.CENTER);
    private final JButton pausar = new JButton("Pausar");
    private final JButton reanudar = new JButton("Reanudar");

    private void mostrar() {
        JFrame ventana = new JFrame("Reproductor de música");
        JButton reproducir = new JButton("Reproducir");
        reproducir.addActionListener(e -> reproducir());
        pausar.addActionListener(e -> pausar());
        reanudar.addActionListener(e -> reanudar());
        pausar.setEnabled(false);
        reanudar.setEnabled(false);

        JPanel botones = new JPanel(new GridLayout(1, 3, 8, 0));
        botones.add(reproducir);
        botones.add(pausar);
        botones.add(reanudar);
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(botones);
        panel.add(estado);
        ventana.setContentPane(panel);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (clip != null) {
                    clip.close();
                }
            }
        });
        ventana.setSize(420, 130);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void reproducir() {
        try {
            if (clip == null) {
                cargarPista();
            }
            clip.stop();
            clip.setMicrosecondPosition(0);
            clip.start();
            estado.setText("Reproduciendo desde el inicio.");
            pausar.setEnabled(true);
            reanudar.setEnabled(false);
        } catch (IOException | UnsupportedAudioFileException
                | LineUnavailableException | IllegalArgumentException e) {
            estado.setText("No se pudo reproducir musica.wav");
        }
    }

    private void cargarPista() throws IOException, UnsupportedAudioFileException,
            LineUnavailableException {
        File ruta = new File("sonidos", "musica.wav");
        try (AudioInputStream audio = AudioSystem.getAudioInputStream(ruta)) {
            Clip pista = AudioSystem.getClip();
            try {
                pista.open(audio);
            } catch (LineUnavailableException | IOException | IllegalArgumentException e) {
                pista.close();
                throw e;
            }
            clip = pista;
        }
        clip.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP
                    && e.getFramePosition() >= clip.getFrameLength()) {
                SwingUtilities.invokeLater(() -> {
                    if (!clip.isRunning() && clip.getFramePosition() >= clip.getFrameLength()) {
                        estado.setText("La pista terminó.");
                        pausar.setEnabled(false);
                        reanudar.setEnabled(false);
                    }
                });
            }
        });
    }

    private void pausar() {
        clip.stop();
        posicionPausa = clip.getMicrosecondPosition();
        estado.setText(String.format("En pausa en %.2f s.", posicionPausa / 1_000_000.0));
        pausar.setEnabled(false);
        reanudar.setEnabled(true);
    }

    private void reanudar() {
        clip.setMicrosecondPosition(posicionPausa);
        clip.start();
        estado.setText(String.format("Reanudado desde %.2f s.", posicionPausa / 1_000_000.0));
        pausar.setEnabled(true);
        reanudar.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReproductorMusica().mostrar());
    }
}
