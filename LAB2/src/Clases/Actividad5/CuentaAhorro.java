package Clases.Actividad5;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double minSaldo;
    public CuentaAhorro(int numCuenta, double saldo) {
        super(numCuenta, saldo);
        this.minSaldo = saldo;
        this.tasaInteres = 2.0;
    }
    public void setTasaInteres(double interes) {
        this.tasaInteres = interes;
    }
    public void retirar(double monto) {
        super.retirar(monto);
        double saldoActual = getSaldo();
        if (saldoActual < minSaldo) {
            minSaldo = saldoActual;
        }
    }
    public void consultar() {
        double interes = minSaldo * tasaInteres / 100;
        depositar(interes);
        minSaldo = getSaldo();
    }
}