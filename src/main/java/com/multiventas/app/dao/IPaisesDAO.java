package com.multiventas.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.multiventas.app.model.Paises;



public interface IPaisesDAO extends CrudRepository<Paises, Long>{
	
	public List<Paises> findByActivo(Boolean activo);
	
	public Paises findByIdPaisAndActivo(Long id, Boolean activo);
}
