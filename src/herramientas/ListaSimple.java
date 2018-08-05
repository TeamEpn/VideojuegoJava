
package herramientas;

public class ListaSimple {
    
    Nodo inicio,fin;
    public static int numero_elementos = 0;
    class Nodo{
        int contenido;
        Nodo siguiente;

        public Nodo(int contenido) {
            this.contenido = contenido;
        }
        
    }
    
    public void insertar(int contenido){
        Nodo nuevo = new Nodo(contenido);
        if(inicio == null){
            inicio = nuevo;
            fin = inicio;
            
        }
        else{
            fin.siguiente = nuevo;
            fin = fin.siguiente;
        }
        
        numero_elementos++;
    }
    
    public boolean esEliminado(int key){
        boolean eliminado = false;
        if(inicio != null){
            Nodo p = inicio;
            Nodo q = p;
            
            while(p!=null){
                
                if(p.contenido == key){
                    if(p == inicio){
                        inicio = null;
                    }
                    else{
                       q.siguiente = p.siguiente;
                       
                    }
                    eliminado = true;
                    numero_elementos--;
                    break;
                    
                }
                else{
                    q = p;
                    p = p.siguiente;
                    
                }
                
            }
        }
        return eliminado;
        
    }
    
    public int[] obtenerNumeros(){
        int[] elementos_actuales = new int[numero_elementos];
        if(inicio != null){
            Nodo p = inicio;
            int i = 0;
            while(p!=null){
                elementos_actuales[i++] = p.contenido;
                p = p.siguiente;
            }
            
        } 
        
        return elementos_actuales;
    }
    
    public void imprimirArreglo(int[] arr){
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i] + "->" );
        System.out.println();
    }
    
    
}
