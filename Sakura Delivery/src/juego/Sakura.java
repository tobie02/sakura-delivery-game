package juego;

import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;
import java.awt.Color;

public class Sakura {
	private int x;
	private int y;
	private double factorMovimiento;
	
	Image sakura;
	Image sakuraRIGHT1;
	Image sakuraRIGHT2;
	Image sakuraLEFT1;
	Image sakuraLEFT2;
	Image sakuraUP1;
	Image sakuraDOWN1;
	
	public Sakura(int x, int y, double radio,  double factorMovimiento, Color color) {
		this.x = x;
		this.y = y;
		this.factorMovimiento = factorMovimiento;
		
		
		// ANIMACIONES DE SAKURA
		sakura = Herramientas.cargarImagen("sakura.png");
		sakuraRIGHT1 = Herramientas.cargarImagen("sakuraRIGHT1.png");
		sakuraRIGHT2 = Herramientas.cargarImagen("sakuraRIGHT2.png");
		sakuraLEFT1 = Herramientas.cargarImagen("sakuraLEFT1.png");
		sakuraLEFT2 = Herramientas.cargarImagen("sakuraLEFT2.png");
		sakuraUP1 = Herramientas.cargarImagen("sakuraUP1.png");
		sakuraDOWN1 = Herramientas.cargarImagen("sakuraDOWN1.png");		
	}
	
	
	//DIBUJAN LAS ANIMACIONES DE SAKURA
	
	public void dibujarSakuraNormal(Entorno e) {
		e.dibujarImagen(sakura, this.x, this.y, 0, 1.1);
	}
	
	public void dibujarSakuraDerecha(Entorno e, int animacion) {
		if (animacion == 1) {
			e.dibujarImagen(sakuraRIGHT1, this.x, this.y, 0, 1.1);
		} else {
			e.dibujarImagen(sakuraRIGHT2, this.x, this.y, 0, 1.1);
		}
			
	}
	
	public void dibujarSakuraIzquierda(Entorno e, int animacion) {
		if (animacion == 1) {
			e.dibujarImagen(sakuraLEFT1, this.x, this.y, 0, 1.1);
		} else {
			e.dibujarImagen(sakuraLEFT2, this.x, this.y, 0, 1.1);
		}
	}
	
	
	public void dibujarSakuraArriba(Entorno e, int animacion) {
		if (animacion == 1) {
			e.dibujarImagen(sakuraDOWN1, this.x, this.y, 0, 1.1);
		} else {
			e.dibujarImagen(sakuraUP1, this.x, this.y, 0, 1.1);
		}
	}
	
	
	
	
	//MOVIMIENTO DE SAKURA
	
	public void moverDerecha(Entorno e) {
		if (this.y==105 || this.y==225 || this.y==345 || this.y==465) { //Limitacion para calles horizontales y entorno
			if (this.x<=785) {
				x += factorMovimiento;
			}
		}
	}
	
	public void moverIzquierda(Entorno e) {
		if (this.y==105 || this.y==225 || this.y==345 || this.y==465) { //Limitacion para calles horizontales y entorno
			if (this.x>=15) {
				x -= factorMovimiento;
			}
		}
	}
	
	public void moverArriba(Entorno e) {
		if (this.x==145 || this.x==305 || this.x==465 || this.x==625) { //Limitacion para calles verticales y entorno
			if (this.y>=15) {
				y -= factorMovimiento;
			}
		}
	}
	
	public void moverAbajo(Entorno e) {
		if (this.x==145 || this.x==305 || this.x==465 || this.x==625) { //Limitacion para calles verticales y entorno
			if (this.y<=585) {
				y += factorMovimiento;
			}
		}
	}
	
	public void correr() {
		this.factorMovimiento = 4;
	}
	
	public void caminar() {
		this.factorMovimiento = 2;
	}
	
	
	// PREGUNTA SI SAKURA ESTA EN COLISION CON ALGO
	
	public boolean colision(double ex, double ey, double eradio) {
		if ((this.x+15>ex-eradio && this.x-15<ex+eradio) && (this.y+15>ey-eradio && this.y-15<ey+eradio)) {
				return true;
			}
		return false;
	}
	

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}

