package com.easyfrete.factory;

import com.easyfrete.strategy.CalculoFrete;
import com.easyfrete.strategy.FreteAgendado;

public class CriadorFreteAgendado extends CriadorCalculoFrete {
	
	@Override
	public CalculoFrete criarCalculoFrete() {
		return new FreteAgendado();
	}
}