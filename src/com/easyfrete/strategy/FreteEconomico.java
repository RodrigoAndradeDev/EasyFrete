package com.easyfrete.strategy;

import com.easyfrete.model.Entrega;

public class FreteEconomico implements CalculoFrete {

	@Override
	public double calcular(Entrega entrega) {
		return entrega.getPeso() * 2.0
			+ entrega.getDistancia() * 0.1;
	}
}