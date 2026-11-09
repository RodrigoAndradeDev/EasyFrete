package com.easyfrete.factory;

import com.easyfrete.strategy.CalculoFrete;

public abstract class CriadorCalculoFrete {
	
	public abstract CalculoFrete criarCalculoFrete();
}
