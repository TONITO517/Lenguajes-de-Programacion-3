package Clases;

public class Coche {
    // Atributos
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private double precio;
    private boolean enMarcha;
    // Constructores
    public Coche() {
        this.marca = "Sin marca";
        this.modelo = "Sin modelo";
        this.anioFabricacion = 2024;
        this.precio = 0.0;
        this.enMarcha = false;
    }
    public Coche(String marca, String modelo, int anioFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.precio = precio;
        this.enMarcha = false;
    }
    // Métodos
    public boolean aplicarDescuento(double descuento) {
        if (anioFabricacion < 2010) {
            precio = precio - (precio * descuento / 100);
            return true;
        } else {
            return false;
        }
    }
    public void encender() {
        enMarcha = true;
        System.out.println("El coche " + modelo + " se ha encendido.");
    }
    public void apagar() {
        enMarcha = false;
        System.out.println("El coche " + modelo + " se ha apagado.");
    }
    public void acelerar() {
        if (enMarcha) {
            System.out.println("El coche " + modelo + " está acelerando.");
        } else {
            System.out.println("Primero enciende el coche.");
        }
    }
    public void frenar() {
        if (enMarcha) {
            System.out.println("El coche " + modelo + " está frenando.");
        } else {
            System.out.println("El coche está apagado, no se puede frenar.");
        }
    }
    // Getters y setters
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAnioFabricacion() {
        return anioFabricacion;
    }
    public void setAñoFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}