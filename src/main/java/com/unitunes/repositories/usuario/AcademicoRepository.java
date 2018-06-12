package com.unitunes.repositories.usuario;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unitunes.model.usuario.Academico;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "academicos")
public interface AcademicoRepository extends PagingAndSortingRepository<Academico, Long> {

}
