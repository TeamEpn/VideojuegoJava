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

    public static BufferedImage cargarImagenCompatibleOpaca(final String ruta) {

        Image imagen = null;
        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));

        } catch (IOException ex) {
            Logger.getLogger(CargadorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage imagen_acelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.OPAQUE);

        //podria copiarla desde la ram a la tarjeta gráfica
        Graphics g = imagen_acelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();

        return imagen_acelerada;

    }

    public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta) {

        Image imagen = null;
        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));

        } catch (IOException ex) {
            Logger.getLogger(CargadorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage imagen_acelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.TRANSLUCENT);

        //podria copiarla desde la ram a la tarjeta gráfica
        Graphics g = imagen_acelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();


        return imagen_acelerada;

    }
}
