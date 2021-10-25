package com.multiventas.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiventas.app.dao.IPaisesDAO;
import com.multiventas.app.dto.PaisNewDTO;
import com.multiventas.app.dto.PaisUpdateDTO;
import com.multiventas.app.dto.PaisesOutDTO;
import com.multiventas.app.model.Paises;
import com.multiventas.app.utils.Converters;

@Service
public class PaisesService implements IPaisesService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IPaisesDAO paisesDAO;

	@Override
	public PaisesOutDTO nuevoPais(PaisNewDTO paisNewDTO) {
		Paises pais = modelMapper.map(paisNewDTO, Paises.class);
		pais.setCreado(new Date());
		pais.setActivo(true);
		
		Paises paisG = paisesDAO.save(pais);
		
		PaisesOutDTO paisOut = modelMapper.map(paisG, PaisesOutDTO.class);
		
		paisOut.setCreado(Converters.dateToTimestamp(paisG.getCreado()));
		paisOut.setActualizado(Converters.dateToTimestamp(paisG.getActualizado()));
		
		return paisOut;
	}

	@Override
	public PaisesOutDTO actualizarPais(PaisUpdateDTO paisesUpdateDTO) {
		Paises pais = paisesDAO.findByIdPaisAndActivo(paisesUpdateDTO.getIdPais(), true);
		
		if (pais != null) {
			Paises paisC = modelMapper.map(paisesUpdateDTO, Paises.class);
			
			paisC.setActualizado(new Date());
			paisC.setCreado(pais.getCreado());
			paisC.setActivo(pais.getActivo());
			
			Paises paisG = paisesDAO.save(paisC);
			
			PaisesOutDTO out = modelMapper.map(paisG, PaisesOutDTO.class);
			
			out.setCreado(Converters.dateToTimestamp(paisG.getCreado()));
			out.setActualizado(Converters.dateToTimestamp(paisG.getActualizado()));
				
			return out;
			
		}else {
			return null;
		}
	}

	@Override
	public Long borrarPais(Long id) {
		Paises pais = paisesDAO.findByIdPaisAndActivo(id, true);
		if (pais == null) {
			return null;
		}else {
			pais.setActivo(false);
			pais.setActualizado(new Date());				
			
			paisesDAO.save(pais);
				
			return pais.getIdPais();
				
		}
	}

	@Override
	public List<PaisesOutDTO> getPaises() {
		List<Paises> paisesL = paisesDAO.findByActivo(true);
		
		List<PaisesOutDTO> paises = new ArrayList<PaisesOutDTO>();
	
		for(Paises productoIn : paisesL){
			PaisesOutDTO paisesOut = modelMapper.map(productoIn, PaisesOutDTO.class);
			
			paisesOut.setCreado(Converters.dateToTimestamp(productoIn.getCreado()));
			paisesOut.setActualizado(Converters.dateToTimestamp(productoIn.getActualizado()));
			
			paises.add(paisesOut);
		}
		
		return paises;
	}

}
