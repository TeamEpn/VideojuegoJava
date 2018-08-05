/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego.objetos;

import java.awt.Rectangle;
import java.util.ArrayList;
import videojuego.objetos.entidad.Enemigo.Enemigo;
import videojuego.objetos.entidad.Jugador.Jugador;

public class Colision {
    
  
    public static void obtenerInfoColisionJugador(Jugador jugador, Objeto col,String[] direccion, ArrayList<Objeto> obj_colision) {
        
        //los lados se reciben en sentido horario
        //en orden de manecillas del reloj: arriba,derecha,abajo,izquierda
        Rectangle[] lados_ente = jugador.objeto_ente.getRectangle();
        Rectangle[] lados_col = col.getRectangle();

        
        if (col.getTag().compareToIgnoreCase(Objeto.Tag.COMIDA) == 0 || col.getTag().compareToIgnoreCase(Objeto.Tag.MONEDA) == 0) {
            
            for(int i=0;i<4;i++)
                if (lados_ente[i].intersects(lados_col[0])) {
                    obj_colision.add(col);
                    return;
                }
        }

         
            
        if (col.getTag().compareToIgnoreCase(Objeto.Tag.ENEMIGO) == 0) {

            //System.out.println(lados_ente[0].x + " con " + lados_col[0].x + " tag: " + col.getId());
            if (lados_ente[0].intersects(lados_col[2])) {
                //"arriba";
                obj_colision.add(col);
                direccion[0] = "enemigo_arriba";
            }
            //else
            //    System.out.println(lados_ente[0].y + " con " + lados_col[2].y + " tag: " + col.getId());
            if (lados_ente[1].intersects(lados_col[3])) {
                //"derecha";
                obj_colision.add(col);
                direccion[1] = "enemigo_derecha";

            }
            if (lados_ente[2].intersects(lados_col[0])) {
                //"abajo";
                obj_colision.add(col);
                direccion[2] = "enemigo_abajo";
            }
            if (lados_ente[3].intersects(lados_col[1])) {
                //"izquierda";
                obj_colision.add(col);
                direccion[3] = "enemigo_izquierda";
            }

        }
        
        if (col.getTag().compareToIgnoreCase(Objeto.Tag.NPC) == 0) {

            //System.out.println(lados_ente[0].x + " con " + lados_col[0].x + " tag: " + col.getId());
            if (lados_ente[0].intersects(lados_col[0])) {
                //"arriba";
                obj_colision.add(col);
                direccion[0] = "npc_arriba";
            }
            //else
            //    System.out.println(lados_ente[0].y + " con " + lados_col[2].y + " tag: " + col.getId());
            if (lados_ente[1].intersects(lados_col[0])) {
                //"derecha";
                obj_colision.add(col);
                direccion[1] = "npc_derecha";

            }
            if (lados_ente[2].intersects(lados_col[0])) {
                //"abajo";
                obj_colision.add(col);
                direccion[2] = "npc_abajo";
            }
            if (lados_ente[3].intersects(lados_col[0])) {
                //"izquierda";
                obj_colision.add(col);
                direccion[3] = "npc_izquierda";
            }

        }

        if (lados_ente[0].intersects(lados_col[0])) {
            //"arriba";
            obj_colision.add(col);
            direccion[0] = "entorno_arriba";
        }
        if (lados_ente[1].intersects(lados_col[0])) {
            //"derecha";
            obj_colision.add(col);
            direccion[1] = "entorno_derecha";
        }
        if (lados_ente[2].intersects(lados_col[0])) {
            //"abajo";
            obj_colision.add(col);
            direccion[2] = "entorno_abajo";
        }
        if (lados_ente[3].intersects(lados_col[0])) {
            //"izquierda";
            obj_colision.add(col);
            direccion[3] = "entorno_izquierda";
        }
    }
    
    
    public static void obtenerInfoColisionEnemigo(Enemigo enemigo, Objeto col,String[] direccion, ArrayList<Objeto> obj_colision) {
        
        //los lados se reciben en sentido horario
        //en orden de manecillas del reloj: arriba,derecha,abajo,izquierda
        Rectangle[] lados_ente = enemigo.objeto_ente.getRectangle();
        Rectangle[] lados_col = col.getRectangle();

        if (col.getTag().compareToIgnoreCase(Objeto.Tag.JUGADOR) == 0) {
            if (lados_ente[0].intersects(lados_col[2])) {
                //"arriba";
                obj_colision.add(col);
                direccion[0] = "jugador_arriba";
            }
            if (lados_ente[1].intersects(lados_col[3])) {
                //"derecha";
                obj_colision.add(col);
                direccion[1] = "jugador_derecha";

            }
            if (lados_ente[2].intersects(lados_col[0])) {
                //"abajo";
                obj_colision.add(col);
                direccion[2] = "jugador_abajo";
            }
            if (lados_ente[3].intersects(lados_col[1])) {
                //"izquierda";
                obj_colision.add(col);
                direccion[3] = "jugador_izquierda";
            }
        }

        if (lados_ente[0].intersects(lados_col[0])) {
            //"arriba";
            obj_colision.add(col);
            direccion[0] = "entorno_arriba";
        }
        if (lados_ente[1].intersects(lados_col[0])) {
            //"derecha";
            obj_colision.add(col);
            direccion[1] = "entorno_derecha";
        }
        if (lados_ente[2].intersects(lados_col[0])) {
            //"abajo";
            obj_colision.add(col);
            direccion[2] = "entorno_abajo";
        }
        if (lados_ente[3].intersects(lados_col[0])) {
            //"izquierda";
            obj_colision.add(col);
            direccion[3] = "entorno_izquierda";
        }
    }
}
