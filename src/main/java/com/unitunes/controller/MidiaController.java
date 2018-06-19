package com.unitunes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitunes.model.Midia;
import com.unitunes.service.MidiaService;

@RestController
@RequestMapping("/midias")
public class MidiaController {

	@Autowired
	MidiaService midiaService;
	
	@PostMapping
	public Midia criarMidia(@RequestBody Midia midia){
		Midia savedMidia = midiaService.criarMidia(midia);
		
		return savedMidia;
	}
	
	@PutMapping(value = "/{id}")
	public Midia atualizarMidia(@PathVariable Long id, @RequestBody Midia midia){
		Midia savedMidia = midiaService.atualizarMidia(id, midia);
		
		return savedMidia;
	}
}
