import java.util.Scanner;
public class NumeroMenor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el primer número decimal: ");
        double num1 = scanner.nextDouble();
        System.out.print("Introduce el segundo número decimal: ");
        double num2 = scanner.nextDouble();
        System.out.print("Introduce el tercer número decimal: ");
        double num3 = scanner.nextDouble();
        double resultado = menor(num1, num2, num3);
        System.out.println("El menor de los 3 números es: " + resultado);
        scanner.close();
    }
    public static double menor(double a, double b, double c) {
        double menorValor = a;
        if (b < menorValor) {
            menorValor = b;
        }
        if (c < menorValor) {
            menorValor = c;
        }
        return menorValor;
    }
}