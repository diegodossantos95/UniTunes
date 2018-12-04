package com.unitunes.model.usuario;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Administrador extends Academico {
	
	@OneToOne
	private ContaBancaria contaBancaria;
	
	public Administrador() { 
	}
	
	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

}
