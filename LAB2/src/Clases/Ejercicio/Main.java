package Clases.Ejercicio;

public class Main {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();
        // Crear profesores
        Profesor prof1 = new Profesor(1, "Karim", "Guevara", "Programación");
        Profesor prof2 = new Profesor(2, "Ángel", "Montesinos", "Data Base");
        sistema.registrarProfesor(prof1);
        sistema.registrarProfesor(prof2);
        // Crear cursos
        Curso curso1 = new Curso("Lenguajes de Programación III", "Programación", prof1);
        Curso curso2 = new Curso("Estructuras Discretas", "Algoritmos", prof2);
        sistema.registrarCurso(curso1);
        sistema.registrarCurso(curso2);
        // Crear estudiantes
        Estudiante est1 = new Estudiante(1, "Anthony", "Vega", "2024001");
        Estudiante est2 = new Estudiante(2, "Pancracio", "Carpio", "2024002");
        sistema.registrarEstudiante(est1);
        sistema.registrarEstudiante(est2);
        // Matricular estudiantes en cursos (composición: cada matrícula depende del curso)
        curso1.matricularEstudiante(est1);
        curso1.matricularEstudiante(est2);
        curso2.matricularEstudiante(est1);
        // Inscribir el curso en el propio estudiante (agregación)
        est1.inscribirCurso(curso1);
        est1.inscribirCurso(curso2);
        est2.inscribirCurso(curso1);
        // Mostrar información
        sistema.mostrarCursosDisponibles();
        System.out.println();
        sistema.mostrarPresentaciones();
        System.out.println("\nTotal de cursos creados en el sistema: " + Curso.totalCursosCreados);
    }
}