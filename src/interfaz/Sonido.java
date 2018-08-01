package interfaz;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sonido implements Runnable {

    public static final Sonido MUSICA_MENU = new Sonido("\\recursos\\sonidos\\LIS.mp3");
    public static final Sonido MUSICA_INICIO = new Sonido("\\recursos\\sonidos\\SilentHill.mp3");
    public static final Sonido MUSICA_MAPA_RAFA = new Sonido("\\recursos\\sonidos\\MegaMan.mp3");
    public static final Sonido MUSICA_MAPA_CARLOS = new Sonido("\\recursos\\sonidos\\Zelda.mp3");
    public static final Sonido MUSICA_MAPA_DANNY = new Sonido("\\recursos\\sonidos\\Pokemon.mp3");
    public static final Sonido EFECTO_VIAJE_TIEMPO = new Sonido("\\recursos\\sonidos\\EkkoTime.mp3");
    
    public Player player;
    private BufferedInputStream bis;
    private final String ruta;
    
    public static Sonido actual;

    public Sonido(final String ruta) {
        this.ruta = ruta;
        inicializar();
        actual = null;

    }
    
    private void inicializar() {
        try {
            File file = new File(".");
            FileInputStream fis;
            String path = file.getCanonicalPath() + ruta;
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            player = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reproducir() {
        try {
            inicializar();
            player = new Player(bis);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(this).start();
    }

    public void detener() {
        player.close();
        player = null;
    }
    
    public static void cambioMusica(Sonido sonido_nuevo) {
        
        if(actual == null){
            sonido_nuevo.reproducir();
            actual = sonido_nuevo;
            return;
        }
        if (actual.player != null) {
            actual.detener();
        }
        if (sonido_nuevo.player == null) {
            sonido_nuevo.reproducir();
            actual = sonido_nuevo;
        } 
    }

    @Override
    public void run() {
        try {
            player.play();
        }catch(NullPointerException nul){
            System.out.println("Cambio brusco de musica, Null pointer exception controlado");
        }        
        catch (JavaLayerException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
