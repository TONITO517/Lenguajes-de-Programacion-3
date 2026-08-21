import java.util.Scanner;

public class NumerosPrimos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número: ");
        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println("No hay números primos menores a 2.");
        } else {
            imprimirNumerosPrimos(n);
        }

        scanner.close();
    }

    public static boolean esPrimo(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false; // descarta pares rápido, excepto el 2
        }
        // Solo se revisan divisores impares hasta la raíz cuadrada de num
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void imprimirNumerosPrimos(int n) {
        for (int num = 2; num <= n; num++) {
            if (esPrimo(num)) {
                System.out.println(num + " es primo.");
            }
        }
    }
}