package com.unitunes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitunes.model.Midia;
import com.unitunes.repositories.MidiaRepository;

@RestController
@RequestMapping("/midias")
public class MidiaController {

	@Autowired
	MidiaRepository midiaRepository;
	
	public Midia criarMidia(@RequestBody Midia midia){
		if(!this.validarMidia(midia)){
			throw new RuntimeException();
		}
		
		Midia savedMidia = midiaRepository.save(midia);
		
		return savedMidia;
	}
	
	private boolean validarMidia(Midia midia){
		return true;
	}
}
