/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.GESTORJUEGO.estados;

import interfaz.Boton;
import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GESTORJUEGO.GestorEstado;
import static videojuego.GESTORJUEGO.estados.EstadoInversion.puede_recolectar;
import videojuego.GestorPrincipal;
import videojuego.hud.HUDJugador;
import videojuego.objetos.entidad.Jugador.Jugador;

/**
 *
 * @author RAFAEL
 */
public class EstadoTienda implements EstadoJuego {

    Jugador jugador;
    HUDJugador hud;
    Boton boton_volver = new Boton(100, 500, "Volver", 100, 70);
    Boton boton_bala = new Boton(150, 250, "15 Balas por $200");
    Boton boton_vida = new Boton(350, 250, "30 de Vida por $400");
    Boton boton_mana = new Boton(550, 250, "30 de Mana por $400");
    boolean dineroSuficiente = true, vidaAlMaximo, manaAlMaximo;

    public EstadoTienda(Jugador jugador) {
        this.jugador = jugador;
        puede_recolectar = false;
        hud = new HUDJugador(jugador);
        vidaAlMaximo = jugador.getVida_actual() == jugador.getVida_maxima();
        manaAlMaximo = jugador.getMana_actual() == jugador.getMana_maximo();
    }

    @Override
    public void actualizar(Lienzo lienzo) {

        if (lienzo.getMouse().isClick_izquierdo()) {

            //VERIFICACION DE BOTONES
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();

            if (this.boton_volver.esClickeado(mx, my)) {
                GestorEstado.cambiarEstado(0);
            } else if (this.boton_bala.esClickeado(mx, my)) {
                if (jugador.getCuenta().haySaldoSuficiente(200)) {
                    dineroSuficiente = true;
                    jugador.getCuenta().saldo = jugador.getCuenta().saldo - 200;
                    jugador.getPistola().balas_totales = jugador.getPistola().balas_totales + 15;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EstadoTienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    dineroSuficiente = false;
                }

            } else if (this.boton_vida.esClickeado(mx, my)) {
                if (jugador.getCuenta().haySaldoSuficiente(400)) {
                    if (!vidaAlMaximo) {
                        jugador.getCuenta().saldo = jugador.getCuenta().saldo - 400;
                        jugador.agregarVida(30);
                    }
                } else {
                    dineroSuficiente = false;
                }
            } else if (this.boton_mana.esClickeado(mx, my)) {
                if (jugador.getCuenta().haySaldoSuficiente(400)) {
                    if (!manaAlMaximo) {
                        jugador.getCuenta().saldo = jugador.getCuenta().saldo - 400;
                        jugador.regenerarMana(30);
                    }
                } else {
                    dineroSuficiente = false;
                }
            }
        }
    }

    @Override
    public void dibujar(Graphics2D g) {

        hud.dibujar(g);

        g.drawImage(new ImageIcon(ClassLoader.class.getResource("/imagenes/tienda.png")).getImage(), 0, 0, null);

        g.setColor(Color.WHITE);
        g.drawString("DINERO: " + jugador.getCuenta().saldo, 100, 450);

        if (!dineroSuficiente) {
            g.setColor(Color.WHITE);
            g.drawString("No tiene suficiente dinero", 100, 420);
        }
        if (vidaAlMaximo) {
            g.setColor(Color.WHITE);
            g.drawString("Tiene la vida al maximo, no puede comprar pocion curativa", 100, 400);
        }
        if (manaAlMaximo) {
            g.setColor(Color.WHITE);
            g.drawString("Tiene el mana al maximo, no puede comprar pocion de mana", 100, 370);
        }

        this.boton_volver.dibujarBoton(g);
        this.boton_bala.dibujarBotonImagen(g, new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/bala.png")).getImage());
        this.boton_vida.dibujarBotonImagen(g, new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/pocionCurativa.png")).getImage());
        this.boton_mana.dibujarBotonImagen(g, new ImageIcon(ClassLoader.class.getResource("/imagenes/hojasObjetos/pocionMana.png")).getImage());
    }

    public void dibujarSinDinero(Graphics2D g) {

    }
}