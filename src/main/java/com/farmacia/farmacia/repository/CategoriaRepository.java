package com.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmacia.farmacia.model.CategoriasModel;

@Repository
public interface CategoriaRepository extends JpaRepository <CategoriasModel , Long>{

	public List<CategoriasModel> findAllByTipoContainingIgnoreCase(@Param ("tipo") String tipo);
	public List<CategoriasModel> findAllByFormatoContainingIgnoreCase(@Param ("formato") String formato);
}
