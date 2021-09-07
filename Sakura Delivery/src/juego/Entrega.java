package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

public class Entrega {
	private int x;
	private int y;
	private double radio;
	private int viajes;
	
	Image flecha;
	Image flores;
	Image ciudad;
	
	
	public Entrega(int x, int y, double radio) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		
		flecha = Herramientas.cargarImagen("flecha.png");
		flores = Herramientas.cargarImagen("flores.png");
		ciudad = Herramientas.cargarImagen("ciudad.png");
	}
	
	public void dibujarCiudad(Entorno e) {
		e.dibujarImagen(ciudad, 400, 300, 0, 1);
	}
	
	
	public void dibujarFlecha(Entorno e, int viajes) {
		if (viajes%2!=0)
			e.dibujarImagen(flecha, this.x, this.y-40, 0, 0.125);
	}
	

	public void dibujarFlores(Entorno e, double x, double y, int n, int viajes) {
		if (viajes%2!=0) {
			e.dibujarImagen(flores, x-n, y, 0, 0.0125);
		}
		else {
			e.dibujarImagen(flores, 385, 345, 0, 0.0125);
		}
	}
	
	
	public void coordenadaEntrega(Entorno e) {
		if (viajes%2!=0) {  // Define que una entrega sea en floreria y otra en un lugar aleatorio, para que busque las flores
			this.x = 385;
			this.y = 345;
		} else {
			// xc e yc son listas paralelas de las coordenadas de las casas
			int i = ((int) (Math.random()*26+0));
			int[] xc = {410,670,100,520,770,265,670,180,585,100,520,85,360,515,110,510,185,30,685,30,580,580,670,430,270,185,185};
			int[] yc = {505,310,320,70,180,430,505,505,570,420,70,500,190,190,190,420,500,185,420,385,385,510,510,75,75,270,180};
			this.x = xc[i];  //xc[i];
			this.y = yc[i];  //yc[i];
		}
		
		viajes++;
	}
	
	
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getRadio() {
		return radio;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	
	
	
}