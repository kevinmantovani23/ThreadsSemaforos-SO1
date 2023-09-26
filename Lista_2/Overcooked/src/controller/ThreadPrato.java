package controller;

import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread {

	private int id;
	private Semaphore entrega;
	private int caso;
	private String nome;
	private double tempoTotal;
	private double tempoCorrido = 0;
	double porcentagem;

	public ThreadPrato(int id, Semaphore entrega) {
		this.id = id;
		this.entrega = entrega;
	}

	
	@Override
	public void run() {
		verificaID();
	}
	
	public void verificaID() {
		caso = id % 2;
		switch (caso) {
		case 0:
			lasanha();
			break;
		case 1:
			sopa();
			break;
		}
	}

	public void sopa() {
		nome = "Sopa de Cebola";
		tempoTotal = ((int) (Math.random() * 301) + 500);
		System.out.println("O prato " + nome + "(" + id + ")" + " comecou a ser cozido.");
		while (tempoCorrido < tempoTotal) {
			try {
				sleep(100);
				tempoCorrido+=100;
				porcentagem = tempoCorrido/tempoTotal * 100;
				porcentagem = Math.round(porcentagem);
				if (porcentagem >= 100) {
					System.out.println("Progresso do prato " + nome + "(" + id + "): 100%" );
					System.out.println("O prato " + nome + "(" + id + ")" + " terminou de ser cozido.");
				}else {
					System.out.println("Progresso do prato " + nome + "(" + id + "): " + porcentagem + "%" );
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		entregar();
	}

	public void lasanha() {
		nome = "Lasanha a Bolonhesa";
		tempoTotal = ((int) (Math.random() * 601) + 600);
		System.out.println("O prato " + nome + "(" + id + ")" + " comecou a ser cozido.");
		while (tempoCorrido < tempoTotal) {
			try {
				sleep(100);
				tempoCorrido+=100;
				porcentagem = tempoCorrido/tempoTotal * 100;
				porcentagem = Math.round(porcentagem);
				if (porcentagem >= 100) {
					System.out.println("Progresso do prato " + nome + "(" + id + "): 100%" );
					System.out.println("O prato " + nome + "(" + id + ")" + " terminou de ser cozido.");
				}else {
					System.out.println("Progresso do prato " + nome + "(" + id + "): " + porcentagem + "%" );
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		entregar();
	}
	
	public void entregar() {
		try {
			entrega.acquire();
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			entrega.release();
			System.out.println("O prato " + nome + "(" + id + ")" + " foi entregue.");
		}
	}
}
