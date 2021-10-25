package com.multiventas.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.multiventas.app.model.Direcciones;

public interface IDireccionesDAO extends CrudRepository<Direcciones, Long> {

	public Direcciones findByIdUsuariosAndActivoAndElegido(Long id, Boolean activo, Boolean elegido);
	
}
