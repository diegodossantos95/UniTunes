package com.unitunes.pagamento.controller.metodos;

import com.unitunes.pagamento.controller.PagamentoController;
import com.unitunes.pagamento.model.HistoricoPagamento.MetodoPagamentoEnum;

public class PagamentoCartaoCredito implements MetodoPagamento {
	
	public static final Integer ATTR_NUMERO_CARTAO = 1, ATTR_DATA_VENCIMENTO = 2, ATTR_NOME_TITULAR = 3, ATTR_CODIGO_SEGURANCA = 3;

	@Override
	public boolean efetuarPagamento(PagamentoController pc) {
		// Obtem informações do comprador para realizar cobrança via cartão de crédito
		String numCartao = (String) pc.getAttr(PagamentoCartaoCredito.ATTR_NUMERO_CARTAO);
		String dataVenc = (String) pc.getAttr(PagamentoCartaoCredito.ATTR_DATA_VENCIMENTO);
		String nomeTitular = (String) pc.getAttr(PagamentoCartaoCredito.ATTR_NOME_TITULAR);
		String codigoSeguranca = (String) pc.getAttr(PagamentoCartaoCredito.ATTR_CODIGO_SEGURANCA);
		
		// Realizar cobrança via cartão de crédito
		return true;
	}
	
	@Override
	public MetodoPagamentoEnum getMetodoPagamento() {
		return MetodoPagamentoEnum.CartaoCredito;
	}


}