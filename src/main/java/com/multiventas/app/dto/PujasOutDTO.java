package com.multiventas.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PujasOutDTO implements Serializable{
	private static final long serialVersionUID = 5702568317627289270L;

	private Long idPujas;
	
	private String titulo;

	private Long fechaInicio;

	private Long fechaFinal;

	private Long fecPriAbo;
	
	private List<VentasOutDTO> ventasOutDTO;
	
}
