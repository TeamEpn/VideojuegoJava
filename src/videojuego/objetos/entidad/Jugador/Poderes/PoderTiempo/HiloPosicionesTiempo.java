
package videojuego.objetos.entidad.Jugador.Poderes.PoderTiempo;

import videojuego.objetos.entidad.Jugador.Jugador;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloPosicionesTiempo implements Runnable {

    Jugador jugador;
    public static ListaPosiciones cola;
    
    @Override
    public void run(){
        
        while(jugador.estaVivo()){
            try {
                Thread.sleep(1000);
                cola.insertar(jugador);
                //System.out.println(cola);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloPosicionesTiempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public HiloPosicionesTiempo(Jugador jugador,int tiempo){
        this.jugador = jugador;
        cola = new ListaPosiciones(tiempo);
    }
    
}