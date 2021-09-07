package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Color;
import java.awt.Image;

public class Interfaz {
	private int x;
	private int y;
	private Color color;
	
	
	public void dibujarPuntaje(Entorno e, int entregas) {  // DIBUJA EL TABLERO DE PUNTAJE
		e.dibujarRectangulo(725, 0, 168, 183, 0, Color.pink);
        e.dibujarRectangulo(725, 0, 158, 173, 0, Color.DARK_GRAY);
        e.cambiarFont(null, 25, Color.pink);
        e.escribirTexto("Score: "+ entregas*5+"/50", 655, 30);
    }
	
	public void dibujarCarga(Entorno e) {  // DIBUJA LA CARGA DEL RASENGAN
		  e.dibujarRectangulo(722, 65, 140, 30, 0, Color.pink);
	      e.dibujarRectangulo(722, 65, Temporizador.tiempo[0]/3+20, 20, 0, Color.CYAN);
	      e.cambiarFont(null, 18, color.BLACK);
	      e.escribirTexto(Temporizador.tiempo[0]/3+"%", 705, 72); 
	}
	
	public void dibujarAsesinatos(Entorno e, int asesinatos) { // DIBUJA EL TABLERO DE ASESINATOS
		x = 40;
		y = 25;
		
		// TABLERO
		e.dibujarRectangulo(0, 0, 265, 110, 0, Color.pink);
		e.dibujarRectangulo(0, 0, 255, 100, 0, Color.DARK_GRAY);
		e.cambiarFont(null, 30, Color.pink);
		e.escribirTexto("" + asesinatos, 70, 35);
		
		// NINJA
		e.dibujarRectangulo(x+3, y-12, 27+10, 5+3, 2.5, Color.red);
        e.dibujarRectangulo(x, y-7, 27+10, 5+3, 1, Color.red);
        e.dibujarCirculo(x, y, 30+10, Color.BLACK);
        e.dibujarCirculo(x-5, y-2, 5+10, Color.white);
        e.dibujarCirculo(x+5, y-2, 5+10, Color.white);
        e.dibujarRectangulo(x, y-5, 27+10, 5+3, 0, Color.red);
        
        // CRUZ
        e.dibujarRectangulo(x, y, 6, 50, 0.7, color.pink);
        e.dibujarRectangulo(x, y, 6, 50, 2.4, color.pink);
        
	}
	
}
