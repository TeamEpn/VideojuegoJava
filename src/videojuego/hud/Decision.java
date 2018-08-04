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

/**
 *
 * @author User
 */
public class Decision {
    
    int HUD_alto = GestorPrincipal.ALTO - 70;
    int HUD_ancho = GestorPrincipal.ANCHO/2 -10;
    
    Boton opcion1 = new Boton(5, HUD_alto-10, "Opcion 1", HUD_ancho, 30);
    Boton opcion2 = new Boton(5+HUD_ancho, HUD_alto-10, "Opcion 2", HUD_ancho, 30);
    Boton opcion3 = new Boton(5, HUD_alto+20, "Opcion 3", HUD_ancho, 30);
    Boton opcion4 = new Boton(5+HUD_ancho, HUD_alto+20, "Opcion 4", HUD_ancho, 30);
    
    
    public void actualizar(Lienzo lienzo){
        
        if(lienzo.getMouse().isClick_izquierdo() && Jugador.nueva_decision){
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();
            
            if(opcion1.esClickeado(mx, my)){
                JOptionPane.showMessageDialog(null, "UNO");
                Jugador.nueva_decision = false;
            }
            else if(opcion2.esClickeado(mx, my)){
                JOptionPane.showMessageDialog(null, "DOS");
                Jugador.nueva_decision = false;
            }
            else if(opcion3.esClickeado(mx, my)){
                JOptionPane.showMessageDialog(null, "TRES");
                Jugador.nueva_decision = false;
            }
            else if(opcion4.esClickeado(mx, my)){
                JOptionPane.showMessageDialog(null, "CUATRO");
                Jugador.nueva_decision = false;
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(Decision.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public void dibujar(Graphics g){
        
        g.setColor(Color.white);
        g.fillRect(0, HUD_alto-50, GestorPrincipal.ANCHO, 200);
        opcion1.dibujarBoton(g);
        opcion2.dibujarBoton(g);
        opcion3.dibujarBoton(g);
        opcion4.dibujarBoton(g);
        
    }
    
    
    
}
