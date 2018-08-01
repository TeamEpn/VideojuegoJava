package videojuego.objetos.entidad.Jugador.Poderes.poderFuego;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import videojuego.GestorPrincipal;
import videojuego.objetos.entidad.Jugador.Jugador;

public class BolaFuego {
    
    public static boolean esta_activa;
    int posx,posy;
    public static ImageIcon img = new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/bolaFuego.png"));
    int inicioX,inicioY;
    Jugador j;
    
    public BolaFuego(Jugador j,int inicioX,int inicioY) {
        this.posx = GestorPrincipal.CENTROX;
        this.posy = GestorPrincipal.CENTROY;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.j = j;
    }
         
    public void dibujar(Graphics g){
        g.drawImage(img.getImage(), posx - j.getX() + inicioX, posy - j.getY() + inicioY, null);
    }
    
    
}
