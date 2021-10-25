package com.multiventas.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.multiventas.app.model.Ventadetallada;

public interface IVentadetalladaDAO extends CrudRepository<Ventadetallada, Long>{
	public Ventadetallada findByIdVentaDetalladaAndActivo(Long id, Boolean activo);
	public List<Ventadetallada> findByIdVentasAndActivo(Long id, Boolean activo);
}
