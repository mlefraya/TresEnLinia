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

        System.out.print("Columna (1-" + midaTaulell + "): ");
        int columna = sc.nextInt();

        // Ajustar las filas y columnas de 1-midaTaulell a 0-(midaTaulell-1)
        fila--;
        columna--;

        // Validar que la fila y la columna estén dentro del rango permitido (0-(midaTaulell-1))
        if (fila < 0 || fila >= midaTaulell || columna < 0 || columna >= midaTaulell) {
            System.out.println("Fila o columna inválida. Inténtalo de nuevo.");
            return recollirJugada(midaTaulell); // Llamada recursiva para obtener una jugada válida
        }

        return fila * midaTaulell + columna; // Devolver el índice de la casilla en un solo número
    }



    public void fiDePartida(int guanyador) {
        if (guanyador == 0) {
            System.out.println("La partida ha terminado en empate.");
        } else {
            System.out.println("¡Felicidades! El jugador " + (guanyador == 1 ? "X" : "O") + " ha ganado la partida.");
        }
    }
}
