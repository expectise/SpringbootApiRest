package com.multiventas.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.multiventas.app.model.Usuarios;

public interface IUsuariosDAO extends CrudRepository<Usuarios, Long> {

	public Optional<Usuarios> findByEmailAndActivo(String email, Boolean activo);
	
	public Optional<Usuarios> findByEmailAndContrasenaAndActivo(String email, String contrasena, Boolean activo);

	public Optional<Usuarios> findByEmail(String email);
	
	public Optional<Usuarios> findByIdUsuariosAndActivo(Long id, Boolean activo);
	
	@Query(value = "select u from Usuarios u where u.esAdmin = 0")
	public List<Usuarios> getUsuarios();
}
