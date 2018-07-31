
package videojuego.objetos.armas;

import java.awt.Rectangle;
import videojuego.GESTORJUEGO.estados.EstadoAventura;
import videojuego.objetos.Objeto;

public class Espada extends Objeto{
    
    public Espada(Rectangle rectangle, String id, String tag) {
        super(rectangle, id, tag);
    }
    
    public void actualizar(){
        if(this.getRectangle()[0].intersects(EstadoAventura.enemigos[0].objeto_ente.getRectangle()[1])){
            System.out.println("TOCADO");
        }
    }
    
    
}
