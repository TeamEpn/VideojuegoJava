package videojuego.hud;

import herramientas.CargadorRecursos;
import videojuego.objetos.entidad.Jugador.Jugador;
import java.awt.Color;
import java.awt.Graphics;
import videojuego.GESTORJUEGO.estados.EstadoInversion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import sprites.HojaSprites;
import videojuego.GestorPrincipal;
import videojuego.objetos.armas.Bala;
import videojuego.objetos.entidad.Enemigo.Enemigo;

public class HUDJugador {

    private final Jugador jugador;

    public HUDJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void dibujar(Graphics g) {
        int interfaz_alto = GestorPrincipal.ALTO - 70;

        ImageIcon img = new ImageIcon(ClassLoader.class.getResource("/imagenes/hud.png"));
        g.drawImage(img.getImage(),0, interfaz_alto, null);

        int porc = 130;
        int res = (jugador.getResistencia_actual()*porc)/jugador.getResistencia_maxima();
        int vid = (jugador.getVida_actual() * porc) / jugador.getVida_maxima();
        int man = (jugador.getMana_actual() * porc) / jugador.getMana_maximo();
        int exp = (jugador.getExp_actual() * porc) / jugador.getExp_maxima();

        //NIVEL
        g.setColor(Color.white);
        g.drawString("Nivel: " + jugador.getNivel(), 500, interfaz_alto + 20);
        
        //CONDICION FISICA
        
        if(!jugador.esta_cansado){
            g.setColor(Color.green);
            g.drawString("ESTADO: Vitalizado", 500, interfaz_alto + 40);
        }
        else{
            g.setColor(Color.orange);
            g.drawString("ESTADO: Cansado, se recuperará en " + jugador.getContador_cansado() + " segundos.", 500, interfaz_alto + 40);
        
        }
        
        g.setColor(Color.LIGHT_GRAY);
        if(EstadoInversion.esta_invirtiendo || EstadoInversion.puede_recolectar)
            g.drawString(EstadoInversion.aviso, 500, interfaz_alto + 60);
        else
            g.drawString("Ninguna inversión pendiente en el banco", 500, interfaz_alto + 60);
            
        //-VIDA

        g.setColor(Color.MAGENTA);
        g.drawRect(50, interfaz_alto + 12, porc, 10);
        g.setColor(Color.red);
        g.fillRect(50, interfaz_alto + 12, vid, 10);
        g.setColor(Color.white);
        g.drawString("VIDA: ", 10, interfaz_alto + 20);
        g.drawString(jugador.getVida_actual() + "/" + jugador.getVida_maxima(), 90, interfaz_alto + 20);

        
        //-MANA        
        g.setColor(Color.blue);
        g.drawRect(50 - 1, interfaz_alto + 32, porc + 1, 10);
        g.setColor(Color.blue);
        g.fillRect(50, interfaz_alto + 33, man, 9);
        g.setColor(Color.white);
        g.drawString("MANA: ", 10, interfaz_alto + 40);
        g.drawString(jugador.getMana_actual() + "/" + jugador.getMana_maximo(), 90, interfaz_alto + 40);

        //-Resistencia
        
        g.setColor(Color.CYAN);
        g.drawRect(50 - 1, interfaz_alto + 52, porc + 1, 10);
        g.setColor(Color.CYAN);
        g.fillRect(50, interfaz_alto + 53, res, 9);
        g.setColor(Color.white);
        g.drawString("RES: ", 10, interfaz_alto + 60);
        g.drawString(jugador.getResistencia_actual()+"/"+jugador.getResistencia_maxima(), 90, interfaz_alto + 62);

        //-EXP
        g.setColor(Color.white);
        g.drawRect(250, interfaz_alto + 32, porc, 10);
        g.setColor(Color.green);
        g.fillRect(250, interfaz_alto + 33, exp, 9);
        g.setColor(Color.white);
        g.drawString("Exp: ", 200, interfaz_alto + 40);
        g.drawString(jugador.getExp_actual() + "/" + jugador.getExp_maxima(), 200 + 90, interfaz_alto + 42);

        //BALAS
        g.setColor(Color.white);
        g.drawString("BALAS: ", 200, interfaz_alto + 20);
        int espaciadoX = 0;

        for (int i = 0; i < jugador.getPistola().cantidad_balas; i++) {
            g.drawImage(Bala.bala_arriba, 250 + espaciadoX, interfaz_alto, null);
            espaciadoX = espaciadoX + 10;
        }

        //DINERO
        g.drawString("DINERO: ", 200, interfaz_alto + 60);
        g.drawString("$" + jugador.getCuenta().saldo + "", 260, interfaz_alto + 60);
        
        //DINERO
        g.drawString("ZOMBIES: " + Enemigo.ZOMBIES_MUERTOS, 0, 100);
    }

}
