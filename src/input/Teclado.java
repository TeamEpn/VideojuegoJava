
package input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
    
    private final static int N_TECLAS = 256;
    private final boolean[] teclas = new boolean[N_TECLAS];
    
    public boolean arriba,abajo,izquierda,derecha;
    
    public boolean salir;
    public boolean correr;
    
    public boolean poder_tiempo;
    
    public void actualizar(){
        //actualiza si se presiono la tecla o no, con true y false
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
        salir = teclas[KeyEvent.VK_ESCAPE];
        correr = teclas[KeyEvent.VK_SHIFT];
        
        
        poder_tiempo = teclas[KeyEvent.VK_R];
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        teclas[ke.getKeyCode()] = true; 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        teclas[ke.getKeyCode()] = false;
    }
    
}
