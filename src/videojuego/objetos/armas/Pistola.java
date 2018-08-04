package videojuego.objetos.armas;

import java.awt.Graphics;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Pistola {

    public int cantidad_balas, tamaño_cartucho, balas_totales;
    public Bala bala;

    public Pistola(int tamaño_cartucho) {
        balas_totales = 100;
        this.tamaño_cartucho = tamaño_cartucho;
        cantidad_balas = tamaño_cartucho;
    }

    public void dibujar(Graphics g, Jugador j) {
        if (bala != null) {
            bala.dibujar(g, j);
        }
    }

    public void recargar() {
        if (balas_totales > tamaño_cartucho - cantidad_balas) {
            balas_totales = balas_totales - (tamaño_cartucho - cantidad_balas);
            cantidad_balas = tamaño_cartucho;
        }else{
            cantidad_balas = cantidad_balas + balas_totales;
            balas_totales = 0;
        }

    }

}
