/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.hud;

import interfaz.Boton;
import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import videojuego.GestorPrincipal;
import static videojuego.objetos.entidad.Jugador.Jugador.nueva_decision;

public class Dialogo {


    int dialogo_alto = GestorPrincipal.ALTO - 170;
    public static boolean activado = false;
    public static String[] dialogo;
    public static final String[] dialogo_terra = 
    {"La vida es tan cruel, ya no lo resisto más.",
     "Todos siguen suicidandose por haber visto 'eso'.",
     "No esperaré más mi turno, acabaré con mi vida ahora mismo."};
    public static final String[] dialogo_rosa = 
    {"Dialogo1 de Rosa.",
     "Dialogo2 de Rosa.",
     "Dialogo3 de Rosa."};
    public static final String[] dialogo_helena = 
    {"Dialogo1 de Helena.",
     "Dialogo2 de Helena.",
     "Dialogo3 de Helena."};
        public static final String[] dialogo_vendedor = 
    {"Que tal muchachito",
     "Parece que es tu primera vez aqui, dejame regalarte algo",
     "Puedes elegir entre mi pistola de balas poderosas pero de lenta recarga o mi pistola rapida que dispara balas menos potentes pero de rapida recarga ¿Cual eliges?"};
    
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
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, dialogo_alto, GestorPrincipal.ANCHO, 100);
        g.setColor(Color.black);
        g.drawString(dialogo[aux], 10, dialogo_alto + 30);
        boton_continua.dibujarBoton(g);
        
    }
}
