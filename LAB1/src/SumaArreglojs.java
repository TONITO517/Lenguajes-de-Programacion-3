/* JAVA */
import java.util.Scanner;
public class SumaArreglojs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el tamaño del arreglo: ");
        int n = scanner.nextInt();
        int[] arreglo = new int[n];
        System.out.println("Introduce los elementos del arreglo:");
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = scanner.nextInt();
        }
        System.out.println("La suma es: " + sumarArreglo(arreglo));
        scanner.close();
    }
    public static int sumarArreglo(int[] arreglo) {
        int suma = 0;
        for (int num : arreglo) {
            suma += num;
        }
        return suma;
    }
}