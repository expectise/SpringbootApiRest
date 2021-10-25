package com.multiventas.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;



@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Table(name = "pagos", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = "idPagos"))
public class Pagos implements Serializable {
	private static final long serialVersionUID = -3256605168432377142L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPagos", nullable = false)
	private Long idPagos;
	
	@Column(name = "idVentas", nullable = false)
	private Long idVentas;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creado")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date creado;
		

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaPago", length = 10)
	private Date fechaPago;
		
	@Column(name = "Activo", nullable = false)
	@ColumnDefault("1")
	private Boolean activo;
		
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Ventas.class)
	@JoinColumn(name = "idVentas", nullable = false, insertable = false, updatable = false)
		
	private Ventas ventas;
		
	@Column(name = "Monto", precision = 12, scale = 2)
	private BigDecimal monto;
	
	@Column(name = "MontoDescuento", precision = 12, scale = 2)
	private BigDecimal montoDescuento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Actualizado")
	private Date actualizado;

}
