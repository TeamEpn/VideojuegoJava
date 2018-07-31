package videojuego.hud;

import videojuego.objetos.entidad.Jugador.Jugador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import videojuego.GestorPrincipal;
import videojuego.objetos.armas.Bala;

public class HUDJugador {
    
    private final Jugador jugador;

    public HUDJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public void dibujar(Graphics g){
        int interfaz_alto = GestorPrincipal.ALTO - 70;
        

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, interfaz_alto, GestorPrincipal.ANCHO, 70);

        
        int porc = 130;
        int vid = (jugador.getVida_actual()*porc)/jugador.getVida_maxima();
        int man = (jugador.getMana_actual()*porc)/jugador.getMana_maximo();
        int exp = (jugador.getExp_actual()*porc)/jugador.getExp_maxima();
        //NIVEL
        g.setColor(Color.white);
        g.drawString("Nivel: " + jugador.getNivel(), 500, interfaz_alto + 20);
        //-VIDA
       
        g.setColor(Color.MAGENTA);
        g.drawRect(50, interfaz_alto + 12, porc, 10);
        g.setColor(Color.red);
        g.fillRect(50, interfaz_alto + 12, vid, 10);
        g.setColor(Color.white);
        g.drawString("VIDA: ", 10, interfaz_alto + 20);
        g.drawString(jugador.getVida_actual()+"/"+jugador.getVida_maxima(), 90, interfaz_alto + 20);

        //-MANA
        
        g.setColor(Color.green);
        g.drawRect(50 - 1, interfaz_alto + 32, porc + 1, 10);
        g.setColor(Color.blue);
        g.fillRect(50, interfaz_alto + 33, man, 9);
        g.setColor(Color.white);
        g.drawString("MANA: ", 10, interfaz_alto + 40);
        g.drawString(jugador.getMana_actual()+"/"+jugador.getMana_maximo(), 90, interfaz_alto + 40);

        //-EXP
        
        g.setColor(Color.white);
        g.drawRect(250, interfaz_alto + 32, porc, 10);
        g.setColor(Color.green);
        g.fillRect(250, interfaz_alto + 33, exp, 9);
        g.setColor(Color.white);
        g.drawString("Exp: ", 200, interfaz_alto + 40);
        g.drawString(jugador.getExp_actual()+"/"+jugador.getExp_maxima(), 200+90, interfaz_alto + 42);

        //BALAS
        g.setColor(Color.white);
        g.drawString("BALAS: ", 200, interfaz_alto + 20);
        int espaciadoX = 0;

        for (int i = 0; i < jugador.getPistola().cantidad_balas; i++) {
            g.drawImage(Bala.bala_arriba, 250 + espaciadoX, interfaz_alto, null);
            espaciadoX = espaciadoX + 10;
        }
        
        //DINERO
        g.drawString("DINERO: ", 200, interfaz_alto + 60);
        g.drawString("$"+jugador.getCuenta().saldo+"", 260, interfaz_alto + 60);
    }
  
}
