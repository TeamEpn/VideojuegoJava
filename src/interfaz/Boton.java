package interfaz;

import java.awt.Color;
import java.awt.Graphics;

public class Boton {
    private final String contenido;
    private final int x,y;
    private final int ancho = 80,alto = 30;

    public Boton(final int posx,final int posy,final String contenido) {
        this.x = posx;
        this.y = posy;
        this.contenido = contenido;
    }
    
    
    public boolean esClickeado(final int mx,final int my) {
        boolean dentro = false;
        if ((x < mx && x + ancho > mx) && (y < my && y + alto > my))
            dentro = true;
        
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

    public String getContenido() {
        return contenido;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}