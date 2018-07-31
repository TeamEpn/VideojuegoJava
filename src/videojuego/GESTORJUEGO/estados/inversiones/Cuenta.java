package videojuego.GESTORJUEGO.estados.inversiones;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import videojuego.GESTORJUEGO.estados.EstadoInversion;

public class Cuenta {

    public int numero_cuenta;
    public double saldo;

    public Cuenta(double saldo_inicial) {
        this.saldo = saldo_inicial;
    }

    public void invertir(double monto, int dias) {

        if(!(dias == 1 || dias == 3 || dias == 7 || dias ==30)){
            System.out.println("Dias invalidos");
            return;
        }
        if (monto <= saldo) {
            final double interes;
            switch(dias){
                case 1:
                    interes = 1.1; break;
                case 3:
                    interes = 1.2; break;
                case 7:
                    interes = 1.3; break;
                case 30:
                    interes = 1.5; break;
                default:
                    interes = 0;
            }
        
        
            saldo -= monto;
            System.out.println("Saldo al inicio: " + saldo);
            Runnable hilo = new Runnable() {

                @Override
                public void run() {
                    try {

                        for (int dia = dias; dia >= 1; dia--) {
                            EstadoInversion.aviso = "Dias restantes: " + dia;
                            Thread.sleep(1000);
                        }
                        double interes_monto = monto * interes;
                        EstadoInversion.aviso = "Nuevo monto(30%): " + interes_monto;
                        Thread.sleep(3000);
                        saldo += interes_monto;
                        EstadoInversion.aviso = "Saldo al fin: " + saldo;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            };
            
            new Thread(hilo).start();

        }
        else
            System.out.println("No hay suficiente saldo para la inversion de " + dias + " dias");

    }
    
    
    public void agregarDinero(double dinero){
        this.saldo += dinero;
    }
    
    public boolean haySaldoSuficiente(double din){
        return saldo >= din;
    }
    
    public void retirarDinero(double dinero){
        if(dinero <= this.saldo)
            this.saldo -= dinero;
        
    }

}
