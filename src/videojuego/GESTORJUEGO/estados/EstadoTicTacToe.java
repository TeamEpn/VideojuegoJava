/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.GESTORJUEGO.estados;

import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprites.HojaSprites;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GESTORJUEGO.GestorEstado;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprites.HojaSprites;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GESTORJUEGO.estados.tictactoeIA.JugadaIA;
import videojuego.GESTORJUEGO.estados.tictactoeIA.Arbol;
import videojuego.GestorPrincipal;
import videojuego.Sonido;
import videojuego.entidad.Jugador.Jugador;

public class EstadoTicTacToe implements EstadoJuego {

    Jugador jugador;
    int[][] board = {{1, 0, 0},
    {0, 1, 2},
    {2, 0, 2}
    };
    boolean game_over;
    String ganador;

    int x = 250, y = 150, lado = 300;
    int lado_cuadrado = lado / 3;
    int turno = 1;

    int dificultad;

    BufferedImage fondo = new HojaSprites("/imagenes/fondo3.jpg", 800, 600, true).obtenerSprite(0, 0).obtenerImagen();

    public EstadoTicTacToe(Jugador jugador) {
        this.jugador = jugador;
        inicializar();
        game_over = true;
        Random random = new Random();
        turno = random.nextInt(2) + 1;
        jugadaIA();

    }

    private void inicializar() {
        board = new int[][]{{0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
        };
        this.ganador = "";
        game_over = false;
        dificultad = 0;

    }

    public void jugadaIA() {
        if (turno == 1 && dificultad != 0) {
            int[] jugada = new int[2];
            switch (dificultad) {
                case 1: //aleatorio
                    jugada = JugadaIA.jugadaFacil(board);
                    break;
                case 2:
                    jugada = JugadaIA.jugadaMedia(board);
                    break;
                case 3:
                    jugada = JugadaIA.jugadaIA(board);
                    break;

            }
            this.board[jugada[0]][jugada[1]] = 1;
            turno = 2;

        }
    }

    @Override
    public void actualizar(Lienzo lienzo) {

        int estado = Arbol.analizarEstado(board);
        if (Arbol.contarCeros(board) > 0 && estado == 0 && dificultad > 0) {
            this.jugadaIA();
        } else if (dificultad != 0) {
            game_over = true;
            switch (estado) {
                case 1:
                    ganador = "MAQUINA";
                    break;
                case -1:
                    ganador = "NICKNAME AQUI :V";
                    break;
                default:
                    ganador = "EMPATE";
                    break;
            }
            this.dificultad = 0;
        }

        if (lienzo.getMouse().isClick_izquierdo()) {

            //VERIFICACION DE BOTONES
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();

            if (game_over) {
                if (estaEnCuadrado(100, 100, 100 + 80, 100 + 30, mx, my)) {
                    jugador.setX(jugador.getX() - 100);
                    this.inicializar();
                    Sonido.cambioMusica(GestorPrincipal.musica_inicio);
                    GestorEstado.cambiarEstado(0);
                    this.game_over = true;
                } else if (dificultad == 0) {
                    
                    if (estaEnCuadrado(100, 200, 100 + 80, 200 + 30, mx, my)) {
                        this.inicializar();
                        this.dificultad = 1;
                    } else if (estaEnCuadrado(100, 230, 100 + 80, 230 + 30, mx, my)) {
                        this.inicializar();
                        this.dificultad = 2;
                    } else if (estaEnCuadrado(100, 260, 100 + 80, 260 + 30, mx, my)) {
                        this.inicializar();
                        this.dificultad = 3;

                    }
                    if (dificultad != 0) {
                        Random random = new Random();
                        this.turno = random.nextInt(2) + 1;                        
                    }
                    
                }
            } // FIN DE LA VERIFICACION DE BOTONES
            else if (turno == 2) {
                try {

                    int desfase = 10;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (estaEnCuadrado(x + (j * lado_cuadrado) + desfase, y + (i * lado_cuadrado) + desfase,
                                    x + (j * lado_cuadrado) + desfase + lado_cuadrado, y + (i * lado_cuadrado) + desfase + lado_cuadrado, mx, my)) {
                                this.board[i][j] = 2;
                                turno = 1;
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
        g.drawString(texto, x + ancho / 3, y + alto - 12);

    }

    @Override
    public void dibujar(Graphics g) {

        g.drawImage(fondo, 0, 0, null);

        g.setColor(Color.green);
        g.fillRect(x-3, y-3, lado+6, lado+6);
        
        g.setColor(Color.black);
        g.fillRect(x, y, lado, lado);

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

        if(game_over)
            this.dibujarBoton(g, 100, 100, "Volver");

        this.dibujarBoton(g, 100, 200, "Facil");
        this.dibujarBoton(g, 100, 230, "Medio");
        this.dibujarBoton(g, 100, 260, "Dificil");

        if (game_over && Arbol.contarCeros(board) != 9) {
            g.setColor(Color.yellow);
            g.drawString("El ganador es: " + ganador, 300, 100);
        }
        else{
            g.setColor(Color.yellow);
            g.drawString("ELIJA UN MODO DE JUEGO", 300, 100);
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
