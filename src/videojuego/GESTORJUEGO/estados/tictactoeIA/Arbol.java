/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.GESTORJUEGO.estados.tictactoeIA;

public class Arbol {

    public int nivel, peso;
    public Arbol hermano, lista;
    public int[][] matriz;

    public Arbol() {
        nivel = 0;
        peso = -99;
        hermano = null;
        lista = null;
    }

    public void agregarContenido(int[][] contenido) {
        this.matriz = contenido;
    }

     public void arbolJugadas(int[] matriz_inicial) {
        agregarContenido(convertirVectorAMatriz(matriz_inicial));
        nivel = 0;
        this.lista = generarNodos(matriz_inicial, 1, 1);
        
    }
     
    public Arbol generarNodos(int[] matriz, int turno, int nivel) {

        Arbol arreglo;
        Arbol puntero;
        int[] siguiente_matriz;
        int espacios_disponibles;

        if (this.noTieneCeros(convertirVectorAMatriz(matriz))) {
            arreglo = null;
            return arreglo;
        }

        arreglo = new Arbol();
        puntero = arreglo;

        for (int i = 0; i < matriz.length; i++) {
            siguiente_matriz = copiarVector(matriz); //rompe referencia

            if (siguiente_matriz[i] == 0) {
                siguiente_matriz[i] = turno;

                puntero.agregarContenido(convertirVectorAMatriz(siguiente_matriz));

                puntero.nivel = nivel;

                int estado = analizarEstado(puntero.matriz);
                
                if (estado != 0) {
                    espacios_disponibles = contarCeros(convertirVectorAMatriz(siguiente_matriz));
                    puntero.peso = estado + calcularNodosHijos(espacios_disponibles) * estado;
                    if (nivel == 1 || nivel == 2) {
                        puntero.peso = puntero.peso * 100;
                    }
                    return arreglo;
                }

                puntero.lista = generarNodos(siguiente_matriz, turno_nuevo(turno), nivel + 1);

                if (puntero.lista != null) {
                    if (puntero.lista.peso >= 0 || puntero.lista.peso < 0) {
                        puntero.peso = sumaPesosHijos(puntero.lista);
                    }
                }

                if (!noTieneCeros(convertirVectorAMatriz(siguiente_matriz))) {
                    puntero.hermano = new Arbol();
                    puntero = puntero.hermano;
                    
                    
                } else if (noTieneCeros(convertirVectorAMatriz(siguiente_matriz))) {
                    puntero.peso = analizarEstado(convertirVectorAMatriz(siguiente_matriz));
                }

            }
        }
        return arreglo;
    }

    public int[] copiarVector(int[] vector_copia){
        int[] vector = new int[9];
        for(int i=0;i<9;i++){
            vector[i] = (int) vector_copia[i];
        }
        return vector;
    }
   

    public int sumaPesosHijos(Arbol lista) {
        Arbol puntero = lista;
        int suma_pesos = 0;

        while (puntero.matriz != null) {
            suma_pesos = suma_pesos + puntero.peso;
            if (puntero.hermano != null) {
                puntero = puntero.hermano;
            } else if (puntero.hermano == null) {
                return suma_pesos;
            }
        }

        return suma_pesos;
    }

    public static int obtenerNumeroJugada(int[] matriz) {

        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            suma = suma + matriz[i];
        }

