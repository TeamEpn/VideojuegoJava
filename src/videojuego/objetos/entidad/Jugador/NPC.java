/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.objetos.entidad.Jugador;

import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprites.HojaSprites;
import videojuego.objetos.Objeto;

public class NPC extends Objeto {

    Jugador jugador;
    public static HojaSprites terra = new HojaSprites("/imagenes/hojasPersonajes/terra1.png", 38, 59, false);
    public static HojaSprites rosa = new HojaSprites("/imagenes/hojasPersonajes/rosa.png", 50, 87, false);
    public static HojaSprites elena = new HojaSprites("/imagenes/hojasPersonajes/1.png", 32, 32, false);

    public HojaSprites hoja;
    public int x, y;
    public int inicialx, inicialy;
    Random random = new Random();

    public int posx, posy;

    public boolean evento_ocurrido = false;

    public NPC(Rectangle rectangle, String id, String tag, HojaSprites hoja, Jugador jugador) {
        super(rectangle, id, tag);
        this.hoja = hoja;
        this.inicialx = jugador.getX();
        this.inicialy = jugador.getY();
        this.jugador = jugador;

        if (id.compareToIgnoreCase("terra") == 0) {
            Runnable hilo = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            x = random.nextInt(10);
                            y = random.nextInt(4);
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }

            };

            new Thread(hilo).start();
        }

    }

    public void setXY(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }

    public void actualizar(Lienzo lienzo) {

        this.setRectangle(new Rectangle[]{new Rectangle(this.posx, this.posy, this.hoja.getAncho_sprites_pixeles(), this.hoja.getAlto_sprites_pixeles())});
    }

    public void dibujar(Graphics g) {

        if(this.id.compareToIgnoreCase("terra") != 0)
            g.drawImage(hoja.obtenerSprite(0, 0).obtenerImagen(), posx, posy, null);
        else
            g.drawImage(hoja.obtenerSprite(x, y).obtenerImagen(), posx, posy, null);
        g.setColor(Color.yellow);
        g.drawRect(this.posx, this.posy, this.hoja.getAncho_sprites_pixeles(), this.hoja.getAlto_sprites_pixeles());
    }

}
