
package herramientas;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class CargadorRecursos {
    
    public static BufferedImage cargarImagenCompatibleOpaca(final String ruta){
        
        Image imagen = null;
        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
                    
                    } catch (IOException ex) {
            Logger.getLogger(CargadorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
        BufferedImage imagen_acelerada = gc.createCompatibleImage(imagen.getWidth(null),imagen.getHeight(null),Transparency.OPAQUE);
        //podria copiarla desde la ram a la tarjeta gráfica
        Graphics g = imagen_acelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
        
        return imagen_acelerada;
        
    }
    
    
    public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta){
        
        Image imagen = null;
        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
                    
                    } catch (IOException ex) {
            Logger.getLogger(CargadorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
        BufferedImage imagen_acelerada = gc.createCompatibleImage(imagen.getWidth(null),imagen.getHeight(null),Transparency.TRANSLUCENT);
        //podria copiarla desde la ram a la tarjeta gráfica
        Graphics g = imagen_acelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
        
        return imagen_acelerada;
        
    }
    
    public static String leerArchivoTexto(final String ruta){
        
        String contenido = "";
        
        //lee y nos devuelve un flujo de bytes, es mejor que texto
        InputStream entrada_bytes = ClassLoader.class.getResourceAsStream(ruta);
        BufferedReader lector = new BufferedReader(new InputStreamReader(entrada_bytes));
        
        String linea;
        try{
            while((linea = lector.readLine()) != null){
                contenido += linea;
                
            }
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(entrada_bytes != null){
                    entrada_bytes.close();
                }
                if(lector!=null){
                    lector.close();
                }
            }catch(IOException er){
                er.printStackTrace();
            }
        }
        
        return contenido;
    }
               
}
