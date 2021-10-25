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

import com.multiventas.app.dto.ProductoNewDTO;
import com.multiventas.app.dto.ProductoOutDTO;
import com.multiventas.app.dto.ProductoUpdateDTO;
import com.multiventas.app.services.IProductoService;

@RestController
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("api/Admin/Producto")
public class ProductosAdminRestController {
	
	@Autowired
	IProductoService productoService;
	
	@PostMapping("/nuevoProducto")
	public ResponseEntity<?> nuevoProducto(@Valid @RequestBody ProductoNewDTO productoNewDTO){
		ProductoOutDTO productoOUT = productoService.nuevoProducto(productoNewDTO);
		
		return new ResponseEntity<>(productoOUT, HttpStatus.CREATED);
	}
	
	@PostMapping("/actualizarProducto")
	public ResponseEntity<?> actualizarProducto(@Valid @RequestBody ProductoUpdateDTO productoUpdateDTO){
		ProductoOutDTO productoOUT = productoService.actualizarProducto(productoUpdateDTO);
		
		if (productoOUT != null) {
			return new ResponseEntity<>(productoOUT, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/borrarProducto/{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable(value="id") Long id) {
			
		Long idDeleted = productoService.borrarProducto(id);
		
		if (idDeleted != null) {
			return new ResponseEntity<>(idDeleted, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/productos")
	public ResponseEntity<?> getProductos() {
			
		List<ProductoOutDTO> productos = productoService.getProductos();
		
		if (productos != null) {
			return new ResponseEntity<>(productos, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	

}