package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Boton {
    private final String contenido;
    private final int x,y;
    private final int ancho,alto;

    public Boton(final int posx,final int posy,final String contenido) {
        this.x = posx;
        this.y = posy;
        this.contenido = contenido;
        ancho = 80;
        alto = 30;
    }

    public Boton(int x, int y,String contenido, int ancho, int alto) {
        this.contenido = contenido;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
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

        g.setColor(Color.white);
        g.fillRect(x + 5, y + 5, ancho - 10, alto - 10);

        g.setColor(Color.black);
        g.drawString(this.contenido, x + ancho / 3, y + alto /2 + 5);

    }
    
        public void dibujarBotonImagen(Graphics g, Image imagen) {

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, imagen.getWidth(null), imagen.getHeight(null));

        g.drawImage(imagen, x, y, null);

        g.setColor(Color.WHITE);
        g.drawString(this.contenido, x - 50, y + imagen.getHeight(null) + 15);

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