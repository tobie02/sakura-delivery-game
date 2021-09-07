package juego;

import entorno.Entorno;
import java.util.Random;

public class Ninjas {
	
	private Ninja[] delNorte;
	private Ninja[] delEste;
	private Ninja[] delSur;
	private Ninja[] delOeste;
	
	public Ninjas(Entorno e) {
		this.delNorte = new Ninja[4];
		this.delEste = new Ninja[4];
		this.delSur = new Ninja[4];
		this.delOeste = new Ninja[4];
		// misma direccion distinta calle || misma calle distinta direccion
		Random r = new Random();
		for(int i=1; i<5; i++) {
			if(i == 1 || i == 3) {
				for(int j=1; j<5; j++) {
					int ir = r.nextInt(5)+2;
					int jr = r.nextInt(5)+2;
					if(i==1) {
						delNorte[j-1] = new Ninja(j*(e.ancho()/5)-15, 0, 30, ir, j*2, i);//x=el x de la calle j*2, y=el borde del entorno, calle=j*2
					}else {
						delSur[j-1] = new Ninja(j*(e.ancho()/5)-15, e.alto(), 30, jr, j*2, i);
					}
				}
			}else {
				for(int j=1; j<5; j++) {
					int ir = r.nextInt(6)+2;
					int jr = r.nextInt(6)+2;
					if(i==2) {
						delEste[j-1] = new Ninja(e.ancho(), j*(e.alto()/5)-15, 30, ir, (j*2)-1, i);
					}else {
						delOeste[j-1] = new Ninja(0, j*(e.alto()/5)-15, 30, jr, (j*2)-1, i);
					}
				}
			}
		}
		for(int i=0; i<delEste.length; i++) {
			if(delEste[i].getCalle() == 5) {
				delEste[i].setVelocidad(2);
			}
			if(delOeste[i].getCalle() == 5) {
				delOeste[i].setVelocidad(2);
			}
		}
	}

	public Ninja[] getDelNorte() {
		return delNorte;
	}

	public Ninja[] getDelEste() {
		return delEste;
	}

	public Ninja[] getDelSur() {
		return delSur;
	}

	public Ninja[] getDelOeste() {
		return delOeste;
	}

}
