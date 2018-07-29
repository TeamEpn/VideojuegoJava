
package videojuego.GESTORJUEGO.estados.tictactoeIA;

public class JugadaIA {

    public static int[] jugadaIA(int[][] board) {

        int row, col;
        int[] jug = new int[2]; //es la variable que retorna

        //recibimos la matriz actual del juego
        int[][] matriz_inicial = board;
        //conversion de la matriz en un vector de 9 elementos, para rellenar las posibles jugadas
        matriz_inicial = Arbol.transponerMatriz(matriz_inicial);

        int[] vector_matriz_inicial = Arbol.desglosarHorizontalmenteMatriz(matriz_inicial);

        //jugada nos dice si somos la primera(1), la segunda(2) u otra jugada (0)
        int jugada = Arbol.obtenerNumeroJugada(vector_matriz_inicial);

        //Es una estrategia complementaria (con jugadas notables).A los arboles de desición con pesos
        int[] fila_col = Arbol.contraJugadas(board);
        
        if (fila_col[0] > 0 && fila_col[1] > 0) {
            System.out.println("CONTRAJUGADA");
            row = fila_col[0];
            col = fila_col[1];
            jug[0] = row;
            jug[1] = col;
            return jug;
        }

        int[] a_b = new int[2];
        switch (jugada) {
        //creamos los siguientes estados, a partir de la matriz actual}
            case 0:
                System.out.println("JUGADA NORMAL");
                Arbol raiz = new Arbol();
                
                raiz.arbolJugadas(vector_matriz_inicial);
                
                
                int nodo_a_jugar = Arbol.elegirSiguienteJugada(raiz.lista);
                a_b = Arbol.usarJugadaElegidaEnEspacioDisponible(board,nodo_a_jugar);
                break;
            case 1:
                System.out.println("PRIMERA JUGADA");
                a_b = Arbol.primeraJugada();
                break;
            case 2:
                System.out.println("SEGUNDA JUGADA");
                a_b = Arbol.segundaJugada(board);
                break;
            default:
                break;
        }

        return a_b;
    }
}
