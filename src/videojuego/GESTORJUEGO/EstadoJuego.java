
package videojuego.GESTORJUEGO;

import interfaz.Lienzo;
import java.awt.Graphics2D;

public interface EstadoJuego {
    void actualizar(Lienzo lienzo);
    void dibujar(final Graphics2D g);
}
