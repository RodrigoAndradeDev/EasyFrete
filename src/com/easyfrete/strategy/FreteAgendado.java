package com.easyfrete.strategy;

import com.easyfrete.model.Entrega;

public class FreteAgendado implements CalculoFrete {

	@Override
	public double calcular(Entrega entrega) {
		return entrega.getPeso() * 2.5
				+ entrega.getDistancia() * 0.15;
	}
}
