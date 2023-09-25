package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread {
	private Semaphore saque;
	private Semaphore deposito;
	private int id;
	private int code, saldo, valorTransacao, tipo;
	private final int TEMPO_TRANSACAO = 4000;
	private String tipoS;

	public ThreadTransacao(Semaphore saque, Semaphore deposito, int id, int code, int saldo, int valorTransacao,
			int tipo) {
		this.saque = saque;
		this.deposito = deposito;
		this.id = id;
		this.code = code;
		this.saldo = saldo;
		this.valorTransacao = valorTransacao;
		this.tipo = tipo;
		
		switch (tipo) {
		case 1:
			this.tipoS = "SAQUE";
			break;
		case 2:
			this.tipoS = "DEPOSITO";
			break;
		}
	}
	
	@Override
	public void run() {
		fila();
	}

	public void fila() {
		System.out.println(" | TRANSACAO " + id + "| TIPO: " + tipoS + " | CODIGO: " + code + " | SALDO: " + saldo
				+ "| VALOR TRANSACAO: " + valorTransacao + " | ESTADO: EM ESPERA |");
		transacao();

	}

	public void transacao() {
		switch (tipo) {
		case 1:
			try {
				saque.acquire();
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				saque.release();
			}
			break;
		case 2:
			try {
				deposito.acquire();
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				deposito.release();
			}
			break;
		}
	}

	public void saque() {
		saldo -= valorTransacao;
		try {
			sleep(TEMPO_TRANSACAO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fim();
	}

	public void deposito() {
		saldo += valorTransacao;
		try {
			sleep(TEMPO_TRANSACAO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fim();
	}

	public void fim() {
		System.out.println(" | TRANSACAO " + id + " | CODIGO: " + code + " | TIPO: " + tipoS +" | NOVO SALDO: " + saldo + 
				" | ESTADO: CONCLUIDA |");
	}
}
