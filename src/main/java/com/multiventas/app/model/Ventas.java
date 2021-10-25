package com.multiventas.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;


@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Table(name = "ventas", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = "idVentas"))
public class Ventas implements Serializable {
	private static final long serialVersionUID = 57221921483246526L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Idventas", nullable = false)
	private Long idVentas;
	
	@Column(name = "IdUsuarios", nullable = false)
	private Long idUsuarios;
	
	@Column(name = "IdPuja", nullable = false)
	private Long idPuja;
	
	@Column(name = "IdDireccion")
	private Long idDireccion;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Direcciones.class)
	@JoinColumn(name = "IdDireccion", nullable = false, insertable = false, updatable = false)
	private Direcciones direcciones;
		
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Pujas.class)
	@JoinColumn(name = "IdPuja", nullable = false, insertable = false, updatable = false)
	private Pujas pujas;
		
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuarios.class)
	@JoinColumn(name = "IdUsuarios", nullable = false, insertable = false, updatable = false)
	private Usuarios usuarios;
		
	@Column(name = "Activo", nullable = false)
	@ColumnDefault("1")
	private Boolean activo;
	
	@Column(name = "Folio", length = 10)
	private String folio;
	
	@Column(name = "CostoEnvio", precision = 8)
	private BigDecimal costoEnvio;
	
	@Column(name = "PrecioExcento", precision = 12)
	private BigDecimal precioExcento;
	
	@Column(name = "Enviado")
	private Byte enviado;
	
	@Column(name = "Guia", length = 20)
	private String guia;
	
	@Column(name = "empresa", length = 60)
	private String empresa;
	
	@OneToMany(mappedBy = "ventas", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ventadetallada> ventadetallada;
	
	@OneToMany(mappedBy = "ventas", fetch = FetchType.LAZY)
	private List<Pagos> pagos;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creado")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date creado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Actualizado")
	private Date actualizado;
	
}
