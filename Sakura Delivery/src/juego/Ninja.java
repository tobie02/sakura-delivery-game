package juego;

import java.awt.Color;
import entorno.Entorno;

public class Ninja {
	
	private double x;
	private double y;
	private double diametro;
	private int velocidad;
	private int calle;      // del 1 al 8
	private int direccion;  // del 1 al 4: 1=norte a sur  2= este a oeste  3= sur a norte  4= oeste a este
	
	public Ninja(double x, double y, double diametro, int velocidad, int calle, int direccion) {
		this.x = x;
		this.y = y;
		this.diametro = diametro;
		this.velocidad = velocidad;
		try {
			setCalle(calle);
			setDireccion(direccion);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void dibujar(Entorno e) {
        e.dibujarRectangulo(x+3, y-12, 27, 5, 2.5, Color.red);
        e.dibujarRectangulo(x, y-7, 27, 5, 1, Color.red);
        e.dibujarCirculo(x, y, diametro, Color.BLACK);
        e.dibujarCirculo(x-5, y-2, 5, Color.white);
        e.dibujarCirculo(x+5, y-2, 5, Color.white);
        e.dibujarRectangulo(x, y-5, 27, 5, 0, Color.red);
    }
	
	public void ataque() {
		if(direccion == 1) {
			y += velocidad;
		}else if(direccion == 2) {
			x -= velocidad;
		}else if(direccion == 3) {
			y -= velocidad;
		}else {
			x += velocidad;
		}
	}
	
	public void volver(Entorno e) {
		if(x < 0 || y < 0 || x > e.ancho() || y > e.alto()) {
			if(direccion == 1 ) {
				y = 0;
			}else if(direccion == 2){
				x = e.ancho();
			}else if(direccion == 3) {
				y = e.alto();
			}else {
				x = 0;
			}
		}
	}
	
	public boolean colision(double ex, double ey, double eradio) {
		if ((this.x+15>ex-eradio && this.x-15<ex+eradio) && (this.y+15>ey-eradio && this.y-15<ey+eradio)) {
				return true;
			}
		return false;
	}
	
	public int getCalle() {
		return calle;
	}
	public void setCalle(int calle) {
		if(calle <= 8 || calle >= 1) {
			this.calle = calle;
		}else {
			throw new RuntimeException("Numero no valido");
		}
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		if(direccion <= 4 || direccion >= 1) {
			this.direccion = direccion;
		}else {
			throw new RuntimeException("Numero no valido");
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDiametro() {
		return diametro;
	}

	public int getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

}
