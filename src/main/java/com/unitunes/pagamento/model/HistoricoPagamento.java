package com.unitunes.pagamento.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HistoricoPagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date data;
	private Float valor;
	
	@Enumerated(EnumType.ORDINAL)
	private MetodoPagamentoEnum metodo;
	
	@ManyToOne
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
