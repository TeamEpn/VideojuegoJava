package videojuego.mapas;

import interfaz.Sonido;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Jugador.Jugador;

public class MapaCasa extends Mapa{
    
    public MapaCasa(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        super(nombre,ruta,ancho,alto,jugador);
    }
    
    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(Graphics g,final int x,final int y,final Jugador jugador) {
        

        objetos = new ArrayList<>();

        //CASA
        
        objetos.add(new Objeto(new Rectangle(282 + desfasex - x, 46 + desfasey - y, 283, 185),"Casa 1 centro",Objeto.Tag.EDIFICIO));
        
        
        //teleport
        objetos.add(new Objeto(new Rectangle(760 + desfasex - x, 277 + desfasey - y, 30, 60),"Teleport ciudad",Objeto.Tag.TELEPORT_CIUDAD));
                
                
        //Arboles, rocas y acantilados
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 182 + desfasey - y, 50, 92),"Arbol 1 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(46 + desfasex - x, 232 + desfasey - y, 50, 92),"Arbol 2 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 0 + desfasey - y, 95, 232),"Acantilado1 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(95 + desfasex - x, 0 + desfasey - y, 96, 184),"Acantilado2 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(189+ desfasex - x, 2 + desfasey - y, 50, 92),"Arbol 1 arriba izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(237 + desfasex - x, 235 + desfasey - y, 45, 40),"Roca 1 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(707 + desfasex - x, 235 + desfasey - y, 45, 40),"Roca 2 derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(565 + desfasex - x, 48 + desfasey - y, 50, 92),"Arbol 2 derecha",Objeto.Tag.ABSORCION_MANA));
        objetos.add(new Objeto(new Rectangle(612 + desfasex - x, 94 + desfasey - y, 50, 92),"Arbol 1 derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(93 + desfasex - x, 463 + desfasey - y, 50, 92),"Arbol 1 abajo izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 407 + desfasey - y, 191, 99),"Acantilado 1 izquierda centro",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(283 + desfasex - x, 411 + desfasey - y, 518, 99),"Acantilado 2 centro derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(284 + desfasex - x, 508 + desfasey - y, 44, 46),"Letrero centro",Objeto.Tag.SUBIDA_EXP));
        objetos.add(new Objeto(new Rectangle(331 + desfasex - x, 557 + desfasey - y, 45, 40),"Roca 3 abajo",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(470 + desfasex - x, 463 + desfasey - y, 50, 92),"Arbol 3 abajo derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(564 + desfasex - x, 509 + desfasey - y, 50, 92),"Arbol 2 abajo derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(611 + desfasex - x, 463 + desfasey - y, 50, 92),"Arbol 1 abajo derecha",Objeto.Tag.NATURALEZA));
        
           
    }

    @Override
    public void musica() {
        Sonido.cambioMusica(Sonido.musica_mapa_carlos);
    }
}
