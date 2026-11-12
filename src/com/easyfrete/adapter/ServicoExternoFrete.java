package com.easyfrete.adapter;

// Simula servico externo
public class ServicoExternoFrete {

	public double calcularValorFrete(
		double peso,
		String cepOrigem,
		String cepDestino
	) {
		return peso * 5.0 + 25.0;
	}
}