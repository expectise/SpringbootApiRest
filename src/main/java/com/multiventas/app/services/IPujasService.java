package com.multiventas.app.services;

import java.util.List;

import com.multiventas.app.dto.AbrirPujaDTO;
import com.multiventas.app.dto.AsignarProductoDTO;
import com.multiventas.app.dto.PujasOutDTO;


public interface IPujasService {
	public boolean abrirPuja(AbrirPujaDTO abrirPujaDTO);
	public boolean pujaAbierta();
	public boolean cerrarPuja();
	public boolean asignarProducto(AsignarProductoDTO asignarProductoDTO);
	public PujasOutDTO getPujaAbierta();
	public List<PujasOutDTO> getPujas(Long from, Long to);
	public boolean desasignarProducto(Long id);
}
