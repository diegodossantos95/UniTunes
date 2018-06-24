package com.unitunes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitunes.model.Midia;
import com.unitunes.model.lista.ListaFavorito;
import com.unitunes.service.ListaFavoritoService;

@RestController
@RequestMapping("/listafavoritos")
public class ListaFavoritoController implements ResourceProcessor<RepositoryLinksResource> {

	@Autowired
	ListaFavoritoService listaService;
	
	@Override
	public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(ListaFavoritoController.class).withRel("listafavoritos"));
        return resource;
	}
	
	@GetMapping
	public List<ListaFavorito> getAll(){
		return listaService.getAll();
    }
	
	@PostMapping(value = "/{id}")
	public ListaFavorito adicionarMidia(@PathVariable Long id, @RequestBody Midia midia){
		return listaService.adicionarMidia(id, midia);
	}
}
