package Clases.Ejercicio;

public class Curso {
    private String nombre;
    private String categoria; // Matemáticas, Programación, etc.
    private Profesor profesor; // Agregación: el curso "tiene un" profesor
    private Estudiante[] estudiantesMatriculados;
    private int numEstudiantes;
    public static final int capacidad_maxima = 30; // constante: cupo máximo
    public static int totalCursosCreados = 0; // variable de clase: cuenta todos los cursos creados

    public Curso(String nombre, String categoria, Profesor profesor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.profesor = profesor;
        this.estudiantesMatriculados = new Estudiante[capacidad_maxima];
        this.numEstudiantes = 0;
        totalCursosCreados++; // cada vez que se crea un curso, se incrementa
    }
    public String getNombre() {
        return nombre;
    }
    public String getCategoria() {
        return categoria;
    }
    public Profesor getProfesor() {
        return profesor;
    }
    public int getNumEstudiantes() {
        return numEstudiantes;
    }
    public boolean hayCupo() {
        return numEstudiantes < capacidad_maxima;
    }
    public boolean matricularEstudiante(Estudiante estudiante) {
        if (hayCupo()) {
            estudiantesMatriculados[numEstudiantes] = estudiante;
            numEstudiantes++;
            return true;
        }
        return false;
    }

    public String toString() {
        return "Curso: " + nombre + " (" + categoria + ") - Profesor: " + profesor.getNombre() +
                " - Matriculados: " + numEstudiantes + "/" + capacidad_maxima;
    }
}