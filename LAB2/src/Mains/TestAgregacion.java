package Mains;

import Clases.Automovil;
import Clases.Motor;
public class TestAgregacion extends Automovil {
    public TestAgregacion(String placa, int nPuertas, String marca, String modelo) {
        super(placa, nPuertas, marca, modelo);
    }

    public static void main(String[] args) {
        // Crear un motor
        Motor motor1 = new Motor(101, 6000);

        // Crear un automóvil
        Automovil auto1 = new Automovil("ABC-123", 4, "Toyota", "Corolla");

        // Asignar el motor al automóvil
        auto1.setMotor(motor1);

        // Mostrar los datos
        System.out.println(auto1);
    }
}