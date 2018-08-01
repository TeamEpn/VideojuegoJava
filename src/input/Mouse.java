package input;
import interfaz.Lienzo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class Mouse extends MouseAdapter {

    private boolean click_izquierdo;
    private boolean click_derecho;
    private int posx,posy;

    public Mouse(final Lienzo lienzo) {
        click_izquierdo = false;
        click_derecho = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) 
            click_izquierdo = true;
        else if (SwingUtilities.isRightMouseButton(e)) 
            click_derecho = true;
        
        this.posx = e.getX();
        this.posy = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            click_izquierdo = false;
        else if (SwingUtilities.isRightMouseButton(e)) 
            click_derecho = false;
        this.posx = e.getX();
        this.posy = e.getY();
    }

    public boolean isClick_izquierdo() {
        return click_izquierdo;
    }

    public boolean isClick_derecho() {
        return click_derecho;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }
}
