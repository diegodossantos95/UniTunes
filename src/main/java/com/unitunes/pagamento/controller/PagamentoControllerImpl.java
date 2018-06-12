package com.unitunes.pagamento.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.unitunes.pagamento.controller.metodos.MetodoPagamento;
import com.unitunes.pagamento.model.Comprador;
import com.unitunes.pagamento.model.HistoricoPagamento;

public class PagamentoControllerImpl implements PagamentoController {
	
	private MetodoPagamento metodoPagamento;
	private Map<Integer, Object> values;
	private Comprador comprador;
	private Float valor;
	
	public PagamentoControllerImpl() {
		this.values = new HashMap<>();
	}

	@Override
	public HistoricoPagamento efetuarPagamento(Comprador c, Float valor) {
		this.comprador = c;
		this.valor = valor;
		boolean pago = this.metodoPagamento.efetuarPagamento(this);
		if (pago) {
			HistoricoPagamento hp = new HistoricoPagamento();
			hp.setComprador(this.getComprador());
			hp.setData(new Date());
			hp.setMetodo(this.metodoPagamento.getMetodoPagamento());
			hp.setValor(this.getValor());
			return hp;
		}
		return null;
	}

	@Override
	public void setMetodoPagamento(MetodoPagamento mp) {
		this.metodoPagamento = mp;
	}

	@Override
	public void setAttr(Integer key, Object value) {
		this.values.put(key, value);
	}

	@Override
	public Object getAttr(Integer key) {
		return this.values.get(key);
	}

	@Override
	public Comprador getComprador() {
		return this.comprador;
	}

	@Override
	public Float getValor() {
		return this.valor;
	}

}
