package videojuego.objetos.entidad.Enemigo;

import java.awt.Color;
import java.awt.Graphics;
import videojuego.objetos.entidad.Entidad;
import videojuego.objetos.entidad.Jugador.Jugador;
import videojuego.GestorPrincipal;
import videojuego.objetos.Objeto;
import interfaz.Lienzo;
import java.util.ArrayList;
import videojuego.GESTORJUEGO.estados.EstadoAventura;

public class Enemigo extends Entidad {

    public String id;
    Jugador jugador;

    int contador = 0;

    public Enemigo(Jugador jugador) {

        //el centrox para ubicarlo en la esquina superior izquierda
        super("/imagenes/hojasEnemigos/1.png", 32, GestorPrincipal.CENTROX + 100 + (int) (Math.random() * 200) + 1, GestorPrincipal.CENTROY + 100, Objeto.Tag.ENEMIGO,
                new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0});

        this.jugador = jugador;
        this.setMapa(EstadoAventura.mapa_actual);

    }

    @Override
    public void actualizar(Lienzo lienzo) {

        this.mover(lienzo);

    }

    @Override
    public void mover(Lienzo lienzo) {

        ArrayList<Objeto> col_dir = new ArrayList<>();
        Objeto col = null;
        String[] direccion = new String[]{"none","none","none","none"};

        if (mapa.objetos != null) {
            for (int i = 0; i < mapa.objetos.size(); i++) {
                this.verificarColision(mapa.objetos.get(i),direccion,col_dir);
                if (col_dir.size()>0) {
                    break;
                }
            }
        }
        
        if (col_dir.isEmpty()) {
            this.verificarColision(jugador.objeto_ente,direccion,col_dir);
        }

        if (jugador.getY() > this.y) {
            if (!(direccion[2].compareToIgnoreCase("entorno_abajo") == 0)
                    && !(direccion[2].compareToIgnoreCase("jugador_abajo") == 0)) {
                this.y++;
            }

        } else if (jugador.getY() <= this.y) {

            if (!(direccion[0].compareToIgnoreCase("entorno_arriba") == 0)
                    && !(direccion[0].compareToIgnoreCase("jugador_arriba") == 0)) {
                this.y--;
            }
        }

        if (jugador.getX() > this.x) {
            if (!(direccion[1].compareToIgnoreCase("entorno_derecha") == 0)
                    && !(direccion[1].compareToIgnoreCase("jugador_derecha") == 0)) {

                this.x++;
            }
        } else if (jugador.getX() <= this.x) {
            if (!(direccion[2].compareToIgnoreCase("entorno_izquierda") == 0)
                    && !(direccion[2].compareToIgnoreCase("jugador_izquierda") == 0)) {
                this.x--;
            }
        }

    }

    @Override
    public void dibujar(Graphics g) {

        if (vida_actual != 0) {
            g.drawImage(this.sprite_actual, this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY(), null);
            g.setColor(Color.red);
            g.fillRect(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 5, vida_actual / 3, 3);
            g.setColor(Color.orange);

            this.generarCollides(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 18, Objeto.Tag.ENEMIGO);
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 2) {
                    if (i == 0) {
                        g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y,
                        this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);
                    } else {
                        g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y + 18,
                                this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);
                    }
                } else {
                    g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y - 3,
                            this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height + 20);
                }

            }

            int porc = 40;
            int vid = (this.vida_actual * porc) / this.vida_maxima;

        }

    }

}
