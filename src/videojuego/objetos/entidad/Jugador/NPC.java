/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.objetos.entidad.Jugador;

import interfaz.Lienzo;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprites.HojaSprites;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Entidad;

public class NPC extends Objeto{

    Jugador jugador;
    public static HojaSprites terra = new HojaSprites("/imagenes/hojasPersonajes/terra1.png", 38, 59, false);
    
    public HojaSprites hoja;
    public int x,y;
    public int inicialx,inicialy;
    Random random = new Random();
    
    
    public NPC(Rectangle rectangle, String id, String tag,HojaSprites hoja,Jugador jugador) {
        super(rectangle, id, tag);
        this.hoja = hoja;
        this.inicialx = jugador.getX();
        this.inicialy = jugador.getY();
        this.jugador = jugador;
        Runnable hilo = new Runnable(){
            @Override
            public void run() {
                while(true){
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

    public void actualizar(Lienzo lienzo) {
        
    }

    public void dibujar(Graphics g) {
        g.drawImage(terra.obtenerSprite(x, y).obtenerImagen(), 10-jugador.getX()+inicialx, 70-jugador.getY()+inicialy, null);
    }
    
}