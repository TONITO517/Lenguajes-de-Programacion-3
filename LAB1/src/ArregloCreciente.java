import java.util.Scanner;

public class ArregloCreciente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arreglo = new int[10];

        System.out.println("Introduce el primer número: ");
        arreglo[0] = scanner.nextInt();

        for (int i = 1; i < arreglo.length; i++) {
            int numero;
            do {
                System.out.println("Introduce un número mayor a " + arreglo[i - 1] + ": ");
                numero = scanner.nextInt();
            } while (numero <= arreglo[i - 1]);

            arreglo[i] = numero;
        }

        System.out.println("Arreglo ingresado:");
        for (int num : arreglo) {
            System.out.print(num + " ");
        }

        scanner.close();
    }
}