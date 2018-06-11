package com.unitunes.pagamento.controller.metodos;

import com.unitunes.pagamento.controller.PagamentoController;
import com.unitunes.pagamento.model.HistoricoPagamento.MetodoPagamentoEnum;

public class PagamentoTransferencia implements MetodoPagamento {
	
	@Override
	public boolean efetuarPagamento(PagamentoController pc) {
		
		// Realizar cobrança via transferência bancária 
		
		return true;
	}

	@Override
	public MetodoPagamentoEnum getMetodoPagamento() {
		return MetodoPagamentoEnum.Transferencia;
	}

}
