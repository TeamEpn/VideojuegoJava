/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.entidad.Jugador;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static videojuego.GestorPrincipal.CENTROX;
import static videojuego.GestorPrincipal.CENTROY;
import videojuego.hud.HUDJugador;

/**
 *
 * @author RAFAEL
 */
public class HiloDisparoArma implements Runnable {

    ImageIcon img;
    Graphics g;
    Jugador j;
    int PosicionX;
    HUDJugador interfaz;
    String direccion;

    public HiloDisparoArma(ImageIcon img, Graphics g, Jugador j, HUDJugador interfaz, String direccion) {
        this.img = img;
        this.g = g;
        this.j = j;
        this.interfaz = interfaz;
        this.direccion = direccion;
    }

    @Override
    public void run() {
        final int delay = 2;
        final int inicioX = j.getX(), inicioY = j.getY();
        
        
        for (int i = 0; i < 600; i++) {

            interfaz = new HUDJugador(j);

            if (direccion.equals("abajo")) {
                try {
                    g.drawImage(img.getImage(), CENTROX - j.getX() + inicioX , CENTROY + i - j.getY() + inicioY, null);
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloDisparoArma.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (direccion.equals("arriba")) {
                try {
                    g.drawImage(img.getImage(), CENTROX - j.getX() + inicioX, CENTROY - i - j.getY() + inicioY, null);
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloDisparoArma.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (direccion.equals("derecha")) {
                try {
                    g.drawImage(img.getImage(), CENTROX + i - j.getX() + inicioX, CENTROY - j.getY() + inicioY, null);
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloDisparoArma.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (direccion.equals("izquierda")) {
                try {
                    g.drawImage(img.getImage(), CENTROX - i - j.getX() + inicioX, CENTROY - j.getY() + inicioY, null);
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloDisparoArma.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
