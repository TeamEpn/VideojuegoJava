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
import videojuego.objetos.Colision;

public class Enemigo extends Entidad {

    public String id;
    Jugador jugador;

    int contador = 0;
    public static int ZOMBIES_MUERTOS = 0;
    public boolean esBoss = false;

    public Enemigo(Jugador jugador, String id) {
        
        //el centrox para ubicarlo en la esquina superior izquierda
        super("/imagenes/hojasEnemigos/1.png", 32, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, Objeto.Tag.ENEMIGO,
                new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0});

        this.jugador = jugador;
        this.setMapa(EstadoAventura.mapa_actual);
        this.nombre = "ZOMBIE";
        this.id = id;
    }

    public Enemigo(Jugador jugador, String id, boolean boss) {

        //el centrox para ubicarlo en la esquina superior izquierda
        super("/imagenes/hojasEnemigos/Boss.png", 50, GestorPrincipal.CENTROX + 100 + (int) (Math.random() * 200) + 1, GestorPrincipal.CENTROY + 100, Objeto.Tag.ENEMIGO,
                new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0});

        this.jugador = jugador;
        this.id = id;
        this.setMapa(EstadoAventura.mapa_actual);
        esBoss = boss;
    }

    @Override
    public void actualizar(Lienzo lienzo) {

        this.mover(lienzo);

    }

    @Override
    public void mover(Lienzo lienzo) {

        ArrayList<Objeto> info_objetos_colisionados = new ArrayList<>();
        String[] direccion = new String[]{"none", "none", "none", "none"};

        if (mapa.objetos != null) {
            for (int i = 0; i < mapa.objetos.size(); i++) {
                Colision.obtenerInfoColisionEnemigo(this, mapa.objetos.get(i), direccion, info_objetos_colisionados);
            }
        }

        if (EstadoAventura.mapa_actual.enemigos != null) {
            Colision.obtenerInfoColisionEnemigo(this, jugador.objeto_ente, direccion, info_objetos_colisionados);
        }

        if (jugador.getY() > this.y) {
            if (!(direccion[2].compareToIgnoreCase("entorno_abajo") == 0)
                    && !(direccion[2].compareToIgnoreCase("jugador_abajo") == 0)) {
                this.sprite_actual = this.frente0;
                this.y++;
            }

        } else if (jugador.getY() <= this.y) {

            if (!(direccion[0].compareToIgnoreCase("entorno_arriba") == 0)
                    && !(direccion[0].compareToIgnoreCase("jugador_arriba") == 0)) {
                this.sprite_actual = this.espalda0;
                this.y--;
            }
        }

        if (jugador.getX() > this.x) {
            if (!(direccion[1].compareToIgnoreCase("entorno_derecha") == 0)
                    && !(direccion[1].compareToIgnoreCase("jugador_derecha") == 0)) {
                this.sprite_actual = this.lado_izquierdo0;
                this.x++;
            }
        } else if (jugador.getX() <= this.x) {
            if (!(direccion[3].compareToIgnoreCase("entorno_izquierda") == 0)
                    && !(direccion[3].compareToIgnoreCase("jugador_izquierda") == 0)) {
                this.sprite_actual = this.lado_derecho0;
                this.x--;
            }
        }

    }

    @Override
    public void dibujar(Graphics g) {

        if (!this.esBoss) {

            g.drawImage(this.sprite_actual, this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY(), null);
            g.setColor(Color.red);
            g.fillRect(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 5, vida_actual / 3, 3);
            g.setColor(Color.orange);

            g.drawImage(this.sprite_actual, this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY(), null);
            g.setColor(Color.red);
            g.fillRect(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 5, vida_actual / 3, 3);
            g.setColor(Color.orange);

            this.generarCollides(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 18, Objeto.Tag.ENEMIGO);

            for (int i = 0; i < 4; i++) {

                g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y,
                        this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);

            }
        } else {

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

            this.generarCollides(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 18, Objeto.Tag.ENEMIGO);

            for (int i = 0; i < 4; i++) {
                g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y,
                        this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);

            }

        }
    }
}
