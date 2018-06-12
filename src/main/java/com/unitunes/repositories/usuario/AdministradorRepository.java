package com.unitunes.repositories.usuario;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unitunes.model.usuario.Administrador;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "administradores")
public interface AdministradorRepository extends PagingAndSortingRepository<Administrador, Long> {

}
