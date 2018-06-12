package com.unitunes.repositories.usuario;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unitunes.model.usuario.Banco;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "bancos")
public interface BancoRepository extends PagingAndSortingRepository<Banco, Integer> {

}
