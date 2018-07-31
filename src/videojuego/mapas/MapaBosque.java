package videojuego.mapas;

import java.awt.Graphics;
import videojuego.objetos.entidad.Jugador.Jugador;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sprites.HojaSprites;
import videojuego.objetos.recolectables.Moneda;
import videojuego.objetos.recolectables.ObjetoCurativo;
import videojuego.objetos.Objeto;
import interfaz.Sonido;

public class MapaBosque extends Mapa{
    
    HojaSprites hoja = new HojaSprites("/imagenes/hojasObjetos/1.png", 32, 32, false);
    Random random = new Random();
    ObjetoCurativo manzana1 = new ObjetoCurativo("manzana_roja", this, "manzana1");
    ObjetoCurativo manzana2 = new ObjetoCurativo("manzana_dorada", this, "manzana2");
    ObjetoCurativo zanahoria1 = new ObjetoCurativo("zanahoria", this, "zanahoria1");
    ObjetoCurativo galleta1 = new ObjetoCurativo("galleta", this, "galleta1");
    ObjetoCurativo orbeVerde1 = new ObjetoCurativo("orbe_verde", this, "orbe_verde1");
    ObjetoCurativo orbeDorado1 = new ObjetoCurativo("orbe_dorado", this, "orbe_dorado1");
    Moneda moneda1 = new Moneda(this, "moneda1");
    Moneda moneda2 = new Moneda(this, "moneda2");
    Moneda moneda3 = new Moneda(this, "moneda3");
    Moneda moneda4 = new Moneda(this, "moneda4");
    Moneda moneda5 = new Moneda(this, "moneda5");
    Object[] col_dir = null;
    Objeto colision = null;
    boolean colisionCurativa = false;
    private final int ANCHO_SPAWNEO = this.ancho - 100;
    private final int ALTO_SPAWNEO = this.alto - 100;
    
    public MapaBosque(final String nombre,final String ruta,final int ancho,final int alto,final Jugador jugador) {
        super(nombre,ruta,ancho,alto,jugador);
        
        moneda1.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda1.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda2.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda2.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda3.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda3.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda4.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda4.setPosY(random.nextInt(ALTO_SPAWNEO));
        moneda5.setPosX(random.nextInt(ANCHO_SPAWNEO));moneda5.setPosY(random.nextInt(ALTO_SPAWNEO));

    }
    
