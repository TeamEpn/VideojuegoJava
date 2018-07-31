/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.GESTORJUEGO.estados;

import interfaz.Boton;
import interfaz.Lienzo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import videojuego.GESTORJUEGO.EstadoJuego;
import videojuego.GestorPrincipal;
import videojuego.objetos.entidad.Jugador.Jugador;

public class EstadoInversion implements EstadoJuego{

    public static String aviso = "";
    double monto;
    Jugador jugador;

    public EstadoInversion(Jugador jugador) {
        this.jugador = jugador;
    }
    
    
    
    @Override
    public void actualizar(Lienzo lienzo) {
        if (lienzo.getMouse().isClick_izquierdo()) {

            //VERIFICACION DE BOTONES
            int mx = lienzo.getMouse().getPosx();
            int my = lienzo.getMouse().getPosy();
            if(this.boton_invertir.esClickeado(mx,my)){
                try {
                    Thread.sleep(200);
                    monto = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la cantidad a invertir: "));
                    
                    if(jugador.getCuenta().haySaldoSuficiente(monto)){
                        aviso = "Monto = " + monto + "\nSaldo inicial: " + jugador.getCuenta().saldo;
                    
                        jugador.getCuenta().invertir(monto, 7);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Insuficiente saldo, su saldo actual es de " + jugador.getCuenta().saldo + " no puede usar tanto dinero");
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(EstadoInversion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    
    Boton boton_invertir = new Boton(100,100,"Invertir");

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GestorPrincipal.ANCHO, GestorPrincipal.ALTO);
        
        this.boton_invertir.dibujarBoton(g);
        g.drawString(aviso, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY);
    }
    
}
