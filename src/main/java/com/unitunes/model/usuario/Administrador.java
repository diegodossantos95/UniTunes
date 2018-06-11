package com.unitunes.model.usuario;

import com.unitunes.pagamento.model.Comprador;

public class Administrador extends Academico {
	
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
