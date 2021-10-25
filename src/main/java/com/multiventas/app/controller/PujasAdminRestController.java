package com.multiventas.app.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiventas.app.dto.AbrirPujaDTO;
import com.multiventas.app.dto.AsignarProductoDTO;
import com.multiventas.app.dto.PujasOutDTO;
import com.multiventas.app.services.IPujasService;

@RestController
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("api/Admin/Pujas")
public class PujasAdminRestController {
	
	@Autowired
	IPujasService pujasService;
	
	@PostMapping("/abrir")
	public ResponseEntity<?> abrirPuja(@Valid @RequestBody AbrirPujaDTO abrirPujaDTO) {
		
		boolean out = pujasService.abrirPuja(abrirPujaDTO);

		return new ResponseEntity<>(out, HttpStatus.OK);
		
	}
	
	@GetMapping("/abierta")
	public ResponseEntity<?> pujaAbierta() {
		
		boolean out = pujasService.pujaAbierta();

		return new ResponseEntity<>(out, HttpStatus.OK);
		
	}
	
	@GetMapping("/cerrar")
	public ResponseEntity<?> cerrarPuja() {
		
		boolean out = pujasService.cerrarPuja();

		return new ResponseEntity<>(out, HttpStatus.OK);
		
	}
	
	@PostMapping("/asignar")
	public ResponseEntity<?> asignarProducto(@Valid @RequestBody AsignarProductoDTO asignarProductoDTO) {
		
		boolean out = pujasService.asignarProducto(asignarProductoDTO);

		return new ResponseEntity<>(out, HttpStatus.OK);
		
	}
	
	@GetMapping("/desasignar/{id}")
	public ResponseEntity<?> desasignarProducto(@PathVariable(value="id") Long id) {
		boolean out = pujasService.desasignarProducto(id);		
		return new ResponseEntity<>(out, HttpStatus.OK);
	}
	
	@GetMapping("/pujaAbierta")
	public ResponseEntity<?> getPuja() {
		
		PujasOutDTO out = pujasService.getPujaAbierta();
		
		if (out != null) {
			return new ResponseEntity<>(out, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/pujas/{from}/{to}")
	public ResponseEntity<?> getPujas(@PathVariable(value="from") Long from, @PathVariable(value="to") Long to) {
		
		List<PujasOutDTO> out = pujasService.getPujas(from, to);
		
		if (out != null) {
			return new ResponseEntity<>(out, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}

}
