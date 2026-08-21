import java.util.Scanner;

public class ConversionSegundoS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce las horas: ");
        int horas = scanner.nextInt();
        System.out.print ("Introduce los minutos: ");
        int minutos = scanner.nextInt();
        System.out.print("Introduce los segundos: ");
        int segundos = scanner.nextInt();
        int total = convertirASegundos(horas, minutos, segundos);
        System.out.println("El equivalente en segundos es: " + total);
        scanner.close();
    }
    public static int convertirASegundos(int horas, int minutos, int segundos) {
        return (horas * 3600) + (minutos * 60) + segundos;
    }
}
