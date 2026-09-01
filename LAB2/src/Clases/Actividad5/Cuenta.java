package Clases.Actividad5;
public class Cuenta {
    private int numCuenta;
    private double saldo;
    public Cuenta(int numCuenta, double saldo) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
    }
    public void depositar(double monto) {
        saldo = saldo + monto;
    }
    public void retirar(double monto) {
        saldo = saldo - monto;
    }
    public double getSaldo() {
        return saldo;
    }
    public int getNumCuenta() {
        return numCuenta;
    }
    public void consultar() {
        // No tiene comportamiento definido en la clase base
    }
}