package videojuego.entidad.Jugador;

import input.Teclado;
import videojuego.Objeto;
import videojuego.entidad.Jugador.Poderes.PoderTiempo.HiloAnimacionTiempo;
import videojuego.entidad.Jugador.Poderes.PoderTiempo.HiloPosicionesTiempo;
import videojuego.hud.HUDJugador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import videojuego.entidad.Entidad;
import videojuego.GestorPrincipal;
import interfaz.Lienzo;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GESTORJUEGO.estados.EstadoAventura;

public class Jugador extends Entidad {

    private final int mana_maximo;
    private int mana_actual;

    private HUDJugador interfaz;
    public EstadoAventura estado_aventura;
    private Pistola pistola;

    public Jugador(Lienzo lienzo) {

        /*
        super("/imagenes/hojasPersonajes/rafa.png", 32, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, Objeto.Tag.JUGADOR);
        super("/imagenes/hojasPersonajes/2.png", 32, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, Objeto.Tag.JUGADOR,
                new int[]{0,0},new int[]{1,0},new int[]{2,0},new int[]{3,0});*/
        super("/imagenes/hojasPersonajes/aventurero.png", 32,64, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, Objeto.Tag.JUGADOR,
                new int[]{0,1},new int[]{0,0},new int[]{0,3},new int[]{0,2});

        mana_actual = (mana_maximo = 100);
        interfaz = new HUDJugador(this);
        iniciarThreadsPermanentes();
        pistola = new Pistola(new Balas(3));

    }

    public int getMana_maximo() {
        return mana_maximo;
    }

    public int getMana_actual() {
        return mana_actual;
    }

    public Pistola getPistola() {
        return pistola;
    }

    public void setPistola(Pistola pistola) {
        this.pistola = pistola;
    }

    private void iniciarThreadsPermanentes() {
        //Hilos para controlar: el poder del tiempo y la regeneracion de vida/mana
        new Thread(new HiloPosicionesTiempo(this, 6)).start();
        new Thread(new HiloJugadorRegeneracion(this)).start();
    }

    public Jugador(int x, int y, int vida_actual, BufferedImage sprite_actual) {
        //este no inicia los treas, es solo usado por el hilo de animacion y tiempo //SOLO DEBE SER USADO POR EL HILO
        super(vida_actual);
        this.x = x;
        this.y = y;

        this.mana_maximo = 100;
        this.sprite_actual = sprite_actual;
    }

    @Override
    public void actualizar(Lienzo lienzo) {
        this.mover(lienzo);
        this.acciones(lienzo);
    }

    public void mover(Lienzo lienzo) {

        Object[] col_dir = null;
        Objeto col = null;
        String direccion = "none";

        if (mapa.objetos != null) {
            for (int i = 0; i < mapa.objetos.size(); i++) {
                col_dir = this.verificarColision(mapa.objetos.get(i));
                if (col_dir[1] != "") {
                    break;
                }
            }
        }
        if(col_dir != null)
            if (estado_aventura.enemigos != null && col_dir[1] == "") {
                for (int i = 0; i < estado_aventura.enemigos.length; i++) {
                    col_dir = this.verificarColision(estado_aventura.enemigos[i].objeto_ente);

                    if (col_dir[1] != "") {
                        break;
                    }
                }
            }

        if (col_dir != null) {
            col = (Objeto) col_dir[0];
            direccion = col_dir[1].toString();
        }

        //System.out.print(col_dir[1].toString());
        if (lienzo.getTeclado().arriba) {
            if (!(direccion.compareToIgnoreCase("entorno_arriba") == 0)) {
                sprite_actual = espalda0;
                y = y - velocidad;
            }
        }
        if (lienzo.getTeclado().abajo) {
            if (!(direccion.compareToIgnoreCase("entorno_abajo") == 0)) {
                sprite_actual = frente0;
                y = y + velocidad;
            }
        }
        if (lienzo.getTeclado().izquierda) {
            if (!(direccion.compareToIgnoreCase("entorno_izquierda") == 0)) {
                sprite_actual = lado_izquierdo0;
                x = x - velocidad;
            }
        }
        if (lienzo.getTeclado().derecha) {
            if (!(direccion.compareToIgnoreCase("entorno_derecha") == 0)) {
                sprite_actual = lado_derecho0;
                x = x + velocidad;
            }
        }

        if (col != null) {
            if (col.getTag().compareToIgnoreCase("enemigo") == 0) {

                if (this.getVida_actual() >= 2) {
                    this.quitarVida(2);
                }
            }
            if (col.getTag().compareToIgnoreCase("absorcion_mana") == 0) {

                if (this.getMana_actual() >= 2) {
                    this.quitarMana(2);
                }
                GestorEstado.cambiarEstado(0);
                this.estado_aventura.mapa_actual = this.estado_aventura.mapas[1];
                this.setMapa(this.estado_aventura.mapa_actual);
            }
            /*if (col.getTag().compareToIgnoreCase("teleport") == 0) {
                Random random = new Random(); 
                this.estado_aventura.mapa_actual = this.estado_aventura.mapas[1+random.nextInt(2)];
                this.setMapa(this.estado_aventura.mapa_actual);
            }*/

            if (col.getTag().compareToIgnoreCase("Tictactoe") == 0) {
                GestorEstado.cambiarEstado(1);
            }
        }

    }

