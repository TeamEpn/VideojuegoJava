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

            if(!EstadoAventura.mapa_actual.enemigos.isEmpty())
                for (int enemigo = 0; enemigo < EstadoAventura.mapa_actual.enemigos.size(); enemigo++) {
                    for (int i = 0; i < 4; i++) {
                        
                        try{
                            if (this.getRectangle()[0].intersects(EstadoAventura.mapa_actual.enemigos.get(enemigo).objeto_ente.getRectangle()[i])) {
                                EstadoAventura.mapa_actual.enemigos.get(enemigo).quitarVida(jugador.getDamage());
                                if(EstadoAventura.mapa_actual.enemigos.isEmpty())
                                    break;

                            }
                        }catch(IndexOutOfBoundsException index){
                            System.out.println("Se borro un enemigo mientras se realizaba una lectura");

                        }
                        
                    }

                }
        }

    }

}
