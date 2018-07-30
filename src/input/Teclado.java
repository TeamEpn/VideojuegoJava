
package input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
    
    private final static int N_TECLAS = 256;
    public static boolean[] teclas = new boolean[N_TECLAS];
    
    public boolean arriba,abajo,izquierda,derecha;
    
    public boolean salir;
    public boolean correr;
    
    public boolean poder_tiempo;
    public boolean disparar_arma;
    public boolean recargar_arma;
    
    public void actualizar(){
        //actualiza si se presiono la tecla o no, con true y false
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
        salir = teclas[KeyEvent.VK_ESCAPE];
        correr = teclas[KeyEvent.VK_SHIFT];
        
        
        poder_tiempo = teclas[KeyEvent.VK_T];
        disparar_arma = teclas[KeyEvent.VK_E];
        recargar_arma = teclas[KeyEvent.VK_R];
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() != KeyEvent.VK_E)
            teclas[ke.getKeyCode()] = true; 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyCode() != KeyEvent.VK_E)
            teclas[ke.getKeyCode()] = false;
        else
            teclas[ke.getKeyCode()] = true; 
    }
    
}