    // EN VEZ DE ARRAYLIST USAR LISTAS ENCADENADAS
    @Override
    public void generarObjetosColisionables(Graphics g, final int x,final int y,final Jugador jugador) {

        

        objetos = new ArrayList<>();

        //BOSQUE
        final int cuadrado_arbol = 100;
        objetos.add(new Objeto(new Rectangle(329 + desfasex - x, 273 + desfasey - y, cuadrado_arbol, cuadrado_arbol),"Arbol1",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(26 + desfasex - x, 354 + desfasey - y, 80, 100),"Arbol2",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(78 + desfasex - x, 381 + desfasey - y, cuadrado_arbol, cuadrado_arbol),"Arbol3",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(216 + desfasex - x, 426 + desfasey - y, 101, 100),"Arbol4",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(177 + desfasex - x, 487 + desfasey - y, 81, 94),"Arbol5",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(272 + desfasex - x, 503 + desfasey - y, 100, 94),"Arbol6",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(274 + desfasex - x, 3 + desfasey - y,100 ,73 ),"Arbol7",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(356 + desfasex - x,29  + desfasey - y,101 ,75 ),"Arbol8",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(469 + desfasex - x,1  + desfasey - y,93 ,45 ),"Arbol9",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(712 + desfasex - x,3  + desfasey - y,90 ,26 ),"Arbol10",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(600 + desfasex - x,26  + desfasey - y,88 ,96 ),"Arbol11",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(383 + desfasex - x,534  + desfasey - y,117 ,61 ),"Arbol12",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(444 + desfasex - x,173  + desfasey - y,84 ,94 ),"Arbol13",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(504 + desfasex - x,130  + desfasey - y,72 ,100 ),"Arbol14",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(465 + desfasex - x,303  + desfasey - y,93 ,97 ),"Arbol15",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(543 + desfasex - x,433  + desfasey - y,102 ,96 ),"Arbol16",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(631 + desfasex - x,309  + desfasey - y, 96, 97),"Arbol17",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(637 + desfasex - x,561  + desfasey - y,83 ,38 ),"Arbol18",Objeto.Tag.NATURALEZA));
        
        objetos.add(new Objeto(new Rectangle(52 + desfasex - x,574  + desfasey - y,31 ,23 ),"Tronco1",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(766 + desfasex - x,100  + desfasey - y,24 ,28 ),"Tronco2",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(224 + desfasex - x,245  + desfasey - y, 14, 59),"Valla1",Objeto.Tag.NATURALEZA));
        objetos.add(new Objeto(new Rectangle(302 + desfasex - x,235  + desfasey - y,50 ,40 ),"Valla2",Objeto.Tag.NATURALEZA));

        objetos.add(new Objeto(new Rectangle(273 + desfasex - x,301  + desfasey - y,27 ,27 ),"Fogata1",Objeto.Tag.ABSORCION_MANA));
        
        objetos.add(new Objeto(new Rectangle(163 + desfasex - x,197  + desfasey - y,32 ,28 ),"TresEnRaya",Objeto.Tag.TICTACTOE));

        
                
        //Edificios
        objetos.add(new Objeto(new Rectangle(230 + desfasex - x, 125 + desfasey - y, 116, 113),"TiendaCampaña1",Objeto.Tag.EDIFICIO));

        objetos.add(new Objeto(new Rectangle(271 + desfasex - x, 231 + desfasey - y, 31, 15),"teleport1",Objeto.Tag.TELEPORT_CIUDAD));
        
        //OBJETOS CURATIVOS
        manzana1.dibujar(g, 52, 520, desfasex, desfasey, jugador);
        manzana2.dibujar(g, 383, 486, desfasex, desfasey, jugador);
        zanahoria1.dibujar(g, 637, 181, desfasex, desfasey, jugador);
        galleta1.dibujar(g, 715, 480, desfasex, desfasey, jugador);
        orbeVerde1.dibujar(g, 484, 84, desfasex, desfasey, jugador);
        orbeDorado1.dibujar(g, 6, 290, desfasex, desfasey, jugador);

        comprobarColisionCurativa(manzana1);
        comprobarColisionCurativa(manzana2);
        comprobarColisionCurativa(zanahoria1);
        comprobarColisionCurativa(galleta1);
        comprobarColisionCurativa(orbeVerde1);
        comprobarColisionCurativa(orbeDorado1);

        
        //DINERO
        if (moneda1.colision == false) {
            moneda1.dibujar(g, moneda1.getPosX(), moneda1.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.getCuenta().agregarDinero(5);
            moneda1.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda1.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda1.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (moneda2.colision == false) {
            moneda2.dibujar(g, moneda2.getPosX(), moneda2.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.getCuenta().agregarDinero(5);
            moneda2.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda2.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda2.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (moneda3.colision == false) {
            moneda3.dibujar(g, moneda3.getPosX(), moneda3.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.getCuenta().agregarDinero(5);
            moneda3.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda3.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda3.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
        if (moneda4.colision == false) {
            moneda4.dibujar(g, moneda1.getPosX(), moneda4.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.getCuenta().agregarDinero(5);
            moneda4.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda4.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda4.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (moneda5.colision == false) {
            moneda5.dibujar(g, moneda5.getPosX(), moneda5.getPosY(), desfasex, desfasey, jugador);
        } else {
            jugador.getCuenta().agregarDinero(5);
            moneda5.setPosX(random.nextInt(ANCHO_SPAWNEO));
            moneda5.setPosY(random.nextInt(ALTO_SPAWNEO));
            moneda5.colision = false;
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapaCiudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        comprobarColisionDinero(moneda1);
        comprobarColisionDinero(moneda2);
        comprobarColisionDinero(moneda3);
        comprobarColisionDinero(moneda4);
        comprobarColisionDinero(moneda5);
           
    }
    
    public void comprobarColisionCurativa(ObjetoCurativo objeto) {
        for (int i = 0; i < this.objetos.size(); i++) {
            col_dir = jugador.verificarColision(this.objetos.get(i));
            if (col_dir[1] != "") {
                if (col_dir[1].equals("entorno_arriba") || col_dir[1].equals("entorno_abajo") || col_dir[1].equals("entorno_izquierda") || col_dir[1].equals("entorno_derecha")) {
                    colision = (Objeto) col_dir[0];
                    if (colision.getId().equals(objeto.getId())) {
                        objeto.colision = true;
                    }
                }
            }
        }
    }

    public void comprobarColisionDinero(Moneda moneda) {
        for (int i = 0; i < this.objetos.size(); i++) {
            col_dir = jugador.verificarColision(this.objetos.get(i));
            if (col_dir[1] != "") {
                if (col_dir[1].equals("entorno_arriba") || col_dir[1].equals("entorno_abajo") || col_dir[1].equals("entorno_izquierda") || col_dir[1].equals("entorno_derecha")) {
                    colision = (Objeto) col_dir[0];
                    if (colision.getId().equals(moneda.getId())) {
                        moneda.colision = true;
                    }
                }
            }
        }
    }

    @Override
    public void musica() {
        Sonido.cambioMusica(Sonido.musica_inicio);
    }

}
