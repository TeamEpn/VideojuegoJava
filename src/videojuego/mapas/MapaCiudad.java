/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.mapas;

import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.GestorPrincipal;
import videojuego.Objeto;
import videojuego.Sonido;
import videojuego.entidad.Jugador.Jugador;

/**
 *
 * @author RAFAEL
 */
public class MapaCiudad extends Mapa{
    
    public MapaCiudad(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        super(nombre,ruta,ancho,alto,jugador);
    }
    
    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(final int x,final int y,final int ancho_jugador,final int alto_jugador) {
        

        objetos = new ArrayList<>();

        //CASAS
        
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 8 + desfasey - y, 38, 125),"Casa 1 Izquierda",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 256 + desfasey - y, 38, 94),"Casa 2 Izquierda",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 441 + desfasey - y, 152, 159),"Casas 3 y 4 Izquierda",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(84 + desfasex - x, 10 + desfasey - y, 146, 279),"Casa 5.1 Izquierda",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(84 + desfasex - x, 289 + desfasey - y, 75, 59),"Casa 5.2 Izquierda",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(301 + desfasex - x, 8 + desfasey - y, 145, 280),"Casa 6.1 Centro",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(374 + desfasex - x, 287 + desfasey - y, 73, 63),"Casa 6.2 Centro",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(381 + desfasex - x, 440 + desfasey - y, 419, 160),"Casas 7, 8, 9, 10 Derecha",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(493 + desfasex - x, 8 + desfasey - y, 122, 158),"Casa 11 Derecha",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(493 + desfasex - x, 225 + desfasey - y, 122, 126),"Casa 12 Derecha",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(661 + desfasex - x, 0 + desfasey - y, 121, 42),"Iglesia Derecha",Objeto.Tag.EDIFICIO));
        objetos.add(new Objeto(new Rectangle(635 + desfasex - x, 225 + desfasey - y, 124, 128),"Casa 13 Derecha",Objeto.Tag.EDIFICIO));
        
                
        //Arboles y faroles y estatua
        objetos.add(new Objeto(new Rectangle(152 + desfasex - x, 470 + desfasey - y, 80, 130),"Arbol y muro Izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(300 + desfasex - x, 471 + desfasey - y, 81, 129),"Arbol y muro Derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(661 + desfasex - x, 42 + desfasey - y, 25, 124),"Flores iglesia izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(755 + desfasex - x, 42 + desfasey - y, 29, 123),"Flores iglesia derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(0 + desfasex - x, 148 + desfasey - y, 37, 79),"Arbol y poste 1 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(39 + desfasex - x, 303 + desfasey - y, 23, 77),"Poste 2 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(159 + desfasex - x, 395 + desfasey - y, 22, 75),"Poste 3 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(252 + desfasex - x, 349 + desfasey - y, 27, 63),"Estatua izquierda",Objeto.Tag.TELEPORT_BOSQUE));
        objetos.add(new Objeto(new Rectangle(352 + desfasex - x, 395 + desfasey - y, 21, 75),"Poste 4 izquierda",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(472 + desfasex - x, 304 + desfasey - y, 21, 75),"Poste 5 derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(616 + desfasex - x, 304 + desfasey - y, 21, 75),"Poste 6 derecha",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(759 + desfasex - x, 304 + desfasey - y, 21, 75),"Poste 7 derecha",Objeto.Tag.NATURALEZA));
        
           
    }

    @Override
    public void musica() {
        Sonido.cambioMusica(GestorPrincipal.musica_mapa_rafa);    
    }
}
