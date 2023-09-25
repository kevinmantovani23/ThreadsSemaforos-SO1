package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPiloto;

public class Main {

	public static void main(String[] args) {
		String piloto[][] = new String[14][3];
		Semaphore a = new Semaphore(1);
		Semaphore b = new Semaphore(1);
		Semaphore c = new Semaphore(1);
		Semaphore d = new Semaphore(1);
		Semaphore e = new Semaphore(1);
		Semaphore f = new Semaphore(1);
		Semaphore g = new Semaphore(1);
		Semaphore pista = new Semaphore(5);
		Semaphore[] time = {a,b,c,d,e,f,g};
		int escuderia = 0;
		int cont=1;
		for (int i = 0; i < 14; i++) {
			if (cont > 2 ) {
				escuderia+=1;
				cont= 1;
			}
			Thread motorista = new ThreadPiloto(i, escuderia, pista, time, piloto);
			cont+=1;
			motorista.start();
				
				
		}
		
	}

}
