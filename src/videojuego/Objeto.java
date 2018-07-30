
package videojuego;

import java.awt.Rectangle;

public class Objeto {
    
    private final Rectangle[] rectangle;
    private final String id, tag;
    
    public class Tag{
        public static final String JUGADOR = "jugador";
        public static final String ENEMIGO = "enemigo";
        public static final String ALIADO = "aliado";
        public static final String EDIFICIO = "edificio";
        public static final String NATURALEZA = "naturaleza";
        public static final String TELEPORT = "teleport";
        public static final String ABSORCION_MANA = "absorcion_mana";
        public static final String TICTACTOE = "tictactoe";
        public static final String SUBIDA_EXP = "subida_exp";
    }

    
    public Objeto(final Rectangle[] rectangle,final String id,final String tag) {
        int lados = 4;
        this.rectangle = new Rectangle[lados];
        for(int i=0;i<lados;i++){
            this.rectangle[i] = rectangle[i];
        }
        
        this.id = id;
        this.tag = tag;
    }
    
    public Objeto(final Rectangle rectangle,final String id,final String tag) {
        this.rectangle = new Rectangle[1];
        this.rectangle[0] = rectangle;
        this.id = id;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public Rectangle[] getRectangle() {
        return rectangle;
    }

}
