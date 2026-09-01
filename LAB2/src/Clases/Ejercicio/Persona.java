package Clases.Ejercicio;

public abstract class Persona {
    // Atributos de instancia
    private int id;
    private String nombre;
    private String apellido;
    // Atributo de clase (constante)
    public static final String UNIVERSIDAD = "UCSM";
    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    // Getters y setters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    // Método abstracto: cada subclase lo implementa a su manera
    public abstract String presentarse();
}