import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número entero: ");
        int numero = scanner.nextInt();
        int original = numero;
        int inverso = 0;
        while (numero != 0) {
            inverso = inverso * 10 + (numero % 10);
            numero /= 10;
        }
        String resultado = (original == inverso) ? "es un palíndromo." : "no es un palíndromo.";
        System.out.println(original + " " + resultado);
        scanner.close();
    }
}