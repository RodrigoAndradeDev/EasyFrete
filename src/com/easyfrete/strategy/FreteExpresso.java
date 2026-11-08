package com.easyfrete.strategy;

import com.easyfrete.model.Entrega;

public class FreteExpresso implements CalculoFrete {
	
	@Override
	public double calcular(Entrega entrega) {
		return entrega.getPeso() * 3.5
				+ entrega.getDistancia() * 0.20;
	}
}
