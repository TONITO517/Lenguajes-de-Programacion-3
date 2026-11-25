package ejercicio1_tienda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
/**
 * Aplicación Swing para la gestión de un producto en tienda mediante
 * enlace de datos manual (Data Binding) entre la interfaz gráfica y el modelo Producto.
 */
public class AppTienda extends JFrame {
    private final Producto producto;
    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtStock;
    private final JTextField txtCategoria;
    private final JButton btnActualizar;
    private final JLabel lblInfoProducto;
    /**
     * Constructor que inicializa el modelo y construye la interfaz gráfica.
     */
    public AppTienda() {
        super("Gestión de Producto en Tienda - Data Binding Manual");
        // Inicialización del modelo con datos iniciales de referencia
        this.producto = new Producto("Laptop Lenovo ThinkPad E14", 3499.90, 15, "Computación");
        // Configuración principal de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(14, 16, 14, 16));
        getContentPane().setBackground(new Color(245, 247, 250));
        // Panel de encabezado
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setOpaque(false);
        JLabel lblEncabezado = new JLabel("Registro y Actualización de Producto");
        lblEncabezado.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblEncabezado.setForeground(new Color(27, 54, 93));
        JLabel lblSubtitulo = new JLabel("Sincronización manual entre formulario Swing y modelo Producto");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(90, 102, 118));
        panelTitulo.add(lblEncabezado, BorderLayout.NORTH);
        panelTitulo.add(lblSubtitulo, BorderLayout.SOUTH);
        add(panelTitulo, BorderLayout.NORTH);
        // Panel central con formulario ordenado en GridBagLayout
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(205, 214, 224)),
                "Datos del Producto",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(27, 54, 93)
        ),
                new EmptyBorder(10, 12, 10, 12)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Font fuenteEtiqueta = new Font("Segoe UI", Font.BOLD, 12);
        Font fuenteCampo = new Font("Segoe UI", Font.PLAIN, 13);
        // Campos de texto precargados desde el modelo
        txtNombre = crearCampoTexto(producto.getNombre(), fuenteCampo);
        txtPrecio = crearCampoTexto(String.format(Locale.US, "%.2f", producto.getPrecio()), fuenteCampo);
        txtStock = crearCampoTexto(String.valueOf(producto.getCantidadStock()), fuenteCampo);
        txtCategoria = crearCampoTexto(producto.getCategoria(), fuenteCampo);
        agregarFilaFormulario(panelFormulario, gbc, 0, "Nombre del producto:", txtNombre, fuenteEtiqueta);
        agregarFilaFormulario(panelFormulario, gbc, 1, "Precio (S/):", txtPrecio, fuenteEtiqueta);
        agregarFilaFormulario(panelFormulario, gbc, 2, "Cantidad en stock:", txtStock, fuenteEtiqueta);
        agregarFilaFormulario(panelFormulario, gbc, 3, "Categoría:", txtCategoria, fuenteEtiqueta);
        // Panel para el botón de actualización
        btnActualizar = new JButton("Actualizar Producto");
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnActualizar.setBackground(new Color(27, 94, 168));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setOpaque(true);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setPreferredSize(new Dimension(190, 34));
        btnActualizar.addActionListener(new ManejadorActualizarProducto());
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 4));
        panelBoton.setOpaque(false);
        panelBoton.add(btnActualizar);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 6, 2, 6);
        panelFormulario.add(panelBoton, gbc);
        add(panelFormulario, BorderLayout.CENTER);
        // Panel inferior para mostrar los datos sincronizados del modelo mediante HTML
        JPanel panelResumen = new JPanel(new BorderLayout());
        panelResumen.setBackground(new Color(236, 243, 252));
        panelResumen.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(165, 192, 224)),
                "Estado Actual del Modelo (Producto)",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(27, 54, 93)
        ),
                new EmptyBorder(8, 12, 8, 12)
        ));
        lblInfoProducto = new JLabel();
        lblInfoProducto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelResumen.add(lblInfoProducto, BorderLayout.CENTER);
        add(panelResumen, BorderLayout.SOUTH);
        // Reflejar el estado inicial del modelo en la vista
        actualizarEtiquetaDesdeModelo();
        setMinimumSize(new Dimension(520, 440));
        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Crea un JTextField con formato consistente.
     */
    private JTextField crearCampoTexto(String valorInicial, Font fuente) {
        JTextField campo = new JTextField(valorInicial, 24);
        campo.setFont(fuente);
        return campo;
    }
    /**
     * Agrega una fila etiqueta + campo de texto al panel con GridBagLayout.
     */
    private void agregarFilaFormulario(JPanel panel, GridBagConstraints gbc, int fila,
            String textoEtiqueta, JTextField campo, Font fuenteEtiqueta) {
        JLabel etiqueta = new JLabel(textoEtiqueta);
        etiqueta.setFont(fuenteEtiqueta);
        etiqueta.setForeground(new Color(45, 55, 72));
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.weightx = 0.32;
        panel.add(etiqueta, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.68;
        panel.add(campo, gbc);
    }
    /**
     * Sincroniza la vista (JLabel en formato HTML) leyendo directamente
     * los atributos actuales desde los métodos get del modelo Producto.
     */
    private void actualizarEtiquetaDesdeModelo() {
        String html = String.format(
                Locale.US,
                "<html><body style='font-family:Segoe UI; font-size:11pt; color:#1E293B;'>"
                + "<table cellpadding='3' cellspacing='0'>"
                + "<tr><td><b>Nombre:</b></td><td>%s</td></tr>"
                + "<tr><td><b>Precio unitario:</b></td><td>S/ %.2f</td></tr>"
                + "<tr><td><b>Cantidad en stock:</b></td><td>%d unidades</td></tr>"
                + "<tr><td><b>Categoría:</b></td><td>%s</td></tr>"
                + "</table></body></html>",
                escaparHtml(producto.getNombre()),
                producto.getPrecio(),
                producto.getCantidadStock(),
                escaparHtml(producto.getCategoria())
        );
        lblInfoProducto.setText(html);
    }
    /**
     * Escapa caracteres especiales para evitar conflictos de renderizado HTML en JLabel.
     */
    private String escaparHtml(String texto) {
        return texto.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
    /**
     * Clase interna manejadora del evento del botón "Actualizar Producto".
     * Realiza la validación de entradas, actualiza el modelo mediante sus métodos set
     * y sincroniza la vista con el estado actualizado del modelo.
     */
    private class ManejadorActualizarProducto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = txtNombre.getText().trim();
            String precioTexto = txtPrecio.getText().trim();
            String stockTexto = txtStock.getText().trim();
            String categoria = txtCategoria.getText().trim();
            // Validación de campos vacíos
            if (nombre.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(
                        AppTienda.this,
                        "Todos los campos son obligatorios. Complete la información del producto.",
                        "Campos Incompletos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            try {
                double precio = Double.parseDouble(precioTexto);
                int cantidadStock = Integer.parseInt(stockTexto);
                // Validación de rango numérico no negativo
                if (precio < 0 || cantidadStock < 0) {
                    JOptionPane.showMessageDialog(
                            AppTienda.this,
                            "El precio y la cantidad en stock no pueden ser valores negativos.",
                            "Valores Inválidos",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                // Actualización manual del modelo mediante métodos setter
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setCantidadStock(cantidadStock);
                producto.setCategoria(categoria);
                // Sincronización de la vista leyendo desde el modelo
                actualizarEtiquetaDesdeModelo();
                // Confirmación en consola del estado actualizado del modelo
                System.out.println("Producto actualizado correctamente en el modelo: " + producto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        AppTienda.this,
                        "Formato numérico incorrecto. Ingrese un decimal válido para el precio y un entero para el stock.",
                        "Error de Formato Numérico",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    /**
     * Permite ejecutar programáticamente la actualización de prueba o interactuar con los campos.
     */
    public void cargarYActualizar(String nombre, String precio, String stock, String categoria) {
        txtNombre.setText(nombre);
        txtPrecio.setText(precio);
        txtStock.setText(stock);
        txtCategoria.setText(categoria);
        btnActualizar.doClick();
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
            AppTienda ventana = new AppTienda();
            ventana.setVisible(true);
        });
    }
}
