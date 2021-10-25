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
@Table(name = "direcciones", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = "IdDirecciones"))
public class Direcciones implements Serializable {
	private static final long serialVersionUID = 3689215875429521820L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDirecciones", unique = true, nullable = false)
	private Long idDirecciones;
	
	@Column(name = "IdUsuarios", nullable = false)
	private Long idUsuarios;
	
	@Column(name = "IdPais", nullable = false)
	private Long idPais;
	
	@JoinColumn(name = "IdPais", nullable = false, insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Paises paises;
	
	@JoinColumn(name = "IdUsuarios", nullable = false, insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuarios usuarios;
	
	@Column(name = "Calle", length = 45)
	private String calle;
	
	@Column(name = "NoExt", length = 5)
	private String noExt;
	
	@Column(name = "NoLong", length = 5)
	private String noLong;
	
	@Column(name = "Colonia", length = 45)
	private String colonia;
	
	@Column(name = "CP", length = 5)
	private String cp;
	
	@Column(name = "Estado", length = 45)
	private String estado;
	
	@Column(name = "Observaciones", length = 40)
	private String observaciones;
	
	@Column(name = "Telefono", length = 16)
	private String telefono;
	
	@Column(name = "Nombre", length = 50)
	private String nombre;
	
	@Column(name = "APPaterno", length = 50)
	private String appaterno;
	
	@Column(name = "APPMaterno", length = 50)
	private String appmaterno;
	
	@Column(name = "Activo")
	@ColumnDefault("1")
	private Boolean activo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creado")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date creado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Actualizado", length = 19)
	private Date actualizado;
	
	@Column(name = "Elegido")
	private Boolean elegido;
	

}
