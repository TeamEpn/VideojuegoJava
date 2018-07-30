/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.mapas;


import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.Objeto;
import videojuego.entidad.Jugador.Jugador;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import sprites.HojaSprites;
import videojuego.GestorPrincipal;
import videojuego.Objeto;
import videojuego.Sonido;
import videojuego.entidad.Jugador.Jugador;
import videojuego.entidad.Jugador.Moneda;
import videojuego.entidad.Jugador.ObjetoCurativo;


public class MapaCiudad extends Mapa {

    HojaSprites hoja = new HojaSprites("/imagenes/hojasObjetos/1.png", 32, 32, false);
    Random random = new Random();
    ObjetoCurativo manzana1 = new ObjetoCurativo("manzana_roja", this, "manzana1");
    ObjetoCurativo manzana2 = new ObjetoCurativo("manzana_dorada", this, "manzana2");
    ObjetoCurativo zanahoria1 = new ObjetoCurativo("zanahoria", this, "zanahoria1");
    ObjetoCurativo galleta1 = new ObjetoCurativo("galleta", this, "galleta1");
    ObjetoCurativo orbeVerde1 = new ObjetoCurativo("orbe_verde", this, "orbe_verde1");
    ObjetoCurativo orbeDorado1 = new ObjetoCurativo("orbe_dorado", this, "orbe_dorado1");
    Moneda moneda1 = new Moneda(this, "moneda1");
    Moneda moneda2 = new Moneda(this, "moneda2");
    Moneda moneda3 = new Moneda(this, "moneda3");
    Moneda moneda4 = new Moneda(this, "moneda4");
    Moneda moneda5 = new Moneda(this, "moneda5");
    Object[] col_dir = null;
    Objeto colision = null;
    boolean colisionCurativa = false;
    private final int ANCHO_SPAWNEO = this.ancho - 100;
    private final int ALTO_SPAWNEO = this.alto - 100;

    public MapaCiudad(final String nombre, final String ruta, final int ancho, final int alto, final Jugador jugador) {
        super(nombre, ruta, ancho, alto, jugador);
        moneda1.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda1.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda2.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda2.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda3.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda3.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda4.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda4.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda5.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda5.setPosY(random.nextInt(ALTO_SPAWNEO));
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
        manzana1.dibujar(g, 250, 306, desfasex, desfasey, jugador);
        manzana2.dibujar(g, 253, 72, desfasex, desfasey, jugador);
        zanahoria1.dibujar(g, 637, 181, desfasex, desfasey, jugador);
        galleta1.dibujar(g, 677, 391, desfasex, desfasey, jugador);
        orbeVerde1.dibujar(g, 55, 48, desfasex, desfasey, jugador);
        orbeDorado1.dibujar(g, 243, 565, desfasex, desfasey, jugador);

        comprobarColisionCurativa(manzana1);
        comprobarColisionCurativa(manzana2);
        comprobarColisionCurativa(zanahoria1);
        comprobarColisionCurativa(galleta1);
        comprobarColisionCurativa(orbeVerde1);
        comprobarColisionCurativa(orbeDorado1);

        //DINERO
        if (moneda1.colision == false) {
            moneda1.dibujar(g, moneda1.getPosX(), moneda1.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.agregarDinero(5);
            moneda1.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda1.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda1.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (moneda2.colision == false) {
            moneda2.dibujar(g, moneda2.getPosX(), moneda2.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.agregarDinero(5);
            moneda2.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda2.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda2.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (moneda3.colision == false) {
            moneda3.dibujar(g, moneda3.getPosX(), moneda3.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.agregarDinero(5);
            moneda3.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda3.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda3.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
        if (moneda4.colision == false) {
            moneda4.dibujar(g, moneda1.getPosX(), moneda4.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.agregarDinero(5);
            moneda4.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda4.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda4.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (moneda5.colision == false) {
            moneda5.dibujar(g, moneda5.getPosX(), moneda5.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.agregarDinero(5);
            moneda5.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda5.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda5.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        comprobarColisionDinero(moneda1);
        comprobarColisionDinero(moneda2);
        comprobarColisionDinero(moneda3);
        comprobarColisionDinero(moneda4);
        comprobarColisionDinero(moneda5);

    }

    public void comprobarColisionCurativa(ObjetoCurativo objeto) {
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

    public void comprobarColisionDinero(Moneda moneda) {
        for (int i = 0; i < this.objetos.size(); i++) {
            col_dir = jugador.verificarColision(this.objetos.get(i));
            if (col_dir[1] != "") {
                if (col_dir[1].equals("entorno_arriba") || col_dir[1].equals("entorno_abajo") || col_dir[1].equals("entorno_izquierda") || col_dir[1].equals("entorno_derecha")) {
                    colision = (Objeto) col_dir[0];
                    if (colision.getId().equals(moneda.getId())) {
                        moneda.colision = true;
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
