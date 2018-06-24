package com.unitunes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitunes.model.Midia;
import com.unitunes.repositories.MidiaRepository;

@Service
public class MidiaService {

	@Autowired
	MidiaRepository midiaRepository;
	
	public Midia criarMidia(Midia midia){
		if(!this.validarMidia(midia)){
			throw new RuntimeException();
		}
		
		Midia savedMidia = midiaRepository.save(midia);
		
		return savedMidia;
	}
	
	public Midia atualizarMidia(Long id, Midia midia){
		Optional<Midia> oldMidia = midiaRepository.findById(id);
		
		if(!this.validarMidia(midia) && !oldMidia.isPresent()){
			throw new RuntimeException();
		}
		
		midia = this.atualizarCampos(oldMidia.get(), midia);
		Midia savedMidia = midiaRepository.save(midia);
		
		return savedMidia;
	}
	
	public Midia visualizarMidia(Long id){
		Optional<Midia> midia = midiaRepository.findById(id);
		
		if(!midia.isPresent()){
			throw new RuntimeException();
		}
		
		return midia.get();
	}
	
	public Midia deletarMidia(Long id){
		Optional<Midia> midia = midiaRepository.findById(id);
		
		if(!midia.isPresent()){
			throw new RuntimeException();
		}
		
		Midia deletedMidia = midia.get();

		midiaRepository.delete(deletedMidia);
		
		return deletedMidia;
	}
	
	private Midia atualizarCampos(Midia oldMidia, Midia newMidia){
		oldMidia.setCategoria(newMidia.getCategoria());
		oldMidia.setConteudo(newMidia.getConteudo());
		oldMidia.setDescricao(newMidia.getDescricao());
		oldMidia.setNome(newMidia.getNome());
		oldMidia.setPreco(newMidia.getPreco());
		oldMidia.setTipo(newMidia.getTipo());
		
		return oldMidia;
	}
	
	private boolean validarMidia(Midia midia){
		boolean precoValido = midia.getPreco() > 0;
		boolean nomeValido = !midia.getNome().isEmpty();
		
		return precoValido && nomeValido;
	}
}
