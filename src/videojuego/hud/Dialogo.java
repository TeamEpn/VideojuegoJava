/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.hud;

import interfaz.Boton;
import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import videojuego.GestorPrincipal;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Dialogo {

    int dialogo_alto = GestorPrincipal.ALTO - 140;
    public static final String[] dialogo = {"hola we", "q hay we", "assaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas"};
    Boton boton_continua = new Boton(800, dialogo_alto + 45, "NEXT", 100, 30);
    int aux = 0;

    public Dialogo() {
    }

    public void actualizar(Lienzo lienzo) {
        if (lienzo.getMouse().isClick_izquierdo()) {
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();
            if (boton_continua.esClickeado(mx, my)) {
                if (aux < 2) {
                    aux++;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dialogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, dialogo_alto, 900, 70);
        g.setColor(Color.WHITE);
        g.drawString(dialogo[aux], 10, dialogo_alto + 10);
        boton_continua.dibujarBoton(g);
    }
}
