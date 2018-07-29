package videojuego.GESTORJUEGO.estados;

import videojuego.mapas.MapaBosque;
import java.awt.Color;
import java.awt.Graphics;
import videojuego.entidad.Jugador.Jugador;
import java.awt.Font;
import videojuego.entidad.Enemigo.Enemigo;
import videojuego.mapas.Mapa;
import interfaz.Lienzo;
import herramientas.CargadorRecursos;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GestorPrincipal;
import videojuego.Sonido;
import videojuego.mapas.MapaCiudad;

public class EstadoAventura implements EstadoJuego{
    //este estado nos muestra al jugador caminando por mapas
    public Mapa[] mapas;
    public Mapa mapa_actual;
    private final Jugador jugador;
    private final String texto_archivo = CargadorRecursos.leerArchivoTexto("/texto/prueba.txt");

    public Enemigo[] enemigos;
    
    public EstadoAventura(Jugador jugador) {
        this.jugador = jugador;
        iniciaMapasAventura();
        this.jugador.setMapa(mapa_actual);
        this.jugador.estado_aventura = this;
        enemigos = new Enemigo[1];
        enemigos[0] = new Enemigo(jugador);
    
    }
    
    private void iniciaMapasAventura(){
        //aqui se deben ubicar las rutas exactas (dentro de la carpeta recursos) en donde esta la imagen del mapa
        mapas = new Mapa[3];
        mapas[0] = new MapaCiudad("Ciudad","/imagenes/mapaRafa.png",800,600,jugador);
        mapas[1] = new MapaBosque("Bosque","/imagenes/mapa1.png",800,600,jugador);
        
        
        mapa_actual = mapas[1];
        mapa_actual.musica();
        
        
    }
    private void dibujarTexto(Graphics g){
        g.setColor(Color.yellow);
        g.setFont(Font.getFont("BOLD"));
        g.drawString("x: " + jugador.getX() + " y: " + jugador.getY(), 10, 15);
        //Texto de un archivo
        g.drawString(texto_archivo, 10, 30);
    }
    
    @Override
    public void actualizar(Lienzo lienzo) {
        jugador.actualizar(lienzo); 
        
        for(Enemigo enemigo:enemigos) // para cuando agreguemos mas de un enemigo
            enemigo.actualizar(lienzo);
    }

    @Override
    public void dibujar(Graphics g) {
        //AQUI SE DIBUJA LA MAYORIA DEL JUEGO
        mapa_actual.dibujar(g);
        jugador.dibujar(g);
        
        for(Enemigo enemigo:enemigos)
            enemigo.dibujar(g);
        
        this.dibujarTexto(g);        
    }
}
