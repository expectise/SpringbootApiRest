package com.multiventas.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiventas.app.dto.UsuariosOutDTO;
import com.multiventas.app.services.IUsuariosService;

@RestController
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("api/Admin/Usuarios")
public class UsuariosAdminRestController {
	@Autowired
	IUsuariosService usuariosService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<?> getUsuarios() {
			
		List<UsuariosOutDTO> usuarios = usuariosService.getUsuarios();
		
		if (usuarios != null) {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
}
