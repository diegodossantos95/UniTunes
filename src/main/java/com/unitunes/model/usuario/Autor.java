package com.unitunes.model.usuario;

public class Autor extends Academico {
	
	private ContaBancaria contaBancaria;
	
	public Autor() { }

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

}

