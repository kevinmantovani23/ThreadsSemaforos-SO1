package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class Main {

	public static void main(String[] args) {
		String sentido[] = {"Norte", "Sul", "Leste", "Oeste"};
		Semaphore semaforo = new Semaphore(1);
		
		for (int i= 1; i<5; i++) {
			Thread carro = new ThreadCarro(sentido, i, semaforo);
			carro.start();
		}
		
	}

}
