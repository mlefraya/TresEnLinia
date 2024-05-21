import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JocTest {

    @Test
    public void testNovaPartida_TableroInicializado() {
        // Arrange
        Joc joc = new Joc();
        int midaTaulell = 3; // Tamaño del tablero a probar

        // Act
        joc.novaPartida(midaTaulell); // Inicializar una nueva partida con un tablero de tamaño 3x3
        char[][] taulell = joc.getTaulell();

        // Assert
        assertNotNull(taulell); // Verificar que el tablero no es nulo

        // Verificar que todas las celdas del tablero estén vacías
        for (int i = 0; i < midaTaulell; i++) {
            for (int j = 0; j < midaTaulell; j++) {
                assertEquals(' ', taulell[i][j]); // Verificar que la celda está vacía
            }
        }
    }






    @Test
    public void testJugar() {
        // Arrange
        Joc joc = new Joc();
        joc.novaPartida(3); // Iniciar una nueva partida con un tablero de 3x3
        TUI tui = new TUI();

        // Act & Assert
        tui.mostrarTaulell(joc.getTaulell(), 3, joc.getTorn()); // Mostrar el tablero inicial
        joc.jugar(0, 0); // Jugador 1
        tui.mostrarTaulell(joc.getTaulell(), 3, joc.getTorn()); // Mostrar el tablero después de la jugada
        assertEquals('X', joc.getTaulell()[0][0]); // Verificar que se colocó una ficha X en la posición (0, 0)
        assertEquals(2, joc.getTorn()); // Verificar que el turno cambió al jugador 2

        // Act & Assert
        joc.jugar(1, 0); // Jugador 2
        tui.mostrarTaulell(joc.getTaulell(), 3, joc.getTorn()); // Mostrar el tablero después de la segunda jugada
        assertEquals('O', joc.getTaulell()[1][0]); // Verificar que se colocó una ficha O en la posición (1, 0)
        assertEquals(1, joc.getTorn()); // Verificar que el turno cambió al jugador 1
    }

    @Test
    public void testJugadaGuanyadora_GanadorEnFila() {
            // Arrange
            Joc joc = new Joc();
            char[][] taulell = {
                {'X', 'X', 'X'},
                {' ', 'O', ' '},
                {'O', ' ', 'O'}
        };
        joc.setTaulell(taulell);
        int fila = 0;
        int columna = 0;

        // Act
        boolean result = joc.jugadaGuanyadora(fila, columna);

        // Assert
        assertTrue(result, "La jugada ganadora en fila debería devolver verdadero");
    }

    @Test
    public void testJugadaGuanyadora_GanadorEnColumna() {
        // Arrange
        Joc joc = new Joc();
        char[][] taulell = {
                {'X', ' ', 'O'},
                {'X', 'O', ' '},
                {'X', 'O', 'X'}
        };
        joc.setTaulell(taulell);
        int fila = 2;
        int columna = 0;

        // Act
        boolean result = joc.jugadaGuanyadora(fila, columna);

        // Assert
        assertTrue(result, "La jugada ganadora en columna debería devolver verdadero");
    }

    @Test
    public void testJugadaGuanyadora_GanadorEnDiagonal() {
        // Arrange
        Joc joc = new Joc();
        char[][] taulell = {
                {'O', 'X', 'O'},
                {' ', 'O', ' '},
                {'X', ' ', 'O'}
        };
        joc.setTaulell(taulell);
        int fila = 0;
        int columna = 2;

        // Act
        boolean result = joc.jugadaGuanyadora(fila, columna);

        // Assert
        assertTrue(result, "La jugada ganadora en diagonal debería devolver verdadero");
    }



    @Test
    public void testJugadaGuanyadora_Empate() {
        // Arrange
        Joc joc = new Joc();
        char[][] taulell = {
                {'X', 'O', 'X'},
                {'X', 'O', 'O'},
                {'O', 'X', 'X'}
        };
        joc.setTaulell(taulell);
        int fila = 2;
        int columna = 0;

        // Act
        boolean result = joc.jugadaGuanyadora(fila, columna);

        // Assert
        assertFalse(result, "La partida no debería haber acabado en victoria");
    }

}
