public class Joc {
    private char[][] taulell = new char[3][3];
    private int torn;
    private TUI tui = new TUI();
    public char[][] getTaulell() {
        return taulell;
    }

    public void setTorn(int torn) {
        this.torn = torn;
    }

    public void setTaulell(char[][] taulell) {
        this.taulell = taulell;
    }

    public int getTorn() {
        return torn;
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
            int jugada = tui.recollirJugada(midaTaulell) -1; // Recoger la jugada del jugador actual
            int fila = jugada / 3;
            int columna = jugada % 3;

            // Realizar la jugada
            jugar(fila, columna);

            // Verificar si hay un ganador o un empate
            if (jugadaGuanyadora(fila, columna)) {
                break; // Salir del bucle si hay un ganador
            }
        }
    }



    public void jugar(int fila, int columna) {
        if (taulell[fila][columna] == ' ') { // Verificar que la casilla esté vacía
            taulell[fila][columna] = (torn == 1) ? 'X' : 'O'; // Colocar la ficha del jugador en la casilla
            torn = (torn == 1) ? 2 : 1; // Cambiar el turno al otro jugador
        } else {
            System.out.println("La casilla seleccionada ya está ocupada. Inténtalo de nuevo.");
        }
    }




    public boolean jugadaGuanyadora(int fila, int columna) {
        char jugador = taulell[fila][columna]; // Obtener el caracter del jugador que hizo la jugada en la casilla especificada

        // Verificar si hay tres fichas del mismo jugador en una fila, columna o diagonal
        boolean guanyador = (taulell[fila][0] == jugador && taulell[fila][1] == jugador && taulell[fila][2] == jugador) || // Fila
                (taulell[0][columna] == jugador && taulell[1][columna] == jugador && taulell[2][columna] == jugador) || // Columna
                (taulell[0][0] == jugador && taulell[1][1] == jugador && taulell[2][2] == jugador) || // Diagonal principal
                (taulell[0][2] == jugador && taulell[1][1] == jugador && taulell[2][0] == jugador); // Diagonal secundaria

        // Mostrar el tablero antes de indicar si hay un ganador o empate
        tui.mostrarTaulell(taulell,3, torn);

        // Si hay un ganador, imprimir mensaje y devolver verdadero
        if (guanyador) {
            System.out.println("¡Felicidades! El jugador " + jugador + " ha ganado la partida.");
            return true;
        }

        // Verificar si hay empate
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (taulell[i][j] == ' ') {
                    empate = false; // Si hay una casilla vacía, no hay empate
                }
            }
        }

        // Si todas las casillas están llenas y no hay un ganador, imprimir mensaje de empate y devolver verdadero
        if (empate) {
            System.out.println("La partida ha terminado en empate.");
            return true;
        }

        return false; // Si no hay un ganador ni un empate, devolver falso
    }




}




