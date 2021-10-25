package com.multiventas.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.multiventas.app.model.Ventas;

public interface IVentasDAO extends CrudRepository<Ventas, Long>{
	
	public Ventas findByIdUsuariosAndIdPujaAndActivo(Long id, Long idPuja, Boolean activo);
	
	public Ventas findByidVentasAndActivo(Long id, Boolean activo);
}
