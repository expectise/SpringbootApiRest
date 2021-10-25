package com.multiventas.app.model;

import java.io.Serializable;
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
@Table(name = "asignaciones", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = "IdAsignaciones"))
public class Asignaciones implements Serializable {
	private static final long serialVersionUID = 7246027544383359373L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdAsignaciones", unique = true, nullable = false)
	private Long idAsignaciones;
	
	@Column(name = "IdPujas", nullable = false)
	private Long idPujas;

	
	@Column(name = "IdProducto", nullable = false)
	private Long idProducto;
	
	@Column(name = "IdUsuarios", nullable = false)
	private Long idUsuarios;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Producto.class)
	@JoinColumn(name = "IdProducto", nullable = false, insertable = false, updatable = false)
	
	private Producto producto;
		
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Pujas.class)
	@JoinColumn(name = "IdPujas", nullable = false, insertable = false, updatable = false)
	private Pujas pujas;

		
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuarios.class)
	@JoinColumn(name = "IdUsuarios", nullable = false, insertable = false, updatable = false)
	private Usuarios usuarios;
		
	
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creado")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date creado;
	
	
	@Column(name = "Activo")
	@ColumnDefault("1")
	private Boolean activo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Actualizado")
	private Date actualizado;

	

}
