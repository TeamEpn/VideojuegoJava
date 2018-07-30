/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.entidad.Jugador;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import videojuego.GestorPrincipal;
import videojuego.mapas.Mapa;

/**
 *
 * @author RAFAEL
 */
public class Bala {
    
    
    public static ImageIcon bala_arriba = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/balaArriba.png"));
    public static ImageIcon bala_abajo = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/balaAbajo.png"));
    public static ImageIcon bala_derecha = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/balaDerecha.png"));
    public static ImageIcon bala_izquierda = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/balaIzquierda.png"));
    
    ImageIcon imagen_actual;
    
    int posx,posy, inicioX, inicioY;
    String direccion;
    
    public Bala(ImageIcon imagen_actual,String direccion,int inicioX,int inicioY) {
        this.imagen_actual = imagen_actual;
        this.posx = GestorPrincipal.CENTROX;
        this.posy = GestorPrincipal.CENTROY;
        this.direccion = direccion;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
    }
    
    public void dibujar(Graphics g, Jugador j){

        if(direccion.equals("arriba"))
            g.drawImage(this.imagen_actual.getImage(), posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
        if(direccion.equals("abajo"))
            g.drawImage(this.imagen_actual.getImage(), posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
        if(direccion.equals("izquierda"))
            g.drawImage(this.imagen_actual.getImage(), posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
        if(direccion.equals("derecha"))
            g.drawImage(this.imagen_actual.getImage(), posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
    }
    
    
    
}
