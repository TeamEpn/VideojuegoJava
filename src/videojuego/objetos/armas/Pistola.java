package videojuego.objetos.armas;

import java.awt.Graphics;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Pistola {
    
    public int cantidad_balas;
    public Bala bala;
    
    public Pistola(int cantidad_balas) {
        this.cantidad_balas = cantidad_balas;
    }

    public void dibujar(Graphics g, Jugador j) {
        if(bala != null)
            bala.dibujar(g, j);
    }
}
