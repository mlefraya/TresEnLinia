public class Main {
    private static Joc joc = new Joc(); // Instancia de la clase Joc
    private static TUI tui = new TUI(); // Instancia de la clase TUI

    public static void main(String[] args) {
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
        joc.novaPartida();
        System.out.println("¡Nueva partida iniciada!");
    }

    public static void carregarPartida() {
        // Implementar la lógica para cargar una partida guardada
        System.out.println("Cargando partida...");
    }


    public static void configuracio() {
        System.out.println("Configuración:");
        // Implementar la lógica para la configuración del juego
    }

    public static void sortir() {
        System.out.println("Gracias por jugar. ¡Hasta luego!");
    }
}
