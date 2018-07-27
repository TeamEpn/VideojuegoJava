
package videojuego.hud;

import videojuego.entidad.Jugador.Jugador;
import java.awt.Color;
import java.awt.Graphics;
import videojuego.GestorPrincipal;

public class HUDJugador {
    
    private final Jugador jugador;

    public HUDJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public void dibujar(Graphics g){
        int interfaz_alto = GestorPrincipal.ALTO - 70;

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, interfaz_alto, GestorPrincipal.ANCHO, 70);

        //-VIDA
        g.setColor(Color.white);
        g.drawString("VIDA: ", 10, interfaz_alto + 20);
        g.setColor(Color.MAGENTA);
        g.drawRect(50, interfaz_alto + 12, jugador.getVida_maxima(), 10);
        g.setColor(Color.red);
        g.fillRect(50, interfaz_alto + 12, jugador.getVida_actual(), 10);

        //-MANA
        g.setColor(Color.white);
        g.drawString("MANA: ", 10, interfaz_alto + 40);
        g.setColor(Color.green);
        g.drawRect(50-1, interfaz_alto + 32, jugador.getMana_maximo()+1, 10);
        g.setColor(Color.blue);
        g.fillRect(50, interfaz_alto + 33, jugador.getMana_actual(), 9);
        
    }
    
    
    
}
