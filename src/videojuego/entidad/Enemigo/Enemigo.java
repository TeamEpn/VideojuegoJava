
package videojuego.entidad.Enemigo;

import java.awt.Color;
import java.awt.Graphics;
import videojuego.entidad.Entidad;
import videojuego.entidad.Jugador.Jugador;
import videojuego.GestorPrincipal;
import videojuego.Objeto;
import interfaz.Lienzo;

public class Enemigo extends Entidad{

    Jugador jugador;
    public Enemigo(Jugador jugador){
        //el centrox para ubicarlo en la esquina superior izquierda
        super("/imagenes/hojasEnemigos/1.png", 32, GestorPrincipal.CENTROX + 100, GestorPrincipal.CENTROY + 100,Objeto.Tag.ENEMIGO,
                new int[]{0,0},new int[]{1,0},new int[]{2,0},new int[]{3,0});
        
        this.jugador = jugador;
        this.setMapa(jugador.getMapa());
        
    }
    
    
    @Override
    public void actualizar(Lienzo lienzo) {
        
        this.mover(lienzo);
        
        
    }
    
    
    @Override
    public void mover(Lienzo lienzo){
        
        Object[] col_dir = null;
        Objeto col = null;
        String direccion = "none";
        
        if(mapa.objetos!=null){
            for (int i = 0; i < mapa.objetos.size(); i++) {
                col_dir = this.verificarColision(mapa.objetos.get(i));
                if(col_dir[1] != ""){
                    break;
                }
            }
        }
        
        if(col_dir[1] == ""){
            col_dir = this.verificarColision(jugador.objeto_ente);
        }
        
        if(col_dir[1] != ""){
            col = (Objeto)col_dir[0];
            direccion = col_dir[1].toString();
        }
        
        
        if(jugador.getY() > this.y){
            if (!(direccion.compareToIgnoreCase("entorno_abajo") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_abajo") == 0)) {
                this.y++;
            }
            
        }
        else if(jugador.getY() <= this.y){
            
            if (!(direccion.compareToIgnoreCase("entorno_arriba") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_arriba") == 0)) {
                this.y--;
            }
        }
        
        if(jugador.getX() > this.x){
            if (!(direccion.compareToIgnoreCase("entorno_derecha") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_derecha") == 0)) {
                
                this.x++;
            }
        }
        else if(jugador.getX() <= this.x){
            if (!(direccion.compareToIgnoreCase("entorno_izquierda") == 0)
                    && !(direccion.compareToIgnoreCase("jugador_izquierda") == 0)) {
                this.x--;
            }
        }
        
        
    }

    @Override
    public void dibujar(Graphics g) {
        
        g.drawImage(this.sprite_actual, this.x+GestorPrincipal.CENTROX-jugador.getX(), this.y+GestorPrincipal.CENTROY-jugador.getY(), null);
        
        g.setColor(Color.orange);
        
        this.generarCollides(this.x+GestorPrincipal.CENTROX-jugador.getX(), this.y+GestorPrincipal.CENTROY-jugador.getY(), Objeto.Tag.ENEMIGO);
        for(int i=0;i<4;i++){
            g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y,
                    this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);
        
        }
        
        
        /*for(int i=0;i<4;i++){
            g.drawRect(this.objeto_ente.getRectangle()[i].x+(this.x-this.posx_inicial-jugador.getX()), this.objeto_ente.getRectangle()[i].y+(this.y-this.posy_inicial-jugador.getY()),
                    this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);
        
        }*/
    }
    
}
