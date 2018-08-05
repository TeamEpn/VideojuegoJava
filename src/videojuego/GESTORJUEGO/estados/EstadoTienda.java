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
import videojuego.hud.Decision;
import videojuego.hud.Dialogo;
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
    Boton boton_bala = new Boton(150, 290, "15 Balas por $200", 50, 16);
    Boton boton_vida = new Boton(350, 250, "30 de Vida por $400", 32, 58);
    Boton boton_mana = new Boton(550, 250, "30 de Mana por $400", 32, 58);
    boolean dineroSuficiente = true, vidaAlMaximo, manaAlMaximo;
    public static boolean primeraVez = true;
    Dialogo dialogo;
    Decision decision;

    public EstadoTienda(Jugador jugador) {
        this.jugador = jugador;
        puede_recolectar = false;
        hud = new HUDJugador(jugador);
        vidaAlMaximo = jugador.getVida_actual() == jugador.getVida_maxima();
        manaAlMaximo = jugador.getMana_actual() == jugador.getMana_maximo();
        dialogo = new Dialogo();
        decision = new Decision("La pistola poderosa", "La pistola chidori");
    }

    @Override
    public void actualizar(Lienzo lienzo) {

        if (primeraVez) {
            if (Dialogo.activado) {
                this.dialogo.actualizar(lienzo);
            } else {
                this.decision.actualizar(lienzo);
                if (lienzo.getMouse().isClick_izquierdo()) {
                    //VERIFICACION DE BOTONES
                    int mx = lienzo.getMouse().getPosx();
                    int my = lienzo.getMouse().getPosy();
                    if(decision.opcion1.esClickeado(mx, my)){
                        jugador.setDescicionPistola("potente");
                    }else if(decision.opcion2.esClickeado(mx, my)){
                        jugador.setDescicionPistola("rapida");
                    }
                    primeraVez = false;
                }

            }
        } else {
            vidaAlMaximo = jugador.getVida_actual() == jugador.getVida_maxima();
            manaAlMaximo = jugador.getMana_actual() == jugador.getMana_maximo();

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
                        dineroSuficiente = true;
                        if (!vidaAlMaximo) {
                            jugador.getCuenta().saldo = jugador.getCuenta().saldo - 400;
                            jugador.regenerarVida(30);
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(EstadoTienda.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        dineroSuficiente = false;
                    }
                } else if (this.boton_mana.esClickeado(mx, my)) {
                    if (jugador.getCuenta().haySaldoSuficiente(400)) {
                        dineroSuficiente = true;
                        if (!manaAlMaximo) {
                            jugador.getCuenta().saldo = jugador.getCuenta().saldo - 400;
                            jugador.regenerarMana(30);
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(EstadoTienda.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        dineroSuficiente = false;
                    }
                }
            }
        }

    }

    @Override
    public void dibujar(Graphics2D g) {

        vidaAlMaximo = jugador.getVida_actual() == jugador.getVida_maxima();
        manaAlMaximo = jugador.getMana_actual() == jugador.getMana_maximo();

        g.drawImage(new ImageIcon(ClassLoader.class.getResource("/imagenes/tienda.png")).getImage(), 0, 0, null);

        if (primeraVez) {
            if (Dialogo.activado) {
                dialogo.setDialogo(Dialogo.dialogo_vendedor);
                dialogo.dibujar(g);
            } else {
                decision.dibujar(g);

            }

        } else {
            hud.dibujar(g);

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

    }
}
