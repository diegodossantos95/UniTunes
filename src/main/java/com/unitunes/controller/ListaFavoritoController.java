package com.unitunes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.unitunes.model.Midia;
import com.unitunes.model.lista.ListaFavorito;
import com.unitunes.repositories.ListaFavoritoRepository;
import com.unitunes.repositories.MidiaRepository;

@RestController
@RequestMapping("/listafavoritos")
public class ListaFavoritoController implements ResourceProcessor<RepositoryLinksResource> {

	@Autowired
	ListaFavoritoRepository listaRepository;
	
	@Autowired
	MidiaRepository midiaRepository;
	
	@Override
	public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(ListaFavoritoController.class).withRel("listafavoritos"));
        return resource;
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET)
	public List<ListaFavorito> getAll(){
		return (List<ListaFavorito>) listaRepository.findAll();
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public ListaFavorito adicionarMidia(Long midiaId, Long listaId){
		Optional<ListaFavorito> lista = listaRepository.findById(listaId);
		Optional<Midia> midia = midiaRepository.findById(listaId);
		
		if(!lista.isPresent() || !midia.isPresent()){
			throw new RuntimeException();
		}
		
		lista.get().getMidias().add(midia.get());
		
		return lista.get();
	}
}
