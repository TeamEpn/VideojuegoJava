
package videojuego.mapas;

import videojuego.objetos.entidad.Jugador.Jugador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import videojuego.GestorPrincipal;
import videojuego.objetos.Objeto;
import sprites.HojaSprites;

public abstract class Mapa {
    
    protected final String nombre;
    protected final BufferedImage sprite;
    protected final int ancho, alto;
    protected final Jugador jugador;
    
    protected final int desfasex,desfasey; //estas indican la posicion inicial del mapa, para no salirnos de tal
    
    public ArrayList<Objeto> objetos;
            
    public Mapa(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.alto = alto;
        this.jugador = jugador;               
        this.sprite = new HojaSprites(ruta, ancho, alto, true).obtenerSprite(0, 0).obtenerImagen();
        
        desfasex = GestorPrincipal.CENTROX;
        desfasey = GestorPrincipal.CENTROY;
    }
    
    public Mapa(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador,int desfasex,int desfasey) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.alto = alto;
        this.jugador = jugador;               
        this.sprite = new HojaSprites(ruta, ancho, alto, true).obtenerSprite(0, 0).obtenerImagen();
        
        this.desfasex = desfasex;
        this.desfasey = desfasey;
    }
    
    public String getNombre() {
        return nombre;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    //metodos obligatorios

    protected abstract void generarObjetosColisionables(Graphics g,final int x,final int y,final Jugador jugador);

    
    public void dibujar(Graphics g){
        
        g.drawImage(this.getSprite(),desfasex  - jugador.getX(),desfasey  - jugador.getY(), null);
        

        this.generarObjetosColisionables(g, jugador.getX(), jugador.getY(), jugador);

        
        
        for(Objeto r: objetos){
            if(r.getTag().compareToIgnoreCase(Objeto.Tag.NATURALEZA) == 0){
                g.setColor(Color.green);
            }

            else if(r.getTag().compareToIgnoreCase(Objeto.Tag.TELEPORT_BOSQUE) == 0){
                g.setColor(Color.yellow);
            }
            else if(r.getTag().compareToIgnoreCase(Objeto.Tag.TELEPORT_CIUDAD) == 0){
                g.setColor(Color.yellow);
            }
            else if(r.getTag().compareToIgnoreCase(Objeto.Tag.TELEPORT_CASA) == 0){
                g.setColor(Color.yellow);
            }
            else if(r.getTag().compareToIgnoreCase(Objeto.Tag.ENEMIGO) == 0){
                g.setColor(Color.red);
            }
            else if(r.getTag().compareToIgnoreCase(Objeto.Tag.EDIFICIO) == 0){
                g.setColor(Color.blue);
            }else if(r.getTag().compareToIgnoreCase(Objeto.Tag.ABSORCION_MANA) == 0){
                g.setColor(Color.magenta);
            }
        
            g.drawRect(r.getRectangle()[0].x, r.getRectangle()[0].y, r.getRectangle()[0].width, r.getRectangle()[0].height);
          
        }
    }
    

    public abstract void musica();
}
