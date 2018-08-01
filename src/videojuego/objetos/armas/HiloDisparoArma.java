package videojuego.objetos.armas;
import java.util.logging.Level;
import java.util.logging.Logger;
import videojuego.GestorPrincipal;
import videojuego.objetos.entidad.Jugador.Jugador;

public class HiloDisparoArma implements Runnable {

    Bala bala;
    Jugador j;
    String direccion;
    
    public HiloDisparoArma(Bala bala, Jugador j, String direccion) {
        this.bala = bala;
        this.j = j;
        this.direccion = direccion;
    }
    
    @Override
    public void run() {
        
        for(int i=1;i<=GestorPrincipal.ANCHO;i++) {
            try {
                if (direccion.equals("abajo")) {
                    bala.posy = bala.posy + 1;
                } else if (direccion.equals("arriba")) {
                    bala.posy = bala.posy - 1;
                } else if (direccion.equals("derecha")) {
                    bala.posx = bala.posx + 1;
                } else if (direccion.equals("izquierda")) {
                    bala.posx = bala.posx - 1;
                }
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloDisparoArma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
