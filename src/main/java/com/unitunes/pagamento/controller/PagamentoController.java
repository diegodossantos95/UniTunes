package com.unitunes.pagamento.controller;

import com.unitunes.pagamento.controller.metodos.MetodoPagamento;
import com.unitunes.pagamento.model.Comprador;
import com.unitunes.pagamento.model.HistoricoPagamento;

public interface PagamentoController {
	
	public HistoricoPagamento efetuarPagamento(Comprador c, Float valor);
	public void setMetodoPagamento(MetodoPagamento mp);
	public void setAttr(Integer key, Object value);
	public Object getAttr(Integer key);
	public Comprador getComprador();
	public Float getValor();

}
