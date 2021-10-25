package com.multiventas.app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiventas.app.dao.IAsignacionesDAO;
import com.multiventas.app.dao.IProductoDAO;
import com.multiventas.app.dao.IPujasDAO;
import com.multiventas.app.dao.IVentadetalladaDAO;
import com.multiventas.app.dao.IVentasDAO;
import com.multiventas.app.dto.AbrirPujaDTO;
import com.multiventas.app.dto.AsignarProductoDTO;
import com.multiventas.app.dto.PujasOutDTO;
import com.multiventas.app.dto.VentadetalladaOutDTO;
import com.multiventas.app.dto.VentasOutDTO;
import com.multiventas.app.model.Asignaciones;
import com.multiventas.app.model.Producto;
import com.multiventas.app.model.Pujas;
import com.multiventas.app.model.Ventadetallada;
import com.multiventas.app.model.Ventas;
import com.multiventas.app.utils.Converters;
import com.multiventas.app.utils.RandomChars;

@Service
public class PujasService implements IPujasService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IPujasDAO pujasDAO;
	
	@Autowired
	private IUsuariosService usuariosService;
	
	@Autowired
	private IAsignacionesDAO asignacionesDAO;
	
	
	@Autowired
	private IVentasDAO ventasDAO;
	
	@Autowired
	private IDireccionesService direccionesService;
	
	@Autowired
	private IProductoDAO productoDAO;
	
	@Autowired
	private IVentadetalladaDAO ventadetalladaDAO;
	
 
	
	@Override
	public boolean abrirPuja(AbrirPujaDTO abrirPujaDTO) {
		Pujas puja = pujasDAO.findPujaAbierta();
		
		if (puja == null) {
			
			Date hoy = new Date();
			Date priAbo = Converters.fromTimestamp(abrirPujaDTO.getFecPriAbo());
			if (priAbo.before(hoy)) {
				return false;
			}else {
				puja = new Pujas();
				puja.setTitulo(abrirPujaDTO.getTitulo());
				puja.setFechaInicio(new Date());
				puja.setFecPriAbo(priAbo);
				pujasDAO.save(puja);
				return true;
			}
			
			
		}else {
			return false;
		}
		
	}

	@Override
	public boolean pujaAbierta() {
		Pujas puja = pujasDAO.findPujaAbierta();
		
		if (puja == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean cerrarPuja() {
	Pujas puja = pujasDAO.findPujaAbierta();
		
	if (puja != null) {
			puja.setFechaFinal(new Date());
			
			pujasDAO.save(puja);
			
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean asignarProducto(AsignarProductoDTO asignarProductoDTO) {

		String role = usuariosService.getUserRole(asignarProductoDTO.getIdUsuarios());
	
		Producto producto = productoDAO.findByIdProductoAndActivo(asignarProductoDTO.getIdProducto(), true);
		
		
		
		
		if (role.equals("Usuario") && producto != null) {
			Pujas puja = pujasDAO.findPujaAbierta();
			if (puja != null) {
					Asignaciones asignacion = new Asignaciones();
					
					asignacion.setActivo(true);
					asignacion.setCreado(new Date());
					asignacion.setIdProducto(asignarProductoDTO.getIdProducto());
					asignacion.setIdUsuarios(asignarProductoDTO.getIdUsuarios());
					asignacion.setIdPujas(puja.getIdPujas());
					asignacionesDAO.save(asignacion);
					
					Ventas venta = ventasDAO.findByIdUsuariosAndIdPujaAndActivo(asignarProductoDTO.getIdUsuarios(), puja.getIdPujas(), true);
					
					Long ventaId;
					
					if (venta == null) {
						
						String folio = RandomChars.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890",6);
						BigDecimal costoEnvio = direccionesService.costoEnvio(asignarProductoDTO.getIdUsuarios());
						BigDecimal precioExcento = direccionesService.precioExcento(asignarProductoDTO.getIdUsuarios());
						Long direccionID = direccionesService.getdireccionId(asignarProductoDTO.getIdUsuarios());
						
						
						venta = new Ventas();
						venta.setActivo(true);
						venta.setCreado(new Date());
						venta.setFolio(folio);
						venta.setCostoEnvio(costoEnvio);
						venta.setIdPuja(puja.getIdPujas());
						venta.setPrecioExcento(precioExcento);
						venta.setIdUsuarios(asignarProductoDTO.getIdUsuarios());
						venta.setIdDireccion(direccionID);

						Ventas ventag = ventasDAO.save(venta);
						
						ventaId = ventag.getIdVentas();
					}else {
						ventaId = venta.getIdVentas();
						
						venta.setActualizado(new Date());
						ventasDAO.save(venta);
						
			
					}
					
					Ventadetallada ventadetallada = new Ventadetallada();
					
					ventadetallada.setActivo(true);
					ventadetallada.setCodigoProducto(producto.getCodigoProducto());
					ventadetallada.setCreado(new Date());
					ventadetallada.setDescripcion(producto.getDescripcion());
					ventadetallada.setIdVentas(ventaId);
					ventadetallada.setPrecio(producto.getPrecio());
					ventadetallada.setTitulo(producto.getTitulo());
					
					ventadetalladaDAO.save(ventadetallada);
					
					return true;
				}
		}
		
	return false;
	}

	@Override
	public PujasOutDTO getPujaAbierta() {
		Pujas puja = pujasDAO.getPujaAbierta();
		
		if (puja != null) {
			List<Ventas> ventas = puja.getVentas();
			
			
			List<VentasOutDTO> ventasOutList = new ArrayList<VentasOutDTO>();
			
			
			for (Ventas venta : ventas) {
				if (venta.getActivo()) {
					List<Ventadetallada> ventadetalladas = ventadetalladaDAO.findByIdVentasAndActivo(venta.getIdVentas(), true);
					
					VentasOutDTO ventasOut =  modelMapper.map(venta, VentasOutDTO.class);
					
					ventasOut.setActualizado(Converters.dateToTimestamp(venta.getActualizado()));
					ventasOut.setCreado(Converters.dateToTimestamp(venta.getCreado()));
					
					
					List<VentadetalladaOutDTO> detalleOutList = new ArrayList<VentadetalladaOutDTO>();
					
					for (Ventadetallada detalle : ventadetalladas) {
						VentadetalladaOutDTO detalleOut = modelMapper.map(detalle, VentadetalladaOutDTO.class);
						
						detalleOut.setActualizado(Converters.dateToTimestamp(detalle.getActualizado()));
						detalleOut.setCreado(Converters.dateToTimestamp(detalle.getCreado()));
	
						
						detalleOutList.add(detalleOut);
						
					}
					
					ventasOut.setVentadetalladaOutDTO(detalleOutList);
					
					ventasOutList.add(ventasOut);
				}
			}
			
			PujasOutDTO pujaOut = modelMapper.map(puja, PujasOutDTO.class);
			pujaOut.setFechaInicio(Converters.dateToTimestamp(puja.getFechaInicio()));
			pujaOut.setFechaFinal(Converters.dateToTimestamp(puja.getFechaFinal()));
			pujaOut.setFecPriAbo(Converters.dateToTimestamp(puja.getFecPriAbo()));
			
			pujaOut.setVentasOutDTO(ventasOutList);
			
			return pujaOut;
			
		}
		
		return null;
	}

	@Override
	public List<PujasOutDTO> getPujas(Long from, Long to) {
		Date dateFrom = Converters.fromTimestamp(from);
		Date dateTo = Converters.fromTimestamp(to);
		
		List<Pujas> pujas = pujasDAO.getPujas(dateFrom, dateTo);
		
		if (pujas != null) {
			List<PujasOutDTO> PujasOutList = new ArrayList<PujasOutDTO>();
			
			for (Pujas puja : pujas) {
				List<Ventas> ventas = puja.getVentas();
				
				
				List<VentasOutDTO> ventasOutList = new ArrayList<VentasOutDTO>();
				
				
				for (Ventas venta : ventas) {
					if (venta.getActivo()) {
						List<Ventadetallada> ventadetalladas = ventadetalladaDAO.findByIdVentasAndActivo(venta.getIdVentas(), true);
						
						VentasOutDTO ventasOut =  modelMapper.map(venta, VentasOutDTO.class);
						
						ventasOut.setActualizado(Converters.dateToTimestamp(venta.getActualizado()));
						ventasOut.setCreado(Converters.dateToTimestamp(venta.getCreado()));
						
						
						List<VentadetalladaOutDTO> detalleOutList = new ArrayList<VentadetalladaOutDTO>();
						
						for (Ventadetallada detalle : ventadetalladas) {
							VentadetalladaOutDTO detalleOut = modelMapper.map(detalle, VentadetalladaOutDTO.class);
							
							detalleOut.setActualizado(Converters.dateToTimestamp(detalle.getActualizado()));
							detalleOut.setCreado(Converters.dateToTimestamp(detalle.getCreado()));
		
							
							detalleOutList.add(detalleOut);
							
						}
						
						ventasOut.setVentadetalladaOutDTO(detalleOutList);
						
						ventasOutList.add(ventasOut);
					}
				}
				
				PujasOutDTO pujaOut = modelMapper.map(puja, PujasOutDTO.class);
				pujaOut.setFechaInicio(Converters.dateToTimestamp(puja.getFechaInicio()));
				pujaOut.setFechaFinal(Converters.dateToTimestamp(puja.getFechaFinal()));
				pujaOut.setFecPriAbo(Converters.dateToTimestamp(puja.getFecPriAbo()));
				
				pujaOut.setVentasOutDTO(ventasOutList);
				
				PujasOutList.add(pujaOut);
			
			}
			
			return PujasOutList;
		}
		return null;
	}

	@Override
	public boolean desasignarProducto(Long id) {
		// TODO Auto-generated method stub
		Ventadetallada detalle = ventadetalladaDAO.findByIdVentaDetalladaAndActivo(id, true);
		
		if (detalle == null) 
			return false;
		
		
		int existentes = ventadetalladaDAO.findByIdVentasAndActivo(detalle.getIdVentas(), true).size();
		
		if (existentes == 1) {
			Ventas ventas = ventasDAO.findByidVentasAndActivo(detalle.getIdVentas(), true);
			ventas.setActivo(false);
			ventas.setActualizado(new Date());
			
			ventasDAO.save(ventas);
		}
		
		detalle.setActivo(false);
		detalle.setActualizado(new Date());
		ventadetalladaDAO.save(detalle);
		
		return true;
	}
	
}