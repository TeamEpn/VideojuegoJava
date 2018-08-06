package videojuego.mapas.ciudad;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import sprites.HojaSprites;
import videojuego.objetos.Objeto;
import interfaz.Sonido;
import videojuego.mapas.Mapa;
import videojuego.objetos.entidad.Jugador.Jugador;
import videojuego.objetos.recolectables.Moneda;
import videojuego.objetos.recolectables.Comida;

public class MapaCiudad extends Mapa {

    HojaSprites hoja = new HojaSprites("/imagenes/hojasObjetos/1.png", 32, 32, false);

    public MapaCiudad(final String nombre, final String ruta, final int ancho, final int alto, final Jugador jugador) {
        super(nombre, ruta, ancho, alto, jugador);
    }

    public MapaCiudad(final String nombre, final String ruta, final int ancho, final int alto, final Jugador jugador,
            int desfasex, int desfasey) {
        super(nombre, ruta, ancho, alto, jugador, desfasex, desfasey);

    }

    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(final int x, final int y, final Jugador jugador) {

        this.objetos = new ArrayList<>();
        this.objetos.addAll(monedas);
        this.objetos.addAll(comidas);

        //CASAS
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 8 + desfasey - y, 38, 125), "Casa 1 Izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 256 + desfasey - y, 38, 94), "Casa 2 Izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 441 + desfasey - y, 152, 159), "Casas 3 y 4 Izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(84 + desfasex - x, 10 + desfasey - y, 146, 279), "Casa 5.1 Izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(84 + desfasex - x, 289 + desfasey - y, 75, 59), "Casa 5.2 Izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(301 + desfasex - x, 8 + desfasey - y, 145, 280), "Casa 6.1 Centro", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(374 + desfasex - x, 287 + desfasey - y, 73, 63), "Casa 6.2 Centro", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(381 + desfasex - x, 440 + desfasey - y, 419, 160), "Casas 7, 8, 9, 10 Derecha", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(493 + desfasex - x, 8 + desfasey - y, 122, 158), "Casa 11 Derecha", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(493 + desfasex - x, 225 + desfasey - y, 122, 126), "Casa 12 Derecha", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(661 + desfasex - x, 0 + desfasey - y, 121, 42), "Iglesia Derecha", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(635 + desfasex - x, 225 + desfasey - y, 124, 128), "Casa 13 Derecha", Objeto.Tag.EDIFICIO));

        //ARBOLES, FLORES Y ESTATUAS
        objetos.add(new Objeto(new Rectangle(152 + desfasex - x, 470 + desfasey - y, 80, 130), "Arbol y muro Izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(300 + desfasex - x, 471 + desfasey - y, 81, 129), "Arbol y muro Derecha", Objeto.Tag.EDIFICIO));

        objetos.add(new Objeto(new Rectangle(661 + desfasex - x, 42 + desfasey - y, 25, 124), "Flores iglesia izquierda", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(755 + desfasex - x, 42 + desfasey - y, 29, 123), "Flores iglesia derecha", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 148 + desfasey - y, 37, 79), "Arbol y poste 1 izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(39 + desfasex - x, 303 + desfasey - y, 23, 77), "Poste 2 izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(159 + desfasex - x, 395 + desfasey - y, 22, 75), "Poste 3 izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(352 + desfasex - x, 395 + desfasey - y, 21, 75), "Poste 4 izquierda", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(472 + desfasex - x, 304 + desfasey - y, 21, 75), "Poste 5 derecha", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(616 + desfasex - x, 304 + desfasey - y, 21, 75), "Poste 6 derecha", Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(759 + desfasex - x, 304 + desfasey - y, 21, 75), "Poste 7 derecha", Objeto.Tag.EDIFICIO));

        //teleports
        objetos.add(new Objeto(new Rectangle(183 + desfasex - x, 255 + desfasey - y, 23, 40), "Casa INN", Objeto.Tag.TELEPORT));
        objetos.add(new Objeto(new Rectangle(234 + desfasex - x, 571 + desfasey - y, 65, 27), "Puerta Zelda", Objeto.Tag.TELEPORT));
        objetos.add(new Objeto(new Rectangle(252 + desfasex - x, 349 + desfasey - y, 27, 63), "Estatua izquierda", Objeto.Tag.TELEPORT));

    }

    @Override
    protected void iniciarObjetosRecolectables() {

        monedas = new ArrayList<>();
        Moneda moneda;
        for (int i = 0; i < 10; i++) {
            this.posX = random.nextInt(ANCHO_SPAWNEO);
            this.posY = random.nextInt(ALTO_SPAWNEO);
            moneda = new Moneda(this, "moneda" + i, posX, posY);
            monedas.add(moneda);
        }

        comidas = new ArrayList<>();
        comidas.add(new Comida(Comida.MANZANA_ROJA, "manzana1", this, -50, 0));
        comidas.add(new Comida(Comida.MANZANA_DORADA, "manzana2", this, -50, 50));
        comidas.add(new Comida(Comida.ZANAHORIA, "zanahoria1", this, -50, 100));
        comidas.add(new Comida(Comida.GALLETA, "galleta1", this, -50, 150));
        comidas.add(new Comida(Comida.ORBE_VERDE, "orbe_verde1", this, -50, 200));
        comidas.add(new Comida(Comida.ORBE_DORADO, "orbe_dorado1", this, -50, 250));

    }

    public void comprobarSIEstaContenido(Moneda moneda) {
        for (int i = 0; i < this.objetos.size(); i++) {
            if (objetos.get(i).getRectangle()[0].contains(moneda.getRectangle()[0])) {
                moneda.colisiona = true;
            }
        }
    }

    @Override
    public void musica() {
        Sonido.cambioMusica(Sonido.MUSICA_MAPA_RAFA);

    }

}
