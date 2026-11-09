package com.easyfrete.observer;

import com.easyfrete.model.Entrega;

public class EntregaHistoricoObserver implements EntregaObserver {

	@Override
	public void atualizar(Entrega entrega) {
		System.out.println(
				"Histório registrado para a entrega"
		);
	}
}
