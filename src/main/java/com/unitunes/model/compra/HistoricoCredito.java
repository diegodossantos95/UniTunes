package com.unitunes.model.compra;

import java.util.Date;

public class HistoricoCredito {
	
	private Long id;
	private Float valor;
	private Date data;
	private TipoHistoricoEnum tipo;
	private Compra compra;
	
	public HistoricoCredito() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoHistoricoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoHistoricoEnum tipo) {
		this.tipo = tipo;
	}
	
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public Compra getCompra() {
		return compra;
	}

	public enum TipoHistoricoEnum {
		Credito(1), Debito(2);
		
		private Integer tipo;
		
		TipoHistoricoEnum(Integer tipo) {
			this.tipo = tipo;
		}
	}

}
