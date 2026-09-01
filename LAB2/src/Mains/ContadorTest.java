package Mains;
import Clases.Contador;
public class ContadorTest extends Contador {
    public static void main(String[] args) {
        Contador c1, c2, c3;
        System.out.println(Contador.acumulador());
        c1 = new Contador(3);
        c2 = new Contador(10);
        c3 = new Contador(); // usa el constructor vacío
        c1.inc();
        c1.inc();
        c2.inc();
        System.out.println(c1.getValor());
        System.out.println(c2.getValor());
        System.out.println(c3.getValor()); // debe imprimir 10 (VALOR_INICIAL)
        System.out.println(Contador.acumulador());
        System.out.println("Contadores creados: " + Contador.nContadores);
        System.out.println("Valor inicial del último contador: " + Contador.ultimoContador);
    }
}