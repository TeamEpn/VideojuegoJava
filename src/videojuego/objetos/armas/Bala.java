package videojuego.objetos.armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import sprites.HojaSprites;
import videojuego.GESTORJUEGO.estados.EstadoAventura;
import videojuego.GestorPrincipal;
import videojuego.mapas.Mapa;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Bala extends Objeto {

    private final static HojaSprites HOJA_BALA = new HojaSprites("/imagenes/hojasObjetos/hojaBala.png", 32, false);

    public static BufferedImage bala_abajo = HOJA_BALA.obtenerSprite(0, 0).obtenerImagen();
    public static BufferedImage bala_arriba = HOJA_BALA.obtenerSprite(1, 0).obtenerImagen();
    public static BufferedImage bala_derecha = HOJA_BALA.obtenerSprite(2, 0).obtenerImagen();
    public static BufferedImage bala_izquierda = HOJA_BALA.obtenerSprite(3, 0).obtenerImagen();

    BufferedImage imagen_actual;

    int posx, posy, inicioX, inicioY;
    String direccion;

    public Bala(BufferedImage imagen_actual, String direccion, int inicioX, int inicioY) {
        super(new Rectangle(GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, 100, 100), "bala", Objeto.Tag.BALASO);
        this.imagen_actual = imagen_actual;
        this.posx = GestorPrincipal.CENTROX;
        this.posy = GestorPrincipal.CENTROY;
        this.direccion = direccion;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
    }

    public void dibujar(Graphics g, Jugador j) {

        if (direccion.equals("arriba")) {
            g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
            g.setColor(Color.yellow);
            g.drawRect(this.getRectangle()[0].x, this.getRectangle()[0].y,
                    this.getRectangle()[0].width, this.getRectangle()[0].height);

            this.setRectangle(new Rectangle[]{new Rectangle(posx - j.getX() + inicioX + 11, posy - j.getY() + inicioY + 2, 12, 25)});
            for (int i = 0; i < EstadoAventura.enemigos.length; i++) {
                if (this.getRectangle()[0].intersects(EstadoAventura.enemigos[i].objeto_ente.getRectangle()[0])) {
                    System.out.println("Golpeó al zombie " + EstadoAventura.enemigos[i].id + " en el rectángulo de abajo");
                    int vidaActual = EstadoAventura.enemigos[i].getVida_actual();
                    if (vidaActual > 0) {
                        EstadoAventura.enemigos[i].setVida_actual(vidaActual - 50);
                    }
                }
            }
        }
        if (direccion.equals("abajo")) {
            g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
            g.setColor(Color.yellow);
            g.drawRect(this.getRectangle()[0].x, this.getRectangle()[0].y,
                    this.getRectangle()[0].width, this.getRectangle()[0].height);
            this.setRectangle(new Rectangle[]{new Rectangle(posx - j.getX() + inicioX + 10, posy - j.getY() + inicioY + 2, 12, 25)});

            for (int i = 0; i < EstadoAventura.enemigos.length; i++) {
                if (this.getRectangle()[0].intersects(EstadoAventura.enemigos[i].objeto_ente.getRectangle()[0])) {
                    System.out.println("Golpeó al zombie " + EstadoAventura.enemigos[i].id + " en el rectángulo de arriba");
                    int vidaActual = EstadoAventura.enemigos[i].getVida_actual();
                    if (vidaActual > 0) {
                        EstadoAventura.enemigos[i].setVida_actual(vidaActual - 50);
                    }
                }
            }
            if (direccion.equals("izquierda")) {
                g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
                g.setColor(Color.yellow);
                g.drawRect(this.getRectangle()[0].x, this.getRectangle()[0].y,
                        this.getRectangle()[0].width, this.getRectangle()[0].height);
                this.setRectangle(new Rectangle[]{new Rectangle(posx - j.getX() + inicioX + 2, posy - j.getY() + inicioY + 10, 25, 12)});

                for (int i = 0; i < EstadoAventura.enemigos.length; i++) {
                    if (this.getRectangle()[0].intersects(EstadoAventura.enemigos[i].objeto_ente.getRectangle()[0])) {
                        System.out.println("Golpeó al zombie " + EstadoAventura.enemigos[i].id + " en el rectángulo de derecha");
                        int vidaActual = EstadoAventura.enemigos[i].getVida_actual();
                        if (vidaActual > 0) {
                            EstadoAventura.enemigos[i].setVida_actual(vidaActual - 50);
                        }
                    }
                }
            }
            if (direccion.equals("derecha")) {
                g.drawImage(this.imagen_actual, posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
                g.setColor(Color.yellow);
                g.drawRect(this.getRectangle()[0].x, this.getRectangle()[0].y,
                        this.getRectangle()[0].width, this.getRectangle()[0].height);
                this.setRectangle(new Rectangle[]{new Rectangle(posx - j.getX() + inicioX + 2, posy - j.getY() + inicioY + 11, 25, 12)});

                for (int i = 0; i < EstadoAventura.enemigos.length; i++) {
                    if (this.getRectangle()[0].intersects(EstadoAventura.enemigos[i].objeto_ente.getRectangle()[0])) {
                        System.out.println("Golpeó al zombie " + EstadoAventura.enemigos[i].id + " en el rectángulo de izquierda");
                        int vidaActual = EstadoAventura.enemigos[i].getVida_actual();
                        if (vidaActual > 0) {
                            EstadoAventura.enemigos[i].setVida_actual(vidaActual - 50);
                        }
                    }
                }
            }
        }
    }
}
