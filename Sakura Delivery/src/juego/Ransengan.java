package juego;

import entorno.Entorno;
import java.awt.Color;

public class Ransengan {

	private double x; //valor de izquierda a derecha en el cual se encuentra el ransengan
	private double y; //valor de arriba a abajo en el cual se encuentra el ransengan
	private double diametro; //valor que establece el l�mite del ransengan
	private int direccion;  //del 1 al 4: 1 = abajo  2 = izquierda  3 = arriba  4 = derecha
	
	public Ransengan(double x, double y, double diametro, int direccion) {
		//crea un ransengan en la ubicaci�n (X,Y) con la direcci�n elegida
		this.x = x;
		this.y = y;
		this.diametro = diametro;
		try {
			setDireccion(direccion);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void dibujar(Entorno e, int direccion, int animacion) { //dibuja el ransengan y lo redibuja constantemente mientras se mueve
		if (direccion==4) {
			if (this.y==105 || this.y==225 || this.y==345 || this.y==465) {
				x = x + 10;
			} else {
				dibujarImpacto(e);
				x = x + 800;
			}
		}
		else if (direccion==2) { 
			if (this.y==105 || this.y==225 || this.y==345 || this.y==465) {
				x = x - 10;
			} else {
				dibujarImpacto(e);
				x = x + 800;
			}
		}
		else if (direccion==1) {
			if (this.x==145 || this.x==305 || this.x==465 || this.x==625) {
				y = y + 10;
			} else {
				dibujarImpacto(e);
				x = x + 800;
			}
		}
		else if (direccion==3) {
			if (this.x==145 || this.x==305 || this.x==465 || this.x==625) {
				y = y - 10;
			} else {
				dibujarImpacto(e);
				x = x + 800;
			}
		}
		if (animacion == 1) { // animaci�n del ransengan con brillitos
			e.dibujarCirculo(x, y, diametro, Color.CYAN);
			e.dibujarRectangulo(x, y, 3, 30, 2, Color.CYAN);
			e.dibujarRectangulo(x, y, 3, 25, 3, Color.CYAN);
			e.dibujarRectangulo(x, y, 4, 25, 1, Color.white);
			e.dibujarCirculo(x, y, diametro-10, Color.WHITE);
		} else {
			e.dibujarCirculo(x, y, diametro, Color.CYAN);
			e.dibujarRectangulo(x, y, 3, 30, 1, Color.WHITE);
			e.dibujarRectangulo(x, y, 3, 25, 5, Color.CYAN);
			e.dibujarRectangulo(x, y, 3, 25, 6, Color.WHITE);
			e.dibujarCirculo(x, y, diametro-5, Color.WHITE);
		}
	}
	
	public void dibujarImpacto(Entorno e) { // dibuja el impacto con el ninja
		e.dibujarCirculo(x, y, diametro+25, Color.CYAN);
		e.dibujarRectangulo(x, y, 3, 30, 2, Color.CYAN);
		e.dibujarRectangulo(x, y, 3, 25, 3, Color.CYAN);
		e.dibujarRectangulo(x, y, 4, 25, 1, Color.white);
		e.dibujarCirculo(x, y, diametro+15, Color.WHITE);
	}
	
	public int getDireccion() { //devuelve la variable direcci�n
		return direccion;
	}
	
	public void setDireccion(int direccion) { //establece el valor recibido como el valor de la variable direccion
		if(direccion <= 4 || direccion >= 1) {
			this.direccion = direccion;
		}else {
			throw new RuntimeException("Numero no valido");
		}
	}

	public double getX() { //devuelve la variable X
		return x;
	}

	public double getY() { //devuelve la variable Y
		return y;
	}

	public double getDiametro() { //devuelve la variable di�metro
		return diametro;
	}
}
