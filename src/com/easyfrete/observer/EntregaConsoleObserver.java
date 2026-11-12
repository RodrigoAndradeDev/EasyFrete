package com.easyfrete.observer;

import com.easyfrete.model.Entrega;

public class EntregaConsoleObserver implements EntregaObserver {

	@Override
	public void atualizar(Entrega entrega) {
		System.out.println(
			"Entrega atualizada: " + entrega.getStatus()
		);
	}
}