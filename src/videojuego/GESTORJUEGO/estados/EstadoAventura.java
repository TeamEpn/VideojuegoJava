package videojuego.GESTORJUEGO.estados;

import herramientas.CargadorRecursos;
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

    private final String texto_archivo = CargadorRecursos.leerArchivoTexto("/texto/prueba.txt");
    public static Enemigo[] enemigos;

    public EstadoAventura(Jugador jugador) {
        this.jugador = jugador;
        iniciaMapasAventura();
        this.jugador.setMapa(mapa_actual);
        EstadoAventura.mapa_actual.iniciarEnemigos();
    }

    private void iniciaMapasAventura() {
        mapas = new Mapa[4];
        mapas[0] = new MapaCiudad("Ciudad", "/imagenes/mapaRafa.png", 800, 600, jugador, GestorPrincipal.CENTROX, -48);
        mapas[1] = new MapaBosque("Bosque", "/imagenes/mapa1.png", 800, 600, jugador, -290, -100);
        mapas[2] = new MapaCasa("Zelda", "/imagenes/mapaCarlos.png", 800, 600, jugador, -246, 0);
        mapas[3] = new MapaCiudadCasaINN("Casa Inversiones", "/imagenes/inn.jpg", 576, 704, jugador, 410, 0);

        mapa_actual = mapas[0];
        this.jugador.estado_aventura = this;
        enemigos = new Enemigo[10];
        for (int i = 0; i < 10; i++) {
            enemigos[i] = new Enemigo(jugador);
            enemigos[i].id = "Zombie " + (i + 1);
        }
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
        jugador.actualizar(lienzo);

        for (Enemigo enemigo : enemigos) // para cuando agreguemos mas de un enemigo
        {
            enemigo.actualizar(lienzo);
        }
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
        for (Enemigo enemigo : enemigos) {
            if (enemigo.getVida_actual() != 0) {
                enemigo.dibujar(g);
            }
        }
        this.dibujarTexto(g);
    }
}
