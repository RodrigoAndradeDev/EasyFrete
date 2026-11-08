package com.easyfrete.model;

import java.util.UUID;

public class Cliente {
	
	private UUID id;
	private String nome;
	private String contato;
	
	public Cliente() {}
	
	public Cliente(UUID id, String nome, String contato) {
		this.id = id;
		this.nome = nome;
		this.contato = contato;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
}
