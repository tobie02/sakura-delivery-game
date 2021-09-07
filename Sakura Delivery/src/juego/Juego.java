package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.awt.Color;
import java.awt.Image;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Sakura sakura;
	private Calles caminos;
	private Interfaz interfaz;
	private Ransengan r;
	private NinjasAlAtaque enemigos;
	private Entrega entrega;
	private boolean seMueve;
	private int viajes;
	private int entregas;
	private boolean disparo;
	private boolean ataque;
	private boolean victoria;
	private int asesino;
	private int animacion;
	private int a;
	Image sakuradead;
	Image sakuraUP1;
	
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - V0.01", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.sakura = new Sakura(385, 345, 30, 2, Color.pink);
		this.caminos = new Calles(entorno);
		this.enemigos = new NinjasAlAtaque(entorno);
		this.entrega = new Entrega(385, 345, 35);
		this.interfaz = new Interfaz();
		sakuradead = Herramientas.cargarImagen("sakuradead.png");
		sakuraUP1 = Herramientas.cargarImagen("sakuraUP1.png");
		disparo = false;
		ataque = false;
		victoria = false;
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		caminos.dibujar(entorno);
		entrega.dibujarCiudad(entorno);
		
		if(sakura!=null)
			enemigos.invasion(entorno, r);
		
		if(Temporizador.tiempo[5] <= 30) {
			entrega.dibujarFlecha(entorno, viajes);
			}
		else if (Temporizador.tiempo[5] == 70) {
			Temporizador.tiempo[5] = 0;
			}
		
		
		interfaz.dibujarPuntaje(entorno, entregas);
		interfaz.dibujarAsesinatos(entorno, asesino);
		interfaz.dibujarCarga(entorno);
		
		
		if(entregas*5 == 50) {  // CONFIGURACION VICTORIA
			victoria = true;
		}
		
		
		
		//MOVIMIENTO DE SAKURA
	
		if(sakura != null) {
			Temporizador.tempo();
			if(!seMueve) 
				sakura.dibujarSakuraNormal(entorno);
			
			
			entrega.dibujarFlores(entorno, sakura.getX(), sakura.getY(), 0, viajes);
			
			if (Temporizador.tiempo[6] <= 15) {
				animacion = 1;
			} else if (Temporizador.tiempo[6] > 15 && Temporizador.tiempo[6] < 30) {
				animacion = 2;
			} else if (Temporizador.tiempo[6] == 30) {
				Temporizador.tiempo[6] = 0;
				animacion = 1;
			}
			
			//DERECHA
			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				sakura.dibujarSakuraDerecha(entorno, animacion);
				entrega.dibujarFlores(entorno, sakura.getX(), sakura.getY(), 10, viajes);
				sakura.moverDerecha(entorno);
				seMueve = true;
			}
			
			//IZQUIERDA
			else if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				sakura.dibujarSakuraIzquierda(entorno, animacion);
				entrega.dibujarFlores(entorno, sakura.getX(), sakura.getY(), -10, viajes);
				sakura.moverIzquierda(entorno);
				seMueve = true;
			} else {
				seMueve = false;
			}
			
			//ARRIBA
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				if(!entorno.estaPresionada(entorno.TECLA_DERECHA) && !entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //ARREGLA UN BUG DE ANIMACION
					sakura.dibujarSakuraArriba(entorno, animacion);
					entrega.dibujarFlores(entorno, sakura.getX(), sakura.getY(), 0, viajes);
				}
				sakura.moverArriba(entorno);
				seMueve = true;
			}
			
			//ABAJO
			else if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				if(!entorno.estaPresionada(entorno.TECLA_DERECHA) && !entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //ARREGLA UN BUG DE ANIMACION
					sakura.dibujarSakuraArriba(entorno, animacion);
					entrega.dibujarFlores(entorno, sakura.getX(), sakura.getY(), 0, viajes);
				}
				sakura.moverAbajo(entorno);
				seMueve = true;
			}
			
			
			
			
			
			//Cambiar de caminar a correr
			if (entorno.estaPresionada(entorno.TECLA_SHIFT)) { // && Temporizador.tiempo[2] == 300
                Temporizador.tiempo[2] = 300;
                Temporizador.tiempo[3] = 0;
                sakura.correr();
            } else { //if (Temporizador.tiempo[3] == 300)
                sakura.caminar();
            }
			
			
			//Verifico si Sakura colisiona con entrega
			if (sakura.colision(entrega.getX(), entrega.getY(), entrega.getRadio())) {
				entrega.coordenadaEntrega(entorno);
				viajes++;
				if (viajes%2==0 && viajes!=0) {
					entregas++;
				}
			}
			
			
			//Secuencia de disparo, se puede dispara cada 7 segundos
			if(entorno.sePresiono(entorno.TECLA_ESPACIO) && Temporizador.tiempo[0] == 300) {
				disparo = true;
				ataque = true;
			} 
			
			if(disparo == true) {
				if(r == null) {
					r = new Ransengan(sakura.getX(), sakura.getY(), 20, 0);
				}
				if(ataque) {
					if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
						r.setDireccion(1);
						ataque = false;
						
					}else
					if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
						r.setDireccion(2);
						ataque = false;
					}else
					if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
						r.setDireccion(3);
						ataque = false;
					}else
					if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
						r.setDireccion(4);
						ataque = false;
					}
				}
				
				r.dibujar(entorno, r.getDireccion(), animacion); // DIBUJA EL RASENGAN
				
				
				// ELIMINA EL RASENGAN SI SE VA DE LA PANTALLA
				if(r.getX() < 0 || r.getX() > entorno.ancho() || r.getY() < 0 || r.getY() > entorno.alto()) {
					r = null;
					disparo = false;
                    Temporizador.tiempo[0] = 0;
				}
				
				
				// COLISION RASENGAN CON NINJAS
				for(int i=0; i<=5; i++) {
					if(r != null && enemigos.milicia[i] != null && enemigos.milicia[i].colision(r.getX(), r.getY(), r.getDiametro())) {
						r.dibujarImpacto(entorno);
						enemigos.milicia[i] = null;
						r = null;
						disparo = false;
						Temporizador.tiempo[0] = 0;
						asesino ++;
					}
				}
			}
			
			
			// COLISION SAKURA CON NINJAS
			for (int i = 0; i <= 5; i++) {
				if(enemigos.milicia[i] != null && sakura != null) {
					if(sakura.colision(enemigos.milicia[i].getX(), enemigos.milicia[i].getY(), enemigos.milicia[i].getDiametro()/2)) {
						sakura = null;
					}
				}
			}
			
			
			
			 // PANTALLA DE DERROTA
		} if(sakura == null && !victoria) {
			entorno.dibujarCirculo(400, 300, 570, Color.pink);
			entorno.dibujarCirculo(400, 300, 550, Color.BLACK);
			entorno.cambiarFont(null, 82, Color.RED);
			entorno.escribirTexto("GAME OVER", 150, 280);
			entorno.cambiarFont(null, 50, Color.RED);
			entorno.escribirTexto("Score:"+entregas*5, 150, 330);
			entorno.dibujarImagen(sakuradead, 400, 380, 0, 3);
			entorno.cambiarFont(null, 25, Color.LIGHT_GRAY);
			entorno.escribirTexto("Pulse ENTER para continuar", 250, 465);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				disparo = false;
				ataque = false;
				r = null;
				entregas = 0;
				asesino = 0;
				this.enemigos = new NinjasAlAtaque(entorno);
				sakura = new Sakura(385, 345, 30, 2, Color.pink);
				Temporizador.tiempo[0] = 300;
                Temporizador.tiempo[1] = 0;
                Temporizador.tiempo[2] = 0;
                Temporizador.tiempo[3] = 0;
			}
			
			// PANTALLA DE VICTORIA
			
		} else if (sakura == null && victoria) {
			entorno.dibujarCirculo(400, 300, 570, Color.pink);
			entorno.dibujarCirculo(400, 300, 550, Color.BLACK);
			entorno.cambiarFont(null, 82, Color.GREEN);
			entorno.escribirTexto("VICTORIA", 200, 220);
			entorno.cambiarFont(null, 50, Color.RED);
			entorno.escribirTexto("Score:"+entregas*5, 150, 280);
			entorno.dibujarImagen(sakuraUP1, 400, 360, 0, 3);
			entorno.cambiarFont(null, 25, Color.LIGHT_GRAY);
			entorno.escribirTexto("Pulse ENTER para jugar de nuevo", 210, 460);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				disparo = false;
				ataque = false;
				r = null;
				entregas = 0;
				asesino = 0;
				this.enemigos = new NinjasAlAtaque(entorno);
				sakura = new Sakura(385, 345, 30, 2, Color.pink);
				victoria = false;
				Temporizador.tiempo[0] = 300;
                Temporizador.tiempo[1] = 0;
                Temporizador.tiempo[2] = 0;
                Temporizador.tiempo[3] = 0;
			}
		}
		
		//a = Temporizador.mostrarSegundos(entorno, a);
		
	}
		
		
		
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
