/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

import herramientas.CargadorRecursos;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author User
 */
public class Sonido implements Runnable {

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
        } catch (JavaLayerException ex) {
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
