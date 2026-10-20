package EJ2;

public class Estudiante {
    private int id;
    private String nombre;
    private String carrera;
    private double nota;
    public Estudiante(int id, String nombre, String carrera, double nota) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.nota = nota;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCarrera() {
        return carrera;
    }
    public double getNota() {
        return nota;
    }
}