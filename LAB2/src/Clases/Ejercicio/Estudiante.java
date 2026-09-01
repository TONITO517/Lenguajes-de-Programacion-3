package Clases.Ejercicio;

public class Estudiante extends Persona {
    private String codigo;
    private Curso[] cursosInscritos;
    private int numCursosInscritos;
    public static final int max_cursos = 6; // constante: límite de cursos por estudiante
    public Estudiante(int id, String nombre, String apellido, String codigo) {
        super(id, nombre, apellido);
        this.codigo = codigo;
        this.cursosInscritos = new Curso[max_cursos];
        this.numCursosInscritos = 0;
    }
    public String getCodigo() {
        return codigo;
    }
    // Método para inscribirse a un curso (relación de agregación con Curso)
    public boolean inscribirCurso(Curso curso) {
        if (numCursosInscritos < max_cursos) {
            cursosInscritos[numCursosInscritos] = curso;
            numCursosInscritos++;
            return true;
        }
        return false;
    }
    public int getNumCursosInscritos() {
        return numCursosInscritos;
    }
    @Override
    public String presentarse() {
        return "Soy " + getNombre() + " " + getApellido() + ", estudiante con código " + codigo;
    }
}