package com.unitunes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitunes.model.Midia;
import com.unitunes.model.lista.ListaFavorito;
import com.unitunes.repositories.ListaFavoritoRepository;
import com.unitunes.repositories.MidiaRepository;

@Service
public class ListaFavoritoService {

	@Autowired
	ListaFavoritoRepository listaRepository;
	
	@Autowired
	MidiaRepository midiaRepository;
	
	public List<ListaFavorito> getAll(){
		return (List<ListaFavorito>) listaRepository.findAll();
    }
	
	public ListaFavorito adicionarMidia(Long listaId, Midia midia){
		Optional<ListaFavorito> lista = listaRepository.findById(listaId);
		Optional<Midia> actualMidia = midiaRepository.findById(midia.getId());
		
		if(!lista.isPresent() || !actualMidia.isPresent()){
			throw new RuntimeException();
		}
		
		lista.get().getMidias().add(actualMidia.get());
		
		return lista.get();
	}
}
