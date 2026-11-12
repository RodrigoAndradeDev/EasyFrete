package com.easyfrete.observer;

import java.util.ArrayList;
import java.util.List;

import com.easyfrete.model.Entrega;

public class EntregaHistoricoObserver implements EntregaObserver {

	private List<String> historico = new ArrayList<>();

	@Override
	public void atualizar(Entrega entrega) {
	
		String registro =
			"Entrega: " + entrega.getId()
			+ " | Novo status: " + entrega.getStatus();
		
		historico.add(registro);
		
		System.out.println("Histórico registrado.");	
	}

	public void listarHistorico() {
	
		if (historico.isEmpty()) {
			System.out.println("Nenhum registro encontrado.");
			return;
		}
	
		System.out.println("\n========== HISTÓRICO ==========");
	
		for (String registro : historico) {
			System.out.println(registro);
		}
	}
}