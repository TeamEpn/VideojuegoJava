package videojuego.hud;

import videojuego.entidad.Jugador.Jugador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
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

        g.drawRect(50 - 1, interfaz_alto + 32, jugador.getMana_maximo() + 1, 10);
        g.setColor(Color.blue);
        g.fillRect(50, interfaz_alto + 33, jugador.getMana_actual(), 9);

        //-EXP
        g.setColor(Color.white);
        g.drawString("Exp: ", 200, interfaz_alto + 40);
        g.setColor(Color.white);
        g.drawRect(250, interfaz_alto + 32, 170, 10);
        g.setColor(Color.green);
        g.fillRect(250, interfaz_alto + 33, jugador.getExpGanada(), 9);

        //BALAS
        g.setColor(Color.white);
        g.drawString("BALAS: ", 200, interfaz_alto + 20);
        ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/hojasObjetos/bala.png"));
        int espaciadoX = 0;

        for (int i = 0; i < jugador.getPistola().cantidad_balas; i++) {
            g.drawImage(img.getImage(), 250 + espaciadoX, interfaz_alto + 10, null);
            espaciadoX = espaciadoX + 60;
        }
        
        //DINERO
        g.drawString("DINERO: ", 200, interfaz_alto + 60);
        g.drawString("$"+jugador.getDinero()+"", 260, interfaz_alto + 60);
    }
  
}
