package videojuego.objetos.entidad.Jugador.Poderes.poderFuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import videojuego.GESTORJUEGO.estados.EstadoAventura;
import videojuego.GestorPrincipal;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Enemigo.Enemigo;
import videojuego.objetos.entidad.Jugador.Jugador;

public class BolaFuego {

    public static boolean esta_activa;
    public boolean colisiona;
    int posx, posy;
    public static ImageIcon img = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/bolaFuego.png"));
    int inicioX, inicioY;
    Jugador j;
    Objeto rectangulo;
    Object[] col_dir;

    public BolaFuego(Jugador j, int inicioX, int inicioY) {
        this.posx = GestorPrincipal.CENTROX;
        this.posy = GestorPrincipal.CENTROY;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.j = j;
        this.colisiona = false;
    }

    public void dibujar(Graphics g) {
        rectangulo = new Objeto(new Rectangle(posx - j.getX() + inicioX + 15, posy - j.getY() + inicioY + 15, 5, 5), "Bola", Objeto.Tag.ARMA_JUGADOR);
        j.regenerarMana(100);//borrar esto, es para testear
        if (comprobarSiInterseca(rectangulo.getRectangle())) {
            colisiona = true;
        }
        if (colisiona == false) {
            g.drawImage(img.getImage(), posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
            j.getMapa().objetos.add(rectangulo);
        }
    }

    public boolean comprobarSiInterseca(Rectangle[] bola) {
        for (int i = 0; i < EstadoAventura.mapa_actual.enemigos.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (rectangulo.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos[i].objeto_ente.getRectangle()[j])) {
                    EstadoAventura.mapa_actual.enemigos[i].quitarVida(2);
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

}
