package videojuego.objetos.recolectables;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import sprites.HojaSprites;
import videojuego.objetos.Objeto;
import videojuego.mapas.Mapa;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Comida extends Objeto{

    String tipo, id;
    ImageIcon imagen;
    int posX, posY;
    Mapa mapa;

    HojaSprites hoja = new HojaSprites("/imagenes/hojasObjetos/1.png", 32, 32, false);
    
    public static final String MANZANA_ROJA = "manzana_roja";
    public static final String MANZANA_DORADA = "manzana_dorada";
    public static final String ZANAHORIA = "zanahoria";
    public static final String GALLETA = "galleta";
    public static final String ORBE_DORADO = "orbe_dorado";
    public static final String ORBE_VERDE = "orbe_verde";
    
    public Comida(String tipo, String id, Mapa mapa,int posx,int posy) {
        super(new Rectangle(0,0, 32, 32), id, Objeto.Tag.COMIDA);
        
        this.tipo = tipo;
        this.mapa = mapa;
        posX = posx; 
        posY = posy;
        
        if (tipo.equals(MANZANA_ROJA)) {
            imagen = new ImageIcon(hoja.obtenerSprite(0, 0).obtenerImagen());
        }
        if (tipo.equals(MANZANA_DORADA)) {
            imagen = new ImageIcon(hoja.obtenerSprite(1, 0).obtenerImagen());
        }
        if (tipo.equals(ZANAHORIA)) {
            imagen = new ImageIcon(hoja.obtenerSprite(2, 0).obtenerImagen());
        }
        if (tipo.equals(GALLETA)) {
            imagen = new ImageIcon(hoja.obtenerSprite(0, 1).obtenerImagen());
        }
        if (tipo.equals(ORBE_DORADO)) {
            imagen = new ImageIcon(hoja.obtenerSprite(1, 1).obtenerImagen());
        }
        if (tipo.equals(ORBE_VERDE)) {
            imagen = new ImageIcon(hoja.obtenerSprite(2, 1).obtenerImagen());
        }
        
        
    }

    public void dibujar(Graphics g, int desfasex, int desfasey, Jugador jugador) {
        
        g.drawImage(imagen.getImage(), posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), 32, 32, null);
        this.setRectangle(new Rectangle[]{new Rectangle(posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), 32, 32)});
        
    }

    public String getTipo() {
        return tipo;
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

}
