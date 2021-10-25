package com.multiventas.app.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiventas.app.captcha.CaptchaValidator;
import com.multiventas.app.dto.LoginDTO;
import com.multiventas.app.dto.LoginDetailsDTO;
import com.multiventas.app.dto.UsuariosDTO;
import com.multiventas.app.services.IUsuariosService;

@RestController
@RequestMapping("Usuarios")
public class UsuariosRestController {
	
	@Autowired
	IUsuariosService usuariosService;
	

	@Autowired
	CaptchaValidator captchaValidator;
	
	@PostMapping("/nuevoUsuario")
	public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody UsuariosDTO usuariosDTO){
		 Boolean isValidCaptcha = captchaValidator.validateCaptcha(usuariosDTO.getCaptchaResponse()); 
		    if(!isValidCaptcha){
		    	return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		    }
		    
		if (usuariosService.usuarioExiste(usuariosDTO.getEmail())) {
			return new ResponseEntity<>(false, HttpStatus.CREATED);
		}else {
			usuariosService.nuevoUsuario(usuariosDTO);
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/usuarioExiste/{email}")
	public ResponseEntity<?> usuarioExiste(@PathVariable(value="email")String email) {
		return new ResponseEntity<>(usuariosService.usuarioExiste(email), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody final LoginDTO login){
		LoginDetailsDTO loginDetailsDTO = usuariosService.login(login);
		
		if (loginDetailsDTO != null) {
			return new ResponseEntity<>(loginDetailsDTO, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(loginDetailsDTO, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/solClaveContra/{email}")
	public ResponseEntity<?> solClaveContra(@PathVariable(value="email")String email){
		boolean response = usuariosService.recordarContra(email);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/reseteaContra/{email}/{clave}")
	public ResponseEntity<?> reseteaContra(@PathVariable(value="email") String email, @PathVariable(value="clave") String clave){
		boolean response = usuariosService.resetearContra(email, clave);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
