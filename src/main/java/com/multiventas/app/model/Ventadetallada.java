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
@Table(name = "ventadetallada", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = "IdVentaDetallada"))
public class Ventadetallada implements Serializable {
	private static final long serialVersionUID = -7370826158881662290L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVentaDetallada", nullable = false)
	private Long idVentaDetallada;
	
	@Column(name = "IdVentas", nullable = false)
	private Long idVentas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdVentas", nullable = false, insertable = false, updatable = false)
	private Ventas ventas;
	
	@Column(name = "Precio", precision = 10)
	private BigDecimal precio;
	
	@Column(name = "Titulo", length = 50)
	private String titulo;
	
	@Column(name = "Descripcion", length = 500)
	private String descripcion;
	
	@Column(name = "CodigoProducto", length = 6)
	private String codigoProducto;
	
	@Column(name = "Activo", nullable = false)
	@ColumnDefault("1")
	private Boolean activo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creado")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date creado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Actualizado")
	private Date actualizado;

}
