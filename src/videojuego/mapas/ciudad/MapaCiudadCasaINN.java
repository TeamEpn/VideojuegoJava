
package videojuego.mapas.ciudad;

import interfaz.Sonido;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.mapas.Mapa;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Jugador.Jugador;
import videojuego.objetos.recolectables.Comida;
import videojuego.objetos.recolectables.Moneda;

public class MapaCiudadCasaINN extends Mapa{

    public MapaCiudadCasaINN(String nombre, String ruta, int ancho, int alto, Jugador jugador, int desfasex, int desfasey) {
        super(nombre, ruta, ancho, alto, jugador, desfasex, desfasey);
    }

    @Override
    protected void generarObjetosColisionables(int x, int y, Jugador jugador) {
        objetos = new ArrayList<>();
        this.objetos.addAll(monedas);
        this.objetos.addAll(comidas);
        //CASAS
        objetos.add(new Objeto(new Rectangle(93 + desfasex - x, 523 + desfasey - y, 36, 29), "Inversion Secreta", Objeto.Tag.INVERSION));
        objetos.add(new Objeto(new Rectangle(188 + desfasex - x, 478 + desfasey - y, 97, 20), "Inversion Secreta", Objeto.Tag.TIENDA));
        objetos.add(new Objeto(new Rectangle(192 + desfasex - x, 660 + desfasey - y, 30, 20), "Puerta a ciudad", Objeto.Tag.TELEPORT));
        
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
        Sonido.cambioMusica(Sonido.MUSICA_MENU);
    }
    
}
