package com.unitunes.repositories.usuario;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.unitunes.model.usuario.Autor;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "autores")
public interface AutorRepository extends PagingAndSortingRepository<Autor, Long> {

}