    private void acciones(Lienzo lienzo) {

        if (lienzo.getTeclado().poder_tiempo) {

            if (mana_actual >= 100) {
                System.out.println("PODER ACTIVADO");
                mana_actual -= 100;
                Jugador[] estados = HiloPosicionesTiempo.cola.obtenerEstadosJugador();
                new Thread(new HiloAnimacionTiempo(this, estados)).start();
            }

        }

        if (lienzo.getTeclado().recargar_arma) {
            pistola = new Pistola(new Balas(3));
            interfaz = new HUDJugador(this);
        }

        if (lienzo.getTeclado().disparar_arma) {
            
            pistola.balas.cantidad--;
            Teclado.teclas[KeyEvent.VK_E] = false;
            
            if (pistola.balas.getCantidad() >= 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (sprite_actual == frente0) {
                    ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/hojasObjetos/balaAbajo.png"));
                    new Thread(new HiloDisparoArma(img, lienzo.getGraphics(), this, interfaz, "abajo")).start();
                }

                else if (sprite_actual == espalda0) {
                    ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/hojasObjetos/balaArriba.png"));
                    new Thread(new HiloDisparoArma(img, lienzo.getGraphics(), this, interfaz, "arriba")).start();
                }

                else if (sprite_actual == lado_derecho0) {
                    ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/hojasObjetos/balaDerecha.png"));
                    new Thread(new HiloDisparoArma(img, lienzo.getGraphics(), this, interfaz, "derecha")).start();
                }

                else if (sprite_actual == lado_izquierdo0) {
                    ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/hojasObjetos/balaIzquierda.png"));
                    new Thread(new HiloDisparoArma(img, lienzo.getGraphics(), this, interfaz, "izquierda")).start();
                }

            }
        }

    }

    public void nuevoEstado(Jugador estado) {
        this.x = estado.x;
        this.y = estado.y;
        this.vida_actual = estado.vida_actual;
        this.sprite_actual = estado.sprite_actual;
    }

    @Override
    public void dibujar(Graphics g) {

        //personaje
        g.drawImage(sprite_actual, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, null);

        g.setColor(Color.red);

        for (int i = 0; i < 4; i++) {
            g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y,
                    this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);
        }

        //estadisticas
        interfaz.dibujar(g);

    }

    public void quitarVida(int cantidad) {
        if (this.vida_actual >= cantidad) {
            this.vida_actual -= cantidad;
        } else {
            this.vida_actual = 0;
        }
    }
    
        public void quitarMana(int cantidad) {
        if (this.mana_actual >= cantidad) {
            this.mana_actual -= cantidad;
        } else {
            this.mana_actual = 0;
        }
    }

    public void regenerarVida(int cantidad) {
        if (this.vida_actual + cantidad <= this.vida_maxima) {
            this.vida_actual += cantidad;
        } else {
            this.vida_actual = 100;
        }
    }

    public void regenerarMana(int cantidad) {
        if (this.mana_actual + cantidad <= this.mana_maximo) {
            this.mana_actual += cantidad;
        } else {
            this.mana_actual = 100;
        }
    }

}
