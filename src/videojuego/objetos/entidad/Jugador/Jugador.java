package videojuego.objetos.entidad.Jugador;

import videojuego.objetos.armas.HiloDisparoArma;
import videojuego.objetos.entidad.Jugador.Poderes.poderFuego.HiloBola;
import videojuego.objetos.armas.Bala;
import videojuego.objetos.armas.Pistola;
import input.Teclado;
import videojuego.objetos.Objeto;
import videojuego.objetos.entidad.Jugador.Poderes.PoderTiempo.HiloAnimacionTiempo;
import videojuego.objetos.entidad.Jugador.Poderes.PoderTiempo.HiloPosicionesTiempo;
import videojuego.hud.HUDJugador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import videojuego.objetos.entidad.Entidad;
import videojuego.GestorPrincipal;
import interfaz.Lienzo;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import sprites.Animacion;
import sprites.HojaSprites;
import java.util.logging.Level;
import java.util.logging.Logger;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GESTORJUEGO.estados.EstadoAventura;
import interfaz.Sonido;
import videojuego.objetos.entidad.Jugador.Poderes.poderFuego.BolaFuego;

public class Jugador extends Entidad {

    private final int mana_maximo;
    private int mana_actual, dinero;

    private HUDJugador interfaz;
    public EstadoAventura estado_aventura;
    private Pistola pistola;
    private BolaFuego bola;

