/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.GESTORJUEGO.estados;

import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GESTORJUEGO.estados.tictactoeIA.JugadaIA;
import videojuego.GESTORJUEGO.estados.tictactoeIA.Arbol;
import videojuego.entidad.Jugador.Jugador;

public class EstadoTicTacToe implements EstadoJuego {

    Jugador jugador;
    boolean game_over;
    String ganador;
    int[][] board;
    int x = 250, y = 150, lado = 300;
    int lado_cuadrado = lado / 3;
    int turno = 1;

    public EstadoTicTacToe(Jugador jugador) {
        this.jugador = jugador;
        inicializar();
        Random random = new Random();
        turno = random.nextInt(2) + 1;
        if (turno == 1) {
            int[] jugada = JugadaIA.jugadaIA(board);
            this.board[jugada[0]][jugada[1]] = 1;
            turno = 2;
        }
        
    }

    private void inicializar(){
        board = new int[][]{{0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
        };
        this.ganador = "";
        game_over = false;
        
    }
    
    @Override
    public void actualizar(Lienzo lienzo) {

        int estado = Arbol.analizarEstado(board);
        if (Arbol.contarCeros(board) > 0 && estado == 0) {
            if (turno == 1) {
                try {
                    Thread.sleep(100);
                    int[] jugada = JugadaIA.jugadaIA(board);
                    this.board[jugada[0]][jugada[1]] = 1;
                    turno = 2;
                    System.out.println(jugada[0] + " " + jugada[1]);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EstadoTicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            game_over = true;
            switch (estado) {
                case 1:
                    ganador = "MAQUINA";
                    break;
                case 2:
                    ganador = "NICKNAME AQUI :V";
                    break;
                default:
                    ganador = "EMPATE";
                    break;
            }
        }

        if (lienzo.getMouse().isClick_izquierdo()) {

            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();

            if (game_over) {
                if (estaEnCuadrado(100, 100, 100 + 80, 100 + 30, mx, my)) {
                    jugador.setX(jugador.getX() - 100);
                    this.inicializar();
                    GestorEstado.cambiarEstado(0);
                }
            }else if (turno == 2) {
                try {

                    int desfase = 10;
                    boolean click_matriz = false;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (estaEnCuadrado(x + (j * lado_cuadrado) + desfase, y + (i * lado_cuadrado) + desfase,
                                    x + (j * lado_cuadrado) + desfase + lado_cuadrado, y + (i * lado_cuadrado) + desfase + lado_cuadrado, mx, my)) {
                                this.board[i][j] = 2;
                                turno = 1;
                                click_matriz = true;
                                break;
                            }

                        }
                    }
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EstadoTicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    public boolean estaEnCuadrado(int x1, int y1, int x2, int y2, int mx, int my) {
        boolean dentro = false;

        if ((x1 < mx && x2 > mx) && (y1 < my && y2 > my)) {
            dentro = true;
        }

        return dentro;
    }

    public void dibujarBoton(Graphics g, int x, int y, String texto) {

        int ancho = 80, alto = 30;
        g.setColor(Color.darkGray);
        g.fillRect(x, y, ancho, alto);

        g.setColor(Color.gray);
        g.fillRect(x + 5, y + 5, ancho - 10, alto - 10);

        g.setColor(Color.white);
        g.drawString(texto, x + ancho / 3, y + alto / 2);

    }

    @Override
    public void dibujar(Graphics g) {

        g.setColor(Color.white);
        //barras verticales
        g.drawLine(x + lado / 3, y, x + lado / 3, y + lado);
        g.drawLine(x + (2 * lado / 3), y, x + (2 * lado / 3), y + lado);

        g.drawLine(x, y + lado / 3, x + lado, y + lado / 3);
        g.drawLine(x, y + (2 * lado / 3), x + lado, y + (2 * lado / 3));
        int desfase = 10;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                switch (this.board[i][j]) {
                    case 0:
                        this.dibujarCuadradoVacio(g, x + (j * lado / 3) + desfase, y + (i * lado / 3) + desfase, lado / 3 - desfase * 2);
                        break;
                    case 1:
                        this.dibujarCuadradoO(g, x + (j * lado / 3) + desfase, y + (i * lado / 3) + desfase, lado / 3 - desfase * 2);
                        break;
                    case 2:
                        this.dibujarCuadradoX(g, x + (j * lado / 3) + desfase, y + (i * lado / 3) + desfase, lado / 3 - desfase * 2);
                        break;
                    default:
                        this.dibujarCuadradoVacio(g, x + (j * lado / 3) + desfase, y + (i * lado / 3) + desfase, lado / 3 - desfase * 2);
                }
            }
        }

        this.dibujarBoton(g, 100, 100, "Volver");

        if (game_over) {
            g.setColor(Color.yellow);
            g.drawString("El ganador es: " + ganador, 300, 500);
        }

    }

    public void dibujarCuadradoVacio(Graphics g, int x, int y, int lado) {
        g.setColor(Color.black);
        g.fillRect(x, y, lado, lado);
    }

    public void dibujarCuadradoO(Graphics g, int x, int y, int lado) {
        g.setColor(Color.black);
        g.fillRect(x, y, lado, lado);

        g.setColor(Color.red);
        g.drawOval(x, y, lado, lado);
    }

    public void dibujarCuadradoX(Graphics g, int x, int y, int lado) {
        g.setColor(Color.black);
        g.fillRect(x, y, lado, lado);

        g.setColor(Color.blue);
        g.drawLine(x, y, x + lado, y + lado);
        g.drawLine(x, y + lado, x + lado, y);

    }

}
