
package videojuego.entidad.Jugador.Poderes.PoderTiempo;

import videojuego.entidad.Jugador.Jugador;


public class NodoJugador {
    
    Jugador jugador;
    NodoJugador anterior;
    NodoJugador siguiente;
    

    public NodoJugador(Jugador jugador) {
        this.jugador = jugador;
        this.anterior = null;
        this.siguiente = null;
        
    }
    
    
    
}