        if (suma == 0) {
            return 1;
        } else if (suma == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    public static int[] primeraJugada() {
        int fila = 0, columna = 2;
        int[] jugada = {fila, columna};

        return jugada;
    }

    public static boolean compararMatriz(int[][] matrizOriginal, int[][] matrizComparar) {
        boolean esIgual = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizOriginal[i][j] == matrizComparar[i][j]) {
                    esIgual = true;
                } else {
                    esIgual = false;
                    return esIgual;
                }
            }
        }
        return esIgual;
    }

    public static int[] segundaJugada(int[][] matrizPJ) {
        int[][] matriz1 = {{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] matriz2 = {{0, 2, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] matriz3 = {{0, 0, 2}, {0, 0, 0}, {0, 0, 0}};
        int[][] matriz4 = {{0, 0, 0}, {2, 0, 0}, {0, 0, 0}};
        int[][] matriz5 = {{0, 0, 0}, {0, 2, 0}, {0, 0, 0}};
        int[][] matriz6 = {{0, 0, 0}, {0, 0, 2}, {0, 0, 0}};
        int[][] matriz7 = {{0, 0, 0}, {0, 0, 0}, {2, 0, 0}};
        int[][] matriz8 = {{0, 0, 0}, {0, 0, 0}, {0, 2, 0}};
        int[][] matriz9 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 2}};
        int fila = 0, columna = 0;

        if (compararMatriz(matrizPJ, matriz1)) {
            fila = 1;
            columna = 1;
        }
        if (compararMatriz(matrizPJ, matriz2)) {
            fila = 0;
            columna = 2;
        }
        if (compararMatriz(matrizPJ, matriz3)) {
            fila = 1;
            columna = 1;
        }
        if (compararMatriz(matrizPJ, matriz4)) {
            fila = 2;
            columna = 0;
        }
        if (compararMatriz(matrizPJ, matriz5)) {
            fila = 2;
            columna = 0;
        }
        if (compararMatriz(matrizPJ, matriz6)) {
            fila = 2;
            columna = 2;
        }
        if (compararMatriz(matrizPJ, matriz7)) {
            fila = 1;
            columna = 1;
        }
        if (compararMatriz(matrizPJ, matriz8)) {
            fila = 2;
            columna = 2;
        }
        if (compararMatriz(matrizPJ, matriz9)) {
            fila = 1;
            columna = 1;
        }
        int[] respuesta = {fila, columna};
        return respuesta;
    }

    public static int[][] rotarMatriz(int[][] matriz) {
        int[][] nuevaMatriz = new int[3][3];
        for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
            for (int k = 0; k < 3; k++) {
                nuevaMatriz[k][j] = matriz[i][k];
            }
        }
        return nuevaMatriz;
    }

    public static int[] contraJugadas(int[][] matriz) {
        int fila = 0, columna = 0;
        int[][] matriz1 = {{2, 0, 0}, {0, 1, 0}, {0, 0, 2}};
        int[][] matriz2 = {{1, 2, 1}, {0, 0, 0}, {0, 2, 0}};
        int[][] matriz3 = {{1, 2, 1}, {2, 0, 0}, {0, 0, 0}};
        int[][] matriz4 = {{1, 2, 1}, {0, 0, 2}, {0, 0, 0}};
        int[][] matriz5 = {{0, 0, 1}, {2, 0, 0}, {0, 0, 0}};
        if (compararMatriz(matriz, matriz1)) {
            fila = 1;
            columna = 2;
        } else {
            if (compararMatriz(rotarMatriz(matriz), matriz1)) {
                fila = 1;
                columna = 2;
            }
        }
        for (int i = 1; i < 4; i++) {
            if (compararMatriz(matriz, matriz2)) {
                fila = 2;
                columna = 2;
                break;
            } else {
                matriz = rotarMatriz(matriz);
            }
        }
        for (int i = 1; i < 4; i++) {
            if (compararMatriz(matriz, matriz3)) {
                fila = 2;
                columna = 2;
                break;
            } else {
                matriz = rotarMatriz(matriz);
            }
        }
        for (int i = 1; i < 4; i++) {
            if (compararMatriz(matriz, matriz4)) {
                fila = 2;
                columna = 2;
                break;
            } else {
                matriz = rotarMatriz(matriz);
            }
        }
        for (int i = 1; i < 4; i++) {
            if (compararMatriz(matriz, matriz5)) {
                fila = 2;
                columna = 2;
                break;
            } else {
                matriz = rotarMatriz(matriz);
            }
        }
        int[] respuesta = {fila, columna};
        return respuesta;
    }

    public static int[][] convertirVectorAMatriz(int vector[]) {
        int matriz3x3[][] = new int[3][3];
        int aux = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz3x3[i][j] = vector[aux];
                aux++;
            }
        }
        return matriz3x3;
    }

    public int turno_nuevo(int turno_actual) {
        int turno_nuevo = -1;
        if (turno_actual == 1) {
            turno_nuevo = 2;
        } else {
            if (turno_actual == 2) {
                turno_nuevo = 1;
            }
        }
        return turno_nuevo;
    }

    public boolean noTieneCeros(int[][] matriz) {
        boolean no_tieneCeros = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == 0) {
                    no_tieneCeros = false;
                    return no_tieneCeros;
                } 
            }
        }
        return no_tieneCeros;
    }

    // mi designio DANNY
    public static int sumaMatrizDiagonal(int[][] matriz, int sentido, int jugador) {
        //setido de la diagonal, 1 para derecha, -1 para izquierda
        int suma = 0;
        int[][] copia = copiaSoloJugador(matriz, jugador);

        if (sentido == 1) {
            suma += copia[0][0] + copia[1][1] + copia[2][2];
        } else if (sentido == -1) {
            suma += copia[0][2] + copia[1][1] + copia[2][0];
        }

        return suma;
    }

    public static int sumaMatrizHorizontal(int[][] matriz, int fila, int jugador) {
        int suma = 0;
        int[][] copia = copiaSoloJugador(matriz, jugador);

        suma += copia[fila][0] + copia[fila][1] + copia[fila][2];
        return suma;
    }

    public static int sumaMatrizVertical(int[][] matriz, int columna, int jugador) {
        int suma = 0;
        int[][] copia = copiaSoloJugador(matriz, jugador);

        suma += copia[0][columna] + copia[1][columna] + copia[2][columna];
        return suma;
    }

    public static int[][] copiaSoloJugador(int[][] matriz, int jugador) {
        int[][] copia = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == jugador) {
                    copia[i][j] = jugador;
                } else {
                    copia[i][j] = 0;
                }
            }
        }

        return copia;
    }

    public static int analizarEstado(int[][] matriz) {
        /**
         * Analiza si alguien gano o perdio en un nodo especifico*
         */

        //comprobacion del jugador 1 en filas, columnas y diagonal
        for (int i = 0; i < 3; i++) {
            if (sumaMatrizHorizontal(matriz, i, 1) == 3) {
                return 1;
            } else if (sumaMatrizVertical(matriz, i, 1) == 3) {
                return 1;
            }

        }

        if (sumaMatrizDiagonal(matriz, 1, 1) == 3) {
            return 1;
        } else if (sumaMatrizDiagonal(matriz, -1, 1) == 3) {
            return 1;
        }

        //comprobacion del jugador 2 en filas, columnas y diagonal
        for (int i = 0; i < 3; i++) {
            if (sumaMatrizHorizontal(matriz, i, 2) == 6) {
                return -1;
            } else if (sumaMatrizVertical(matriz, i, 2) == 6) {
                return -1;
            }

        }

        if (sumaMatrizDiagonal(matriz, 1, 2) == 6) {
            return -1;
        } else if (sumaMatrizDiagonal(matriz, -1, 2) == 6) {
            return -1;
        }

        return 0;

    }

    //FALTA TESTEAR
    public static int elegirSiguienteJugada(Arbol lista) {
        //Una vez generada todo el arbol con sus pesos, elegiremos (del nivel 1) el nodo con mayor peso

        int nodo_elegido = 1;
        int jugada = 0;
        Arbol puntero = lista;
        int mayor = -999;

        if (puntero.lista == null) {
            jugada = 1;
            return jugada;
        }

        while (puntero.peso >= 0 || puntero.peso < 0) {
            //Comparacion hecha a causa de 'nan'
            if (puntero.peso >= mayor) {
                mayor = puntero.peso;
                jugada = nodo_elegido;
            }
            puntero = puntero.hermano;
            if (puntero == null) {
                break;
            }
            nodo_elegido = nodo_elegido + 1;

        }

        return jugada;
    }

    //funcion extra factorial
    public double factorial(int n) {
        double factorial = 1;

        for (int i = n; i > 1; i--) {
            factorial *= i;
        }

        return factorial;

    }

    public int calcularNodosHijos(int espacios_disponibles) {
        double total = 0;

        //n!*sumatorio(1/(n-i)!) en donde i va desde 1 hasta n, es la formula para obtener la cantidad de nodos
        //que se pueden generar dados n espacios disponibles
        int n = espacios_disponibles;
        double parte_factorial = factorial(n);
        double sumatorio = 0;

        for (int i = 1; i <= n; i++) {
            sumatorio = sumatorio + (1 / factorial(n - i));
        }

        total = parte_factorial * sumatorio;

        return (int) total;
    }

    //funcion extra 
    public static int[][] obtenerEspaciosDisponibles(int[][] matriz_inicial) {
        int matriz[][] = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz_inicial[i][j] == 0) {
                    matriz[i][j] = 1;
                } else {
                    matriz[i][j] = 0;
                }
            }
        }

        return matriz;
    }

    //funcion extra
    public static int[][] transponerMatriz(int[][] matriz) {
        int mat[][] = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mat[i][j] = matriz[j][i];
            }
        }

        return mat;

    }

    public static int[] desglosarHorizontalmenteMatriz(int[][] matriz) {

        int[] vector = new int[9];
        int contador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                vector[contador++] = matriz[j][i];
            }
        }
        return vector;

    }

    //funcion temporal
    public void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }
    }

    //FALTA TESTEAR
    public static int[] usarJugadaElegidaEnEspacioDisponible(int[][] matriz_inicial, int limite) {
        //Esta funcion elige un espacio en blanco y aplica la jugada elegida en la funcion
        //elegirSiguienteJugada(lista), primero transforma en 'unos' los espacios disponibles y la variable
        //limite nos permite ir recorriendo los 'unos' hasta llegar al 'uno' que nosotros queremos (la posicion)
        //ESTA ES UNA DE LAS FUNCIONES MAS COMPLEJAS   

        int matriz[][] = Arbol.obtenerEspaciosDisponibles(matriz_inicial); //obtiene los espacios disponibles

        matriz = transponerMatriz(matriz);

        int[] vector = desglosarHorizontalmenteMatriz(matriz); //hay una manera mas facil de hacer esto

        int fila = 0;
        int columna = 0;
        int contador = limite;

        for (int i = 0; i < 9; i++) {
            columna = i % 3; // elije la columna correcta

            if (i == 3 || i == 6) {
                fila = fila + 1;
            }

            if (vector[i] == 1) {
                contador = contador - 1;
                if (contador == 0) {
                    break;
                }
            }

        }
        return new int[]{fila, columna};

    }

    //funcion 5
    public static int contarCeros(int[][] matriz) {
        //cuenta cuantos espacios disponibles quedan en la matriz, esta función es usada en conjunto con la
        //funcion que cuenta cuantos nodos debe generar, segun su formula matemática
        int suma = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == 0) {
                    suma++;
                }
            }
        }
        return suma;
    }

}
