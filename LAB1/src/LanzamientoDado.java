import java.util.Random;

public class LanzamientoDado {
    public static void main(String[] args) {
        Random random = new Random();
        int[] frecuencias = new int[6]; // posición 0 = cara 1, ..., posición 5 = cara 6

        for (int i = 0; i < 20000; i++) {
            int cara = random.nextInt(6) + 1; // genera un número entre 1 y 6
            frecuencias[cara - 1]++;
        }

        for (int i = 0; i < frecuencias.length; i++) {
            System.out.println("Cara " + (i + 1) + ": " + frecuencias[i] + " veces");
        }
    }
}