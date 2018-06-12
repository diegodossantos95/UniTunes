package com.unitunes.model.usuario;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Autor extends Academico {
	
	@OneToOne
	private ContaBancaria contaBancaria;
	
	public Autor() { }

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

}

