package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorredor extends Thread {
	private final int CORREDOR_DISTANCIA = 200;
	private Semaphore semaforo;
	private int id;

	public ThreadCorredor(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		andando();
	}

	public void andando() {
		int distancia = 0;
		while (distancia < CORREDOR_DISTANCIA) {
			try {
				int velocidade = (int) (Math.random() * 3) + 4;
				distancia += velocidade;
				System.out.println("Pessoa " + id + " andou " + distancia + "m");
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		esperando();
	}

	public void esperando() {
		try {
			System.out.println("Pessoa " + id + " esta esperando na porta");
			semaforo.acquire();
			abrindo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Pessoa "+ id + " passou pela porta");
			semaforo.release();
		}
	}
	
	public void abrindo() {
		int tempoAbrir = (int)(Math.random()*2)+ 1;
		try {
			System.out.println("Pessoa "+ id+ " esta passando pela porta");
			sleep(tempoAbrir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
