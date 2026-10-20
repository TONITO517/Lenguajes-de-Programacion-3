package EJ2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BaseDatos.crearTablaYDatos();
        GestoraEstudiantes gestora = new GestoraEstudiantes();
        gestora.cargarDesdeBD();
        try {
            System.out.print("\nOrdenar por (id/nombre/carrera/nota): ");
            String campoOrden = scanner.nextLine();
            System.out.print("Orden (A = ascendente, D = descendente): ");
            boolean ascendente = scanner.nextLine().equalsIgnoreCase("A");
            System.out.print("Cantidad máxima de registros (0 = todos): ");
            int limite = scanner.nextInt();
            String[] campos = {"id", "nombre", "carrera", "nota"};
            System.out.println("\nResultado:");
            gestora.consultar(campos, null, null, campoOrden, ascendente, limite);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}