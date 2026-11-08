package com.easyfrete.strategy;

import com.easyfrete.model.Entrega;

public interface CalculoFrete {

	double calcular(Entrega entrega);
}
