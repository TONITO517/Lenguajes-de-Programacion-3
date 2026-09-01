package Mains;

import Clases.Coche;

public class EjemploCoche extends Coche {
    public static void main(String[] args) {
        // Crear objetos coche
        Coche cocheDeportivo = new Coche("Ferrari", "F8", 2022, 250000);
        Coche cocheTodoTerreno = new Coche("Toyota", "Land Cruiser", 2008, 45000);

        // Encender los coches
        cocheDeportivo.encender();
        cocheTodoTerreno.encender();

        // Acelerar y frenar los coches
        cocheDeportivo.acelerar();
        cocheDeportivo.frenar();

        cocheTodoTerreno.acelerar();
        cocheTodoTerreno.frenar();

        // Apagar los coches
        cocheDeportivo.apagar();
        cocheTodoTerreno.apagar();

        // Probar el descuento
        boolean tieneDescuento = cocheTodoTerreno.aplicarDescuento(10);
        if (tieneDescuento) {
            System.out.println("Se aplicó descuento. Nuevo precio: " + cocheTodoTerreno.getPrecio());
        } else {
            System.out.println("No se aplicó descuento porque el coche no es antiguo.");
        }
    }
}