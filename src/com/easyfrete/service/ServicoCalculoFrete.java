package com.easyfrete.service;

import com.easyfrete.model.Entrega;
import com.easyfrete.strategy.CalculoFrete;

public class ServicoCalculoFrete {
	
	public double calcular(Entrega entrega, CalculoFrete estrategia) {
		double valor = estrategia.calcular(entrega);
	    entrega.setValorFrete(valor);
	    
		return valor;
	}
}
