package com.multiventas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiventas.app.services.IUsuariosService;


@RestController
@PreAuthorize("hasAuthority('Preconfirmado')")
@RequestMapping("api/Confirma")
public class ConfirmaEmailRestController {
	@Autowired
	IUsuariosService usuariosService;
	
	@GetMapping("/email/{clave}")
	public ResponseEntity<?> usuarioExiste(@PathVariable(value="clave") String clave) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		
		boolean confirmado = usuariosService.confirmaEmail(email, clave);
		
		return new ResponseEntity<>(confirmado, HttpStatus.OK);
	}


}
