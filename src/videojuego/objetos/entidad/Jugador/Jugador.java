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
import sprites.Animacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import videojuego.GESTORJUEGO.GestorEstado;
import videojuego.GESTORJUEGO.estados.EstadoAventura;
import videojuego.GESTORJUEGO.estados.inversiones.Cuenta;
import interfaz.Sonido;
import interfaz.Ventana;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import videojuego.GESTORJUEGO.estados.EstadoTienda;
import videojuego.hud.Decision;
import videojuego.hud.Dialogo;
import videojuego.mapas.Mapa;
import videojuego.objetos.Colision;
import videojuego.objetos.armas.Espada;
import videojuego.objetos.entidad.Enemigo.Enemigo;
import videojuego.objetos.entidad.Jugador.Poderes.poderFuego.BolaFuego;
import videojuego.objetos.recolectables.Comida;
import videojuego.objetos.recolectables.Moneda;

public class Jugador extends Entidad {

    //Estadísticas
    private int nivel;
    private int damage, damagePistola;
    private int mana_maximo, mana_actual;
    private int resistencia_maxima, resistencia_actual;
    private int exp_maxima, exp_actual;
    private int reg_vida;
    private int reg_mana;
    private int reg_resistencia;
    private Cuenta cuenta;
    private HUDJugador interfaz;
    public boolean esta_cansado;
    int contador_cansado;
    //Objetos
    private Pistola pistola;
    private Espada espada;
    private BolaFuego bola;
    public EstadoAventura estado_aventura;

    public static int mapa_contador = 0;

    int[] vista = {0, 0, 1, 0};

    int[] contadores = {0, 0, 0, 0}; // para mostrar cierto sprite de animacion
    boolean[] esperando_hilo = {false, false, false, false}; //cada intercambio de animacion tiene un retraso
    int delay = 400; // este es el retraso de la animacion
    int delayPistola = 2;

    public static int karma_malo = 0;
    public static int karma_bueno = 0;

    private String descicionPistola = "normal";

    public Jugador(Lienzo lienzo) {
        super("/imagenes/hojasPersonajes/aventurero.png", 32, 64, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, Objeto.Tag.JUGADOR);

        this.vida_actual = (this.vida_maxima = 200);

        this.exp_maxima = 100;
        mana_actual = (mana_maximo = 300);
        cuenta = new Cuenta(1000);
        interfaz = new HUDJugador(this);
        this.damage = 20;
        this.damagePistola = 20;
        this.reg_vida = 3;
        this.reg_mana = 2;
        this.reg_resistencia = 20;
        this.resistencia_actual = (resistencia_maxima = 300);
        nivel = 1;
        pistola = new Pistola(10);
        this.espada = new Espada(new Rectangle(GestorPrincipal.CENTROX, GestorPrincipal.CENTROY + 70, 32, 25), "arma_espada", Objeto.Tag.ARMA_JUGADOR, this);

        this.nombre = "JUGADOR";
        iniciarThreadsPermanentes();
        
        
    }

    public Jugador(int x, int y, int vida_actual, BufferedImage sprite_actual) {
        //este no inicia los treas, es solo usado por el hilo de animacion y tiempo //SOLO DEBE SER USADO POR EL HILO
        super(vida_actual);
        this.x = x;
        this.y = y;

        this.mana_maximo = 100;
        this.sprite_actual = sprite_actual;
    }

    private void iniciarThreadsPermanentes() {
        //Hilos para controlar: el poder del tiempo y la regeneracion de vida/mana
        new Thread(new HiloPosicionesTiempo(this, 6)).start();
        new Thread(new HiloJugadorRegeneracion(this)).start();
    }

