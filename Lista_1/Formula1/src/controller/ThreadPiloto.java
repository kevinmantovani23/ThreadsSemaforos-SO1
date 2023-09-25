package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPiloto extends Thread {
	// Média de velocidade de cada piloto = entre 30 e 60 m/s
	// Tamanho do circuito = 1000m
	private int id;
	private int escuderia;
	private Semaphore pista;
	private Semaphore time[];
	private final int TAMANHO_CIRCUITO = 1000;
	private String piloto[][];
	private long tempo = 99999999;
	static AtomicInteger cont = new AtomicInteger(0);

	public ThreadPiloto(int id, int escuderia, Semaphore pista, Semaphore[] time, String[][] piloto) {
		super();
		this.id = id;
		this.escuderia = escuderia;
		this.pista = pista;
		this.time = time;
		this.piloto = piloto;
	}

	@Override
	public void run() {
		espera();
		listarpiloto();
	}

	public void espera() {
		System.out.println(
				"Piloto " + (id + 1) + " da escuderia " + (escuderia + 1) + " esta na espera para entrar na pista.");
		filaesc();

	}

	public void filaesc() {
		try {
			System.out.println("Piloto " + (id + 1) + ", da equipe " + (escuderia + 1)
					+ " agora esta na fila da escuderia.");
			time[escuderia].acquire();
			filapista();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			time[escuderia].release();
		}
	}

	public void filapista() {
		try {
			System.out.println("Piloto " + (id + 1) + ", da equipe " + (escuderia + 1)
					+ " agora esta esperando na fila da pista.");
			pista.acquire();
			corrida();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			pista.release();
			System.out.println("Piloto " + (id + 1) + ", da equipe " + (escuderia + 1)
					+ " terminou as voltas, seu melhor tempo foi: " + tempo + "ms");
		}
	}

	public void corrida() {
		System.out.println("Piloto " + (id + 1) + ", da equipe " + (escuderia + 1) + " comecou as suas voltas.");
		long temporario;
		for (int i = 1; i < 4; i++) {
			int distanciaPercorrida = 0;
			temporario = System.currentTimeMillis();
			while (distanciaPercorrida < TAMANHO_CIRCUITO) {
				try {
					distanciaPercorrida += (int) ((Math.random() * 71) + 30);
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			temporario = System.currentTimeMillis() - temporario;
			System.out.println("O piloto " + (id + 1) + " fez a volta " + i + " em " + temporario + "ms");
			if (temporario < tempo) {
				tempo = temporario;
			}

		}
	}

	public void listarpiloto() {
		piloto[id][2] = Long.toString(tempo);
		piloto[id][1] = Integer.toString(escuderia);
		piloto[id][0] = Integer.toString(id);
		cont.incrementAndGet();
		grid();
	}

	public void grid() {
		String temp[][] = new String[1][3];
		if(cont.get() == 14) {
			for(int i = 1; i<14; i++) {
				for(int x = 0; x < 14-i; x++) {
					if (Double.parseDouble(piloto[x][2]) > Double.parseDouble(piloto[x+1][2])) {
						for (int z = 0; z <3; z++) {
							temp[0][z] = piloto[x][z];
							piloto[x][z] = piloto[x+1][z];
							piloto[x+1][z] = temp[0][z];
						}
					}
				}
			}
			System.out.println("\n==================================================================");
			System.out.println("                            GRID                                 ");
			System.out.println("==================================================================");
			for(int i= 0; i<14; i++) {
				System.out.println("| " +(i+1) + "o LUGAR: PILOTO "+ (Integer.parseInt(piloto[i][0])+1) + " | ESCUDERIA "+ 
			(Integer.parseInt(piloto[i][1])+1) + " | VOLTA MAIS RAPIDA: " + piloto[i][2] + "ms |");
			}
			System.out.println("==================================================================");
		}
		
	}

}
