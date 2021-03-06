package videojuego.objetos.recolectables;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import videojuego.objetos.Objeto;
import videojuego.mapas.Mapa;
import videojuego.objetos.Colision;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Moneda extends Objeto {

    public static ImageIcon imagen = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/moneda.png"));

    String id;
    int posX, posY;
    Mapa mapa;
    public boolean colisiona = false;
    Random random = new Random();

    public Moneda(Mapa mapa, String id, int posx, int posy) {
        super(new Rectangle(posx, posy, 32, 32), id, Objeto.Tag.MONEDA);

        this.mapa = mapa;
        this.id = id;
        this.posX = posx;
        this.posY = posy;

    }

    public void dibujar(Graphics g, int desfasex, int desfasey, Jugador jugador) {
        g.drawImage(imagen.getImage(), posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), null);
        this.setRectangle(new Rectangle[]{new Rectangle(posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), 15, 15)});
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
