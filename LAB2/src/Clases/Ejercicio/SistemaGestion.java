package Clases.Ejercicio;

public class SistemaGestion {
    private Estudiante[] estudiantes;
    private Profesor[] profesores;
    private Curso[] cursos;
    private int numEstudiantes;
    private int numProfesores;
    private int numCursos;
    public static final int max_registros = 50; // constante

    public SistemaGestion() {
        estudiantes = new Estudiante[max_registros];
        profesores = new Profesor[max_registros];
        cursos = new Curso[max_registros];
        numEstudiantes = 0;
        numProfesores = 0;
        numCursos = 0;
    }
    public void registrarEstudiante(Estudiante e) {
        estudiantes[numEstudiantes] = e;
        numEstudiantes++;
    }
    public void registrarProfesor(Profesor p) {
        profesores[numProfesores] = p;
        numProfesores++;
    }
    public void registrarCurso(Curso c) {
        cursos[numCursos] = c;
        numCursos++;
    }
    // Muestra los cursos disponibles (con cupo)
    public void mostrarCursosDisponibles() {
        System.out.println("Cursos disponibles:");
        for (int i = 0; i < numCursos; i++) {
            if (cursos[i].hayCupo()) {
                System.out.println(cursos[i]);
            }
        }
    }
    // Demuestra polimorfismo: recorre personas de distinto tipo (Estudiante/Profesor)
    public void mostrarPresentaciones() {
        Persona[] personas = new Persona[numEstudiantes + numProfesores];
        int index = 0;
        for (int i = 0; i < numEstudiantes; i++) {
            personas[index] = estudiantes[i];
            index++;
        }
        for (int i = 0; i < numProfesores; i++) {
            personas[index] = profesores[i];
            index++;
        }
        for (Persona persona : personas) {
            System.out.println(persona.presentarse()); // cada una se presenta distinto
        }
    }
}