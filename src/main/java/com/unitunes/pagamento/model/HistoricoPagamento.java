package com.unitunes.pagamento.model;

import java.util.Date;

public class HistoricoPagamento {
	
	private Long id;
	private Date data;
	private Float valor;
	private MetodoPagamentoEnum metodo;
	private Comprador comprador;
	
	public HistoricoPagamento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public MetodoPagamentoEnum getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoPagamentoEnum metodo) {
		this.metodo = metodo;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public enum MetodoPagamentoEnum {
		BoletoBancario(1), CartaoCredito(2), Transferencia(3);
		
		private Integer metodo;
		
		MetodoPagamentoEnum (Integer metodo) {
			this.metodo = metodo;
		}
	}
}
