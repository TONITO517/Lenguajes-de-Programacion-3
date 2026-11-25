package ejercicio2_temperaturas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.Locale;
import javax.swing.JPanel;
/**
 * Panel personalizado que dibuja un gráfico de líneas dinámico de las
 * temperaturas diarias de la semana utilizando Graphics2D y antialiasing.
 */
public class PanelGraficoTemperaturas extends JPanel {
    private static final String[] DIAS = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
    private double[] temperaturas;
    /**
     * Constructor que inicializa el panel con temperaturas semanales por defecto.
     *
     * @param temperaturasIniciales Arreglo de 7 valores de temperatura (Lunes a Domingo).
     */
    public PanelGraficoTemperaturas(double[] temperaturasIniciales) {
        this.temperaturas = Arrays.copyOf(temperaturasIniciales, 7);
        setPreferredSize(new Dimension(720, 400));
        setBackground(Color.WHITE);
    }
    /**
     * Actualiza el arreglo de temperaturas y solicita el repintado dinámico del gráfico.
     *
     * @param nuevasTemperaturas Arreglo con las 7 temperaturas diarias.
     */
    public void setTemperaturas(double[] nuevasTemperaturas) {
        if (nuevasTemperaturas == null || nuevasTemperaturas.length != 7) {
            throw new IllegalArgumentException("Se requieren exactamente 7 temperaturas (Lunes a Domingo).");
        }
        this.temperaturas = Arrays.copyOf(nuevasTemperaturas, 7);
        repaint();
    }
    /**
     * Devuelve una copia del arreglo actual de temperaturas.
     */
    public double[] getTemperaturas() {
        return Arrays.copyOf(temperaturas, temperaturas.length);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            // Activación de antialiasing para líneas, figuras y texto nítidos
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            int ancho = getWidth();
            int alto = getHeight();
            // Márgenes del área de trazado
            int margenIzq = 72;
            int margenDer = 36;
            int margenSup = 52;
            int margenInf = 60;
            int anchoGrafico = Math.max(ancho - margenIzq - margenDer, 100);
            int altoGrafico = Math.max(alto - margenSup - margenInf, 100);
            // Fondo sutil del área de trazado
            g2.setColor(new Color(250, 252, 255));
            g2.fillRect(margenIzq, margenSup, anchoGrafico, altoGrafico);
            // Título del gráfico
            g2.setFont(new Font("Segoe UI", Font.BOLD, 15));
            g2.setColor(new Color(27, 54, 93));
            String titulo = "Variación de Temperaturas Semanales (°C)";
            FontMetrics fmTitulo = g2.getFontMetrics();
            g2.drawString(titulo, (ancho - fmTitulo.stringWidth(titulo)) / 2, 30);
            // Cálculo de escala proporcional dinámica (mínimo y máximo)
            double minTemp = temperaturas[0];
            double maxTemp = temperaturas[0];
            for (double t : temperaturas) {
                if (t < minTemp) {
                    minTemp = t;
                }
                if (t > maxTemp) {
                    maxTemp = t;
                }
            }
            // Margen de respiro para que los puntos no queden pegados a los bordes
            double rango = maxTemp - minTemp;
            double holgura = (rango < 1.0) ? 2.5 : Math.max(1.5, rango * 0.18);
            double minEscala = Math.floor(minTemp - holgura);
            double maxEscala = Math.ceil(maxTemp + holgura);
            if (maxEscala <= minEscala) {
                maxEscala = minEscala + 5.0;
            }
            // Cuadrícula horizontal y etiquetas del eje Y
            int divisionesY = 5;
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            FontMetrics fmEjes = g2.getFontMetrics();
            for (int i = 0; i <= divisionesY; i++) {
                double fraccion = (double) i / divisionesY;
                int y = margenSup + (int) Math.round(altoGrafico * (1.0 - fraccion));
                double valorY = minEscala + fraccion * (maxEscala - minEscala);
                // Línea horizontal de cuadrícula sutil
                g2.setColor(new Color(224, 231, 240));
                g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                        10.0f, new float[]{4.0f, 4.0f}, 0.0f));
                g2.drawLine(margenIzq, y, margenIzq + anchoGrafico, y);
                // Etiqueta de temperatura en el eje Y
                String etiquetaY = String.format(Locale.US, "%.1f°C", valorY);
                g2.setColor(new Color(71, 85, 105));
                g2.drawString(etiquetaY, margenIzq - fmEjes.stringWidth(etiquetaY) - 10, y + 4);
            }
            // Dibujo de los ejes principales X e Y
            g2.setColor(new Color(51, 65, 85));
            g2.setStroke(new BasicStroke(1.8f));
            g2.drawLine(margenIzq, margenSup, margenIzq, margenSup + altoGrafico);
            g2.drawLine(margenIzq, margenSup + altoGrafico, margenIzq + anchoGrafico, margenSup + altoGrafico);
            // Cálculo de coordenadas (x, y) para cada día de la semana
            int n = temperaturas.length;
            int[] puntosX = new int[n];
            int[] puntosY = new int[n];
            int paddingHorizontal = Math.max(28, anchoGrafico / 14);
            double pasoX = (double) (anchoGrafico - 2 * paddingHorizontal) / (n - 1);
            for (int i = 0; i < n; i++) {
                puntosX[i] = margenIzq + paddingHorizontal + (int) Math.round(i * pasoX);
                double proporcionY = (temperaturas[i] - minEscala) / (maxEscala - minEscala);
                puntosY[i] = margenSup + altoGrafico - (int) Math.round(proporcionY * altoGrafico);
            }
            // Relleno degradado semitransparente bajo la curva para mayor legibilidad
            Path2D areaBajoCurva = new Path2D.Double();
            areaBajoCurva.moveTo(puntosX[0], margenSup + altoGrafico);
            for (int i = 0; i < n; i++) {
                areaBajoCurva.lineTo(puntosX[i], puntosY[i]);
            }
            areaBajoCurva.lineTo(puntosX[n - 1], margenSup + altoGrafico);
            areaBajoCurva.closePath();
            g2.setPaint(new GradientPaint(
                    0, margenSup, new Color(37, 99, 235, 55),
                    0, margenSup + altoGrafico, new Color(37, 99, 235, 5)
            ));
            g2.fill(areaBajoCurva);
            // Línea poligonal principal con BasicStroke suavizado
            g2.setColor(new Color(29, 78, 216));
            g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawPolyline(puntosX, puntosY, n);
            // Dibujo de puntos circulares, valores numéricos y etiquetas del eje X
            Font fuenteValores = new Font("Segoe UI", Font.BOLD, 11);
            Font fuenteDias = new Font("Segoe UI", Font.BOLD, 12);
            int radio = 5;
            for (int i = 0; i < n; i++) {
                // Marca vertical sutil en el eje X
                g2.setColor(new Color(148, 163, 184));
                g2.setStroke(new BasicStroke(1.2f));
                g2.drawLine(puntosX[i], margenSup + altoGrafico, puntosX[i], margenSup + altoGrafico + 5);
                // Etiqueta del día en el eje X
                g2.setFont(fuenteDias);
                g2.setColor(new Color(51, 65, 85));
                FontMetrics fmDia = g2.getFontMetrics();
                g2.drawString(DIAS[i], puntosX[i] - fmDia.stringWidth(DIAS[i]) / 2, margenSup + altoGrafico + 22);
                // Punto circular relleno con halo blanco
                g2.setColor(Color.WHITE);
                g2.fillOval(puntosX[i] - (radio + 2), puntosY[i] - (radio + 2), (radio + 2) * 2, (radio + 2) * 2);
                g2.setColor(new Color(220, 38, 38));
                g2.fillOval(puntosX[i] - radio, puntosY[i] - radio, radio * 2, radio * 2);
                // Valor exacto de la temperatura encima de cada punto con fondo suave legible
                String valorTexto = String.format(Locale.US, "%.1f°C", temperaturas[i]);
                g2.setFont(fuenteValores);
                FontMetrics fmValor = g2.getFontMetrics();
                int anchoTexto = fmValor.stringWidth(valorTexto);
                int altoTexto = fmValor.getAscent();
                int xTexto = puntosX[i] - anchoTexto / 2;
                int yTexto = Math.max(margenSup + altoTexto + 4, puntosY[i] - 14);
                g2.setColor(new Color(255, 255, 255, 220));
                g2.fillRoundRect(xTexto - 4, yTexto - altoTexto, anchoTexto + 8, altoTexto + 3, 6, 6);
                g2.setColor(new Color(30, 41, 59));
                g2.drawString(valorTexto, xTexto, yTexto);
            }
            // Etiqueta del eje X
            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
            g2.setColor(new Color(71, 85, 105));
            String tituloEjeX = "Días de la Semana";
            FontMetrics fmEjeX = g2.getFontMetrics();
            g2.drawString(tituloEjeX, margenIzq + (anchoGrafico - fmEjeX.stringWidth(tituloEjeX)) / 2, alto - 12);
        } finally {
            g2.dispose();
        }
    }
}
