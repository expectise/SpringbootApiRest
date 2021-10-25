package com.multiventas.app.services;

import java.util.List;

import com.multiventas.app.dto.ProductoNewDTO;
import com.multiventas.app.dto.ProductoOutDTO;
import com.multiventas.app.dto.ProductoUpdateDTO;

public interface IProductoService {
	
	public ProductoOutDTO nuevoProducto(ProductoNewDTO productoNewDTO);
	
	public ProductoOutDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO);
	
	public Long borrarProducto(Long id);
	
	public List<ProductoOutDTO> getProductos();
	
	public boolean existeProducto(Long id);

}
