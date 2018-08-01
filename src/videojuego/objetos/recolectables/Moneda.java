package videojuego.objetos.recolectables;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import videojuego.objetos.Objeto;
import videojuego.mapas.Mapa;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Moneda {

    String id;
    ImageIcon imagen;
    int posX, posY;
    Mapa mapa;
    public boolean colision = false;
    Objeto rectangulo = null;

    public Moneda(Mapa mapa, String id) {
        this.mapa = mapa;
        this.id = id;
        imagen = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/moneda.png"));

    }

    public void dibujar(Graphics g, int posX, int posY, int desfasex, int desfasey, Jugador jugador) {
        if (colision == false) {
            rectangulo = new Objeto(new Rectangle(posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), 15, 18), id, Objeto.Tag.AGREGAR_DINERO);
            if (comprobarSiContiene(rectangulo.getRectangle()) == false) {
                g.drawImage(imagen.getImage(), posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), 15, 18, null);
                mapa.objetos.add(rectangulo);
            } else {
                colision = true;
            }
        } else {
            mapa.objetos.remove(rectangulo);
        }

    }

    public boolean comprobarSiContiene(Rectangle[] moneda) {
        for (int i = 0; i < mapa.objetos.size(); i++) {
            Rectangle[] lados_objeto = mapa.objetos.get(i).getRectangle();
            if (lados_objeto[0].contains(moneda[0])) {
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
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

    public boolean isColision() {
        return colision;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
