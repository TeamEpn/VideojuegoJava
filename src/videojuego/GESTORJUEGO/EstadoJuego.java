package videojuego.GESTORJUEGO;

import java.awt.Graphics;
import interfaz.Lienzo;

public interface EstadoJuego {
    //agrupa clases y las tratamos como un mismo tipo
    void actualizar(Lienzo lienzo);
    void dibujar(final Graphics g);
}
