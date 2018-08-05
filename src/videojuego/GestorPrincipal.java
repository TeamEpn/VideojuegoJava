package videojuego;

import videojuego.objetos.entidad.Jugador.Jugador;
import interfaz.Lienzo;
import interfaz.Logueo;
import interfaz.Ventana;
import java.util.logging.Level;
import java.util.logging.Logger;
import videojuego.GESTORJUEGO.GestorEstado;

public class GestorPrincipal {

    public static int aps = 0, fps = 0; //actualizaciones por segundo y framses por segundo

    public static boolean esta_funcionando = false;
    public static float escalado = 1f;
    public static int ANCHO = 900, ALTO = 650;
    public static final int CENTROX = ANCHO / 2, CENTROY = ALTO / 2;

    private final Lienzo lienzo;
    private final Ventana ventana;
    private Logueo logueo;
    private final GestorEstado ge;

    private final Jugador jugador;

    public GestorPrincipal() {
        /*logueo = new Logueo();
        while (logueo.isAcceso() == false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        esta_funcionando = true;
        lienzo = new Lienzo(ANCHO, ALTO);
        jugador = new Jugador(lienzo);
        ventana = new Ventana(lienzo);
        ge = new GestorEstado(jugador);
    }

    public static void main(String[] args) {
        GestorPrincipal gp = new GestorPrincipal();
        gp.iniciarBuclePrincipal();
    }

    private void iniciarBuclePrincipal() {
        int aps = 0, fps = 0; //actualizaciones por segundo y framses por segundo
        final int NS_POR_S = 1000000000; //para realizar transformaciones
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_S / APS_OBJETIVO;
        long referenciaActualización = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;
        while (esta_funcionando) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualización;
            referenciaActualización = inicioBucle;

            // delta aumenta hasta alcanzar un sesentavo de segundo
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            while (delta >= 1) {
                //este while se ejecuta exactamente 60 veces por segundo
                actualizar();
                aps++;
                delta -= 1;
            }
            dibujar(); // aqui se cuentan los frames por segundo
            fps++;

            if (System.nanoTime() - referenciaContador > NS_POR_S) {
                ventana.setTitle("Magical Poli, " + "FPS: " + fps + " APS: " + aps);
                aps = 0;
                fps = 0; //por segundo
                referenciaContador = System.nanoTime();
            }
        }
    }

    private void actualizar() {
        lienzo.getTeclado().actualizar(); //actualiza las teclas que pulsamos
        ge.actualizar(lienzo);
    }

    private void dibujar() {
        lienzo.dibujar(ge); //el gestor de estado manejara todos los dibujos
    }
}
