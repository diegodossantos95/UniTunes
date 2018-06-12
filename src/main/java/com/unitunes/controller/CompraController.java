package com.unitunes.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unitunes.model.Midia;
import com.unitunes.model.compra.Compra;
import com.unitunes.model.compra.HistoricoCredito;
import com.unitunes.model.compra.Compra.TipoCompraEnum;
import com.unitunes.model.compra.HistoricoCredito.TipoHistoricoEnum;
import com.unitunes.model.usuario.Academico;
import com.unitunes.pagamento.controller.PagamentoController;
import com.unitunes.pagamento.controller.PagamentoControllerImpl;
import com.unitunes.pagamento.controller.metodos.PagamentoBoletoBancario;
import com.unitunes.pagamento.controller.metodos.PagamentoCartaoCredito;
import com.unitunes.pagamento.controller.metodos.PagamentoTransferencia;
import com.unitunes.pagamento.model.HistoricoPagamento;
import com.unitunes.repositories.compra.CompraRepository;
import com.unitunes.repositories.compra.HistoricoCreditoRepository;
import com.unitunes.repositories.usuario.AcademicoRepository;

@Component
public class CompraController {
	
	private Map<Integer, String> metodosPagamento;
	private static final Integer METODO_PG_BOLETO = 1, METODO_PG_CARTAO = 2, METODO_PG_TRANSF = 3;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private AcademicoRepository academicoRepository;
	
	public CompraController() {
		this.metodosPagamento = new HashMap<>();
		this.metodosPagamento.put(METODO_PG_BOLETO, "Boleto Bancário");
		this.metodosPagamento.put(METODO_PG_CARTAO, "Cartão de Crédito");
		this.metodosPagamento.put(METODO_PG_TRANSF, "Transferência");
	}
	
	public Compra compraMidia(Midia m, Academico a, Integer metodoPagamento, Map<Integer, Object> pagamentoDetalhes, Float valorCreditos) {
		Compra c = new Compra();
		c.setData(new Date());
		c.setAcademico(a);
		c.setTipo(TipoCompraEnum.Midia);
		boolean salvarAcademico = false;
		if (valorCreditos >= m.getPreco()) {
			HistoricoCredito hc = this.criarHistoricoDebito(a, m.getPreco());
			c.setHistoricoCredito(hc);
			hc.setCompra(c);
			a.setCreditos(a.getCreditos() - m.getPreco());
			salvarAcademico = true;
		} else {
			if (valorCreditos > 0) {
				HistoricoCredito hc = this.criarHistoricoDebito(a, valorCreditos);
				c.setHistoricoCredito(hc);
				hc.setCompra(c);
				a.setCreditos(a.getCreditos() - valorCreditos);
				salvarAcademico = true;
			}
			
			Float due = m.getPreco() - valorCreditos;
			PagamentoController pc = this.createPagamentoController(metodoPagamento, pagamentoDetalhes);
			HistoricoPagamento hp = pc.efetuarPagamento(a, due);
			c.setHistoricoPagamento(hp);
		}
		if (salvarAcademico) this.academicoRepository.save(a);
		this.compraRepository.save(c);
		return c;
	}
	
	public Compra compraCredito(Academico a, Float valor, Integer metodoPagamento, Map<Integer, Object> pagamentoDetalhes) {
		PagamentoController pc = this.createPagamentoController(metodoPagamento, pagamentoDetalhes);
		HistoricoPagamento pg = pc.efetuarPagamento(a, valor);
		if (pg != null) {
			HistoricoCredito hc = this.criarHistoricoCredito(a, valor);
			Compra c = new Compra();
			c.setAcademico(a);
			c.setData(new Date());
			c.setHistoricoCredito(hc);
			c.setTipo(TipoCompraEnum.Credito);
			hc.setCompra(c);
			a.setCreditos(a.getCreditos() + valor);
			this.compraRepository.save(c);
			this.academicoRepository.save(a);
			return c;
		}
		return null;		
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
	
	public HistoricoCredito criarHistoricoDebito(Academico a, Float valor) {
		HistoricoCredito hc = new HistoricoCredito();
		hc.setData(new Date());
		hc.setTipo(TipoHistoricoEnum.Debito);
		hc.setValor(valor);
		return hc;
	}
	
	public HistoricoCredito criarHistoricoCredito(Academico a, Float valor) {
		HistoricoCredito hc = new HistoricoCredito();
		hc.setData(new Date());
		hc.setTipo(TipoHistoricoEnum.Credito);
		hc.setValor(valor);
		return hc;
	}
}
