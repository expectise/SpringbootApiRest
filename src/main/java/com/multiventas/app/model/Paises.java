package com.multiventas.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "paises", catalog = "multiventas")
public class Paises implements Serializable {
	private static final long serialVersionUID = -2432458299385213594L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPais", unique = true, nullable = false)
	private Long idPais;
	
	@Column(name = "Pais", nullable = false, length = 45)
	private String pais;
	
	@Column(name = "ValorEnvio", precision = 5)
	private BigDecimal valorEnvio;
	
	@Column(name = "PrecioExcento", precision = 5)
	private BigDecimal precioExcento;
	
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
	
	@OneToMany(mappedBy = "paises")
	private Set<Direcciones> direcciones = new HashSet<Direcciones>();

}
