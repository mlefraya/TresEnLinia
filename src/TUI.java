import java.util.Scanner;

public class TUI {
    Scanner sc = new Scanner(System.in);
    public int mostrarmenu() {
        System.out.println("Menú del Tres en Raya:");
        System.out.println("1. Jugar nueva partida");
        System.out.println("2. Cargar partida");
        System.out.println("3. Configuración");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");

        int opcion = sc.nextInt();
        return opcion;
    }
    public int mostrarMenuConfiguracion() {
        System.out.println("Configuración:");
        System.out.println("1. Modificación de la medida del tablero");
        System.out.println("2. Volver al menú principal");
        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer del scanner
        return opcion;
    }



    public void mostrarTaulell(char[][] taulell, int midaTaulell, int torn) {
        if (taulell == null || taulell.length == 0 || taulell.length != midaTaulell || taulell[0].length != midaTaulell) {
            System.out.println("Error: El tablero no se ha inicializado correctamente.");
            return;
        }

        System.out.println("Tablero:");
        for (int i = 0; i < midaTaulell; i++) {
            for (int j = 0; j < midaTaulell; j++) {
                System.out.print("[" + taulell[i][j] + "] ");
            }
            System.out.println();
        }
        System.out.println("Turno del jugador: " + (torn == 1 ? "X" : "O"));
    }





    public int recollirJugada(int midaTaulell) {
        System.out.println("Elige la fila y la columna donde deseas colocar tu ficha (1-" + midaTaulell + ").");
        System.out.print("Fila (1-" + midaTaulell + "): ");
        int fila = sc.nextInt();

        // Verificar si se desea guardar la partida
        if (fila == -1) {
            System.out.print("Columna (1-" + midaTaulell + "): ");
            int columna = sc.nextInt();
            if (columna == -1) {
                return -1; // Se desea guardar la partida
            } else {
                System.out.println("Para guardar la partida, tanto la fila como la columna deben ser -1. Inténtalo de nuevo.");
                return recollirJugada(midaTaulell); // Volver a pedir la jugada si la columna no es -1
            }
        } else if (fila < 1 || fila > midaTaulell) {
            System.out.println("Fila inválida. Inténtalo de nuevo.");
            return recollirJugada(midaTaulell); // Volver a pedir la fila si es inválida
        }

        System.out.print("Columna (1-" + midaTaulell + "): ");
        int columna = sc.nextInt();

        if (columna == -1) {
            return -1; // Se desea guardar la partida
        } else if (columna < 1 || columna > midaTaulell) {
            System.out.println("Columna inválida. Inténtalo de nuevo.");
            return recollirJugada(midaTaulell); // Volver a pedir la columna si es inválida
        }

        // Convertir fila y columna a índice de la casilla
        return ((fila - 1) * midaTaulell) + (columna - 1);
    }

















    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            System.out.println("La partida ha terminado en empate.");
        } else {
            System.out.println("¡Felicidades! El jugador " + (guanyador == 1 ? "X" : "O") + " ha ganado la partida.");
        }
    }
}
