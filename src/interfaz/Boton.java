/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Color;
import java.awt.Graphics;

public class Boton {
    String contenido;
    int x,y;
    int ancho = 80,alto = 30;

    public Boton(int posx, int posy,String contenido) {
        this.x = posx;
        this.y = posy;
        this.contenido = contenido;
    }
    
    
    public boolean esClickeado(int mx, int my) {
        boolean dentro = false;

        if ((x < mx && x + ancho > mx) && (y < my && y + alto > my)) {
            dentro = true;
        }

        return dentro;
    }
    
    public void dibujarBoton(Graphics g) {

        g.setColor(Color.darkGray);
        g.fillRect(x, y, ancho, alto);

        g.setColor(Color.gray);
        g.fillRect(x + 5, y + 5, ancho - 10, alto - 10);

        g.setColor(Color.white);
        g.drawString(this.contenido, x + ancho / 3, y + alto - 12);

    }
    
}
