package videojuego.objetos.entidad;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import videojuego.GestorPrincipal;
import videojuego.objetos.Objeto;
import interfaz.Lienzo;
import java.util.ArrayList;
import videojuego.mapas.Mapa;
import sprites.HojaSprites;
import videojuego.GESTORJUEGO.estados.EstadoAventura;

public abstract class Entidad {

    protected String nombre;

    protected int x, y;
    protected int posx_inicial, posy_inicial;
    protected final int velocidad_original = 2;
    protected int velocidad = velocidad_original;

    protected boolean esta_vivo;
    protected int vida_maxima;
    protected int vida_actual;

    //posiciones_sprites
    protected BufferedImage frente0;
    protected BufferedImage espalda0;
    protected BufferedImage lado_derecho0;
    protected BufferedImage lado_izquierdo0;
    public BufferedImage sprite_actual;

    //ancho y alto del collide
    public final int ancho_ente, alto_ente, sep = 3; // separacion entre lineas
    public Objeto objeto_ente;

    protected Mapa mapa;

    public Entidad(int vida_actual) {
        this.vida_maxima = 100;
        this.vida_actual = vida_maxima;

        frente0 = null;
        espalda0 = null;
        lado_derecho0 = null;
        lado_izquierdo0 = null;

        this.objeto_ente = null;
        ancho_ente = 0;
        alto_ente = 0;
    }

    public Entidad(String ruta_imagen, int lado, int centrox, int centroy, String tag, int[] frente, int[] espalda, int[] lado_izquierdo, int[] lado_derecho) {

        ancho_ente = lado / 2;
        alto_ente = lado / 2;

        if (tag.compareToIgnoreCase(Objeto.Tag.JUGADOR) != 0) {
            this.x = centrox - GestorPrincipal.CENTROX;
            this.y = centroy - GestorPrincipal.CENTROY;
        }
        posx_inicial = (int) x;
        posy_inicial = (int) y;

        frente0 = new HojaSprites(ruta_imagen, lado, false).obtenerSprite(frente[0], frente[1]).obtenerImagen();
        espalda0 = new HojaSprites(ruta_imagen, lado, false).obtenerSprite(espalda[0], espalda[1]).obtenerImagen();
        lado_derecho0 = new HojaSprites(ruta_imagen, lado, false).obtenerSprite(lado_derecho[0], lado_derecho[1]).obtenerImagen();
        lado_izquierdo0 = new HojaSprites(ruta_imagen, lado, false).obtenerSprite(lado_izquierdo[0], lado_izquierdo[1]).obtenerImagen();

        sprite_actual = frente0;

        generarCollides(centrox, centroy, tag);

        this.vida_maxima = 100;
        vida_actual = vida_maxima;
        esta_vivo = true;

    }

    public Entidad(String ruta_imagen, int ancho, int alto, int centrox, int centroy, String tag, int[] frente, int[] espalda, int[] lado_izquierdo, int[] lado_derecho) {

        ancho_ente = ancho / 2;
        alto_ente = alto / 2;

        if (tag.compareToIgnoreCase(Objeto.Tag.JUGADOR) != 0) {
            this.x = centrox - GestorPrincipal.CENTROX;
            this.y = centroy - GestorPrincipal.CENTROY;
        }
        posx_inicial = (int) x;
        posy_inicial = (int) y;

        frente0 = new HojaSprites(ruta_imagen, ancho, alto, false).obtenerSprite(frente[0], frente[1]).obtenerImagen();
        espalda0 = new HojaSprites(ruta_imagen, ancho, alto, false).obtenerSprite(espalda[0], espalda[1]).obtenerImagen();
        lado_derecho0 = new HojaSprites(ruta_imagen, ancho, alto, false).obtenerSprite(lado_derecho[0], lado_derecho[1]).obtenerImagen();
        lado_izquierdo0 = new HojaSprites(ruta_imagen, ancho, alto, false).obtenerSprite(lado_izquierdo[0], lado_izquierdo[1]).obtenerImagen();

        sprite_actual = frente0;

        generarCollides(centrox, centroy, tag);

        this.vida_maxima = 100;
        vida_actual = vida_maxima;
        esta_vivo = true;

    }

    protected void generarCollides(int centrox, int centroy, String tag) {
        
        int aumento = 1;
        if(tag.compareToIgnoreCase(Objeto.Tag.ENEMIGO) == 0)
            aumento = (alto_ente/2)*3;
        Rectangle collide_arriba = new Rectangle(centrox + ancho_ente / 2 + sep, centroy + alto_ente, ancho_ente - sep, 1);
        Rectangle collide_derecha = new Rectangle(centrox + 26, centroy + alto_ente + sep + 2, 1, alto_ente + aumento - sep * 2);
        Rectangle collide_abajo = new Rectangle(centrox + ancho_ente / 2 + sep, centroy + alto_ente * 2 + aumento, ancho_ente - sep, 1);
        Rectangle collide_izquierda = new Rectangle(centrox + ancho_ente / 2, centroy + alto_ente + sep + 2, 1, alto_ente + aumento - sep * 2);

        this.objeto_ente = new Objeto(new Rectangle[]{collide_arriba, collide_derecha,
            collide_abajo, collide_izquierda}, this.nombre, tag);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getVida_maxima() {
        return vida_maxima;
    }

    public int getVida_actual() {
        return vida_actual;
    }

    public int getAncho_jugador() {
        return ancho_ente;
    }

    public int getAlto_jugador() {
        return alto_ente;
    }

    public boolean estaVivo() {
        return esta_vivo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public abstract void mover(Lienzo lienzo);

    public abstract void actualizar(Lienzo lienzo);

    public abstract void dibujar(Graphics g);

    public void agregarVida(int cantidad) {
        if (this.vida_actual + cantidad <= this.vida_maxima) {
            this.vida_actual += cantidad;
        } else {
            this.vida_actual = 100;
        }
    }

    public void quitarVida(int cantidad) {
        if (this.vida_actual >= cantidad) {
            this.vida_actual -= cantidad;
        } else {
            this.vida_actual = 0;
            EstadoAventura.mapa_actual.enemigos.remove(this);
        }
    }

    public void regenerarVida(int cantidad) {
        if (this.vida_actual + cantidad <= this.vida_maxima) {
            this.vida_actual += cantidad;
        } else {
            this.vida_actual = this.vida_maxima;
        }
    }

}
