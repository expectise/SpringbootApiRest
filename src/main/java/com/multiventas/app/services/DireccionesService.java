package com.multiventas.app.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.multiventas.app.dao.IDireccionesDAO;
import com.multiventas.app.dao.IPaisesDAO;
import com.multiventas.app.model.Direcciones;
import com.multiventas.app.model.Paises;

@Service
public class DireccionesService implements IDireccionesService{

	@Autowired
	IPaisesDAO paisesDAO;
	
	@Autowired
	IDireccionesDAO direccionesDAO;
	
	@Override
	public BigDecimal costoEnvio(Long idUsuario) {
		Direcciones direccion = direccionesDAO.findByIdUsuariosAndActivoAndElegido(idUsuario,true ,true);
		
		if (direccion != null) {
			
			Paises pais = paisesDAO.findByIdPaisAndActivo(direccion.getIdPais(), true);
		
			if (pais != null) 
				return pais.getValorEnvio();
						
		}
		
		return new BigDecimal("0");
	}

	@Override
	public BigDecimal precioExcento(Long idUsuario) {
		Direcciones direccion = direccionesDAO.findByIdUsuariosAndActivoAndElegido(idUsuario,true ,true);
		
		if (direccion != null) {
			
			Paises pais = paisesDAO.findByIdPaisAndActivo(direccion.getIdPais(), true);
		
			if (pais != null) 
				return pais.getPrecioExcento();
						
		}
		
		return new BigDecimal("0");
	}

	@Override
	public Long getdireccionId(Long idUsuario) {
		Direcciones direccion = direccionesDAO.findByIdUsuariosAndActivoAndElegido(idUsuario,true ,true);
		
		if (direccion != null)
			return direccion.getIdDirecciones();
		
		return null;
	}

}
