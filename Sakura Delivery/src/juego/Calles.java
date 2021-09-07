package juego;

import entorno.Entorno;

public class Calles {
	
	Calle[] calles;
	
	public Calles(Entorno e) {
		this.calles = new Calle[8];
		
		int i=0, j=1;
		while(i<8) {
			calles[i] = new Calle(j*(e.ancho()/5)-15, j*(e.alto()/5)-15, e.ancho()*2, 30, i+1);
			calles[i+1] = new Calle(j*(e.ancho()/5)-15, j*(e.alto()/5)-15, 30, e.alto()*2, i+2);
			i += 2;
			j++;
		}
	}
	
	public void dibujar(Entorno e) {
		for (int i=0; i<calles.length; i++) {
			calles[i].dibujar(e);
		}
	}

}
