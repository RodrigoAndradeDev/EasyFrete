package com.easyfrete.model;

import java.util.ArrayList;
import java.util.List;

public class ServicoEntrega {

	private List<Entrega> entregas;
	
	private List<EntregaObserver> observers;
	
	public void cadastrar(Entrega entrega) {
		if(entrega != null) {
			entregas.add(entrega);
		}
	}
	
	public void listar() {

	}
	
	public Entrega buscarPorId(String idBusca){
		for(Entrega e : entregas ) {
			
			if ((e.getId() != null) && (e.getId().equals(idBusca))) {
				return e;
			}
		}
		return null;
	}
	
	public void atualizarStatus(Entrega e, StatusEntrega st) {
		e.setStatus(st);
	}
	
	public void notificarObservadores() {
		
	}
}
