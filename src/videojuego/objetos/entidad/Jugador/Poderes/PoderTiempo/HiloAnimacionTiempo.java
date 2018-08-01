package videojuego.objetos.entidad.Jugador.Poderes.PoderTiempo;

import videojuego.objetos.entidad.Jugador.Jugador;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprites.Animacion;

public class HiloAnimacionTiempo implements Runnable {

    Jugador jugador;
    Jugador[] estados;
    int delay;
    public HiloAnimacionTiempo(Jugador jugador, Jugador[] estados){
        this.jugador = jugador;
        this.estados = estados;
        
    }
    
    @Override
    public void run(){
        int transicion = 0;
        int limite = estados.length;
        delay = 130;
        Animacion.mostrarAnimacion(Animacion.animacion_tiempo,delay*2/3);
        
        while(transicion < limite){
            try {
                jugador.nuevoEstado(estados[transicion++]);
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloPosicionesTiempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
