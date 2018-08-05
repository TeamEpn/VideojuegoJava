package videojuego.hud;

import videojuego.objetos.entidad.Jugador.Jugador;
import java.awt.Color;
import java.awt.Graphics;
import videojuego.GESTORJUEGO.estados.EstadoInversion;
import javax.swing.ImageIcon;
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

        //NIVEL y karma
        g.setColor(Color.white);
        g.drawString("Nivel: " + jugador.getNivel(), 800, interfaz_alto + 20);
        g.setColor(Color.blue);
        g.drawString("KarmaB: " + Jugador.karma_bueno, 980, interfaz_alto + 20);
        g.setColor(Color.red);
        g.drawString("KarmaM: " + Jugador.karma_malo, 1080, interfaz_alto + 20);
        
        //CONDICION FISICA
        
        if(!jugador.esta_cansado){
            g.setColor(Color.green);
            g.drawString("ESTADO: Vitalizado", 800, interfaz_alto + 40);
        }
        else{
            g.setColor(Color.orange);
            g.drawString("ESTADO: Cansado, se recuperará en " + jugador.getContador_cansado() + " segundos.", 800, interfaz_alto + 40);
        
        }
        
        g.setColor(Color.LIGHT_GRAY);
        if(EstadoInversion.esta_invirtiendo || EstadoInversion.puede_recolectar)
            g.drawString(EstadoInversion.aviso, 800, interfaz_alto + 60);
        else
            g.drawString("Ninguna inversión pendiente en el banco", 800, interfaz_alto + 60);
            
        //-VIDA

        g.setColor(Color.MAGENTA);
        g.drawRect(120, interfaz_alto + 12, porc, 10);
        g.setColor(Color.red);
        g.fillRect(120, interfaz_alto + 12, vid, 10);
        g.setColor(Color.white);
        g.drawString("VIDA: ", 60, interfaz_alto + 20);
        g.drawString(jugador.getVida_actual() + "/" + jugador.getVida_maxima(), 140, interfaz_alto + 21);

        
        //-MANA        
        g.setColor(Color.blue);
        g.drawRect(120, interfaz_alto + 32, porc + 1, 10);
        g.setColor(Color.blue);
        g.fillRect(120, interfaz_alto + 33, man, 9);
        g.setColor(Color.white);
        g.drawString("MANA: ", 60, interfaz_alto + 40);
        g.drawString(jugador.getMana_actual() + "/" + jugador.getMana_maximo(), 140, interfaz_alto + 41);

        //-Resistencia
        
        g.setColor(new Color(0x294d84));
        g.drawRect(120, interfaz_alto + 52, porc + 1, 10);
        g.setColor(new Color(0x294d84));
        g.fillRect(120, interfaz_alto + 53, res, 9);
        g.setColor(Color.white);
        g.drawString("RES: ", 60, interfaz_alto + 60);
        g.drawString(jugador.getResistencia_actual()+"/"+jugador.getResistencia_maxima(), 140, interfaz_alto + 63);

        //-EXP
        g.setColor(Color.white);
        g.drawRect(570, interfaz_alto + 32, porc, 10);
        g.setColor(Color.green);
        g.fillRect(570, interfaz_alto + 33, exp, 9);
        g.setColor(Color.white);
        g.drawString("Exp: ", 500, interfaz_alto + 40);
        g.drawString(jugador.getExp_actual() + "/" + jugador.getExp_maxima(), 600, interfaz_alto + 42);

        //BALAS
        g.setColor(Color.white);
        g.drawString("BALAS: ", 500, interfaz_alto + 20);
        int espaciadoX = 0;
        
        for (int i = 0; i < jugador.getPistola().cantidad_balas; i++) {
            g.drawImage(Bala.bala_arriba, 550 + espaciadoX, interfaz_alto, null);
            espaciadoX = espaciadoX + 10;
        }
        
        g.drawString(jugador.getPistola().cantidad_balas + "/" + jugador.getPistola().balas_totales, 580 + espaciadoX, interfaz_alto + 20);

        //DINERO
        g.drawString("DINERO: ", 500, interfaz_alto + 60);
        g.drawString("$" + jugador.getCuenta().saldo + "", 580, interfaz_alto + 60);
        
        //DINERO
        g.drawString("ZOMBIES: " + Enemigo.ZOMBIES_MUERTOS, 0, 100);
    }

}
