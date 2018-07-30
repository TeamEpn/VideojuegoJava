package videojuego;

import videojuego.entidad.Jugador.Jugador;
import interfaz.Lienzo;
import interfaz.Ventana;

import videojuego.GESTORJUEGO.GestorEstado;

public class GestorPrincipal {
    public static int aps=0,fps=0; //actualizaciones por segundo y framses por segundo

    private boolean esta_funcionando = false;
    public static final int ANCHO = 800,ALTO = 600,CENTROX = ANCHO/2,CENTROY = ALTO/2;
    
    private final Lienzo lienzo;
    private final Ventana ventana;
    private final GestorEstado ge;
    
    private final Jugador jugador;
    
        

    public static Sonido musica_menu = new Sonido("\\recursos\\sonidos\\LIS.mp3");
    public static Sonido musica_inicio = new Sonido("\\recursos\\sonidos\\SilentHill.mp3");
    public static Sonido musica_mapa_rafa = new Sonido("\\recursos\\sonidos\\MegaMan.mp3");
    public static Sonido musica_mapa_carlos = new Sonido("\\recursos\\sonidos\\Zelda.mp3");
    public static Sonido musica_mapa_danny = new Sonido("\\recursos\\sonidos\\Pokemon.mp3");
    
    
    public GestorPrincipal(){
        esta_funcionando = true;
        lienzo = new Lienzo(ANCHO,ALTO);
        
        jugador = new Jugador(lienzo);
        
        ventana = new Ventana(lienzo);        
        ge = new GestorEstado(jugador);
        
    }
    
    public static void main(String[] args){
        GestorPrincipal gp = new GestorPrincipal();
        gp.iniciarBuclePrincipal();
    }

    private void iniciarBuclePrincipal() {

        int aps=0,fps=0; //actualizaciones por segundo y framses por segundo

        
        final int NS_POR_S = 1000000000; //para realizar transformaciones
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_S/APS_OBJETIVO;
        
        // para la actualización
        long referenciaActualización = System.nanoTime();
                // referencia para que muestre informacion segundo a segundo
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        

        while(esta_funcionando){
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualización;
            referenciaActualización = inicioBucle;
            
            // delta aumenta hasta alcanzar un sesentavo de segundo
            delta += tiempoTranscurrido/NS_POR_ACTUALIZACION;
            while(delta>=1){
                //este while se ejecuta exactamente 60 veces por segundo
                actualizar(); 
                aps++;
                delta -= 1;
            }
            
            dibujar(); // aqui se cuentan los frames por segundo
            fps++;
            
            if(System.nanoTime() - referenciaContador > NS_POR_S){
                ventana.setTitle("Magical Poli, " + "FPS: " + fps + " APS: " + aps);
                aps = 0;
                fps = 0; //por segundo
                referenciaContador = System.nanoTime();
            }
        }
    }
    
    private void actualizar(){
        lienzo.getTeclado().actualizar(); //actualiza las teclas que pulsamos
        
        ge.actualizar(lienzo);   
    }
    
    private void dibujar(){
        lienzo.dibujar(ge); //el gestor de estado manejara todos los dibujos
    }
    
}
