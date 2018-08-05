/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.hud;

import interfaz.Boton;
import interfaz.Lienzo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import videojuego.GestorPrincipal;
import videojuego.mapas.Mapa;
import videojuego.objetos.entidad.Jugador.Jugador;
import static videojuego.objetos.entidad.Jugador.Jugador.nueva_decision;
import videojuego.objetos.entidad.Jugador.NPC;
public class Dialogo {


    int dialogo_alto = GestorPrincipal.ALTO - 170;
    public static boolean activado = false;
    public static String[] dialogo;
    public static final String[] dialogo_terra = 
    {"Dialogo1 de Terra.",
     "Dialogo2 de Terra.",
     "Dialogo3 de Terra."};
    public static final String[] dialogo_rosa = 
    {"Dialogo1 de Rosa.",
     "Dialogo2 de Rosa.",
     "Dialogo3 de Rosa."};
    public static final String[] dialogo_helena = 
    {"Dialogo1 de Helena.",
     "Dialogo2 de Helena.",
     "Dialogo3 de Helena."};
    
    Boton boton_continua = new Boton(GestorPrincipal.ANCHO-100, dialogo_alto + 75, "NEXT", 100, 30);
    
    public int aux = 0;


    public Dialogo() {
    }

    public void setDialogo(String[] dialogo) {
        Dialogo.dialogo = dialogo;
    }
    

    public void actualizar(Lienzo lienzo) {
        if (lienzo.getMouse().isClick_izquierdo()) {
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();
            if (boton_continua.esClickeado(mx, my)) {
                if (aux < dialogo.length-1) {
                    aux++;
                    
                }
                else if(activado){
                    activado = false;
                    nueva_decision = true;
                }
                    
                
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dialogo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }


    public void dibujar(Graphics2D g) {
        
        g.setColor(new Color(0x74e1fc));
        g.fillRect(0, dialogo_alto, GestorPrincipal.ANCHO, 100);
        g.setColor(Color.black);
        g.drawString(dialogo[aux], 10, dialogo_alto + 10);
        boton_continua.dibujarBoton(g);
        
    }
}
