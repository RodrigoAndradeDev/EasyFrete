package com.easyfrete.factory;

import com.easyfrete.adapter.FreteTerceirizadoAdapter;
import com.easyfrete.adapter.ServicoExternoFrete;
import com.easyfrete.strategy.CalculoFrete;

public class CriadorFreteTerceirizado extends CriadorCalculoFrete {
	
	@Override
	public CalculoFrete criarCalculoFrete() {
		ServicoExternoFrete externo = new ServicoExternoFrete();
		return new FreteTerceirizadoAdapter(externo);
	}
}