    @Override
    public void actualizar(Lienzo lienzo) {
        this.mover(lienzo);
        this.acciones(lienzo);
        if (descicionPistola.equals("potente")) {
            damagePistola = 45;
        }
        if (descicionPistola.equals("rapida")) {
            damagePistola = 7;
        }
        if (this.nueva_decision) {
            decision.actualizar(lienzo);
        }
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(sprite_actual, GestorPrincipal.CENTROX, GestorPrincipal.CENTROY, null);  // personaje
        g.setColor(Color.red);

        if (Animacion.esta_activa) {
            g.drawImage(Animacion.imagen_actual, Animacion.x, Animacion.y, null);
        }

        if (pistola.cantidad_balas >= -1) {
            pistola.dibujar(g, this);
        }

        if (BolaFuego.esta_activa) {
            bola.dibujar(g);
        }

        for (int i = 0; i < 4; i++) {
            g.drawRect(this.objeto_ente.getRectangle()[i].x, this.objeto_ente.getRectangle()[i].y,
                    this.objeto_ente.getRectangle()[i].width, this.objeto_ente.getRectangle()[i].height);
        }
        interfaz.dibujar(g);  //estadisticas
        //DEBUG
        g.setColor(Color.MAGENTA);
        Rectangle esp = this.espada.getRectangle()[0];
        g.drawRect(esp.x, esp.y, esp.width, esp.height);

        if (this.nueva_decision) {
            decision.dibujar(g);
        }
    }

