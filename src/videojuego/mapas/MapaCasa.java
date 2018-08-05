package videojuego.mapas;

import interfaz.Sonido;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Jugador.Jugador;
import videojuego.objetos.recolectables.Comida;
import videojuego.objetos.recolectables.Moneda;

public class MapaCasa extends Mapa{
    
    public MapaCasa(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        super(nombre,ruta,ancho,alto,jugador);
    }

    public MapaCasa(String nombre, String ruta, int ancho, int alto, Jugador jugador, int desfasex, int desfasey) {
        super(nombre, ruta, ancho, alto, jugador, desfasex, desfasey);
    }
    
    
    
    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(final int x,final int y,final Jugador jugador) {
        

        objetos = new ArrayList<>();
        this.objetos.addAll(monedas);
        this.objetos.addAll(comidas);
        //CASA
        objetos.add(new Objeto(new Rectangle(282 + desfasex - x, 46 + desfasey - y, 283, 185),"Casa 1 centro",Objeto.Tag.EDIFICIO));
        
        //teleport
        objetos.add(new Objeto(new Rectangle(230 + desfasex - x, + desfasey- y, 60, 20),"teleport_ciudad",Objeto.Tag.TELEPORT));
                
                
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
    protected void iniciarObjetosRecolectables() {
        monedas = new ArrayList<>();
        for(int i=0;i<10;i++){
            monedas.add(new Moneda(this,"moneda"+i,random.nextInt(ANCHO_SPAWNEO),random.nextInt(ALTO_SPAWNEO)));
        }
        
        comidas = new ArrayList<>();
        comidas.add(new Comida(Comida.MANZANA_ROJA,"manzana1",this,-50,0));
        comidas.add(new Comida(Comida.MANZANA_DORADA,"manzana2",this,-50,50));
        comidas.add(new Comida(Comida.ZANAHORIA,"zanahoria1",this,-50,100));
        comidas.add(new Comida(Comida.GALLETA,"galleta1",this,-50,150));
        comidas.add(new Comida(Comida.ORBE_VERDE,"orbe_verde1",this,-50,200));
        comidas.add(new Comida(Comida.ORBE_DORADO,"orbe_dorado1",this,-50,250));
        
    }

    @Override
    public void musica() {
        Sonido.cambioMusica(Sonido.MUSICA_MAPA_CARLOS);
    }
}
