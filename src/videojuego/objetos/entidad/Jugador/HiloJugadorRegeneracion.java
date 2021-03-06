package videojuego.objetos.entidad.Jugador;
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
                jugador.regenerarVida(jugador.getReg_vida());
                jugador.regenerarMana(jugador.getReg_mana());
                jugador.regeneraResistencia(jugador.getReg_resistencia());
                
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloPosicionesTiempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public HiloJugadorRegeneracion(final Jugador jugador){
        this.jugador = jugador;
        
    }
}
