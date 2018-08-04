package videojuego.GESTORJUEGO.estados;

import videojuego.mapas.MapaBosque;
import java.awt.Color;
import java.awt.Graphics;
import videojuego.objetos.entidad.Jugador.Jugador;
import videojuego.objetos.entidad.Enemigo.Enemigo;
import videojuego.mapas.Mapa;
import interfaz.Lienzo;
import java.awt.Graphics2D;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GestorPrincipal;
import videojuego.mapas.MapaCasa;
import videojuego.mapas.ciudad.MapaCiudad;
import videojuego.mapas.ciudad.MapaCiudadCasaINN;

public class EstadoAventura implements EstadoJuego {

    //este estado nos muestra al jugador caminando por mapas
    public static Mapa[] mapas;
    public static Mapa mapa_actual;
    private final Jugador jugador;

    public static Enemigo[] enemigos;

    public EstadoAventura(Jugador jugador) {
        this.jugador = jugador;
        iniciaMapasAventura();
        this.jugador.setMapa(mapa_actual);
        EstadoAventura.mapa_actual.iniciarEnemigos(5);
    }

    private void iniciaMapasAventura() {
        mapas = new Mapa[4];
        mapas[0] = new MapaCiudad("Ciudad", "/imagenes/mapaRafa.png", 800, 600, jugador, GestorPrincipal.CENTROX, 1);
        mapas[1] = new MapaBosque("Bosque", "/imagenes/mapa1.png", 800, 600, jugador, 0, -100);
        mapas[2] = new MapaCasa("Zelda", "/imagenes/mapaCarlos.png", 800, 600, jugador, -246, 0);
        mapas[3] = new MapaCiudadCasaINN("Casa Inversiones", "/imagenes/inn.jpg", 576, 704, jugador, 410, 0);

        mapa_actual = mapas[0];
        mapa_actual.musica();
        this.jugador.estado_aventura = this;
    }

    private void dibujarTexto(Graphics g) {
        g.setColor(Color.yellow);
        g.drawString("x: " + jugador.getX() + " y: " + jugador.getY(), 10, 15);
    }

    @Override
    public void actualizar(Lienzo lienzo) {
        jugador.actualizar(lienzo);

        if (mapa_actual.enemigos != null) {
            for (Enemigo enemigo : mapa_actual.enemigos) // para cuando agreguemos mas de un enemigo
            {
                enemigo.actualizar(lienzo);
            }
        }
        
        mapa_actual.actualizar(lienzo);

    }

    public void dibujar(Graphics2D g) {

        mapa_actual.dibujar(g);
        jugador.dibujar(g);

        if (mapa_actual.enemigos != null) {
            for (Enemigo enemigo : mapa_actual.enemigos) {

                enemigo.dibujar(g);
            }
        }

        this.dibujarTexto(g);
    }
}
