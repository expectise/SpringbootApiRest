package com.multiventas.app.services;

import java.util.List;

import com.multiventas.app.dto.PaisNewDTO;
import com.multiventas.app.dto.PaisUpdateDTO;
import com.multiventas.app.dto.PaisesOutDTO;

public interface IPaisesService {
	public PaisesOutDTO nuevoPais(PaisNewDTO paisNewDTO);
	
	public PaisesOutDTO actualizarPais(PaisUpdateDTO paisUpdateDTO);
	
	public Long borrarPais(Long id);
	
	public List<PaisesOutDTO> getPaises();
}
