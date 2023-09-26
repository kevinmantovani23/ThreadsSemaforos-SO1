package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread{
	
	private int id;
	private Semaphore semaforo;
	private double tempo;
	
	public ThreadTransacao(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		verifica();
	}
	
	public void verifica() {
		int caso = id%3;
		switch(caso) {
			case 1:
				caso1();
				break;
			case 2:
				caso2();
				break;
			case 0:
				caso0();
				break;
		}
	}
	
	private void caso1() {
		calculo1();
		bd1();
		calculo1();
		bd1();
	}

	
	

	private void caso2() {
		calculo2();
		bd2();
		calculo2();
		bd2();
		calculo2();
		bd2();
		
	}
	
	private void caso0() {
		calculo0();
		bd0();
		calculo0();
		bd0();
		calculo0();
		bd0();
	}
	
	
	private void bd0() {
		try {
			semaforo.acquire();
			System.out.println("Thread "+ id + " comecou a realizar transacoes no banco de dados.");
			sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Thread "+ id + " realizou a transacao em 1.5s");
			semaforo.release();
		}
	}
	
	
	private void calculo0() {
		try {
			tempo = (Math.random()*1001) + 1000;
			System.out.println("Thread "+ id + " comecou a realizar calculos.");
			sleep((long)tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tempo = tempo/1000;
		tempo = Math.round(tempo * 100);
		tempo = tempo/100;
		System.out.println("Thread "+ id + " realizou os calculos em " + tempo + "s");
		
	}
	
	
	private void bd2() {
		try {
			semaforo.acquire();
			System.out.println("Thread "+ id + " comecou a realizar transacoes no banco de dados.");
			sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Thread "+ id + " realizou a transacao em 1.5s");
			semaforo.release();
		}
		
	}

	private void calculo2() {
		try {
			tempo = (Math.random()*1001) + 500;
			System.out.println("Thread "+ id + " começou a realizar calculos.");
			sleep((long)tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tempo = tempo/1000;
		tempo = Math.round(tempo * 100);
		tempo = tempo/100;
		System.out.println("Thread "+ id + " realizou os calculos em " + tempo + "s");
		
	}
		
	

	

	private void bd1() {
		try {
			semaforo.acquire();
			System.out.println("Thread "+ id + " começou a realizar transacoes no banco de dados.");
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Thread "+ id + " realizou a transacao em 1s");
			semaforo.release();
		}
	}

	private void calculo1() {
		try {
			tempo = (Math.random()*801) + 200;
			System.out.println("Thread "+ id + " começou a realizar calculos.");
			sleep((long)tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tempo = tempo/1000;
		tempo = Math.round(tempo * 100);
		tempo = tempo/100;
		System.out.println("Thread "+ id + " realizou os calculos em " + tempo + "s");
		
	}
	
}
