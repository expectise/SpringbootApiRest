package com.multiventas.app.services;

import java.math.BigDecimal;

public interface IDireccionesService {
	
	public BigDecimal costoEnvio(Long idUsuario);
	
	public BigDecimal precioExcento(Long idUsuario);
	
	public Long getdireccionId(Long idUsuario);

}
