package ejercicio3;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class BotoneraSonidos {
    private Clip clip;
    private final JLabel estado = new JLabel("Selecciona un efecto.", JLabel.CENTER);

    private void mostrar() {
        JFrame ventana = new JFrame("Botonera de efectos de sonido");
        JPanel botones = new JPanel(new GridLayout(1, 3, 8, 0));
        agregarBoton(botones, "Aplausos", "aplausos.wav");
        agregarBoton(botones, "Campana", "campana.wav");
        agregarBoton(botones, "Explosión", "explosion.wav");

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(botones);
        panel.add(estado);
        ventana.setContentPane(panel);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cerrarClip();
            }
        });
        ventana.setSize(400, 130);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void agregarBoton(JPanel panel, String nombre, String archivo) {
        JButton boton = new JButton(nombre);
        boton.addActionListener(e -> reproducir(nombre, archivo));
        panel.add(boton);
    }

    private void reproducir(String nombre, String archivo) {
        cerrarClip();
        File ruta = new File("sonidos", archivo);
        try (AudioInputStream audio = AudioSystem.getAudioInputStream(ruta)) {
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            estado.setText("Efecto seleccionado: " + nombre);
        } catch (IOException | UnsupportedAudioFileException
                | LineUnavailableException | IllegalArgumentException e) {
            cerrarClip();
            estado.setText("No se pudo reproducir " + archivo);
        }
    }

    private void cerrarClip() {
        if (clip != null) {
            clip.close();
            clip = null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BotoneraSonidos().mostrar());
    }
}
