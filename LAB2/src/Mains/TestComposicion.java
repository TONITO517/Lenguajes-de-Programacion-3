package Mains;

import Clases.Persona;

public class TestComposicion extends Persona{
    public TestComposicion(int id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }

    public static void main(String[] args) {
        Persona persona1 = new Persona(1, "Juan", "Pérez");
        Persona persona2 = new Persona(2, "María", "López");

        System.out.println(persona1);
        System.out.println(persona2);
    }
}