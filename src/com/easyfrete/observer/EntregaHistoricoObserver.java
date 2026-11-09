package com.easyfrete.observer;

import com.easyfrete.model.Entrega;

public class EntregaHistoricoObserver implements EntregaObserver {

	@Override
	public void atualizar(Entrega entrega) {
		System.out.println(
				"Histórico registrado para a entrega"
		);
	}
}
