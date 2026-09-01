package Clases.Ejercicio;

public class Profesor extends Persona {
    private String especialidad;
    public Profesor(int id, String nombre, String apellido, String especialidad) {
        super(id, nombre, apellido);
        this.especialidad = especialidad;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    @Override
    public String presentarse() {
        return "Soy el profesor " + getNombre() + " " + getApellido() + ", especialista en " + especialidad;
    }
}