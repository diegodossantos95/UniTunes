package com.unitunes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.unitunes.model.Midia;

@RepositoryRestResource(collectionResourceRel = "midias", path = "midias")
public interface MidiaRepository extends PagingAndSortingRepository<Midia, Long>{

}
