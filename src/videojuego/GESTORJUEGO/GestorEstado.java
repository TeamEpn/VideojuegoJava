package videojuego.GESTORJUEGO;

import java.awt.Graphics;
import videojuego.entidad.Jugador.Jugador;
import interfaz.Lienzo;
import videojuego.GESTORJUEGO.estados.EstadoAventura;

public class GestorEstado {
    //organiza mas el código
    
    //el estado actual lo dibuja y actualiza, se planea estos estados:
    //aventura: RPG y combate libre, combate por turnos, menu
    
    
    private EstadoJuego[] estados;
    private EstadoJuego estado_actual;
    
    public GestorEstado(final Jugador jugador){
        iniciarEstados(jugador);
        estado_actual = estados[0];
    }

    private void iniciarEstados(final Jugador jugador) {
        estados = new EstadoJuego[1];
        estados[0] = new EstadoAventura(jugador);
        
    }
    
    public void actualizar(Lienzo lienzo){
        estado_actual.actualizar(lienzo);
    }
    
    public void dibujar(final Graphics g){
        estado_actual.dibujar(g);
    }
    
    public void cambiarEstado(final int nuevo_estado){
        estado_actual = estados[nuevo_estado];
    }
    
    public EstadoJuego obtenerEstadoActual(){
        return estado_actual;
    }
}
