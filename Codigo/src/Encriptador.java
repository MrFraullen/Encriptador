import Codificador.Codificador;
import Decodificador.Decodificador;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Incriptador {
    private static final int CODIFICAR = 1;
    private static final int DECODIFICAR = 2;
    private static final int SALIR = 3;

    public static void escribir(Object texto) { System.out.print(texto); }
    public static void escribirl(Object texto) { System.out.println(texto); }

    public static void main(String[] args) {
        int res = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            escribirl("Menú:\n1. Codificar texto.\n2. Decodificar texto.\n3. Salir");
            escribir("Elige una opción (1,2,3): ");
            try {
                res = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea
                switch (res) {
                    case CODIFICAR -> Codificador.codificar();
                    case DECODIFICAR -> Decodificador.decodificar();
                    case SALIR -> escribirl("Saliendo del programa.");
                    default -> escribirl("Opción no reconocida.");
                }
            } catch (InputMismatchException e) {
                escribirl("Error: La opción ingresada no es numérica.");
                scanner.nextLine(); // Limpiar buffer
            }
        } while (res != SALIR);
    }
}