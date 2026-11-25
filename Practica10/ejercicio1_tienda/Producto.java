package ejercicio1_tienda;
/**
 * Clase modelo que representa un producto dentro de una tienda.
 * Encapsula los datos principales y provee métodos de acceso (get/set)
 * para soportar el enlace manual de datos (Data Binding) con la interfaz Swing.
 */
public class Producto {
    private String nombre;
    private double precio;
    private int cantidadStock;
    private String categoria;
    /**
     * Constructor por defecto que inicializa un producto con valores base.
     */
    public Producto() {
        this.nombre = "";
        this.precio = 0.0;
        this.cantidadStock = 0;
        this.categoria = "";
    }
    /**
     * Constructor parametrizado para inicializar todos los atributos del producto.
     *
     * @param nombre Nombre descriptivo del producto.
     * @param precio Precio unitario del producto.
     * @param cantidadStock Cantidad disponible en inventario.
     * @param categoria Categoría comercial a la que pertenece.
     */
    public Producto(String nombre, double precio, int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }
    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidadStock() {
        return cantidadStock;
    }
    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return String.format(
                "Producto{nombre='%s', precio=%.2f, cantidadStock=%d, categoria='%s'}",
                nombre, precio, cantidadStock, categoria
        );
    }
}
