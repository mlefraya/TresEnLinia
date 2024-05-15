public class Joc {
    private char[][] taulell;
    private int torn;
    private TUI tui = new TUI();

    public int getTorn() {
        return torn;
    }

    public char[][] getTaulell() {
        return taulell;
    }

    public void setTaulell(char[][] taulell) {
        this.taulell = taulell;
    }

    public void novaPartida(int midaTaulell) {
        this.taulell = new char[midaTaulell][midaTaulell];
        for (int i = 0; i < midaTaulell; i++) {
            for (int j = 0; j < midaTaulell; j++) {
                taulell[i][j] = ' ';
            }
        }
        torn = 1; // Inicializar el turno del jugador a 1 al comenzar una nueva partida

        // Bucle para el desarrollo del juego
        while (true) {
            tui.mostrarTaulell(taulell, midaTaulell, torn);
            // Mostrar el tablero actual
            int jugada = tui.recollirJugada(midaTaulell);
            int fila = jugada / midaTaulell;
            int columna = jugada % midaTaulell;

            // Realizar la jugada
            jugar(fila, columna);

            // Verificar si hay un ganador o un empate
            if (jugadaGuanyadora(fila, columna)) {
                break; // Salir del bucle si hay un ganador
            }
        }
    }

    public void jugar(int fila, int columna) {
        if (fila < 0 || fila >= taulell.length || columna < 0 || columna >= taulell[0].length) {
            System.out.println("Fila o columna inválida. Inténtalo de nuevo.");
            return;
        }

        if (taulell[fila][columna] == ' ') { // Verificar que la casilla esté vacía
            taulell[fila][columna] = (torn == 1) ? 'X' : 'O'; // Colocar la ficha del jugador en la casilla
            torn = (torn == 1) ? 2 : 1; // Cambiar el turno al otro jugador
        } else {
            System.out.println("La casilla seleccionada ya está ocupada. Inténtalo de nuevo.");
        }
    }

    public boolean jugadaGuanyadora(int fila, int columna) {
        char jugador = taulell[fila][columna]; // Obtener el caracter del jugador que hizo la jugada en la casilla especificada

        // Verificar si hay tres fichas del mismo jugador en una fila
        for (int i = 0; i < taulell.length; i++) {
            int count = 0;
            for (int j = 0; j < taulell[0].length; j++) {
                if (taulell[i][j] == jugador) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    mostrarTableroYMensajeGanador(jugador);
                    return true;
                }
            }
        }

        // Verificar si hay tres fichas del mismo jugador en una columna
        for (int j = 0; j < taulell[0].length; j++) {
            int count = 0;
            for (int i = 0; i < taulell.length; i++) {
                if (taulell[i][j] == jugador) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    mostrarTableroYMensajeGanador(jugador);
                    return true;
                }
            }
        }

        // Verificar la diagonal principal
        int countDiagonalPrincipal = 0;
        for (int i = 0; i < taulell.length; i++) {
            if (taulell[i][i] == jugador) {
                countDiagonalPrincipal++;
            } else {
                countDiagonalPrincipal = 0;
            }
            if (countDiagonalPrincipal == 3) {
                mostrarTableroYMensajeGanador(jugador);
                return true;
            }
        }

        // Verificar la diagonal secundaria
        int countDiagonalSecundaria = 0;
        for (int i = 0; i < taulell.length; i++) {
            if (taulell[i][taulell.length - 1 - i] == jugador) {
                countDiagonalSecundaria++;
            } else {
                countDiagonalSecundaria = 0;
            }
            if (countDiagonalSecundaria == 3) {
                mostrarTableroYMensajeGanador(jugador);
                return true;
            }
        }

        return false; // Si no hay un ganador, devolver falso
    }

    private void mostrarTableroYMensajeGanador(char jugador) {
        tui.mostrarTaulell(taulell, taulell.length, torn);
        System.out.println("¡Felicidades! El jugador " + jugador + " ha ganado la partida.");
    }


}



