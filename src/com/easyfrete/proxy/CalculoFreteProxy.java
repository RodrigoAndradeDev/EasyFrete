package com.easyfrete.proxy;

import com.easyfrete.model.Entrega;
import com.easyfrete.strategy.CalculoFrete;

public class CalculoFreteProxy implements CalculoFrete {
	
	private CalculoFrete calculoReal;
	
	public CalculoFreteProxy(CalculoFrete calculoReal) {
		if (calculoReal == null) {
	        throw new IllegalArgumentException(
	            "A estratégia de cálculo é obrigatória."
	        );
	    }
		
		this.calculoReal = calculoReal;
	}
	
	@Override
	public double calcular(Entrega entrega) {
		if (entrega == null) {
            throw new IllegalArgumentException(
                "A entrega não pode ser nula."
            );
        }

        if (entrega.getPeso() <= 0) {
            throw new IllegalArgumentException(
                "O peso deve ser positivo."
            );
        }

        if (entrega.getOrigem() == null ||
            entrega.getDestino() == null) {

            throw new IllegalArgumentException(
                "Origem e destino são obrigatórios."
            );
        }

        return calculoReal.calcular(entrega);
	}
	
}