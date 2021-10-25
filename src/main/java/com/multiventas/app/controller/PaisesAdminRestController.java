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

import com.multiventas.app.dto.PaisNewDTO;
import com.multiventas.app.dto.PaisUpdateDTO;
import com.multiventas.app.dto.PaisesOutDTO;
import com.multiventas.app.services.IPaisesService;

@RestController
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("api/Admin/Paises")
public class PaisesAdminRestController {
	@Autowired
	IPaisesService paisesService;
	
	@PostMapping("/nuevoPais")
	public ResponseEntity<?> nuevoPais(@Valid @RequestBody PaisNewDTO paisNewDTO){
		PaisesOutDTO productoOUT = paisesService.nuevoPais(paisNewDTO);
		
		return new ResponseEntity<>(productoOUT, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/actualizarPais")
	public ResponseEntity<?> actualizarPais(@Valid @RequestBody PaisUpdateDTO paisUpdateDTO){
		PaisesOutDTO paisOUT = paisesService.actualizarPais(paisUpdateDTO);
		
		if (paisOUT != null) {
			return new ResponseEntity<>(paisOUT, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/borrarPais/{id}")
	public ResponseEntity<?> borrarPais(@PathVariable(value="id") Long id) {
			
		Long idDeleted = paisesService.borrarPais(id);
		
		if (idDeleted != null) {
			return new ResponseEntity<>(idDeleted, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/paises")
	public ResponseEntity<?> getPaises() {
			
		List<PaisesOutDTO> paises = paisesService.getPaises();
		
		if (paises != null) {
			return new ResponseEntity<>(paises, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}

}
