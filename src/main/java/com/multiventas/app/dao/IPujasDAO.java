package com.multiventas.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.multiventas.app.model.Pujas;

public interface IPujasDAO extends CrudRepository<Pujas, Long>{
	
	@Query(value = "select p from Pujas p where p.fechaFinal is null and p.activo = 1 ORDER BY p.idPujas DESC")
	public Pujas findPujaAbierta();
	
	@Query(value = "select distinct p from Pujas p \r\n"
			+ "left outer join fetch p.ventas v \r\n"
			+ "where p.activo = 1 and p.fechaFinal between :from and :to")
	public List<Pujas> getPujas(Date from, Date to);
	
	@Query(value = "select distinct p from Pujas p \r\n"
			+ "left outer join fetch p.ventas v \r\n"
			+  "where p.idPujas= :id and p.activo = 1")
	public Pujas getPuja(Long id);
	
	@Query(value = "select distinct p from Pujas p \r\n"
			+ "left outer join fetch p.ventas v \r\n"
			+ "where p.fechaFinal is null and p.activo = 1")
	public Pujas getPujaAbierta();
	
	
	
}
