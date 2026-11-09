package com.easyfrete.model;

import java.util.UUID;

import com.easyfrete.enums.StatusEntrega;
import com.easyfrete.enums.TipoEntrega;

public class Entrega {

    private UUID id;
    private Cliente cliente;
    private Endereco origem;
    private Endereco destino;
    private double peso;
    private double distancia;
    private double valorFrete;
    private StatusEntrega status;
    private TipoEntrega tipo;

    public Entrega(
            Cliente cliente,
            Endereco origem,
            Endereco destino,
            double peso,
            double distancia,
            TipoEntrega tipo
    ) {
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        this.distancia = distancia;
        this.tipo = tipo;
        this.valorFrete = 0.0;
        this.status = StatusEntrega.AGUARDANDO;
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

	public TipoEntrega getTipo() {
		return tipo;
	}

	public void setTipo(TipoEntrega tipo) {
		this.tipo = tipo;
	}
}