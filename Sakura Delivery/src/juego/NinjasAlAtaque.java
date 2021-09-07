package juego;

import entorno.Entorno;
import java.util.Random;

public class NinjasAlAtaque {
	//  son los ninjas que aparecen en pantalla
	Ninjas reserva;
	Ninja[] milicia;
	
	public NinjasAlAtaque(Entorno e) {
		this.reserva = new Ninjas(e);
		this.milicia = new Ninja[6];
		prepararMilicia(milicia.length);//
	}
	//  copia en milicia los ninjas que van a aparecer en pantalla 
	public void prepararMilicia(int vacios) {//  vacios= los elementos vacios dentro de milicia
		Random r = new Random();
		int i = milicia.length - vacios;
		boolean sePuede = true;
		while(i<milicia.length) {
			int direc = r.nextInt(4)+1;//  el arreglo dentro de recerva que va atomar en cuenta
			int enCalle = r.nextInt(4);//  el indice del elemento del arreglo que se va a copiar
			sePuede = true;//  si es falso, se va a buscar un nuevo elemento
			if(direc == 1) {
				for(int j=0; j<5; j++) {
					if(milicia[j] != null && milicia[j].getCalle() == reserva.getDelNorte()[enCalle].getCalle()) {
						sePuede = false;
					}
				}
				if(sePuede) {
					milicia[i] =new Ninja(reserva.getDelNorte()[enCalle].getX(), reserva.getDelNorte()[enCalle].getY(), reserva.getDelNorte()[enCalle].getDiametro(), reserva.getDelNorte()[enCalle].getVelocidad(), reserva.getDelNorte()[enCalle].getCalle(), reserva.getDelNorte()[enCalle].getDireccion());
					i++;
				}
			}else if(direc == 2) {
				for(int j=0; j<5; j++) {
					if(milicia[j] != null && milicia[j].getCalle() == reserva.getDelEste()[enCalle].getCalle()) {
						sePuede = false;
					}
				}
				if(sePuede) {
					milicia[i] =new Ninja(reserva.getDelEste()[enCalle].getX(), reserva.getDelEste()[enCalle].getY(), reserva.getDelEste()[enCalle].getDiametro(), reserva.getDelEste()[enCalle].getVelocidad(), reserva.getDelEste()[enCalle].getCalle(), reserva.getDelEste()[enCalle].getDireccion());
					i++;
				}
			}else if(direc == 3) {
				for(int j=0; j<5; j++) {
					if(milicia[j] != null && milicia[j].getCalle() == reserva.getDelSur()[enCalle].getCalle()) {
						sePuede = false;
					}
				}
				if(sePuede) {
					milicia[i] =new Ninja(reserva.getDelSur()[enCalle].getX(), reserva.getDelSur()[enCalle].getY(), reserva.getDelSur()[enCalle].getDiametro(), reserva.getDelSur()[enCalle].getVelocidad(), reserva.getDelSur()[enCalle].getCalle(), reserva.getDelSur()[enCalle].getDireccion());
					i++;
				}
			}else {
				for(int j=0; j<5; j++) {
					if(milicia[j] != null && milicia[j].getCalle() == reserva.getDelOeste()[enCalle].getCalle()) {
						sePuede = false;
					}
				}
				if(sePuede) {
					milicia[i] =new Ninja(reserva.getDelOeste()[enCalle].getX(), reserva.getDelOeste()[enCalle].getY(), reserva.getDelOeste()[enCalle].getDiametro(), reserva.getDelOeste()[enCalle].getVelocidad(), reserva.getDelOeste()[enCalle].getCalle(), reserva.getDelOeste()[enCalle].getDireccion());
					i++;
				}
			}
		}
	}
	
	public void vaciosAlFinal() {
		//  mueve al final los elementos vacios de milicia al final del arreglo
		Ninja[] nuevo = new Ninja[milicia.length];
		int j=0;
		for(int i=0; i<milicia.length; i++) {
			if(milicia[i] != null) {
				nuevo[j] = milicia[i];
				j++;
			}
		}
		milicia = nuevo;
	}
	
	public void cambio() {
		//   cuenta cuantos elemetos vacios hay en milicia y luego copia otro ninja en ellos
        int vacios = 0;
        for(int i=0; i<milicia.length; i++) {
            if(milicia[i] == null) {
                vacios++;
            }
        }
        if ((Temporizador.tiempo[1] == 1000 && vacios < 2) || vacios == 2) { 
            vaciosAlFinal();
            prepararMilicia(vacios);
            Temporizador.tiempo[1] = 0;
        }
    }
	
	public void invasion(Entorno e, Ransengan r) {
		//  dibuja los elementos de milicia en pantalla, los mueve y verifica si hay un elemento vacio
		int i=0;
		while(i < milicia.length) {
			if(milicia[i] != null) {
				milicia[i].dibujar(e);
				milicia[i].ataque();
				milicia[i].volver(e);
			}
			i++;
			cambio();
		}
	}
	
}
