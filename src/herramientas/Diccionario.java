/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.util.HashMap;

/**
 *
 * @author RAFAEL
 */
public class Diccionario {

    private HashMap diccionario;
    private Pila pila;
    private final LeerEscribirArchivos LEA;
    int capa1, capa2, capa3, capa4;

    public Diccionario() {
        diccionario = new HashMap();
        pila = new Pila();
        LEA = new LeerEscribirArchivos();
    }

    public void agregarDatos(String datos) {

        boolean coincidencia = false;
        String nombre = datos.split("/")[0], contraseña = datos.split("/")[1];
        String contraAscii = transformarAscii(contraseña);
        datos = nombre+"/"+contraAscii;
        

        if (contraseña.length() > 3) {
            capa1 = hashModulo(Integer.parseInt(contraAscii.substring(0, 7)));
        } else {
            capa1 = hashModulo(Integer.parseInt(contraAscii));
        }

        capa2 = hashModulo(capa1);
        capa3 = hashModulo(capa2);
        capa4 = hashModulo(capa3);

        pila.push(datos);
        pila.push(capa1+"");
        pila.push(capa2+"");
        pila.push(capa3+"");
        pila.push(capa4+"");

        for (int i = 0; i < diccionario.size(); i++) {
            if (diccionario.containsKey(capa4)) {
                coincidencia = true;
            }
        }

        if (coincidencia) {
            System.out.println("coincidencia encontrada, no se puede guardar esa contraseña");
        } else {
            diccionario.put(capa4, pila);
        }
    }

    public String obtenerDatos(int llave) {
        return diccionario.get(llave).toString();
    }

    public String transformarAscii(String clave) {

        String claveAscii = "";

        for (int i = 0; i < clave.length(); i++) {
            claveAscii = claveAscii + clave.codePointAt(i);
        }

        return claveAscii;
    }

    public int hashModulo(int clave) {

        int llave = (clave % 10) + 1;

        return llave;
    }

    public void crearArchivo() {
        LEA.escribirArchivos(diccionario, "datos.hm");
    }

    public HashMap obtenerDatosArchivo() {
        return LEA.leerArchivo("datos.hm");
    }

    public HashMap getDiccionario() {
        return diccionario;
    }
}
