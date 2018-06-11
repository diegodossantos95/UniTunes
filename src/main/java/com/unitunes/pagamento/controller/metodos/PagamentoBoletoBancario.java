package com.unitunes.pagamento.controller.metodos;

import com.unitunes.pagamento.controller.PagamentoController;
import com.unitunes.pagamento.model.HistoricoPagamento.MetodoPagamentoEnum;

public class PagamentoBoletoBancario implements MetodoPagamento {
	
	public static final Integer ATTR_ADDRESS = 1;

	@Override
	public boolean efetuarPagamento(PagamentoController pc) {
		// Obtem informações do comprador para emitir boleto
		String address = (String) pc.getAttr(PagamentoBoletoBancario.ATTR_ADDRESS);
		String cpf = pc.getComprador().getCPF();
		String nome = pc.getComprador().getName();
		
		// Emitir boleto bancario
		return true;
	}

	@Override
	public MetodoPagamentoEnum getMetodoPagamento() {
		return MetodoPagamentoEnum.BoletoBancario;
	}

}
