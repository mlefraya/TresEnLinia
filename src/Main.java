import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static Joc joc = new Joc(); // Instancia de la clase Joc
    private static TUI tui = new TUI(); // Instancia de la clase TUI
    private static int midaTaulell = 3; // default
    private static final String CONFIG_FILE = "config";
    private static final String SAVED_GAMES_FOLDER = "savedgames";

    public static void main(String[] args) {
        Joc joc = new Joc();
        joc.carregarPartidaDesDeFitxer("20240521_172203.txt");
        TUI tui = new TUI();
        tui.mostrarTaulell(joc.getTaulell(), joc.getMidaTaulell(), joc.getTorn());
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
        joc.novaPartida(midaTaulell);
    }

    public static void carregarPartida() {
        List<String> partidesGuardades = obtenirPartidesGuardades();
        if (partidesGuardades.isEmpty()) {
            System.out.println("No hay partidas guardadas.");
            return;
        }

        System.out.println("Partidas guardadas:");
        for (int i = 0; i < partidesGuardades.size(); i++) {
            System.out.println((i + 1) + ". " + partidesGuardades.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Elige el número de la partida que deseas cargar: ");
        int eleccion = scanner.nextInt();

        if (eleccion < 1 || eleccion > partidesGuardades.size()) {
            System.out.println("Elección inválida. Inténtalo de nuevo.");
            return;
        }

        String nomFitxer = partidesGuardades.get(eleccion - 1);
        joc.carregarPartidaDesDeFitxer(nomFitxer); // Cargar la partida seleccionada

        // Verificar si el arreglo taulell se inicializa correctamente
        if (joc.getTaulell() == null) {
            System.out.println("Error: El tablero no se ha inicializado correctamente.");
            return;
        }

        // Después de cargar la partida, mostrar el tablero y continuar el juego
        while (true) {
            tui.mostrarTaulell(joc.getTaulell(), midaTaulell, joc.getTorn());
            int jugada = tui.recollirJugada(midaTaulell);

            // Verificar si se elige guardar la partida
            if (jugada == -1) {
                joc.guardarPartida(nomFitxer);
                System.out.println("Partida guardada.");
                break; // Salir del bucle si se elige guardar la partida
            }

            // Calcular fila y columna
            int fila = (jugada - 1) / midaTaulell;
            int columna = (jugada - 1) % midaTaulell;

            // Verificar si la jugada está dentro de los límites del tablero
            if (fila >= 0 && fila < midaTaulell && columna >= 0 && columna < midaTaulell) {
                // Realizar la jugada si la casilla es válida
                joc.jugar(fila, columna);

                // Verificar si hay un ganador o un empate
                if (joc.jugadaGuanyadora(fila, columna)) {
                    break; // Salir del bucle si hay un ganador
                }
            } else {
                System.out.println("Fila o columna inválida. Inténtalo de nuevo.");
            }
        }
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

    public static List<String> obtenirPartidesGuardades() {
        List<String> partidesGuardades = new ArrayList<>();
        File savedGamesDir = new File(SAVED_GAMES_FOLDER);

        if (savedGamesDir.exists() && savedGamesDir.isDirectory()) {
            File[] files = savedGamesDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        partidesGuardades.add(file.getName());
                    }
                }
            }
        }

        return partidesGuardades;
    }

    public static void sortir() {
        System.out.println("Gracias por jugar. ¡Hasta luego!");
    }
}
