
package videojuego.mapas;

import videojuego.objetos.entidad.Jugador.Jugador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import videojuego.GestorPrincipal;
import videojuego.objetos.Objeto;
import sprites.HojaSprites;
import videojuego.objetos.entidad.Enemigo.Enemigo;
import videojuego.objetos.recolectables.Moneda;
import videojuego.objetos.recolectables.Comida;

public abstract class Mapa {
    
    protected final String nombre;
    protected final BufferedImage sprite;
    protected final int ancho, alto;
    protected final Jugador jugador;
    public ArrayList<Enemigo> enemigos;
    protected final int desfasex,desfasey; //estas indican la posicion inicial del mapa, para no salirnos de tal
    
    
    protected Random random = new Random();
    
    public ArrayList<Objeto> objetos = new ArrayList<>();
    public ArrayList<Comida> comidas;
    public ArrayList<Moneda> monedas;    
    protected final int ANCHO_SPAWNEO;
    protected final int ALTO_SPAWNEO;
    
    
    public Mapa(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.alto = alto;
        
        this.jugador = jugador; 
        this.sprite = new HojaSprites(ruta, ancho, alto, true).obtenerSprite(0, 0).obtenerImagen();
        
        desfasex = GestorPrincipal.CENTROX;
        desfasey = GestorPrincipal.CENTROY;
        
        
        ANCHO_SPAWNEO = this.ancho - 100;
        ALTO_SPAWNEO = this.alto - 100;
        iniciarObjetosRecolectables();
        
    }
    
    public Mapa(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador,int desfasex,int desfasey) {
        this.nombre = nombre;
        this.ancho = ancho;
        this.alto = alto;
        this.jugador = jugador;               
        this.sprite = new HojaSprites(ruta, ancho, alto, true).obtenerSprite(0, 0).obtenerImagen();
        
        this.desfasex = desfasex;
        this.desfasey = desfasey;
        
        ANCHO_SPAWNEO = this.ancho - 100;
        ALTO_SPAWNEO = this.alto - 100;
        iniciarObjetosRecolectables();
        
        
    }
    
       
    protected abstract void generarObjetosColisionables(Graphics g,final int x,final int y,final Jugador jugador);
    protected abstract void iniciarObjetosRecolectables();
    public abstract void musica();
    
    public void iniciarEnemigos(int cantidad){
        enemigos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            enemigos.add(new Enemigo(jugador,"zombie "+i));
        }
    }
    
    public void dibujar(Graphics g){
        
        g.drawImage(this.getSprite(),desfasex  - jugador.getX(),desfasey  - jugador.getY(), null);
        this.generarObjetosColisionables(g, jugador.getX(), jugador.getY(), jugador);
        
        //dibuja solo rectangulos
        for(Objeto r: objetos){
            if(r.getTag().compareToIgnoreCase(Objeto.Tag.NATURALEZA) == 0){
                g.setColor(Color.green);
            }else if(r.getTag().compareToIgnoreCase(Objeto.Tag.TELEPORT) == 0){
                g.setColor(Color.yellow);
            }else if(r.getTag().compareToIgnoreCase(Objeto.Tag.ENEMIGO) == 0){
                g.setColor(Color.red);
            }else if(r.getTag().compareToIgnoreCase(Objeto.Tag.EDIFICIO) == 0){
                g.setColor(Color.blue);
            }else if(r.getTag().compareToIgnoreCase(Objeto.Tag.ABSORCION_MANA) == 0){
                g.setColor(Color.magenta);
            }else if(r.getTag().compareToIgnoreCase(Objeto.Tag.INVERSION) == 0){
                g.setColor(Color.yellow);
            }
            g.drawRect(r.getRectangle()[0].x, r.getRectangle()[0].y, r.getRectangle()[0].width, r.getRectangle()[0].height);
        }
        
        if(comidas != null)
            for(Comida comida:comidas){
                comida.dibujar(g, desfasex, desfasey, jugador);
            }
        
        if(monedas != null)
            for(Moneda moneda:monedas){
                moneda.dibujar(g, desfasex, desfasey, jugador);
            }
        
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
}
