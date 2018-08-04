package videojuego.objetos.armas;

import java.awt.Rectangle;
import videojuego.GESTORJUEGO.estados.EstadoAventura;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Espada extends Objeto {

    Jugador jugador;

    public Espada(Rectangle rectangle, String id, String tag, Jugador jugador) {
        super(rectangle, id, tag);
        this.jugador = jugador;
    }

    public void actualizar() {

        if (EstadoAventura.mapa_actual.enemigos != null) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < EstadoAventura.mapa_actual.enemigos.length; j++) {
                    if (this.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos[j].objeto_ente.getRectangle()[i])) {
                        EstadoAventura.mapa_actual.enemigos[j].quitarVida(jugador.getDamage());
                        break;
                    }
                }

            }
        }

    }

}
