package com.multiventas.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;


@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Table(name = "pujas", catalog = "multiventas")
public class Pujas implements Serializable {
	private static final long serialVersionUID = -7281383206669479957L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPujas", unique = true, nullable = false)
	private Long idPujas;
	
	@Column(name = "Titulo", length = 45)
	private String titulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FechaInicio")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date fechaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FechaFinal")
	private Date fechaFinal;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FecPriAbo", length = 10)
	private Date fecPriAbo;
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "pujas")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ventas> ventas;
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "pujas")
	private List<Asignaciones> asignaciones;
	
	@Column(name = "Activo")
	@ColumnDefault("1")
	private Boolean activo;


}
