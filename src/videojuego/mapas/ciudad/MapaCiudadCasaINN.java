
package videojuego.mapas.ciudad;

import interfaz.Sonido;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.mapas.Mapa;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Jugador.Jugador;

public class MapaCiudadCasaINN extends Mapa{

    public MapaCiudadCasaINN(String nombre, String ruta, int ancho, int alto, Jugador jugador, int desfasex, int desfasey) {
        super(nombre, ruta, ancho, alto, jugador, desfasex, desfasey);
    }

    @Override
    protected void generarObjetosColisionables(Graphics g, int x, int y, Jugador jugador) {
        objetos = new ArrayList<>();

        //CASAS
        objetos.add(new Objeto(new Rectangle(93 + desfasex - x, 523 + desfasey - y, 36, 29), "Inversion Secreta", Objeto.Tag.INVERSION));
        objetos.add(new Objeto(new Rectangle(192 + desfasex - x, 660 + desfasey - y, 30, 20), "Puerta a ciudad", Objeto.Tag.TELEPORT_CIUDAD));
        
    }

    @Override
    public void musica() {
        Sonido.cambioMusica(Sonido.MUSICA_MENU);
    }
    
}
