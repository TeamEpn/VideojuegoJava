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

    
    public static Sonido musica_menu = new Sonido("\\recursos\\sonidos\\LIS.mp3");
    public static Sonido musica_inicio = new Sonido("\\recursos\\sonidos\\SilentHill.mp3");
    public static Sonido musica_mapa_rafa = new Sonido("\\recursos\\sonidos\\MegaMan.mp3");
    public static Sonido musica_mapa_carlos = new Sonido("\\recursos\\sonidos\\Zelda.mp3");
    public static Sonido musica_mapa_danny = new Sonido("\\recursos\\sonidos\\Pokemon.mp3");
    public static Sonido sonido_viaje_tiempo = new Sonido("\\recursos\\sonidos\\EkkoTime.mp3");
    
    
    public Player player;
    BufferedInputStream bis;
    String ruta;
    
    public static Sonido actual;

    public Sonido(String ruta) {
        this.ruta = ruta;
        inicializar();
        actual = null;

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

    public void detener() {

        player.close();
        player = null;

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

}
