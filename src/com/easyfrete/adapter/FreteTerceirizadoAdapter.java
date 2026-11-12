package com.easyfrete.adapter;

import com.easyfrete.model.Entrega;
import com.easyfrete.strategy.CalculoFrete;

public class FreteTerceirizadoAdapter implements CalculoFrete {
	
	private ServicoExternoFrete servicoExterno;
	
	public FreteTerceirizadoAdapter(ServicoExternoFrete servicoExterno) {
		if (servicoExterno == null) {
	        throw new IllegalArgumentException(
	            "O serviço externo é obrigatório."
	        );
	    }
		
		this.servicoExterno = servicoExterno;
	}
	
	@Override
	public double calcular(Entrega entrega) {
		return servicoExterno.calcularValorFrete(
				entrega.getPeso(),
				entrega.getOrigem().getCep(),
				entrega.getDestino().getCep()
		);				
	}
}