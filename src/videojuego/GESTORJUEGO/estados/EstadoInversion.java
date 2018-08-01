package videojuego.GESTORJUEGO.estados;

import interfaz.Boton;
import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GestorPrincipal;
import videojuego.objetos.entidad.Jugador.Jugador;

public class EstadoInversion implements EstadoJuego {

    public static String aviso = "";
    double monto;
    Jugador jugador;
    Boton boton_volver = new Boton(100, 100, "Volver", 100, 70);
    Boton boton_invertir = new Boton(100, 230, "Invertir");
    public static boolean puede_recolectar, esta_invirtiendo;
    Boton boton_recolectar = new Boton(100, 300, "Recolectar", 150, 150);

    private static double interes;

    public EstadoInversion(Jugador jugador) {
        this.jugador = jugador;
        puede_recolectar = false;
    }

    @Override
    public void actualizar(Lienzo lienzo) {
        if (lienzo.getMouse().isClick_izquierdo()) {

            //VERIFICACION DE BOTONES
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();
            if (this.boton_invertir.esClickeado(mx, my)) {

                try {
                    if (!esta_invirtiendo) {
                        Thread.sleep(200);
                        monto = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la cantidad a invertir: "));

                        if (jugador.getCuenta().haySaldoSuficiente(monto)) {

                            jugador.getCuenta().invertir(monto, 7);
                            esta_invirtiendo = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Insuficiente saldo, su saldo actual es de " + jugador.getCuenta().saldo + " no puede usar tanto dinero");
                        }
                    } else {
                        Thread.sleep(200);
                        JOptionPane.showMessageDialog(null, "Usted ya tiene una inversion en progreso");
                    }

                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(EstadoInversion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (this.boton_volver.esClickeado(mx, my)) {
                GestorEstado.cambiarEstado(0);
            } else if (this.boton_recolectar.esClickeado(mx, my) && puede_recolectar) {

                jugador.getCuenta().agregarDinero(interes);
                interes = 0;
                puede_recolectar = false;
                aviso = "Dinero recolectado, gracias por su inversión";

            }

        }
    }

    public static double getInteres() {
        return interes;
    }

    public static void setInteres(double interes) {
        EstadoInversion.interes = interes;
    }

    @Override
    public void dibujar(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GestorPrincipal.ANCHO, GestorPrincipal.ALTO);

        this.boton_volver.dibujarBoton(g);
        this.boton_invertir.dibujarBoton(g);

        if (puede_recolectar) {
            this.boton_recolectar.dibujarBoton(g);
        }

        g.setColor(Color.white);
        g.drawString(aviso, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY);
    }

}
