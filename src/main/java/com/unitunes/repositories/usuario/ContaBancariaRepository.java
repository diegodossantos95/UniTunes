package com.unitunes.repositories.usuario;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unitunes.model.usuario.Autor;
import com.unitunes.model.usuario.ContaBancaria;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "contas")
public interface ContaBancariaRepository extends PagingAndSortingRepository<ContaBancaria, Long> {

}
