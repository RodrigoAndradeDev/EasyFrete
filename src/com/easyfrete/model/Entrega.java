package com.easyfrete.model;

import java.util.UUID;

import com.easyfrete.enums.StatusEntrega;

public class Entrega {
	
	private UUID id;
	private Cliente cliente;
	private Endereco origem;
	private Endereco destino;
	private double peso;
	private double distancia;
	private double valorFrete;
	private StatusEntrega status;
	
	public Entrega() {}
	
	public Entrega(
			UUID id,
			Cliente cliente,
			Endereco origem,
			Endereco destino,
			double peso,
			double distancia,
			double valorFrete,
			StatusEntrega status
	) {
		this.id = id;
		this.cliente = cliente;
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
		this.distancia = distancia;
		this.valorFrete = valorFrete;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
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
	
	public Endereco getDestino() {
		return destino;
	}
	
	public void setDestino(Endereco destino) {
		this.destino = destino;
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
	
	public double getValorFrete() {
		return valorFrete;
	}
	
	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	public StatusEntrega getStatus() {
		return status;
	}
	
	public void setStatus(StatusEntrega status) {
		this.status = status;
	}
}
