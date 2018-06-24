package com.unitunes.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.unitunes.pagamento.repositories.HistoricoPagamentoRepository;
import com.unitunes.repositories.MidiaRepository;
import com.unitunes.repositories.compra.CompraRepository;
import com.unitunes.repositories.compra.HistoricoCreditoRepository;
import com.unitunes.repositories.usuario.AcademicoRepository;

@RestController
@RequestMapping("compras/{idAcademico}")
public class CompraController {
	
	private Map<Integer, String> metodosPagamento;
	private static final Integer METODO_PG_BOLETO = 1, METODO_PG_CARTAO = 2, METODO_PG_TRANSF = 3;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private AcademicoRepository academicoRepository;
	
	@Autowired
	private MidiaRepository midiaRepository;
	
	@Autowired
	private HistoricoPagamentoRepository historicoPagamentoRepository;
	
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	
	@Autowired
	public CompraController() {
		this.metodosPagamento = new HashMap<>();
		this.metodosPagamento.put(METODO_PG_BOLETO, "Boleto Bancário");
		this.metodosPagamento.put(METODO_PG_CARTAO, "Cartão de Crédito");
		this.metodosPagamento.put(METODO_PG_TRANSF, "Transferência");
	}	
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Compra> listarCompras(@PathVariable("idAcademico") Long idAcademico) {
		return this.compraRepository.findByAcademico_Id(idAcademico);
	}
	
	@RequestMapping(value="/tipo/{tipo}", method=RequestMethod.GET)
	public Iterable<Compra> listarComprasPorTipo(@PathVariable("idAcademico") Long idAcademico, @PathVariable("tipo") String tipo) {
		return this.compraRepository.findByAcademicoIdAndTipo(idAcademico, TipoCompraEnum.valueOf(tipo));
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.POST)
	public ResponseEntity<Object> efetuarCompra(@PathVariable("id") Long idMidia, 
								@PathVariable("idAcademico") Long idAcademico, 
								@RequestParam("metodoPagamento") Integer metodoPagamento,
								@RequestParam("detalhesPagamento") String[] detalhesPg,
								@RequestParam("valorCreditos") Float valorCreditos) {
		
		Optional<Midia> m = this.midiaRepository.findById(idMidia);
		if (!m.isPresent())
			return new ResponseEntity<>("Invalid Midia ID.", HttpStatus.UNPROCESSABLE_ENTITY);
		
		Optional<Academico> a = this.academicoRepository.findById(idAcademico);
		if (!a.isPresent())
			return new ResponseEntity<>("Invalid Academico ID.", HttpStatus.UNPROCESSABLE_ENTITY);
		
		Map<Integer, Object> detalhesPagamento = this.criarPagamentoDetalhes(metodoPagamento, detalhesPg);
		
		return new ResponseEntity<>(this.comprarMidia(m.get(), 
														a.get(), 
														metodoPagamento,
														detalhesPagamento, 
														valorCreditos),
									HttpStatus.OK);	
	}
	
	@RequestMapping(value="/credito", method=RequestMethod.POST)
	public ResponseEntity<Object> efetuarCompra(@PathVariable("idAcademico") Long idAcademico, 
								@RequestParam("metodoPagamento") Integer metodoPagamento,
								@RequestParam("detalhesPagamento") String[] detalhesPg,
								@RequestParam("valor") Float valor) {
		
		
		Optional<Academico> a = this.academicoRepository.findById(idAcademico);
		if (!a.isPresent())
			return new ResponseEntity<>("Invalid Academico ID.", HttpStatus.UNPROCESSABLE_ENTITY);
		
		Map<Integer, Object> detalhesPagamento = this.criarPagamentoDetalhes(metodoPagamento, detalhesPg);
		
		return new ResponseEntity<>(this.compraCredito(a.get(), valor, metodoPagamento, detalhesPagamento), HttpStatus.OK);
	}
	
	private Map<Integer, Object> criarPagamentoDetalhes(Integer metodoPagamento, String[] detalhes) {
		Map<Integer, Object> detalhesPagamento = new HashMap<>();
		if (metodoPagamento == CompraController.METODO_PG_BOLETO) {
			detalhesPagamento.put(PagamentoBoletoBancario.ATTR_ADDRESS, detalhes[0]);
		} else if (metodoPagamento == CompraController.METODO_PG_CARTAO) {
			detalhesPagamento.put(PagamentoCartaoCredito.ATTR_NUMERO_CARTAO, detalhes[0]);
			detalhesPagamento.put(PagamentoCartaoCredito.ATTR_NOME_TITULAR, detalhes[1]);
			detalhesPagamento.put(PagamentoCartaoCredito.ATTR_DATA_VENCIMENTO, detalhes[2]);
			detalhesPagamento.put(PagamentoCartaoCredito.ATTR_CODIGO_SEGURANCA, detalhes[3]);
		}
		//else if (metodoPagamento == CompraController.METODO_PG_TRANSF) { } // Nenhum atributo é requerido para transferência
		return detalhesPagamento;
	}
	
	private Compra comprarMidia(Midia m, Academico a, Integer metodoPagamento, Map<Integer, Object> pagamentoDetalhes, Float valorCreditos) {
		Compra c = new Compra();
		c.setData(new Date());
		c.setAcademico(a);
		c.setTipo(TipoCompraEnum.Midia);
		boolean salvarAcademico = false;
		if (valorCreditos >= m.getPreco()) {
			HistoricoCredito hc = this.criarHistoricoDebito(a, m.getPreco());
			c.setHistoricoCredito(hc);
			this.historicoCreditoRepository.save(hc);
			hc.setCompra(c);
			a.setCreditos(a.getCreditos() - m.getPreco());
			salvarAcademico = true;
		} else {
			if (valorCreditos > 0) {
				HistoricoCredito hc = this.criarHistoricoDebito(a, valorCreditos);
				c.setHistoricoCredito(hc);
				this.historicoCreditoRepository.save(hc);
				hc.setCompra(c);
				a.setCreditos(a.getCreditos() - valorCreditos);
				salvarAcademico = true;
			}
			
			Float due = m.getPreco() - valorCreditos;
			PagamentoController pc = this.createPagamentoController(metodoPagamento, pagamentoDetalhes);
			HistoricoPagamento hp = pc.efetuarPagamento(a, due);
			this.historicoPagamentoRepository.save(hp);
			c.setHistoricoPagamento(hp);
		}
		if (salvarAcademico) this.academicoRepository.save(a);
		this.compraRepository.save(c);
		return c;
	}
	
	private Compra compraCredito(Academico a, Float valor, Integer metodoPagamento, Map<Integer, Object> pagamentoDetalhes) {
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
	
	private Map<Integer, String> obterOpcoesPagamento(Midia m, Academico a) {
		if (m.getPreco() > 0) {
			return this.metodosPagamento;
		} else {
			return new HashMap<>();
		}
	}
	
	private HistoricoCredito criarHistoricoDebito(Academico a, Float valor) {
		HistoricoCredito hc = new HistoricoCredito();
		hc.setData(new Date());
		hc.setTipo(TipoHistoricoEnum.Debito);
		hc.setValor(valor);
		return hc;
	}
	
	private HistoricoCredito criarHistoricoCredito(Academico a, Float valor) {
		HistoricoCredito hc = new HistoricoCredito();
		hc.setData(new Date());
		hc.setTipo(TipoHistoricoEnum.Credito);
		hc.setValor(valor);
		return hc;
	}
}
