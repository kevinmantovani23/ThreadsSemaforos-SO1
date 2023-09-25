package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCorredor;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for(int i = 1; i<5; i++) {
			Thread pessoa = new ThreadCorredor (i, semaforo);
			pessoa.start();
		}
	}

}
