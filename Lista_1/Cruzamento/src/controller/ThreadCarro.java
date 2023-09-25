package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCarro extends Thread{
	private String sentido[];
	private int id;
	private Semaphore semaforo;
	AtomicInteger atomic = new AtomicInteger(0);
	int i = atomic.getAndIncrement();
	
	public ThreadCarro (String sentido[], int id, Semaphore semaforo) {
		this.sentido = sentido;
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run(){
		chegando();
		esperando();
	}
	
	public void chegando() {
		System.out.println("Carro " + id + " chegou no cruzamento na direcao "+ sentido[i]);
	}
	
	public void esperando() {
			try {
				semaforo.acquire();
				passando();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforo.release();
				System.out.println("Carro "+ id + " passou.");
			}
	}
	
	public void passando() {
		int tempoPassar =(int) (Math.random()*4001)+1000;
		System.out.println("Carro "+ id+ " esta passando pelo cruzamento na direcao " + sentido[i]);
		try {
			sleep(tempoPassar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
