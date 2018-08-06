package interfaz;

import input.Mouse;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import input.Teclado;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GestorPrincipal;

public class Lienzo extends Canvas {
    
    private final int ancho,alto;
    private final Teclado teclado;
    private final Mouse mouse;

    public Lienzo(final int ancho,final int alto) {
        this.ancho = ancho;
        this.alto = alto;
        teclado = new Teclado();
        mouse = new Mouse(this);
        addMouseListener(mouse);
        
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho,alto));
        addKeyListener(teclado);
        setFocusable(true);
        requestFocus(); //escribimos directamente en la ventana, apunta a la ventana
    }
    
        
    public void dibujar(final GestorEstado ge){
        BufferStrategy buffer = getBufferStrategy();
        
        if(buffer == null){
            createBufferStrategy(3); // cuantas imagenes se almacenan en pantalla
            return;
        }
        
        Graphics2D g = (Graphics2D)buffer.getDrawGraphics(); //pintamos dentro de una imagen(en la memoria)
        
        //g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.setColor(Color.black);
        g.fillRect(0, 0, ancho, alto);
        //g.setStroke(new BasicStroke(3.0f));
        g.scale(GestorPrincipal.escalado, GestorPrincipal.escalado);
        //*Aqui se dibuja todo el juego*//
        ge.dibujar(g);
        
        //Esto le permite dibujar solo entre actualizaciones de pantalla
        Toolkit.getDefaultToolkit().sync(); //lo hace fluido
        g.dispose(); // destruye el objeto de la memoria 
        buffer.show();
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Teclado getTeclado() {
        return teclado;
    }

    public Mouse getMouse() {
        return mouse;
    }
}