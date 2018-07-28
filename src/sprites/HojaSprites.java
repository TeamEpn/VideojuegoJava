package sprites;

import java.awt.image.BufferedImage;
import herramientas.CargadorRecursos;

public class HojaSprites {

    final private int ancho_hoja_pixeles;
    final private int alto_hoja_pixeles;

    final private int ancho_nsprites;
    final private int alto_nsprites;

    final private int ancho_sprites_pixeles;
    final private int alto_sprites_pixeles;

    private final Sprite[] sprites;
    
    public HojaSprites(final String ruta, final int tam_sprites, final boolean hoja_opaca) {
        //asume los sprites como cuadrados, se extraen los sprites desde una imagen
        final BufferedImage imagen;

        if (hoja_opaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        }

        ancho_hoja_pixeles = imagen.getWidth();
        alto_hoja_pixeles = imagen.getHeight();

        ancho_nsprites = ancho_hoja_pixeles / tam_sprites; // split
        alto_nsprites = alto_hoja_pixeles / tam_sprites; // split

        ancho_sprites_pixeles = tam_sprites;
        alto_sprites_pixeles = tam_sprites;

        sprites = new Sprite[ancho_nsprites * alto_nsprites];
        extraerSpritesDesdeImagen(imagen);
    }

    //los asume rectangulares
    public HojaSprites(final String ruta, final int ancho_sprite, final int alto_sprite, final boolean hoja_opaca) {
        final BufferedImage imagen;

        if (hoja_opaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        }

        ancho_hoja_pixeles = imagen.getWidth();
        alto_hoja_pixeles = imagen.getHeight();

        ancho_nsprites = ancho_hoja_pixeles / ancho_sprite; // split
        alto_nsprites = alto_hoja_pixeles / alto_sprite; // split

        System.out.println(ancho_hoja_pixeles);
        System.out.println(alto_hoja_pixeles);
        ancho_sprites_pixeles = ancho_sprite;
        alto_sprites_pixeles = alto_sprite;

        sprites = new Sprite[ancho_nsprites * alto_nsprites];
        extraerSpritesDesdeImagen(imagen);
    }
    
    
    

    private void extraerSpritesDesdeImagen(final BufferedImage imagen) {
        for (int y = 0; y < alto_nsprites; y++) {
            for (int x = 0; x < ancho_nsprites; x++) {
                final int posicionx = x * ancho_sprites_pixeles;
                final int posiciony = y * alto_sprites_pixeles;
                sprites[x+y*ancho_nsprites] = new Sprite(imagen.getSubimage(posicionx, posiciony, ancho_sprites_pixeles, alto_sprites_pixeles));
                //la operacion de arriba, recorre desde la esquina superior izquierda del sprite, de izquierda a derecha, hasta el final
            }
        }
    }
    
    public Sprite obtenerSprite(final int x,final int y){
        //'x' representa la fila, 'Y' la columna. De la matriz de sprites REAL
        return sprites[x + y * ancho_nsprites];
    }

}
