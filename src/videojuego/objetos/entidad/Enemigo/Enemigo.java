package videojuego.objetos.entidad.Enemigo;

import java.awt.Color;
import java.awt.Graphics;
import videojuego.objetos.entidad.Entidad;
import videojuego.objetos.entidad.Jugador.Jugador;
import videojuego.GestorPrincipal;
import videojuego.objetos.Objeto;
import interfaz.Lienzo;
import java.util.Random;
import videojuego.GESTORJUEGO.estados.EstadoAventura;

public class Enemigo extends Entidad {

    public String id;
    Jugador jugador;

    int contador = 0;
    public static int ZOMBIES_MUERTOS = 0;
    public boolean esBoss = false;

    public Enemigo(Jugador jugador) {

        //el centrox para ubicarlo en la esquina superior izquierda
        super("/imagenes/hojasEnemigos/1.png", 32, GestorPrincipal.CENTROX + 100 + (int) (Math.random() * 200) + 1, GestorPrincipal.CENTROY + 100, Objeto.Tag.ENEMIGO,
                new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0});

        this.jugador = jugador;
        this.setMapa(EstadoAventura.mapa_actual);

    }

    public Enemigo(Jugador jugador, boolean boss) {

        //el centrox para ubicarlo en la esquina superior izquierda
        super("/imagenes/hojasEnemigos/Boss.png", 50, GestorPrincipal.CENTROX + 100 + (int) (Math.random() * 200) + 1, GestorPrincipal.CENTROY + 100, Objeto.Tag.ENEMIGO,
                new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0});

        this.jugador = jugador;
        this.setMapa(EstadoAventura.mapa_actual);
        esBoss = boss;
    }

    @Override
    public void actualizar(Lienzo lienzo) {

        this.mover(lienzo);

    }

    @Override
    public void mover(Lienzo lienzo) {

        Object[] col_dir = null;
        Objeto col = null;
        String direccion = "none";

        if (mapa.objetos != null) {
            for (int i = 0; i < mapa.objetos.size(); i++) {
                col_dir = this.verificarColision(mapa.objetos.get(i));
                if (col_dir[1] != "") {
                    break;
                }
            }
        }
        if (col_dir != null) {
            if (col_dir[1] == "") {
                col_dir = this.verificarColision(jugador.objeto_ente);
            }

            if (col_dir[1] != "") {
                col = (Objeto) col_dir[0];
                direccion = col_dir[1].toString();
            }
        }

        if (jugador.getY() > this.y) {
            if (!(direccion.compareToIgnoreCase("entorno_abajo") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_abajo") == 0)) {
                this.y++;
            }

        } else if (jugador.getY() <= this.y) {

            if (!(direccion.compareToIgnoreCase("entorno_arriba") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_arriba") == 0)) {
                this.y--;
            }
        }

        if (jugador.getX() > this.x) {
            if (!(direccion.compareToIgnoreCase("entorno_derecha") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_derecha") == 0)) {

                this.x++;
            }
        } else if (jugador.getX() <= this.x) {
            if (!(direccion.compareToIgnoreCase("entorno_izquierda") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_izquierda") == 0)) {
                this.x--;
            }
        }

    }

    @Override
    public void dibujar(Graphics g) {

        if (esBoss == false) {
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

            } else if (ZOMBIES_MUERTOS < 10) {
                this.x = new Random().nextInt(EstadoAventura.mapa_actual.getAncho()-100);
                this.y = new Random().nextInt(EstadoAventura.mapa_actual.getAlto()-100);
                this.vida_actual = this.vida_maxima;
                ZOMBIES_MUERTOS++;
            } else {
                EstadoAventura.mapa_actual.iniciarBossFinal();
            }
        } else if (vida_actual != 0) {
            g.drawImage(this.sprite_actual, this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY(), null);
            g.setColor(Color.red);
            g.fillRect(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 5, vida_actual / 3, 3);
            g.setColor(Color.orange);

            this.vida_maxima = 900;
            this.generarCollidesBoss(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 18, Objeto.Tag.ENEMIGO);

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

        } else if (EstadoAventura.mapa_actual.getNombre().equals("Ciudad")) {
            ZOMBIES_MUERTOS = 0;
            EstadoAventura.mapa_actual = EstadoAventura.mapas[1];
            EstadoAventura.mapa_actual.musica();
            EstadoAventura.mapa_actual.iniciarEnemigos(5);
            jugador.setMapa(EstadoAventura.mapa_actual);
        } else if (EstadoAventura.mapa_actual.getNombre().equals("Bosque")) {
            ZOMBIES_MUERTOS = 0;
            EstadoAventura.mapa_actual = EstadoAventura.mapas[2];
            EstadoAventura.mapa_actual.musica();
            EstadoAventura.mapa_actual.iniciarEnemigos(5);
            jugador.setMapa(EstadoAventura.mapa_actual);
        } else if (EstadoAventura.mapa_actual.getNombre().equals("Zelda")) {
            ZOMBIES_MUERTOS = 0;
            EstadoAventura.mapa_actual = EstadoAventura.mapas[0];
            EstadoAventura.mapa_actual.musica();
            EstadoAventura.mapa_actual.iniciarEnemigos(5);
            jugador.setMapa(EstadoAventura.mapa_actual);
        }
    }

}
