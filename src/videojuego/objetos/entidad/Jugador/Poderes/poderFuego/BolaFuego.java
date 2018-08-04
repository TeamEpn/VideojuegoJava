package videojuego.objetos.entidad.Jugador.Poderes.poderFuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import videojuego.GESTORJUEGO.estados.EstadoAventura;
import videojuego.GestorPrincipal;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Enemigo.Enemigo;
import videojuego.objetos.entidad.Jugador.Jugador;

public class BolaFuego extends Objeto {

    public static boolean esta_activa;
    int posx, posy;
    public static ImageIcon img = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/bolaFuego.png"));
    int inicioX, inicioY;
    Jugador j;
    Objeto rectangulo;
    Object[] col_dir;

    public BolaFuego(Jugador j, int inicioX, int inicioY) {
        super(new Rectangle(GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, 30, 30), "bolaFuego", Objeto.Tag.BALA);
        this.posx = GestorPrincipal.CENTROX;
        this.posy = GestorPrincipal.CENTROY;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.j = j;
    }

    public void dibujar(Graphics g) {
        if (!(EstadoAventura.mapa_actual.getNombre().equals("Casa Inversiones"))) {
            rectangulo = new Objeto(new Rectangle(posx - j.getX() + inicioX + 15, posy - j.getY() + inicioY + 15, 5, 5), "Bola", Objeto.Tag.ARMA_JUGADOR);
            if (comprobarSiInterseca(rectangulo.getRectangle())) {
                esta_activa = false;
            }
            if (esta_activa == true) {
                g.drawImage(img.getImage(), posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
                g.setColor(Color.yellow);
                g.drawRect(this.getRectangle()[0].x, this.getRectangle()[0].y,
                        this.getRectangle()[0].width, this.getRectangle()[0].height);
                this.setRectangle(new Rectangle[]{new Rectangle(posx - j.getX() + inicioX, posy - j.getY() + inicioY, 30, 30)});
            }

            for (int i = 0; i < EstadoAventura.mapa_actual.enemigos.size(); i++) {
                if (this.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos.get(i).objeto_ente.getRectangle()[0])
                        || this.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos.get(i).objeto_ente.getRectangle()[1])
                        || this.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos.get(i).objeto_ente.getRectangle()[2])
                        || this.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos.get(i).objeto_ente.getRectangle()[3])) {
                    EstadoAventura.mapa_actual.enemigos.get(i).quitarVida(j.getDamage() + 30);
                    esta_activa = false;
                }
            }
        }
    }

    public boolean comprobarSiInterseca(Rectangle[] bola) {

        for (int i = 0; i < EstadoAventura.mapa_actual.enemigos.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (rectangulo.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos.get(i).objeto_ente.getRectangle()[j])) {
                    EstadoAventura.mapa_actual.enemigos.get(i).quitarVida(2);

                    return true;
                }
            }
        }
        for (int i = 0; i < j.getMapa().objetos.size(); i++) {
            Rectangle[] lados_objeto = j.getMapa().objetos.get(i).getRectangle();
            if (lados_objeto[0].intersects(bola[0])) {
                return true;
            }
        }
        return false;
    }

    public void actualizar() {

        if (EstadoAventura.mapa_actual.enemigos != null) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < EstadoAventura.mapa_actual.enemigos.size(); j++) {
                    if (rectangulo.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos.get(j).objeto_ente.getRectangle()[i])) {
                        EstadoAventura.mapa_actual.enemigos.get(j).quitarVida(2);
                        break;
                    }
                }

            }
        }

    }

}
