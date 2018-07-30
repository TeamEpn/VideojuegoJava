/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.entidad.Jugador;

import java.awt.Graphics;

public class Pistola {
    

    public int cantidad_balas;
    Bala bala;
    
    public Pistola(int cantidad_balas) {
        this.cantidad_balas = cantidad_balas;
    }

    void dibujar(Graphics g, Jugador j) {
        if(bala != null)
            bala.dibujar(g, j);
    }
}
