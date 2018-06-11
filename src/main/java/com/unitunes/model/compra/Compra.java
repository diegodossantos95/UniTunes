package com.unitunes.model.compra;

import java.util.Date;
import java.util.List;

public class Compra {
	private Long id;
	private Date data;
	private TipoCompraEnum tipo;
	private List<HistoricoCredito> historicoCreditos;

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

	public List<HistoricoCredito> getHistoricoCreditos() {
		return historicoCreditos;
	}

	public void setHistoricoCreditos(List<HistoricoCredito> historicoCreditos) {
		this.historicoCreditos = historicoCreditos;
	}

	public enum TipoCompraEnum {
		Midia(1), Credito(2);
		
		private Integer tipo;
		
		TipoCompraEnum(Integer tipo) {
			this.tipo = tipo;
		}
	}
}
