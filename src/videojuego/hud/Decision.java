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
import java.util.ArrayList;
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
    
    public static ArrayList<String> decisiones = new ArrayList<>();
    
    public void actualizar(Lienzo lienzo){
        
        if(lienzo.getMouse().isClick_izquierdo() && Jugador.nueva_decision){
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();
            
            if(opcion1.esClickeado(mx, my)){
                decisiones.add(opcion1.getContenido());
                Jugador.karma_malo +=100;
                Jugador.nueva_decision = false;
            }
            else if(opcion2.esClickeado(mx, my)){
                decisiones.add(opcion1.getContenido());
                Jugador.karma_bueno +=100;
                Jugador.nueva_decision = false;
            }
            decisiones.get(0);
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
        
    }
    
    
    
}
