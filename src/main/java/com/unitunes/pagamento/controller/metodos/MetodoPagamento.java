package com.unitunes.pagamento.controller.metodos;

import com.unitunes.pagamento.controller.PagamentoController;
import com.unitunes.pagamento.model.HistoricoPagamento.MetodoPagamentoEnum;

public interface MetodoPagamento {

	public boolean efetuarPagamento(PagamentoController pc);
	public MetodoPagamentoEnum getMetodoPagamento();
}
