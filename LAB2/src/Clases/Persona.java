package Clases;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private Cuenta cuenta;
    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        // determine el numero de cuenta
        int numeroCuenta = id * 100; // ejemplo simple para generar el número de cuenta
        this.cuenta = new Cuenta(numeroCuenta);
    }
    // Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String toString() {
        return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", " + cuenta + "]";
    }
}