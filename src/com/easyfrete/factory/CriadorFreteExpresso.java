package com.easyfrete.factory;

import com.easyfrete.strategy.CalculoFrete;
import com.easyfrete.strategy.FreteExpresso;

public class CriadorFreteExpresso extends CriadorCalculoFrete {
	
	@Override
	public CalculoFrete criarCalculoFrete() {
		return new FreteExpresso();
	}
}
