import javax.swing.*;
import java.awt.*;
public class AppProducto {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppProducto::crearGUI);
    }

    private static void crearGUI() {
        JFrame frame = new JFrame("Gestión de Producto - Binding Manual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 360);
        frame.setLocationRelativeTo(null);
        Producto producto = new Producto("Laptop", 2500.50, 10, "Tecnología");

        // Componentes (get del modelo -> setText de la vista)
        JTextField nombreField = new JTextField(producto.getNombre(), 15);
        JTextField precioField = new JTextField(String.valueOf(producto.getPrecio()), 15);
        JTextField stockField = new JTextField(String.valueOf(producto.getCantidadStock()), 15);
        JTextField categoriaField = new JTextField(producto.getCategoria(), 15);
        JButton boton = new JButton("Actualizar Producto");
        JLabel resultado = new JLabel();
        mostrar(resultado, producto);
        boton.addActionListener(e -> {
            try {
                String nombre = nombreField.getText().trim();
                String categoria = categoriaField.getText().trim();
                if (nombre.isEmpty() || categoria.isEmpty()) {
                    throw new IllegalArgumentException("Nombre y categoría no pueden estar vacíos.");
                }
                double precio = Double.parseDouble(precioField.getText().trim());
                int stock = Integer.parseInt(stockField.getText().trim());
                if (precio < 0 || stock < 0) {
                    throw new IllegalArgumentException("Precio y stock no pueden ser negativos.");
                }
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setCantidadStock(stock);
                producto.setCategoria(categoria);

                mostrar(resultado, producto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Precio debe ser decimal (ej. 25.50) y stock un entero.",
                        "Dato inválido", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(),
                        "Dato inválido", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 8));
        form.add(new JLabel("Nombre:"));    form.add(nombreField);
        form.add(new JLabel("Precio:"));    form.add(precioField);
        form.add(new JLabel("Stock:"));     form.add(stockField);
        form.add(new JLabel("Categoría:")); form.add(categoriaField);

        JPanel centro = new JPanel(new BorderLayout(5, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        centro.add(form, BorderLayout.NORTH);
        centro.add(boton, BorderLayout.CENTER);
        centro.add(resultado, BorderLayout.SOUTH);

        frame.add(centro);
        frame.setVisible(true);
    }
    private static void mostrar(JLabel label, Producto p) {
        label.setText("<html><b>Producto actualizado:</b><br>"
                + "Nombre: " + p.getNombre() + "<br>"
                + "Precio: S/ " + String.format("%.2f", p.getPrecio()) + "<br>"
                + "Stock: " + p.getCantidadStock() + " unidades<br>"
                + "Categoría: " + p.getCategoria() + "</html>");
    }
}
