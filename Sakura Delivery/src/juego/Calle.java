package juego;

import java.awt.Color;
import entorno.Entorno;

public class Calle {

	private double x;
	private double y;
	private int ancho;
	private int alto;
	private int valor;
	//valor es un entero cullo valor va desde 1 al 8
	
	public Calle(int x, int y, int ancho, int alto, int valor) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		try {
			setValor(valor);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.GRAY);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		if(valor >= 1 || valor <= 8) {
			this.valor = valor;
		}else {
			throw new RuntimeException("Ese valor no sirve");
		}
	}
	
}
