/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.entidad.Jugador;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import sprites.HojaSprites;
import videojuego.Objeto;
import videojuego.mapas.Mapa;

/**
 *
 * @author RAFAEL
 */
public class ObjetoCurativo {

    HojaSprites hoja;
    String tipo, id;
    ImageIcon imagen;
    int posX, posY, contador = 0;
    Mapa mapa;
    public boolean colision = false;
    Objeto rectangulo = null;

    public ObjetoCurativo(String tipo, Mapa mapa, String id) {
        this.tipo = tipo;
        this.mapa = mapa;
        this.id = id;

        hoja = new HojaSprites("/imagenes/hojasObjetos/1.png", 32, 32, false);

        if (tipo.equals("manzana_roja")) {
            imagen = new ImageIcon(hoja.obtenerSprite(0, 0).obtenerImagen());
        }
        if (tipo.equals("manzana_dorada")) {
            imagen = new ImageIcon(hoja.obtenerSprite(1, 0).obtenerImagen());
        }
        if (tipo.equals("zanahoria")) {
            imagen = new ImageIcon(hoja.obtenerSprite(2, 0).obtenerImagen());
        }
        if (tipo.equals("galleta")) {
            imagen = new ImageIcon(hoja.obtenerSprite(0, 1).obtenerImagen());
        }
        if (tipo.equals("orbe_verde")) {
            imagen = new ImageIcon(hoja.obtenerSprite(1, 1).obtenerImagen());
        }
        if (tipo.equals("orbe_dorado")) {
            imagen = new ImageIcon(hoja.obtenerSprite(2, 1).obtenerImagen());
        }
    }

    public void dibujar(Graphics g, int posX, int posY, int desfasex, int desfasey, Jugador jugador) {
        if (colision == false) {
            if (mapa.getNombre().equals("Ciudad")) {
                g.drawImage(imagen.getImage(), posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), 32, 32, null);
                rectangulo = new Objeto(new Rectangle(posX + desfasex - jugador.getX(), posY + desfasey - jugador.getY(), 32, 32), id, Objeto.Tag.AGREGAR_VIDA);
                mapa.objetos.add(rectangulo);
            }
        } else{
            mapa.objetos.remove(rectangulo);
            if(contador == 0){
                jugador.agregarVida(20);
                contador ++;
            }
        }
        

    }

    public String getTipo() {
        return tipo;
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

}
