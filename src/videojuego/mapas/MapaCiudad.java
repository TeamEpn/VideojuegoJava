/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.mapas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import sprites.HojaSprites;
import videojuego.GestorPrincipal;
import videojuego.Objeto;
import videojuego.Sonido;
import videojuego.entidad.Jugador.Jugador;
import videojuego.entidad.Jugador.ObjetoCurativo;

/**
 *
 * @author RAFAEL
 */
public class MapaCiudad extends Mapa {

    HojaSprites hoja = new HojaSprites("/imagenes/hojasObjetos/1.png", 32, 32, false);
    ObjetoCurativo manzana1 = new ObjetoCurativo("manzana_roja", this, "manzana1");
    ObjetoCurativo manzana2 = new ObjetoCurativo("manzana_dorada", this, "manzana2");
    ObjetoCurativo zanahoria1 = new ObjetoCurativo("zanahoria", this, "zanahoria1");
    ObjetoCurativo galleta1 = new ObjetoCurativo("galleta", this, "galleta1");
    Object[] col_dir = null;
    Objeto colision = null;
    boolean colisionCurativa = false;

    public MapaCiudad(final String nombre, final String ruta, final int ancho, final int alto, final Jugador jugador) {
        super(nombre, ruta, ancho, alto, jugador);
    }

    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(Graphics g, final int x, final int y, final Jugador jugador) {

        objetos = new ArrayList<>();

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
        objetos.add(new Objeto(new Rectangle(152 + desfasex - x, 470 + desfasey - y, 80, 130), "Arbol y muro Izquierda", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(300 + desfasex - x, 471 + desfasey - y, 81, 129), "Arbol y muro Derecha", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(661 + desfasex - x, 42 + desfasey - y, 25, 124), "Flores iglesia izquierda", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(755 + desfasex - x, 42 + desfasey - y, 29, 123), "Flores iglesia derecha", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 148 + desfasey - y, 37, 79), "Arbol y poste 1 izquierda", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(39 + desfasex - x, 303 + desfasey - y, 23, 77), "Poste 2 izquierda", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(159 + desfasex - x, 395 + desfasey - y, 22, 75), "Poste 3 izquierda", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(252 + desfasex - x, 349 + desfasey - y, 27, 63), "Estatua izquierda", Objeto.Tag.TELEPORT_BOSQUE));
        objetos.add(new Objeto(new Rectangle(352 + desfasex - x, 395 + desfasey - y, 21, 75), "Poste 4 izquierda", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(472 + desfasex - x, 304 + desfasey - y, 21, 75), "Poste 5 derecha", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(616 + desfasex - x, 304 + desfasey - y, 21, 75), "Poste 6 derecha", Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(759 + desfasex - x, 304 + desfasey - y, 21, 75), "Poste 7 derecha", Objeto.Tag.NATURALEZA));

        //OBJETOS CURATIVOS
//        ImageIcon manzana = new ImageIcon(hoja.obtenerSprite(0, 0).obtenerImagen());
        manzana1.dibujar(g, 250, 306, desfasex, desfasey, jugador);
        manzana2.dibujar(g, 253, 72, desfasex, desfasey, jugador);
        zanahoria1.dibujar(g, 637, 181, desfasex, desfasey, jugador);
        galleta1.dibujar(g, 677, 391, desfasex, desfasey, jugador);

        comprobarColision(manzana1);
        comprobarColision(manzana2);
        comprobarColision(zanahoria1);
        comprobarColision(galleta1);
//

//
//        if (colisionCurativa == false) {
//            g.drawImage(manzana.getImage(), 250 + desfasex - x, 306 + desfasey - y, 32, 32, null);
//            objetos.add(new Objeto(new Rectangle(250 + desfasex - x, 306 + desfasey - y, 33, 33), "Manzana 1", Objeto.Tag.AGREGAR_VIDA));
//
//            g.drawImage(manzana.getImage(), 253 + desfasex - x, 72 + desfasey - y, null);
//            objetos.add(new Objeto(new Rectangle(253 + desfasex - x, 72 + desfasey - y, 32, 32), "Manzana 2", Objeto.Tag.AGREGAR_VIDA));
//
//            g.drawImage(manzana.getImage(), 637 + desfasex - x, 181 + desfasey - y, null);
//            objetos.add(new Objeto(new Rectangle(637 + desfasex - x, 181 + desfasey - y, 32, 32), "Manzana 3", Objeto.Tag.AGREGAR_VIDA));
//
//            g.drawImage(manzana.getImage(), 677 + desfasex - x, 391 + desfasey - y, null);
//            objetos.add(new Objeto(new Rectangle(677 + desfasex - x, 391 + desfasey - y, 32, 32), "Manzana 4", Objeto.Tag.AGREGAR_VIDA));
//        }
    }

    public void comprobarColision(ObjetoCurativo objeto) {
        for (int i = 0; i < this.objetos.size(); i++) {
            col_dir = jugador.verificarColision(this.objetos.get(i));
            if (col_dir[1] != "") {
                if (col_dir[1].equals("entorno_arriba") || col_dir[1].equals("entorno_abajo") || col_dir[1].equals("entorno_izquierda") || col_dir[1].equals("entorno_derecha")) {
                    colision = (Objeto) col_dir[0];
                    if (colision.getId().equals(objeto.getId())) {
                        objeto.colision = true;
                    }
                }
            }
        }
    }

    @Override
    public void musica() {
        Sonido.cambioMusica(GestorPrincipal.musica_mapa_rafa);
    }
}
