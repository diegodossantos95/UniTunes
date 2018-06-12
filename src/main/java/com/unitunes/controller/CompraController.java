package com.unitunes.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.unitunes.model.Midia;
import com.unitunes.model.compra.Compra;
import com.unitunes.model.compra.HistoricoCredito;
import com.unitunes.model.usuario.Academico;
import com.unitunes.pagamento.controller.PagamentoController;
import com.unitunes.pagamento.controller.PagamentoControllerImpl;
import com.unitunes.pagamento.controller.metodos.PagamentoBoletoBancario;
import com.unitunes.pagamento.controller.metodos.PagamentoCartaoCredito;
import com.unitunes.pagamento.controller.metodos.PagamentoTransferencia;
import com.unitunes.pagamento.model.HistoricoPagamento;

public class CompraController {
	
	private Map<Integer, String> metodosPagamento;
	private static final Integer METODO_PG_BOLETO = 1, METODO_PG_CARTAO = 2, METODO_PG_TRANSF = 3;
	
	public CompraController() {
		this.metodosPagamento = new HashMap<>();
		this.metodosPagamento.put(METODO_PG_BOLETO, "Boleto Bancário");
		this.metodosPagamento.put(METODO_PG_CARTAO, "Cartão de Crédito");
		this.metodosPagamento.put(METODO_PG_TRANSF, "Transferência");
	}
	
	public boolean compraMidia(Midia m, Academico a, Integer metodoPagamento, Map<Integer, Object> pagamentoDetalhes, Float valorCreditos) {
		if (valorCreditos >= m.getPreco()) {
			HistoricoCredito hc = this.deduzirCreditos(a, m.getPreco());
			return true;
		} else {
			HistoricoCredito hc = this.deduzirCreditos(a, valorCreditos);
			Float due = m.getPreco() - valorCreditos;
			PagamentoController pc = this.createPagamentoController(metodoPagamento, pagamentoDetalhes);
			
			HistoricoPagamento hp = pc.efetuarPagamento(a, due);
			return true;
		}
	}
	
	public boolean compraCredito(Academico a, Float valor, Integer metodoPagamento, Map<Integer, Object> pagamentoDetalhes) {
		PagamentoController pc = this.createPagamentoController(metodoPagamento, pagamentoDetalhes);
		HistoricoPagamento pg = pc.efetuarPagamento(a, valor);
		if (pg != null) {
			HistoricoCredito hc = this.adicionarCreditos(a, valor);
			return true;
		}
		return false;		
	}
	
	private PagamentoController createPagamentoController(Integer metodoPagamento, Map<Integer, Object> pagamentoDetalhes) {
		PagamentoController pc = new PagamentoControllerImpl();
		if (metodoPagamento == METODO_PG_BOLETO) pc.setMetodoPagamento(new PagamentoBoletoBancario());
		else if (metodoPagamento == METODO_PG_CARTAO) pc.setMetodoPagamento(new PagamentoCartaoCredito());
		else pc.setMetodoPagamento(new PagamentoTransferencia());
		
		for (Entry<Integer, Object> attr : pagamentoDetalhes.entrySet()) {
			pc.setAttr(attr.getKey(), attr.getValue());
		}
		return pc;
	}
	
	public Map<Integer, String> obterOpcoesPagamento(Midia m, Academico a) {
		if (m.getPreco() > 0) {
			return this.metodosPagamento;
		} else {
			return new HashMap<>();
		}
	}
	
	public HistoricoCredito deduzirCreditos(Academico a, Float valor) {
		//TODO
		return null;
	}
	
	public HistoricoCredito adicionarCreditos(Academico a, Float valor) {
		//TODO
		return null;
	}
	
	public Compra salvarCompra(Compra c) {
		//TODO
		return null;
	}
}
