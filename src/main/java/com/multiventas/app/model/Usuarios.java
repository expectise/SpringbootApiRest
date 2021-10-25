package com.multiventas.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "usuarios", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = {"Email", "IdUsuarios"}))
public class Usuarios implements Serializable {
	private static final long serialVersionUID = -2157155584438625212L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdUsuarios", unique = true, nullable = false)
	private Long idUsuarios;
	
	@Column(name = "Nickname", nullable = false, length = 50)
	private String nickname;
	
	@Column(name = "Contrasena", nullable = false, length = 30)
	private String contrasena;
	
	@Column(name = "Nombre", length = 50)
	private String nombre;
	
	@Column(name = "APPaterno", length = 50)
	private String appaterno;
	
	@Column(name = "APMaterno", length = 50)
	private String apmaterno;
	
	@Column(name = "Telefono", length = 16)
	private String telefono;
	
	@Column(name = "Email", unique = true, length = 100)
	private String email;
	
	@Column(name = "EsAdmin")
	@ColumnDefault("0")
	private Boolean esAdmin;
	
	@Column(name = "Activo")
	@ColumnDefault("1")
	private Boolean activo;
	
	@Column(name = "Confirmado")
	@ColumnDefault("0")
	private Boolean confirmado;
	
	@Column(name = "Latitud", precision = 8, scale = 6)
	private BigDecimal latitud;
	
	@Column(name = "Longitud", precision = 9, scale = 6)
	private BigDecimal longitud;
	
	@Column(name = "IP", length = 15)
	private String ip;
	
	@Column(name = "Confirmacion", length = 4)
	private String confirmacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecConMsg")
	private Date fecConMsg;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecConfirma")
	private Date fecConfirma;
	
	@Column(name = "RecordarContra", length = 6)
	private String recordarContra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecContraMsg")
	private Date fecContraMsg;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecContrafirma")
	private Date fecContrafirma;
	
	@OneToMany(mappedBy = "usuarios")
	private List<Ventas> ventas;
	
	@OneToMany(mappedBy = "usuarios")
	private List<Asignaciones> asignaciones;
	
	@OneToMany(mappedBy = "usuarios")
	private List<Direcciones> direcciones;
	
	@OneToMany(mappedBy = "usuarios")
	private List<Login> login;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creado")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date creado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Actualizado")
	private Date actualizado;

}
