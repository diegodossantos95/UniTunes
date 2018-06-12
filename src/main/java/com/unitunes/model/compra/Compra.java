package com.unitunes.model.compra;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.unitunes.model.usuario.Academico;
import com.unitunes.pagamento.model.HistoricoPagamento;
@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date data;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoCompraEnum tipo;
	
	@OneToOne(optional = true)
	private HistoricoCredito historicoCredito;
	
	@OneToOne(optional = true)
	private HistoricoPagamento historicoPagamento;
	
	@ManyToOne
	private Academico academico;

	public Compra() {
		
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

	public TipoCompraEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoCompraEnum tipo) {
		this.tipo = tipo;
	}

	public HistoricoCredito getHistoricoCredito() {
		return historicoCredito;
	}

	public void setHistoricoCredito(HistoricoCredito historicoCredito) {
		this.historicoCredito = historicoCredito;
	}
	
	public HistoricoPagamento getHistoricoPagamento() {
		return historicoPagamento;
	}
	
	public void setHistoricoPagamento(HistoricoPagamento historicoPagamento) {
		this.historicoPagamento = historicoPagamento;
	}
	
	public Academico getAcademico() {
		return academico;
	}
	
	public void setAcademico(Academico academico) {
		this.academico = academico;
	}

	public enum TipoCompraEnum {
		Midia(1), Credito(2);
		
		private Integer tipo;
		
		TipoCompraEnum(Integer tipo) {
			this.tipo = tipo;
		}
	}
}
