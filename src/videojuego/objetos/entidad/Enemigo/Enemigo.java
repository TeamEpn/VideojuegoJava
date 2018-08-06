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

        this.setMapa(EstadoAventura.mapa_actual);
        boolean buen_spawn = false;
        for (int a = 0; a < 1000; a++) {
            String[] direccion = {"", "", "", ""};
            ArrayList<Objeto> info_objetos_colisionados = new ArrayList<>();

            if (mapa.objetos != null) {
                for (int i = 0; i < mapa.objetos.size(); i++) {
                    Colision.obtenerInfoColisionEnemigo(this, mapa.objetos.get(i), direccion, info_objetos_colisionados);
                }

                if (info_objetos_colisionados.isEmpty()) {
                    buen_spawn = true;
                    break;
                } else {
                    this.x = this.random.nextInt(800) - jugador.getX();
                    this.y = this.random.nextInt(600) - jugador.getY();
                }
            }

        }
        if (!buen_spawn) {
            System.out.println("DIRECTO");
            this.x = 730 - jugador.getX();
            this.y = 170 - jugador.getY();
        }

        this.jugador = jugador;

        this.nombre = "ZOMBIE";
        this.id = id;
    }

    public Enemigo(Jugador jugador, String id, boolean boss) {

        //el centrox para ubicarlo en la esquina superior izquierda
        super("/imagenes/hojasEnemigos/Boss.png", 50, GestorPrincipal.CENTROX + 100 + (int) (Math.random() * 200) + 1, GestorPrincipal.CENTROY + 100, Objeto.Tag.ENEMIGO,
                new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0});
        this.nombre = "ZOMBIE JEFE";
        this.jugador = jugador;
        this.id = id;
        this.setMapa(EstadoAventura.mapa_actual);
        this.vida_actual = (this.vida_maxima = 1000);
        esBoss = boss;
        this.velocidad = this.velocidad_original * 2;
    }

    @Override
    public void actualizar(Lienzo lienzo) {

        this.mover(lienzo);

    }

    @Override
    public void mover(Lienzo lienzo) {
        int dEvasionColsiion = 2;
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
                this.y += this.velocidad / 2;

            } else {
                if (direccion[2].compareToIgnoreCase("entorno_abajo") == 0) {
                    if(jugador.getX()>this.x)
                        this.x = this.x + dEvasionColsiion;
                    else
                        this.x = this.x - dEvasionColsiion;
                }
            }

        } else if (jugador.getY() < this.y) {

            if (!(direccion[0].compareToIgnoreCase("entorno_arriba") == 0)
                    && !(direccion[0].compareToIgnoreCase("jugador_arriba") == 0)) {
                this.sprite_actual = this.espalda0;
                this.y -= this.velocidad / 2;

            } else {
                if (direccion[0].compareToIgnoreCase("entorno_arriba") == 0) {
                     if(jugador.getX()>this.x)
                        this.x = this.x + dEvasionColsiion;
                    else
                        this.x = this.x - dEvasionColsiion;
                }
            }
        }
        if (jugador.getX() > this.x) {
            if (!(direccion[1].compareToIgnoreCase("entorno_derecha") == 0)
                    && !(direccion[1].compareToIgnoreCase("jugador_derecha") == 0)) {
                this.sprite_actual = this.lado_izquierdo0;
                this.x += this.velocidad / 2;

            } else {
                if (direccion[1].compareToIgnoreCase("entorno_derecha") == 0) {
                    if(jugador.getY()>this.y)
                    this.y = this.y + dEvasionColsiion;
                    else
                    this.y = this.y - dEvasionColsiion;    
                }
            }
        } else if (jugador.getX() < this.x) {
            if (!(direccion[3].compareToIgnoreCase("entorno_izquierda") == 0)
                    && !(direccion[3].compareToIgnoreCase("jugador_izquierda") == 0)) {
                this.sprite_actual = this.lado_derecho0;
                this.x -= this.velocidad / 2;

            } else {
                if (direccion[3].compareToIgnoreCase("entorno_izquierda") == 0) {
                   if(jugador.getY()>this.y)
                    this.y = this.y + dEvasionColsiion;
                    else
                    this.y = this.y - dEvasionColsiion; 
                }
            }
        }

    }

    @Override
    public void dibujar(Graphics g
    ) {

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
            g.fillRect(this.x + GestorPrincipal.CENTROX - jugador.getX(), this.y + GestorPrincipal.CENTROY - jugador.getY() - 5, vida_actual / 20, 6);
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
        }
    }
}
