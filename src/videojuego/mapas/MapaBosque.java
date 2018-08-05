package videojuego.mapas;

import videojuego.mapas.ciudad.MapaCiudad;
import java.awt.Graphics;
import videojuego.objetos.entidad.Jugador.Jugador;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprites.HojaSprites;
import videojuego.objetos.recolectables.Moneda;
import videojuego.objetos.recolectables.Comida;
import videojuego.objetos.Objeto;
import interfaz.Sonido;

public class MapaBosque extends Mapa{
    
    HojaSprites hoja = new HojaSprites("/imagenes/hojasObjetos/1.png", 32, 32, false);
     
    public MapaBosque(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        super(nombre,ruta,ancho,alto,jugador);
    }

    public MapaBosque(String nombre, String ruta, int ancho, int alto, Jugador jugador, int desfasex, int desfasey) {
        super(nombre, ruta, ancho, alto, jugador, desfasex, desfasey);
    }
    
    
    
    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(final int x,final int y,final Jugador jugador) {

        objetos = new ArrayList<>();
        this.objetos.addAll(monedas);
        this.objetos.addAll(comidas);
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
        objetos.add(new Objeto(new Rectangle(273 + desfasex - x,301  + desfasey - y,27 ,27 ),"Fogata1",Objeto.Tag.ABSORCION_MANA));
        objetos.add(new Objeto(new Rectangle(163 + desfasex - x,197  + desfasey - y,32 ,28 ),"TresEnRaya",Objeto.Tag.TICTACTOE));
       
                
        //Edificios
        objetos.add(new Objeto(new Rectangle(230 + desfasex - x, 125 + desfasey - y, 116, 113),"TiendaCampaña1",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(740 + desfasex - x, 458 + desfasey - y, 40, 50),"teleport_ciudad",Objeto.Tag.TELEPORT));
                  
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
        Sonido.cambioMusica(Sonido.MUSICA_INICIO);
    }

}
