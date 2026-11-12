package com.easyfrete.factory;

import com.easyfrete.strategy.CalculoFrete;
import com.easyfrete.strategy.FreteEconomico;

public class CriadorFreteEconomico extends CriadorCalculoFrete {
	
	@Override
	public CalculoFrete criarCalculoFrete() {
		return new FreteEconomico();
	}
}