import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

    public void novaPartida(int midaTaulell) {
        this.taulell = new char[midaTaulell][midaTaulell];
        for (int i = 0; i < midaTaulell; i++) {
            for (int j = 0; j < midaTaulell; j++) {
                taulell[i][j] = ' ';
            }
        }
        torn = 1; // Inicializar el turno del jugador a 1 al comenzar una nueva partida

        // Generar un nombre de archivo único basado en la fecha y la hora actual
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String nomFitxer = formatter.format(date) + ".txt";

        // Bucle para el desarrollo del juego
        while (true) {
            tui.mostrarTaulell(taulell, midaTaulell, torn);
            // Mostrar el tablero actual
            int jugada = tui.recollirJugada(midaTaulell);

            // Verificar si se elige guardar la partida
            if (jugada == -1) {
                guardarPartida(nomFitxer);
                System.out.println("Partida guardada.");
                break; // Salir del bucle si se elige guardar la partida
            }

            // Calcular fila y columna
            int fila = jugada / midaTaulell;
            int columna = jugada % midaTaulell;

            // Verificar si la jugada está dentro de los límites del tablero
            if (fila >= 0 && fila < midaTaulell && columna >= 0 && columna < midaTaulell) {
                // Realizar la jugada si la casilla es válida
                jugar(fila, columna);

                // Verificar si hay un ganador o un empate
                if (jugadaGuanyadora(fila, columna)) {
                    break; // Salir del bucle si hay un ganador
                }
            } else {
                System.out.println("Fila o columna inválida. Inténtalo de nuevo.");
            }
        }
    }

    public void jugar(int fila, int columna) {
        if (taulell[fila][columna] == ' ') { // Verificar que la casilla esté vacía
            taulell[fila][columna] = (torn == 1) ? 'X' : 'O'; // Colocar la ficha del jugador en la casilla
            if (jugadaGuanyadora(fila, columna)) {
                return; // Salir del método si hay un ganador
            }
            torn = (torn == 1) ? 2 : 1; // Cambiar el turno al otro jugador
        } else {
            System.out.println("La casilla seleccionada ya está ocupada. Inténtalo de nuevo.");
        }
    }

    public void guardarPartida(String nomFitxer) {
        File savedGamesDir = new File("savedgames");
        if (!savedGamesDir.exists()) {
            savedGamesDir.mkdir();
        }

        File partida = new File(savedGamesDir, nomFitxer);
        try (PrintWriter writer = new PrintWriter(new FileWriter(partida))) {
            // Guardar el turno del jugador
            writer.println(torn);

            // Guardar el contenido del tablero
            for (char[] fila : taulell) {
                for (char casella : fila) {
                    writer.print(casella);
                }
                writer.println(); // Salto de línea después de cada fila
            }

            System.out.println("Partida guardada correctamente en " + nomFitxer);
        } catch (IOException e) {
            System.out.println("Error al guardar la partida.");
            e.printStackTrace();
        }
    }

    public void carregarPartidaDesDeFitxer(String nomFitxer) {
        File partida = new File("savedgames", nomFitxer);
        try (Scanner scanner = new Scanner(partida)) {
            if (scanner.hasNextInt()) {
                torn = scanner.nextInt();
                scanner.nextLine();  // Consumir la línea restante
            }

            List<char[]> taulellList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    taulellList.add(linea.toCharArray());
                }
            }

            if (!taulellList.isEmpty()) {
                int midaTaulellCargado = taulellList.size();
                taulell = new char[midaTaulellCargado][midaTaulellCargado];
                for (int i = 0; i < midaTaulellCargado; i++) {
                    taulell[i] = taulellList.get(i);
                }
                System.out.println("Partida cargada correctamente desde " + nomFitxer);
            } else {
                System.out.println("El archivo de partida está vacío o no contiene información del tablero.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar la partida.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado al cargar la partida.");
            e.printStackTrace();
        }
    }


    public int getMidaTaulell() {
        return taulell != null ? taulell.length : 0;
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
