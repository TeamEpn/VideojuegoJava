package videojuego.GESTORJUEGO;

import videojuego.GESTORJUEGO.estados.EstadoAventura;
import videojuego.GESTORJUEGO.estados.EstadoTicTacToe;
import videojuego.GESTORJUEGO.estados.EstadoInversion;
import java.awt.Graphics;
import videojuego.objetos.entidad.Jugador.Jugador;
import interfaz.Lienzo;
import java.awt.Graphics2D;

public class GestorEstado {
    
    private static EstadoJuego[] estados;
    private static EstadoJuego estado_actual;
    
    public GestorEstado(final Jugador jugador){
        iniciarEstados(jugador);
        estado_actual = estados[0];
    }

    private void iniciarEstados(final Jugador jugador) {
        estados = new EstadoJuego[3];
        estados[0] = new EstadoAventura(jugador);
        estados[1] = new EstadoTicTacToe(jugador);
        estados[2] = new EstadoInversion(jugador);
    }
    
    public void actualizar(Lienzo lienzo){
        estado_actual.actualizar(lienzo);
    }
    
    public void dibujar(final Graphics2D g){
        estado_actual.dibujar(g);
    }
    
    public static void cambiarEstado(final int nuevo_estado){
        estado_actual = estados[nuevo_estado];
    }
}
