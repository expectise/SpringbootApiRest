package com.multiventas.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiventas.app.dao.IProductoDAO;
import com.multiventas.app.dto.ProductoNewDTO;
import com.multiventas.app.dto.ProductoOutDTO;
import com.multiventas.app.dto.ProductoUpdateDTO;
import com.multiventas.app.model.Producto;
import com.multiventas.app.utils.Converters;

@Service
public class ProductoService implements IProductoService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IProductoDAO productoDAO;
	


	@Override
	public ProductoOutDTO nuevoProducto(ProductoNewDTO productoNewDTO) {
		
		Producto producto = modelMapper.map(productoNewDTO, Producto.class);
		
		producto.setCreado(new Date());
		
		producto.setActivo(true);
		
		Producto productoG = productoDAO.save(producto);
		
		
		ProductoOutDTO out = modelMapper.map(productoG, ProductoOutDTO.class);
		
		out.setCreado(Converters.dateToTimestamp(productoG.getCreado()));
		out.setActualizado(Converters.dateToTimestamp(productoG.getActualizado()));
		
		return out;

	}



	@Override
	public ProductoOutDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO) {
		
		Producto producto = productoDAO.findByIdProductoAndActivo(productoUpdateDTO.getIdProducto(), true);
		
		if (producto != null) {
		
		Producto productoC = modelMapper.map(productoUpdateDTO, Producto.class);
		
		
		productoC.setActualizado(new Date());
		productoC.setCreado(producto.getCreado());
		productoC.setActivo(producto.getActivo());
		
		Producto productoG = productoDAO.save(productoC);
		
	
		ProductoOutDTO out = modelMapper.map(productoG, ProductoOutDTO.class);
			
		out.setCreado(Converters.dateToTimestamp(productoG.getCreado()));
		out.setActualizado(Converters.dateToTimestamp(productoG.getActualizado()));
			
		return out;

		}else {
			return null;
		}
	}



	@Override
	public Long borrarProducto(Long id) {
		Producto producto = productoDAO.findByIdProductoAndActivo(id, true);
		
		if (producto == null) {
			return null;
		}else {
				producto.setActivo(false);
				producto.setActualizado(new Date());				
				productoDAO.save(producto);
				
				return producto.getIdProducto();
				
		}
		
		
	}



	@Override
	public List<ProductoOutDTO> getProductos() {
		List<Producto> productosL = productoDAO.findByActivo(true);
		
		List<ProductoOutDTO> productos = new ArrayList<ProductoOutDTO>();
	
		for(Producto productoIn : productosL){
			ProductoOutDTO productoOut = modelMapper.map(productoIn, ProductoOutDTO.class);
			
			productoOut.setCreado(Converters.dateToTimestamp(productoIn.getCreado()));
			productoOut.setActualizado(Converters.dateToTimestamp(productoIn.getActualizado()));
			
			productos.add(productoOut);
		}
		
		return productos;
	}



	@Override
	public boolean existeProducto(Long id) {
		Producto producto = productoDAO.findByIdProductoAndActivo(id, true);
		
		if (producto != null) {
			return true;
		}
		
		return false;
	}
	
	

}
