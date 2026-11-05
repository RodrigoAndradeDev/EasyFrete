package com.easyfrete.model;

public class Entrega {
	
	private String id;
	
	private Cliente cliente;
	
	private Endereco origem;
	
	private double peso;
	
	private double distancia;
	
	private double Frete;
	
	private StatusEntrega status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getOrigem() {
		return origem;
	}

	public void setOrigem(Endereco origem) {
		this.origem = origem;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public double getFrete() {
		return Frete;
	}

	public void setFrete(double frete) {
		Frete = frete;
	}

	public StatusEntrega getStatus() {
		return status;
	}

	public void setStatus(StatusEntrega status) {
		this.status = status;
	}

}
