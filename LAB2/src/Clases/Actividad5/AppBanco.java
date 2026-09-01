package Clases.Actividad5;
import java.util.Scanner;
public class AppBanco {
    public static void main(String[] args) {
        Cuenta[] cuentas = new Cuenta[10];
        cuentas[0] = new CuentaCorriente(1, 1000);
        cuentas[1] = new CuentaAhorro(2, 2000);
        cuentas[2] = new CuentaCorriente(3, 1500);
        cuentas[3] = new CuentaAhorro(4, 3000);
        cuentas[4] = new CuentaCorriente(5, 500);
        cuentas[5] = new CuentaAhorro(6, 1800);
        cuentas[6] = new CuentaCorriente(7, 2500);
        cuentas[7] = new CuentaAhorro(8, 4000);
        cuentas[8] = new CuentaCorriente(9, 700);
        cuentas[9] = new CuentaAhorro(10, 900);
        Scanner in = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.print("D)epositar R)etirar C)onsultar S)alir: ");
            String op = in.next();

            if (op.equals("D") || op.equals("R")) {
                System.out.print("Ingrese un número de cuenta (0-9) y un monto: ");
                int num = in.nextInt();
                double monto = in.nextDouble();

                if (op.equals("D")) {
                    cuentas[num].depositar(monto);
                } else {
                    cuentas[num].retirar(monto);
                }
                System.out.println("Saldo: " + cuentas[num].getSaldo());

            } else if (op.equals("C")) {
                for (int n = 0; n < cuentas.length; n++) {
                    cuentas[n].consultar();
                    System.out.println(n + " " + cuentas[n].getSaldo());
                }

            } else if (op.equals("S")) {
                done = true;
            }
        }
        in.close();
    }
}