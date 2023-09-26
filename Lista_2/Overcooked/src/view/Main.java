package view;

import java.util.concurrent.Semaphore;
import controller.ThreadPrato;

public class Main {

	public static void main(String[] args) {
		Semaphore entrega = new Semaphore(1);
		for (int i = 1; i<6; i++) {
			Thread prato = new ThreadPrato(i, entrega);
			prato.start();
		}

	}

}
