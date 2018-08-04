package input;

import herramientas.ListaSimple;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Teclado implements KeyListener {

    private final static int N_TECLAS = 256;
    public static boolean[] teclas = new boolean[N_TECLAS];

    //Movimiento
    public boolean arriba, abajo, izquierda, derecha;
    public boolean salir;
    public boolean correr;
    //Skills
    public boolean poder_tiempo;
    public boolean disparar_arma;
    public boolean recargar_arma;
    public boolean poderBola;
    public boolean ataque_espada;

    //Temporales
    public boolean cambiarPersonaje;

    ListaSimple lista = new ListaSimple();

    public void actualizar() {
        //actualiza si se presiono la tecla o no, con true y false
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
        salir = teclas[KeyEvent.VK_ESCAPE];
        correr = teclas[KeyEvent.VK_SHIFT];
        cambiarPersonaje = teclas[KeyEvent.VK_Q];
        poderBola = teclas[KeyEvent.VK_1];
        poder_tiempo = teclas[KeyEvent.VK_T];
        disparar_arma = teclas[KeyEvent.VK_E];
        recargar_arma = teclas[KeyEvent.VK_R];
        ataque_espada = teclas[KeyEvent.VK_2];
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if ((ke.getKeyCode() != KeyEvent.VK_E) && (ke.getKeyCode() != KeyEvent.VK_1) && (ke.getKeyCode() != KeyEvent.VK_2)) {
            teclas[ke.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if ((ke.getKeyCode() != KeyEvent.VK_1) && (ke.getKeyCode() != KeyEvent.VK_E) && (ke.getKeyCode() != KeyEvent.VK_2)) {
            teclas[ke.getKeyCode()] = false;
        } else {
            teclas[ke.getKeyCode()] = true;
        }

        lista.insertar(ke.getKeyCode());
        int[] arr = lista.obtenerNumeros();
        
        mirarCombinaciones(arr);
        
        arr = lista.obtenerNumeros();
        if(arr.length >0)
            lista.imprimirArreglo(arr);
        //System.out.println(ke.getKeyCode());
    }

    public void mirarCombinaciones(int[] arr) {

        //w,d,s,a = 87,68,83,65
        //cheat uno = wwwdddsssaaa = 87, 87, 87, 68, 68, 68, 83, 83, 83, 65, 65, 65
        //cheat dos = aaasssdddwww = 65, 65, 65, 83, 83, 83, 68, 68, 68, 87, 87, 87
        boolean[] combinacion_valida = {true,true};

        
        int[] comb1 = {87, 87, 87, 68, 68, 68, 83, 83, 83, 65, 65, 65};
        int[] comb2 = {65, 65, 65, 83, 83, 83, 68, 68, 68, 87, 87, 87};
        int[][] combinaciones = {comb1,
                                 comb2};
        
        for(int i=0;i<combinaciones.length;i++){
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != combinaciones[i][j]) {
                    //comb invalida
                    combinacion_valida[i] = false;
                    break;                            
                }
                
                if (combinacion_valida[i] && arr.length-1 == j) {
                    
                    if(arr.length == combinaciones[i].length){
                        JOptionPane.showMessageDialog(null, "CHEAT " + (i+1) + " ACTIVADO");
                        lista = new ListaSimple();
                        ListaSimple.numero_elementos = 0;
                        return;
                    }
                }

            }
        }
        
        int bandera = 0;
        //si todas las combinaciones posibles fueron invalidas entonces se renueva todo
        for(int i=0;i<combinacion_valida.length;i++){
            if(!combinacion_valida[i]){
                bandera++;
            }
        }
        
        if(bandera == combinacion_valida.length){
            lista = new ListaSimple();
            ListaSimple.numero_elementos = 0;
        }
        
        
        

    }

}
