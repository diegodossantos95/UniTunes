package com.unitunes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unitunes.model.lista.ListaFavorito;;

@RepositoryRestResource(collectionResourceRel = "listafavoritos", path = "listafavoritos")
public interface ListaFavoritoRepository extends PagingAndSortingRepository<ListaFavorito, Long>{

}
