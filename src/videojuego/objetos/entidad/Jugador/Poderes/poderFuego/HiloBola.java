package videojuego.objetos.entidad.Jugador.Poderes.poderFuego;
import java.util.logging.Level;
import java.util.logging.Logger;
import videojuego.GestorPrincipal;
import videojuego.objetos.entidad.Jugador.Jugador;

public class HiloBola implements Runnable {

    Jugador j;
    String direccion;
    BolaFuego bola;

    public HiloBola(Jugador j, String direccion,BolaFuego bola) {
        
        this.j = j;
        this.direccion = direccion;
        this.bola = bola;
    }

    @Override
    public void run() {
        try {
            final int delay = 2;
            for (int i=1;i<=GestorPrincipal.ANCHO;i++) {
                
                if (direccion.equals("abajo")) {
                    bola.posy = bola.posy + 1;
                } else if (direccion.equals("arriba")) {
                    bola.posy = bola.posy - 1;
                } else if (direccion.equals("derecha")) {
                    bola.posx = bola.posx + 1;
                } else if (direccion.equals("izquierda")) {
                    bola.posx = bola.posx - 1;
                }
                
                Thread.sleep(delay);
            }
            BolaFuego.esta_activa = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloBola.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    }