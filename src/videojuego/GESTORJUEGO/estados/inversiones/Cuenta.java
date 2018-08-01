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
            final String porc;
            final double interes;
            switch(dias){
                case 1:
                    interes = 1.1; porc = "10%"; break;
                    
                case 3:
                    interes = 1.2; porc = "20%"; break;
                case 7:
                    interes = 1.3; porc = "30%"; break;
                case 30:
                    interes = 1.5; porc = "50%"; break;
                default:
                    interes = 0; porc = "0%";
            }
        
        
            saldo -= monto;
            Runnable hilo = new Runnable() {

                @Override
                public void run() {
                    try {

                        for (int dia = dias; dia >= 1; dia--) {
                            EstadoInversion.aviso = "Inversion de " + monto + " en progreso, dias restantes: " + dia;
                            Thread.sleep(1000);
                        }
                        double interes_monto = monto * interes;
                        EstadoInversion.aviso = "Dinero en el banco por retirar: " + interes_monto + ", Beneficio del: " + porc;
                        EstadoInversion.setInteres(interes_monto);
                        EstadoInversion.puede_recolectar = true;
                        EstadoInversion.esta_invirtiendo = false;
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
