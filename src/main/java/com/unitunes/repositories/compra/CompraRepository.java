package com.unitunes.repositories.compra;

import org.springframework.data.repository.CrudRepository;

import com.unitunes.model.compra.Compra;
import com.unitunes.model.compra.Compra.TipoCompraEnum;

public interface CompraRepository extends CrudRepository<Compra, Long>{

	public Iterable<Compra> findByAcademico_Id(Long id);
	
	public Iterable<Compra> findByAcademicoIdAndTipo(Long id, TipoCompraEnum tipo);
}
