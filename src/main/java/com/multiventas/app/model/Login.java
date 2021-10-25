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
@Table(name = "login", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = "idLogin"))
public class Login implements Serializable {
	private static final long serialVersionUID = 3420957214888468665L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLogin", unique = true, nullable = false)
	private Long idLogin;
	
	@Column(name = "IdUsuario", nullable = false)
	private Long idUsuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false, insertable = false, updatable = false)
	
	private Usuarios usuarios;
	
	@Column(name = "FBToken", length = 100)
	private String fbtoken;
	
	@Column(name = "Activo")
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
