
package videojuego.entidad.Jugador.Poderes.PoderTiempo;

import videojuego.entidad.Jugador.Jugador;

public class ListaPosiciones {
    private final int len_maximo;
    private int len_actual;
    private NodoJugador inicio, fin;

    public ListaPosiciones(final int len_maximo) {
        this.len_maximo = len_maximo;
        this.len_actual = 0;
        inicio = null;
    }
    
    public void insertar(final Jugador jugador){
        
        NodoJugador nuevo = new NodoJugador(new Jugador(jugador.getX(),jugador.getY(),jugador.getVida_actual(),jugador.sprite_actual));
        if(estaVacia()){
            inicio = nuevo;
            fin = inicio;
            len_actual++;
        }
        else{
            if(len_actual==len_maximo){
               inicio = inicio.siguiente;
               inicio.anterior = null;
               nuevo.anterior = fin;
               fin.siguiente = nuevo;
               fin = fin.siguiente;
            }
            else{
                nuevo.anterior = fin;
                fin.siguiente = nuevo;
                fin = fin.siguiente;
                len_actual++;
            }
        }
    }
    
    public Jugador[] obtenerEstadosJugador(){
        
        Jugador[] estados = new Jugador[len_actual];
        int i = 0;
        NodoJugador p = fin;
        while(p!=null){
            estados[i++] = p.jugador;
            p = p.anterior;
        }
        
        return estados;               
        
    }
    
    public boolean estaVacia(){
        return inicio == null;
    }

    @Override
    public String toString() {
        
        String salida = "****DEBUG****\n";
        
        NodoJugador p = fin;
        while(p!=null){
            salida += p.jugador.getVida_actual();
            
            if(len_actual == len_maximo && p.anterior == null) salida+="|"; else salida+=" -> ";
            p = p.anterior;
        }
        
        
        return salida;
    }
    
    
    
}
