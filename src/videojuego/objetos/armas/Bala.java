package videojuego.objetos.armas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sprites.HojaSprites;
import videojuego.GestorPrincipal;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Bala {
    
    private final static HojaSprites HOJA_BALA = new HojaSprites("/imagenes/hojasObjetos/hojaBala.png",32,false);
    
    public static final BufferedImage bala_abajo = HOJA_BALA.obtenerSprite(0, 0).obtenerImagen();
    public static final BufferedImage bala_arriba = HOJA_BALA.obtenerSprite(1, 0).obtenerImagen();
    public static final BufferedImage bala_derecha = HOJA_BALA.obtenerSprite(2, 0).obtenerImagen();
    public static final BufferedImage bala_izquierda = HOJA_BALA.obtenerSprite(3, 0).obtenerImagen();
    
    private final BufferedImage imagen_actual;
    
    public int posx,posy;
    private final int inicioX, inicioY;
    private final String direccion;
    
    public Bala(final BufferedImage imagen_actual,final String direccion,final int inicioX,final int inicioY) {
        this.imagen_actual = imagen_actual;
        this.posx = GestorPrincipal.CENTROX;
        this.posy = GestorPrincipal.CENTROY;
        this.direccion = direccion;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
    }
    
    public void dibujar(Graphics g, Jugador j){

        if(direccion.equals("arriba"))
            g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
        if(direccion.equals("abajo"))
            g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
        if(direccion.equals("izquierda"))
            g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
        if(direccion.equals("derecha"))
            g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
    }
}
