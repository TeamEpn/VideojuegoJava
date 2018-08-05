package interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Ventana extends JFrame {
    
    public Ventana(final Lienzo lienzo){
        configurarVentana(lienzo);
    }

    public void configurarVentana(final Lienzo lienzo) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(lienzo, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
    
    public void configurarVentanaPantallaCompleta(final Lienzo lienzo,int ancho,int alto) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(lienzo, BorderLayout.CENTER);
        this.setSize(ancho, alto);
        this.pack();
        this.setVisible(true);
    }
}
