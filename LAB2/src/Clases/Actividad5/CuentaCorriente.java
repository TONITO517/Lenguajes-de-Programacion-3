package Clases.Actividad5;

public class CuentaCorriente extends Cuenta {
    private int retiros;
    private final int LIBRE_RETIROS = 3;
    private final double TARIFA_TRANSACCION = 3.0;
    public CuentaCorriente(int numCuenta, double saldo) {
        super(numCuenta, saldo);
        this.retiros = 0;
    }
    public void retirar(double monto) {
        super.retirar(monto);
        retiros++;
        if (retiros > LIBRE_RETIROS) {
            super.retirar(TARIFA_TRANSACCION);
        }
    }
    public void consultar() {
        retiros = 0;
    }
}