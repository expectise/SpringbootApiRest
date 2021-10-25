package com.multiventas.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.multiventas.app.model.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long>{
	
	public List<Producto> findByActivo(Boolean activo);
	
	public Producto findByIdProductoAndActivo(Long id, Boolean activo);

}
