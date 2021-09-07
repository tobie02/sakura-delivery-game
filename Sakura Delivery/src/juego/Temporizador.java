package juego;

import java.awt.Color;
import entorno.Entorno;

public class Temporizador {
	
	// 65 TICKS EQUIVALE A 1 SEGUNDO --> 325 TICKS EQUIVALEN A 5 SEGUNDOS

    static int[] tiempo = {300,0,0,0,0,0,0}; // tiempo[0] es el tiempo del ransengan, tiempo [1] es el tiempo de los ninjas,
    //agregar aca los tiempos agregados
    //El tiempo establecido es temporal no se cuantos seg son capaz haya que incrementarlo o disminuirlo

    public static int[] tempo() {
    	if (tiempo[4] < 65) { // SEGUNDOS
    		tiempo[4] += 1;
    	}
    	if (tiempo[6] < 30) { // ANIMACIONES SAKURA 
    		tiempo[6] += 1;
    	}
    	if (tiempo[5] < 70) { // ANIMACION FLECHA
    		tiempo[5] += 1;
    	}
        if(tiempo[1] < 1000) { //RESPAWN NINJAS
            tiempo[1] += 1;
        }
        if(tiempo[0] < 300) { //COOLDOWN RANSENGAN 
            tiempo [0] += 1;
        }
        if(tiempo[2] < 300) { //COOLDOWN CORRER
            tiempo[2] += 1;
        }
        if(tiempo[3] < 300) { //COOLDOWN CORRER
            tiempo[3] += 1;
        }
        return tiempo;
    }
    
    // DIBUJA LOS SEGUNDOS EN PANTALLA
    
    public static int mostrarSegundos(Entorno e, int a) {
    	if (Temporizador.tiempo[4]==65) {
			Temporizador.tiempo[4] = 0;
			a++;
		} 
		e.cambiarFont(null, 50, Color.black);
		e.escribirTexto(a+"", 400, 350);	
		return a;
    }
    
}