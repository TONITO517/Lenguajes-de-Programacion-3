package ejercicio2_temperaturas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
/**
 * Ventana principal que permite ingresar las temperaturas diarias de Lunes a Domingo
 * y visualizarlas de forma dinámica en un gráfico de líneas 2D.
 */
public class AppTemperaturas extends JFrame {
    private static final String[] NOMBRES_DIAS = {
        "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    };
    private static final double[] TEMPERATURAS_INICIALES = {
        18.5, 21.0, 19.8, 23.4, 25.1, 22.0, 20.3
    };
    private final JTextField[] camposTemperaturas;
    private final JButton btnMostrarGrafico;
    private final PanelGraficoTemperaturas panelGrafico;
    private final JLabel lblResumenEstadistico;
    /**
     * Constructor que configura el formulario superior y el panel de trazado central.
     */
    public AppTemperaturas() {
        super("Gráfico de Temperaturas Semanales - Java 2D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(12, 14, 12, 14));
        getContentPane().setBackground(new Color(245, 247, 250));
        // Panel superior con los 7 campos de temperatura y el botón de actualización
        JPanel panelSuperior = new JPanel(new BorderLayout(8, 8));
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(205, 214, 224)),
                "Ingreso de Temperaturas Diarias (°C)",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(27, 54, 93)
        ),
                new EmptyBorder(8, 10, 8, 10)
        ));
        JPanel panelDias = new JPanel(new GridLayout(2, 7, 8, 4));
        panelDias.setOpaque(false);
        Font fuenteDia = new Font("Segoe UI", Font.BOLD, 12);
        Font fuenteCampo = new Font("Segoe UI", Font.PLAIN, 13);
        for (String dia : NOMBRES_DIAS) {
            JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
            lblDia.setFont(fuenteDia);
            lblDia.setForeground(new Color(45, 55, 72));
            panelDias.add(lblDia);
        }
        camposTemperaturas = new JTextField[7];
        for (int i = 0; i < 7; i++) {
            JTextField txtTemp = new JTextField(String.format(Locale.US, "%.1f", TEMPERATURAS_INICIALES[i]));
            txtTemp.setFont(fuenteCampo);
            txtTemp.setHorizontalAlignment(SwingConstants.CENTER);
            camposTemperaturas[i] = txtTemp;
            panelDias.add(txtTemp);
        }
        btnMostrarGrafico = new JButton("Mostrar Gráfico");
        btnMostrarGrafico.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnMostrarGrafico.setBackground(new Color(27, 94, 168));
        btnMostrarGrafico.setForeground(Color.WHITE);
        btnMostrarGrafico.setOpaque(true);
        btnMostrarGrafico.setFocusPainted(false);
        btnMostrarGrafico.setPreferredSize(new Dimension(170, 32));
        btnMostrarGrafico.addActionListener(new ManejadorMostrarGrafico());
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
        panelBoton.setOpaque(false);
        panelBoton.add(btnMostrarGrafico);
        panelSuperior.add(panelDias, BorderLayout.CENTER);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);
        // Panel central con el gráfico de líneas personalizado
        panelGrafico = new PanelGraficoTemperaturas(TEMPERATURAS_INICIALES);
        panelGrafico.setBorder(BorderFactory.createLineBorder(new Color(205, 214, 224)));
        add(panelGrafico, BorderLayout.CENTER);
        // Barra inferior con estadísticas rápidas de la semana
        lblResumenEstadistico = new JLabel("", SwingConstants.CENTER);
        lblResumenEstadistico.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblResumenEstadistico.setForeground(new Color(51, 65, 85));
        actualizarEstadisticas(TEMPERATURAS_INICIALES);
        add(lblResumenEstadistico, BorderLayout.SOUTH);
        setMinimumSize(new Dimension(760, 560));
        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Calcula y muestra el mínimo, máximo y promedio de las temperaturas ingresadas.
     */
    private void actualizarEstadisticas(double[] valores) {
        double min = valores[0];
        double max = valores[0];
        double suma = 0.0;
        for (double v : valores) {
            if (v < min) {
                min = v;
            }
            if (v > max) {
                max = v;
            }
            suma += v;
        }
        double promedio = suma / valores.length;
        lblResumenEstadistico.setText(String.format(
                Locale.US,
                "Mínima semanal: %.1f°C | Máxima semanal: %.1f°C | Promedio: %.1f°C",
                min, max, promedio
        ));
    }
    /**
     * Clase interna manejadora del evento del botón "Mostrar Gráfico".
     * Valida los 7 valores numéricos y solicita el repintado del panel gráfico.
     */
    private class ManejadorMostrarGrafico implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double[] nuevasTemperaturas = new double[7];
            for (int i = 0; i < 7; i++) {
                String texto = camposTemperaturas[i].getText().trim();
                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            AppTemperaturas.this,
                            "Debe ingresar un valor de temperatura para el día " + NOMBRES_DIAS[i] + ".",
                            "Dato Faltante",
                            JOptionPane.WARNING_MESSAGE
                    );
                    camposTemperaturas[i].requestFocus();
                    return;
                }
                try {
                    nuevasTemperaturas[i] = Double.parseDouble(texto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            AppTemperaturas.this,
                            "El valor ingresado para " + NOMBRES_DIAS[i] + " (\"" + texto
                            + "\") no es un número válido.",
                            "Error de Formato Numérico",
                            JOptionPane.ERROR_MESSAGE
                    );
                    camposTemperaturas[i].requestFocus();
                    return;
                }
            }
            // Actualización del panel gráfico (internamente invoca repaint())
            panelGrafico.setTemperaturas(nuevasTemperaturas);
            actualizarEstadisticas(nuevasTemperaturas);
            System.out.print("Temperaturas actualizadas en el gráfico: ");
            for (int i = 0; i < 7; i++) {
                System.out.printf(Locale.US, "%s=%.1f°C%s", NOMBRES_DIAS[i], nuevasTemperaturas[i],
                        (i < 6 ? ", " : System.lineSeparator()));
            }
        }
    }
    /**
     * Permite cargar un nuevo conjunto de 7 temperaturas y disparar la actualización del gráfico.
     */
    public void cargarYMostrar(String[] valores) {
        for (int i = 0; i < 7 && i < valores.length; i++) {
            camposTemperaturas[i].setText(valores[i]);
        }
        btnMostrarGrafico.doClick();
    }
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
            // Se conserva el Look and Feel predeterminado si Nimbus no está disponible
        }
        SwingUtilities.invokeLater(() -> {
            AppTemperaturas ventana = new AppTemperaturas();
            ventana.setVisible(true);
        });
    }
}
