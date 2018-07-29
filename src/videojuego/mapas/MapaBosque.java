package videojuego.mapas;

import videojuego.entidad.Jugador.Jugador;
import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.Objeto;

public class MapaBosque extends Mapa{
    
    public MapaBosque(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        super(nombre,ruta,ancho,alto,jugador);
    }
    
    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(final int x,final int y,final int ancho_jugador,final int alto_jugador) {
        

        objetos = new ArrayList<>();

        //BOSQUE
        final int cuadrado_arbol = 100;
        objetos.add(new Objeto(new Rectangle(329 + desfasex - x, 273 + desfasey - y, cuadrado_arbol, cuadrado_arbol),"Arbol1",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(26 + desfasex - x, 354 + desfasey - y, 80, 100),"Arbol2",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(78 + desfasex - x, 381 + desfasey - y, cuadrado_arbol, cuadrado_arbol),"Arbol3",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(216 + desfasex - x, 426 + desfasey - y, 101, 100),"Arbol4",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(177 + desfasex - x, 487 + desfasey - y, 81, 94),"Arbol5",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(272 + desfasex - x, 503 + desfasey - y, 100, 94),"Arbol6",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(274 + desfasex - x, 3 + desfasey - y,100 ,73 ),"Arbol7",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(356 + desfasex - x,29  + desfasey - y,101 ,75 ),"Arbol8",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(469 + desfasex - x,1  + desfasey - y,93 ,45 ),"Arbol9",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(712 + desfasex - x,3  + desfasey - y,90 ,26 ),"Arbol10",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(600 + desfasex - x,26  + desfasey - y,88 ,96 ),"Arbol11",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(383 + desfasex - x,534  + desfasey - y,117 ,61 ),"Arbol12",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(444 + desfasex - x,173  + desfasey - y,84 ,94 ),"Arbol13",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(504 + desfasex - x,130  + desfasey - y,72 ,100 ),"Arbol14",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(465 + desfasex - x,303  + desfasey - y,93 ,97 ),"Arbol15",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(543 + desfasex - x,433  + desfasey - y,102 ,96 ),"Arbol16",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(631 + desfasex - x,309  + desfasey - y, 96, 97),"Arbol17",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(637 + desfasex - x,561  + desfasey - y,83 ,38 ),"Arbol18",Objeto.Tag.NATURALEZA));
        
        objetos.add(new Objeto(new Rectangle(52 + desfasex - x,574  + desfasey - y,31 ,23 ),"Tronco1",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(766 + desfasex - x,100  + desfasey - y,24 ,28 ),"Tronco2",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(224 + desfasex - x,245  + desfasey - y, 14, 59),"Valla1",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(302 + desfasex - x,235  + desfasey - y,50 ,40 ),"Valla2",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(273 + desfasex - x,301  + desfasey - y,27 ,27 ),"Fogata1",Objeto.Tag.TICTACTOE));
        
                
        //Edificios
        objetos.add(new Objeto(new Rectangle(230 + desfasex - x, 125 + desfasey - y, 116, 113),"TiendaCampaña1",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(271 + desfasex - x, 231 + desfasey - y, 31, 15),"teleport1",Objeto.Tag.TELEPORT));
        
           
    }

}
