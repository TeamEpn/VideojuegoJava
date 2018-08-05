package videojuego.objetos.armas;

import java.awt.Graphics;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Pistola {

    public int cantidad_balas, tamaño_cartucho, balas_totales;
    ;
    public Bala[] balas;

    public Pistola(int tamaño_cartucho) {
        balas_totales = 100;
        this.tamaño_cartucho = tamaño_cartucho;
        cantidad_balas = tamaño_cartucho;
        this.balas = new Bala[cantidad_balas];
    }

    public void disparar(int[] vista, Jugador jugador) {
        final int inicioX = jugador.getX(), inicioY = jugador.getY();
        if (vista[2] == 1) {
            balas[cantidad_balas] = new Bala(Bala.bala_abajo, "abajo", inicioX, inicioY);
            //System.out.println(bal);
            new Thread(new HiloDisparoArma(balas[cantidad_balas], jugador, "abajo")).start();
        } else if (vista[0] == 1) {
            balas[cantidad_balas] = new Bala(Bala.bala_arriba, "arriba", inicioX, inicioY);
            new Thread(new HiloDisparoArma(balas[cantidad_balas], jugador, "arriba")).start();
        } else if (vista[1] == 1) {
            balas[cantidad_balas] = new Bala(Bala.bala_derecha, "derecha", inicioX, inicioY);
            new Thread(new HiloDisparoArma(balas[cantidad_balas], jugador, "derecha")).start();
        } else if (vista[3] == 1) {
            balas[cantidad_balas] = new Bala(Bala.bala_izquierda, "izquierda", inicioX, inicioY);
            new Thread(new HiloDisparoArma(balas[cantidad_balas], jugador, "izquierda")).start();
        }
    }

    public void recargar() {
        if (balas_totales > tamaño_cartucho - cantidad_balas) {
            balas_totales = balas_totales - (tamaño_cartucho - cantidad_balas);
            cantidad_balas = tamaño_cartucho;
        } else {
            cantidad_balas = cantidad_balas + balas_totales;
            balas_totales = 0;
        }
    }
    public void dibujar(Graphics g, Jugador j) {
        for (Bala bala : balas) {
            if (bala != null) {
                bala.dibujar(g, j);
            }
        }

    }

}
