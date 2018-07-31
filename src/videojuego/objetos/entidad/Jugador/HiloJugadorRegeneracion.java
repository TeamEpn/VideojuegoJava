
package videojuego.objetos.entidad.Jugador;
import videojuego.objetos.entidad.Jugador.Jugador;
import videojuego.objetos.entidad.Jugador.Poderes.PoderTiempo.HiloPosicionesTiempo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloJugadorRegeneracion implements Runnable {

    private final Jugador jugador;
    
    @Override
    public void run(){
        
        while(jugador.estaVivo()){
            try {
                Thread.sleep(1000);
                jugador.regenerarVida(50);
                jugador.regenerarMana(50);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloPosicionesTiempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public HiloJugadorRegeneracion(final Jugador jugador){
        this.jugador = jugador;
        
    }
}