    @Override
    public void mover(Lienzo lienzo) {

        ArrayList<Objeto> info_objetos_colisionados = new ArrayList<>();
        String[] direccion = new String[]{"none", "none", "none", "none"};

        if (mapa.objetos != null) {
            for (int i = 0; i < mapa.objetos.size(); i++) {
                Colision.obtenerInfoColisionJugador(this, mapa.objetos.get(i), direccion, info_objetos_colisionados);
            }
        }

        if (EstadoAventura.mapa_actual.enemigos != null) {
            for (int i = 0; i < EstadoAventura.mapa_actual.enemigos.size(); i++) {
                Colision.obtenerInfoColisionJugador(this, EstadoAventura.mapa_actual.enemigos.get(i).objeto_ente, direccion, info_objetos_colisionados);
            }
        }

        if (lienzo.getTeclado().arriba) {
            if (!(direccion[0].compareToIgnoreCase("entorno_arriba") == 0)
                    && !(direccion[0].compareToIgnoreCase("npc_arriba") == 0)) {

                sprite_actual = this.hoja_completa.obtenerSprite(contadores[0], 0).obtenerImagen();
                if (!esperando_hilo[0]) {
                    animacionCaminarThread(5, delay / this.velocidad - this.velocidad, 0); //cero es arriba, se maneja como manecillas de reloj
                }

                this.espada.setRectangle(new Rectangle[]{new Rectangle(GestorPrincipal.CENTROX, GestorPrincipal.CENTROY - 10, 32, 25)});
                y = y - velocidad;

                vista = new int[]{1, 0, 0, 0};
            }
        }
        if (lienzo.getTeclado().derecha) {
            if (!(direccion[1].compareToIgnoreCase("entorno_derecha") == 0)
                    && !(direccion[0].compareToIgnoreCase("npc_derecha") == 0)) {

                sprite_actual = this.hoja_completa.obtenerSprite(contadores[1], 2).obtenerImagen();
                if (!esperando_hilo[1]) {
                    animacionCaminarThread(8, delay / this.velocidad - this.velocidad * 10, 1); //cero es arriba, se maneja como manecillas de reloj
                }
                this.espada.setRectangle(new Rectangle[]{new Rectangle(GestorPrincipal.CENTROX + 30, GestorPrincipal.CENTROY + 10, 25, 32)});

                x = x + velocidad;
                vista = new int[]{0, 1, 0, 0};
            }
        }
        if (lienzo.getTeclado().abajo) {
            if (!(direccion[2].compareToIgnoreCase("entorno_abajo") == 0)
                    && !(direccion[0].compareToIgnoreCase("npc_abajo") == 0)) {
                sprite_actual = this.hoja_completa.obtenerSprite(contadores[2], 1).obtenerImagen();
                if (!esperando_hilo[2]) {
                    animacionCaminarThread(5, delay / this.velocidad - this.velocidad * 10, 2); //cero es arriba, se maneja como manecillas de reloj
                }
                this.espada.setRectangle(new Rectangle[]{new Rectangle(GestorPrincipal.CENTROX, GestorPrincipal.CENTROY + 70, 32, 25)});
                y = y + velocidad;
                vista = new int[]{0, 0, 1, 0};
            }
        }

        if (lienzo.getTeclado().izquierda) {
            if (!(direccion[3].compareToIgnoreCase("entorno_izquierda") == 0)
                    && !(direccion[0].compareToIgnoreCase("npc_izquierda") == 0)) {

                sprite_actual = this.hoja_completa.obtenerSprite(contadores[3], 3).obtenerImagen();
                if (!esperando_hilo[3]) {
                    animacionCaminarThread(8, delay / this.velocidad - this.velocidad * 10, 3); //cero es arriba, se maneja como manecillas de reloj
                }
                this.espada.setRectangle(new Rectangle[]{new Rectangle(GestorPrincipal.CENTROX - 16, GestorPrincipal.CENTROY + 10, 25, 32)});

                x = x - velocidad;
                vista = new int[]{0, 0, 0, 1};
            }
        }

        for (int i = 0; i < info_objetos_colisionados.size(); i++) {
            this.comprobarColisiones(info_objetos_colisionados.get(i));
        }

        if (lienzo.getTeclado().correr && this.resistencia_actual > 0 && !esta_cansado) {

            this.velocidad = this.velocidad_original * 2;
            this.quitarResistencia(1);

        } else {
            this.velocidad = this.velocidad_original;
        }

        if (this.resistencia_actual == 0 && !this.esta_cansado) {
            this.esta_cansado = true;
            Runnable hilo_cansancio = new Runnable() {
                @Override
                public void run() {
                    int tiempo = 10;
                    contador_cansado = tiempo;
                    try {
                        for (int i = 1; i <= tiempo; i++) {
                            Thread.sleep(1000);
                            contador_cansado--;
                        }
                        esta_cansado = false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            new Thread(hilo_cansancio).start();
        }
    }

    int sentido = 1;

    public void animacionCaminarThread(int maximo, int delay, int direccion) {

        this.esperando_hilo[direccion] = true;
        Runnable hilo = new Runnable() {
            @Override
            public void run() {

                try {
                    if (direccion == 0 || direccion == 2) {
                        if (contadores[direccion] > 0) {
                            if (contadores[direccion] >= maximo - 1) {
                                sentido = -1;
                            }
                        } else {
                            sentido = 1;
                        }

                        contadores[direccion] += sentido;
                        Thread.sleep(delay);
                        esperando_hilo[direccion] = false;
                    } else {
                        if (contadores[direccion] >= maximo - 1) {
                            contadores[direccion] = 0;
                        } else {
                            contadores[direccion]++;
                        }
                        Thread.sleep(delay);
                        esperando_hilo[direccion] = false;
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        new Thread(hilo).start();

    }

    public static boolean nueva_decision = false;
    Decision decision = new Decision("Ignorar", "Salvar");
    private boolean esta_recargando = false;

    public void acciones(Lienzo lienzo) {

        if (lienzo.getTeclado().poder_tiempo) {
            Teclado.teclas[KeyEvent.VK_T] = false;
            if (mana_actual >= 50) {
                
                if(EstadoAventura.mapa_actual.getNombre().equals("Casa Inversiones") && EstadoTienda.salioDelINN == false){
                    EstadoTienda.primeraVez = true;
                    descicionPistola = "normal";
                    pistola.tamaño_cartucho = 10;
                    damagePistola = 20;
                }
                
                mana_actual -= 50;
                Jugador[] estados = HiloPosicionesTiempo.cola.obtenerEstadosJugador();
                
                
                Sonido.EFECTO_VIAJE_TIEMPO.reproducir();
                new Thread(new HiloAnimacionTiempo(this, estados)).start();
                Animacion.esta_activa = true;
                Animacion.imagen_actual = Animacion.animacion_tiempo.obtenerSprite(0, 0).obtenerImagen();
                Animacion.x = GestorPrincipal.CENTROX;
                Animacion.y = GestorPrincipal.CENTROY;

                if (this.mapa.ultimo_dialogo.compareToIgnoreCase("terra") == 0) {
                    Mapa.terra.evento_ocurrido = false;
                    this.mapa.dialogo.aux = 0;
                    Decision.decisiones.remove(Decision.decisiones.size() - 1);
                    this.mapa.once_terra = true;
                    System.out.println("Terra REINICIADA");
                } else if (this.mapa.ultimo_dialogo.compareToIgnoreCase("rosa") == 0) {
                    Mapa.rosa.evento_ocurrido = false;
                    this.mapa.dialogo.aux = 0;
                    Decision.decisiones.remove(Decision.decisiones.size() - 1);
                    this.mapa.once_rosa = true;
                    System.out.println("rosa REINICIADA");
                } else if (this.mapa.ultimo_dialogo.compareToIgnoreCase("helena") == 0) {
                    Mapa.helena.evento_ocurrido = false;
                    this.mapa.dialogo.aux = 0;
                    Decision.decisiones.remove(Decision.decisiones.size() - 1);
                    this.mapa.once_helena = true;
                    System.out.println("helena REINICIADA");
                }
            }

        }

        if (lienzo.getTeclado().poderBola) {
            Teclado.teclas[KeyEvent.VK_1] = false;

            if (mana_actual >= 50 && this.resistencia_actual >= 10 && !this.esta_cansado && !BolaFuego.esta_activa) {

                try {
                    mana_actual -= 50;

                    this.quitarResistencia(10);
                    Thread.sleep(100);
                    bola = new BolaFuego(this, this.x, this.y);
                    Sonido.BOLA_DE_FUEGO.reproducir();
                    if (this.vista[2] == 1) {
                        new Thread(new HiloBola(this, "abajo", bola)).start();
                    } else if (this.vista[0] == 1) {
                        new Thread(new HiloBola(this, "arriba", bola)).start();
                    } else if (this.vista[1] == 1) {
                        new Thread(new HiloBola(this, "derecha", bola)).start();
                    } else if (this.vista[3] == 1) {
                        new Thread(new HiloBola(this, "izquierda", bola)).start();
                    }
                    BolaFuego.esta_activa = true;

                } catch (InterruptedException ex) {
                    Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        if (lienzo.getTeclado().recargar_arma) {
            lienzo.getTeclado().recargar_arma = false;

            if (!esta_recargando) {
                if (descicionPistola.equals("normal")) {
                    delayPistola = 2;
                    Sonido.RECARGAR_ARMA_NORMAL.reproducir();
                } else if (descicionPistola.equals("potente")) {
                    delayPistola = 4;
                    Sonido.RECARGAR_ARMA_LENTO.reproducir();
                } else if (descicionPistola.equals("rapida")) {
                    delayPistola = 1;
                    Sonido.RECARGAR_ARMA_RAPIDO.reproducir();
                }

                esta_recargando = true;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            for (int segundo = 0; segundo < delayPistola; segundo++) {
                                Thread.sleep(1000);
                            }
                            pistola.recargar();
                            esta_recargando = false;

                        } catch (Exception e) {
                            System.out.println("Error en la recarga de pistola");
                        }

                    }
                }).start();
                interfaz = new HUDJugador(this);
            }

        }

        if (lienzo.getTeclado().pantalla_completa) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            GestorPrincipal.ANCHO = screenSize.width;
            GestorPrincipal.ALTO = screenSize.height;

        }

        if (lienzo.getTeclado().disparar_arma) {

            Teclado.teclas[KeyEvent.VK_E] = false;

            if (!esta_recargando) {
                if (pistola.cantidad_balas > 0) {
                    pistola.cantidad_balas--;
                    Sonido.DISPARO.reproducir();

                        try {
                            Thread.sleep(20);
                            pistola.disparar(vista, this);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                        }


                }

            }
        }

        if (lienzo.getTeclado().ataque_espada && this.resistencia_actual >= 50 && !this.esta_cansado) {

            try {
                this.quitarResistencia(50);
                Thread.sleep(30);

                Animacion.esta_activa = true;

                if (vista[2] == 1) {

                    Animacion.imagen_actual = Animacion.animacion_espada_frente.obtenerSprite(0, 0).obtenerImagen();
                    Animacion.x = GestorPrincipal.CENTROX;
                    Animacion.y = GestorPrincipal.CENTROY + 48;
                    Animacion.mostrarAnimacion(Animacion.animacion_espada_frente, 10);

                } else if (vista[0] == 1) {

                    Animacion.imagen_actual = Animacion.animacion_espada_espalda.obtenerSprite(0, 0).obtenerImagen();
                    Animacion.x = GestorPrincipal.CENTROX;
                    Animacion.y = GestorPrincipal.CENTROY - 24;
                    Animacion.mostrarAnimacion(Animacion.animacion_espada_espalda, 10);

                } else if (vista[1] == 1) {

                    Animacion.imagen_actual = Animacion.animacion_espada_derecha.obtenerSprite(0, 0).obtenerImagen();
                    Animacion.x = GestorPrincipal.CENTROX + 25;
                    Animacion.y = GestorPrincipal.CENTROY;
                    Animacion.mostrarAnimacion(Animacion.animacion_espada_derecha, 10);

                } else if (vista[3] == 1) {

                    Animacion.imagen_actual = Animacion.animacion_espada_izquierda.obtenerSprite(0, 0).obtenerImagen();
                    Animacion.x = GestorPrincipal.CENTROX - 24;
                    Animacion.y = GestorPrincipal.CENTROY;
                    Animacion.mostrarAnimacion(Animacion.animacion_espada_izquierda, 10);
                }

                this.espada.actualizar();
                Teclado.teclas[KeyEvent.VK_2] = false;
            } catch (InterruptedException ex) {
                Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void comprobarColisiones(Objeto col) {
        if (col != null) {
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.ENEMIGO) == 0) {
                this.quitarVida(1);
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.ABSORCION_MANA) == 0) {

                if (this.getMana_actual() >= 2) {
                    this.quitarMana(2);
                }
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.TELEPORT) == 0) {

                if (this.mapa == EstadoAventura.mapas[0]) {
                    if (col.getId().compareToIgnoreCase("Casa INN") == 0) {
                        EstadoAventura.mapa_actual = EstadoAventura.mapas[3];
                    } else if (col.getId().compareToIgnoreCase("Puerta Zelda") == 0) {
                        EstadoAventura.mapa_actual = EstadoAventura.mapas[2];
                    } else if (col.getId().compareToIgnoreCase("Estatua izquierda") == 0) {
                        EstadoAventura.mapa_actual = EstadoAventura.mapas[1];
                    }
                } else if (this.mapa == EstadoAventura.mapas[1]) {
                    if (col.getId().compareToIgnoreCase("teleport_ciudad") == 0) {
                        EstadoAventura.mapa_actual = EstadoAventura.mapas[0];
                    }
                } else if (this.mapa == EstadoAventura.mapas[2]) {
                    if (col.getId().compareToIgnoreCase("teleport_ciudad") == 0) {
                        EstadoAventura.mapa_actual = EstadoAventura.mapas[0];
                    }
                } else if (this.mapa == EstadoAventura.mapas[3]) {
                    EstadoTienda.salioDelINN = true;
                    EstadoAventura.mapa_actual = EstadoAventura.mapas[0];
                }

                this.setMapa(EstadoAventura.mapa_actual);
                EstadoAventura.mapa_actual.musica();
                EstadoAventura.mapa_actual.iniciarEnemigos(3);
                

                this.x = -1 * this.mapa.iniciox;
                this.y = -1 * this.mapa.inicioy;

            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.INVERSION) == 0) {
                this.y += 5;
                GestorEstado.cambiarEstado(2);
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.TIENDA) == 0) {
                if (EstadoTienda.primeraVez) {
                    Dialogo.activado = true;
                }
                this.y += 5;
                GestorEstado.cambiarEstado(3);
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.TICTACTOE) == 0) {

                GestorEstado.cambiarEstado(1);
                Sonido.cambioMusica(Sonido.MUSICA_MENU);
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.SUBIDA_EXP) == 0) {
                try {
                    this.ganarExperiencia(this.nivel); //para que suba la exp mas rapido conforme avance
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.MONEDA) == 0) {
                this.cuenta.agregarDinero(100);
                this.mapa.monedas.remove((Moneda) col);
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.COMIDA) == 0) {
                this.regenerarVida(20);
                this.mapa.comidas.remove((Comida) col);
            }
            if (col.getTag().compareToIgnoreCase(Objeto.Tag.NPC) == 0) {

                boolean bandera = false;
                if (col.getId().compareToIgnoreCase("terra") == 0) {
                    if (!Mapa.terra.evento_ocurrido) {

                        this.mapa.dialogo.aux = 0;
                        Mapa.terra.evento_ocurrido = true;

                        bandera = !bandera;
                    }
                } else if (col.getId().compareToIgnoreCase("rosa") == 0) {
                    if (!Mapa.rosa.evento_ocurrido) {
                        this.mapa.dialogo.aux = 0;
                        Mapa.rosa.evento_ocurrido = true;
                        bandera = !bandera;
                    }
                } else if (col.getId().compareToIgnoreCase("helena") == 0) {
                    if (!Mapa.helena.evento_ocurrido) {
                        this.mapa.dialogo.aux = 0;
                        Mapa.helena.evento_ocurrido = true;
                        bandera = !bandera;
                    }
                }

                if (!Dialogo.activado && bandera) {
                    Dialogo.activado = true;
                }

            }

        }
    }

    public void regenerarMana(int cantidad) {
        if (this.mana_actual + cantidad <= this.mana_maximo) {
            this.mana_actual += cantidad;
        } else {
            this.mana_actual = this.mana_maximo;
        }
    }

    public void regeneraResistencia(int cantidad) {
        if (this.resistencia_actual + cantidad <= this.resistencia_maxima) {
            this.resistencia_actual += cantidad;
        } else {
            this.resistencia_actual = this.resistencia_maxima;
        }
    }

    public void quitarMana(int cantidad) {
        if (this.mana_actual >= cantidad) {
            this.mana_actual -= cantidad;
        } else {
            this.mana_actual = 0;
        }
    }

    public void quitarResistencia(int cantidad) {
        if (this.resistencia_actual >= cantidad) {
            this.resistencia_actual -= cantidad;
        } else {
            this.resistencia_actual = 0;
        }
    }

    public void ganarExperiencia(int exp) {
        int exp_restante = this.exp_maxima - this.exp_actual;
        if (exp <= exp_restante) {
            this.exp_actual += exp;
        } else {
            this.nivel++;
            int aumento = nivel * 10;
            this.vida_maxima += aumento;
            this.mana_maximo += aumento;
            this.resistencia_maxima += aumento / 2;
            this.exp_maxima += aumento;
            this.exp_actual = exp - exp_restante;
            this.reg_vida += 2;
            this.reg_mana += 2;
            this.reg_resistencia += 2;
        }
    }

    public void nuevoEstado(Jugador estado) {
        this.x = estado.x;
        this.y = estado.y;
        this.vida_actual = estado.vida_actual;
        this.sprite_actual = estado.sprite_actual;
    }

    //GETTERS SETERS
    public int getNivel() {
        return nivel;
    }

    public int getMana_maximo() {
        return mana_maximo;
    }

    public int getMana_actual() {
        return mana_actual;
    }

    public int getExp_maxima() {
        return exp_maxima;
    }

    public int getExp_actual() {
        return exp_actual;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public Pistola getPistola() {
        return pistola;
    }

    public int getReg_vida() {
        return reg_vida;
    }

    public int getReg_mana() {
        return reg_mana;
    }

    public int getResistencia_maxima() {
        return resistencia_maxima;
    }

    public int getResistencia_actual() {
        return resistencia_actual;
    }

    public int getReg_resistencia() {
        return reg_resistencia;
    }

    public int getContador_cansado() {
        return contador_cansado;
    }

    public int getDamage() {
        return damage;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public String getDescicionPistola() {
        return descicionPistola;
    }

    public void setDescicionPistola(String descicionPistola) {
        this.descicionPistola = descicionPistola;
    }

    public int getDamagePistola() {
        return damagePistola;
    }

    public void setDamagePistola(int damagePistola) {
        this.damagePistola = damagePistola;
    }

}
