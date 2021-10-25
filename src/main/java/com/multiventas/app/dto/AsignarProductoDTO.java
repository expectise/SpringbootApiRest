package com.multiventas.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignarProductoDTO implements Serializable {
	private static final long serialVersionUID = 7510864948841246158L;

	@NotNull
	private Long idProducto;
	
	@NotNull
	private Long idUsuarios;

}
