package view;

import java.util.concurrent.Semaphore;
import controller.ThreadTransacao;
public class Main {

	public static void main(String[] args) {
		Semaphore saque = new Semaphore(1);
		Semaphore deposito = new Semaphore(1);
		int code, saldo, valorTransacao, tipo;
		
		for(int i=1; i<21; i++) {
			code = (int)(Math.random()*101);
			saldo = (int)(Math.random()*10000);
			valorTransacao = (int)(Math.random()*1001);
			tipo = (int)(Math.random()*2) + 1;
			Thread transacao = new ThreadTransacao(saque, deposito, i, code, saldo, valorTransacao, tipo);
			transacao.start();
		}
	}

}
