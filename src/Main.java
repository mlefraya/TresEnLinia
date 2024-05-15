import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class Main {
    private static Joc joc = new Joc(); // Instancia de la clase Joc
    private static TUI tui = new TUI(); // Instancia de la clase TUI
    private static int midaTaulell = 3; // default
    private static final String CONFIG_FILE = "config";

    public static void main(String[] args) {
        carregarConfiguracio();
        while (true) {
            int opcion = tui.mostrarmenu(); // Mostrar el menú del Tres en Raya y obtener la opción seleccionada

            switch (opcion) {
                case 1:
                    novaPartida();
                    break;
                case 2:
                    carregarPartida();
                    break;
                case 3:
                    configuracio();
                    break;
                case 4:
                    sortir();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
                    break;
            }
        }
    }

    public static void novaPartida() {
        char[][] taulell = new char[midaTaulell][midaTaulell]; // Crear el tablero
        joc.novaPartida(midaTaulell);

        System.out.println("¡Nueva partida iniciada!");
    }


    public static void carregarPartida() {
        // Implementar la lógica para cargar una partida guardada
        System.out.println("Cargando partida...");
    }


    public static void configuracio() {
        int opcio;
        do {
            opcio = tui.mostrarMenuConfiguracion();
            switch (opcio) {
                case 1:
                    modificarMidaTaulell();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
                    break;
            }
        } while (opcio != 2);
    }



    public static void modificarMidaTaulell() {
        System.out.print("Introduce la nueva medida del tablero (entre 3 y 10, -1 para volver): ");
        int novaMida = tui.sc.nextInt();

        if (novaMida != -1) {
            if (novaMida < 3 || novaMida > 10) {
                System.out.println("Error: La nueva medida del tablero debe estar entre 3 y 10.");
            } else {
                midaTaulell = novaMida; // Modificar la medida del tablero
                guardarConfiguracio(); // Guardar la nueva medida en el archivo
                System.out.println("La nueva medida del tablero es: " + midaTaulell);
            }
        }
    }


    private static void guardarConfiguracio() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE))) {
            writer.println(midaTaulell);
        } catch (IOException e) {
            System.out.println("Error al guardar la configuración en el archivo.");
        }
    }
    private static void carregarConfiguracio() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            try (Scanner scanner = new Scanner(configFile)) {
                if (scanner.hasNextInt()) {
                    midaTaulell = scanner.nextInt();
                }
            } catch (IOException e) {
                System.out.println("Error al cargar la configuración del archivo.");
            }
        }
    }



    public static void sortir() {
        System.out.println("Gracias por jugar. ¡Hasta luego!");
    }
}
