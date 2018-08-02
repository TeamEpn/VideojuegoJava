/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USRKAP
 */
public class LeerEscribirArchivos {

    private final String RUTA = "recursos" + File.separator + "archivos" + File.separator;

    public void escribirArchivos(HashMap hm, String nombre) {
        try {
            FileOutputStream fos = new FileOutputStream(RUTA + nombre);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(hm);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerEscribirArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerEscribirArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public HashMap leerArchivo(String nombre) {
        try {
            FileInputStream fis = new FileInputStream(RUTA + nombre);
            ObjectInputStream in = new ObjectInputStream(fis);
            HashMap hm = (HashMap)in.readObject();
            in.close();
            return hm;
            
        } catch (IOException ex) {
            Logger.getLogger(LeerEscribirArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LeerEscribirArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
