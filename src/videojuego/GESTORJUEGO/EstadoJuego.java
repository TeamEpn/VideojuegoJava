/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.GESTORJUEGO;

import interfaz.Lienzo;
import java.awt.Graphics;

/**
 *
 * @author User
 */
public interface EstadoJuego {
    void actualizar(Lienzo lienzo);
    void dibujar(final Graphics g);
}
