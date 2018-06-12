package com.unitunes.model.lista;

public class ListaFavorito {
	
	private Long id;
	private String nome;
	private ListaMidia lista;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ListaMidia getLista() {
		return lista;
	}

	public void setLista(ListaMidia lista) {
		this.lista = lista;
	}
}
