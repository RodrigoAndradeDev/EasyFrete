package com.easyfrete.service;

import java.util.List;
import java.util.UUID;

import com.easyfrete.enums.StatusEntrega;
import com.easyfrete.model.Entrega;

import java.util.ArrayList;

public class ServicoEntrega {

	private List<Entrega> entregas = new ArrayList<>();
	
	public ServicoEntrega() {}

	public ServicoEntrega(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	public void cadastrarEntrega(Entrega entrega) {
		entregas.add(entrega);
	}
	
	public void listarEntregas() {
		for (Entrega entrega : entregas) {
			System.out.println(entrega);
		}
	}
	
	public Entrega buscarEntregaPorId(UUID id) {
		Entrega entregaEncontrada = null;
		
		for(Entrega entrega : entregas) {
			if(entrega.getId().equals(id)) {
				entregaEncontrada = entrega;
				break;
			}
		}
		
		if(entregaEncontrada == null) {
			throw new RuntimeException("Entrega com ID: '" + id + "' não encontrada.");
		}
		
		return entregaEncontrada;
	}
	
	public void atualizarStatusEntrega(Entrega entrega, StatusEntrega status) {
		entrega.setStatus(status);
	}
}