    private int expGanada;

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
        pistola = new Pistola(10);
        dinero = 0;

    }

    public int getMana_maximo() {
        return mana_maximo;
    }

    public int getMana_actual() {
        return mana_actual;
    }


    public int getExpGanada() {
        return expGanada;
    }

    public void setExpGanada(int expGanada) {
        this.expGanada = expGanada;
    }
    public Pistola getPistola() {
        return pistola;
    }

    public void setPistola(Pistola pistola) {
        this.pistola = pistola;
    }


    public int getDinero() {
        return dinero;
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


    @Override
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
        if (col_dir != null) {
            if (estado_aventura.enemigos != null && col_dir[1] == "") {
                for (int i = 0; i < estado_aventura.enemigos.length; i++) {
                    col_dir = this.verificarColision(estado_aventura.enemigos[i].objeto_ente);

                    if (col_dir[1] != "") {
                        break;
                    }
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


        if (lienzo.getTeclado().cambiarPersonaje) {

            frente0 = new HojaSprites("/imagenes/hojasPersonajes/2.png", 32, true).obtenerSprite(0, 0).obtenerImagen();
            espalda0 = new HojaSprites("/imagenes/hojasPersonajes/2.png", 32, true).obtenerSprite(1, 0).obtenerImagen();
            lado_derecho0 = new HojaSprites("/imagenes/hojasPersonajes/2.png", 32, true).obtenerSprite(2, 0).obtenerImagen();
            lado_izquierdo0 = new HojaSprites("/imagenes/hojasPersonajes/2.png", 32, true).obtenerSprite(3, 0).obtenerImagen();
            sprite_actual = frente0;
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
            if (col.getTag().compareToIgnoreCase("teleport_ciudad") == 0) {
                
                this.estado_aventura.mapa_actual = this.estado_aventura.mapas[0];
                this.estado_aventura.mapa_actual.musica();
                this.setMapa(this.estado_aventura.mapa_actual);
            }
            if (col.getTag().compareToIgnoreCase("teleport_bosque") == 0) {
                
                this.estado_aventura.mapa_actual = this.estado_aventura.mapas[1];
                this.estado_aventura.mapa_actual.musica();
                this.setMapa(this.estado_aventura.mapa_actual);
            }

            if (col.getTag().compareToIgnoreCase("Tictactoe") == 0) {
                GestorEstado.cambiarEstado(1);
                Sonido.cambioMusica(Sonido.musica_menu);
            }
            if (col.getTag().compareToIgnoreCase("subida_exp") == 0) {
                if (this.getExpGanada() <= 169) {
                    this.ganarExp(2);
                }
            }
        
            if (col.getTag().compareToIgnoreCase("teleport_ciudad") == 0) {

                this.estado_aventura.mapa_actual = this.estado_aventura.mapas[0];
                this.estado_aventura.mapa_actual.musica();
                this.setMapa(this.estado_aventura.mapa_actual);
            }
            if (col.getTag().compareToIgnoreCase("teleport_bosque") == 0) {

                this.estado_aventura.mapa_actual = this.estado_aventura.mapas[1];
                this.estado_aventura.mapa_actual.musica();
                this.setMapa(this.estado_aventura.mapa_actual);
            }
            if (col.getTag().compareToIgnoreCase("teleport_casa") == 0) {

                this.estado_aventura.mapa_actual = this.estado_aventura.mapas[2];
                this.estado_aventura.mapa_actual.musica();
                this.setMapa(this.estado_aventura.mapa_actual);
            }
            if (col.getTag().compareToIgnoreCase("agregar_vida") == 0) {
                if (this.getVida_actual() < this.vida_maxima) {
                    this.agregarVida(20);
                } else {
                    this.vida_actual = vida_maxima;
                }
            }
            if (col.getTag().compareToIgnoreCase("agregar_dinero") == 0) {
                this.agregarDinero(10);
            }
            if (col.getTag().compareToIgnoreCase("Tictactoe") == 0) {
                GestorEstado.cambiarEstado(1);
                Sonido.cambioMusica(Sonido.musica_menu);
            }
        }

    }

    public void acciones(Lienzo lienzo) {


        if (lienzo.getTeclado().poder_tiempo) {

            if (mana_actual >= 100) {
                System.out.println("PODER ACTIVADO");
                mana_actual -= 100;
                Jugador[] estados = HiloPosicionesTiempo.cola.obtenerEstadosJugador();
                
                Sonido.sonido_viaje_tiempo.reproducir();
                new Thread(new HiloAnimacionTiempo(this, estados)).start();
                Animacion.esta_activa = true;
                Animacion.imagen_actual = Animacion.animacion_tiempo.obtenerSprite(0, 0).obtenerImagen();
                Animacion.x = GestorPrincipal.CENTROX;
                Animacion.y = GestorPrincipal.CENTROY;
            }

        }

        if (lienzo.getTeclado().poderBola) {
            Teclado.teclas[KeyEvent.VK_1] = false;

            if (mana_actual >= 50) {
                try {
                    mana_actual -= 50;
                    Thread.sleep(100);
                    bola = new BolaFuego(this,this.x,this.y);
                    
                    
                    if (sprite_actual == frente0) {
                        new Thread(new HiloBola(this,"abajo",bola)).start();
                    } else if (sprite_actual == espalda0) {
                        new Thread(new HiloBola(this,"arriba",bola)).start();
                    } else if (sprite_actual == lado_derecho0) {
                        new Thread(new HiloBola(this,"derecha",bola)).start();
                    } else if (sprite_actual == lado_izquierdo0) {
                        new Thread(new HiloBola(this,"izquierda",bola)).start();
                    }
                    bola.esta_activa = true;

                } catch (InterruptedException ex) {
                    Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

        
        if (lienzo.getTeclado().recargar_arma) {
            pistola = new Pistola(10);
            interfaz = new HUDJugador(this);
        }

        if (lienzo.getTeclado().disparar_arma) {


            pistola.cantidad_balas--;
            Teclado.teclas[KeyEvent.VK_E] = false;

            if (pistola.cantidad_balas >= 0) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                final int inicioX = this.getX(), inicioY = this.getY();
                if (sprite_actual == frente0) {
                    pistola.bala = new Bala(Bala.bala_abajo, "abajo", inicioX, inicioY);
                    //System.out.println(bal);
                    new Thread(new HiloDisparoArma(pistola.bala, this, "abajo")).start();
                } else if (sprite_actual == espalda0) {
                    pistola.bala = new Bala(Bala.bala_arriba, "arriba", inicioX, inicioY);
                    new Thread(new HiloDisparoArma(pistola.bala, this, "arriba")).start();
                } else if (sprite_actual == lado_derecho0) {
                    pistola.bala = new Bala(Bala.bala_derecha, "derecha", inicioX, inicioY);
                    new Thread(new HiloDisparoArma(pistola.bala, this, "derecha")).start();
                } else if (sprite_actual == lado_izquierdo0) {
                    pistola.bala = new Bala(Bala.bala_izquierda, "izquierda", inicioX, inicioY);
                    new Thread(new HiloDisparoArma(pistola.bala, this, "izquierda")).start();

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
        
        if(Animacion.esta_activa){
            g.drawImage(Animacion.imagen_actual, Animacion.x, Animacion.y, null);
        }


        if (pistola.cantidad_balas > 0) {
            pistola.dibujar(g, this);
        }
        
        if(bola.esta_activa){
            bola.dibujar(g);
        }
        
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


    public void ganarExp(int cantidad) {
        this.expGanada += cantidad;
    }
    public void agregarVida(int cantidad) {
        if (this.vida_actual + cantidad <= this.vida_maxima) {
            this.vida_actual += cantidad;
        } else {
            this.vida_actual = 100;
        }
    }
    public void agregarDinero(int cantidad) {
        dinero = dinero + cantidad;
    }
}
