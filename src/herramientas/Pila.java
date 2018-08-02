/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.io.Serializable;

/**
 *
 * @author RAFAEL
 */
public class Pila implements Serializable{

    private static final int TAMANIO = 5;
    private int cima;
    private String[] listaPila;

    public Pila() {
        cima = -1;
        listaPila = new String[TAMANIO];
    }

    public void push(String dato) {

        if (pilaLlena()) {
            System.out.println("LA PILA ESTA LLENA");
        } else {
            cima++;
            listaPila[cima] = dato;
        }

    }

    public String pop() {
        if (pilaVacia()) {
            return "LA PILA ESTA VACIA, NO SE PUEDE QUITAR";
        } else {
            String aux = listaPila[cima];
            cima--;
            return aux;
        }
    }

    public void limpiarPila() {
        cima = -1;
    }

    public int cimaPila() {
        return cima;
    }

    public boolean pilaVacia() {
        return cima == -1;
    }

    public boolean pilaLlena() {
        return cima == TAMANIO - 1;
    }

}
