package sprites;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Animacion {

    public static boolean esta_activa = false;
    public static HojaSprites animacion_tiempo = new HojaSprites("/animaciones/animacion_tiempo.png", 32, 64, false);
    public static HojaSprites animacion_espada = new HojaSprites("/animaciones/animacion_espada.png", 32, 48, false);

    public static BufferedImage imagen_actual;
    public static int x, y;

    public static void mostrarAnimacion(HojaSprites animacion, final int delay) {
        Runnable hilo = new Runnable() {

            @Override
            public void run() {
                try {
                    for (int y1 = 0; y1 < animacion.getAlto_nsprites(); y1++) {
                        for (int x1 = 0; x1 < animacion.getAncho_nsprites(); x1++) {

                            imagen_actual = animacion.obtenerSprite(x1, y1).obtenerImagen();
                            Thread.sleep(delay);
                        }
                    }
                    esta_activa = false;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Animacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        new Thread(hilo).start();
    }
}
