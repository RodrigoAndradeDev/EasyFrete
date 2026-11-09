package com.easyfrete.service;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import com.easyfrete.enums.StatusEntrega;
import com.easyfrete.model.Entrega;
import com.easyfrete.observer.EntregaObserver;

public class ServicoEntrega {

	private List<Entrega> entregas = new ArrayList<>();
	private List<EntregaObserver> observers = new ArrayList<>();
	
	public ServicoEntrega() {}

	public ServicoEntrega(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	public void adicionarObserver(EntregaObserver observer) {
		if (observer == null) {
	        throw new IllegalArgumentException(
	            "O observer não pode ser nulo."
	        );
	    }
		
	    observers.add(observer);
	}

	public void removerObserver(EntregaObserver observer) {
	    observers.remove(observer);
	}

	private void notificarObservers(Entrega entrega) {
	    for (EntregaObserver observer : observers) {
	        observer.atualizar(entrega);
	    }
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
		notificarObservers(entrega);
	}
}
