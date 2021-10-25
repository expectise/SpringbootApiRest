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
@Table(name = "producto", catalog = "multiventas", uniqueConstraints = @UniqueConstraint(columnNames = "IdProducto"))
public class Producto implements Serializable {
	private static final long serialVersionUID = -6608080830891567152L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdProducto", unique = true, nullable = false)
	private Long idProducto;
	
	@Column(name = "Titulo", length = 50)
	private String titulo;
	
	@Column(name = "Descripcion", length = 500)
	private String descripcion;
	
	@Column(name = "Precio", precision = 10, scale = 2)
	private BigDecimal precio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Creado")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date creado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Actualizado")
	private Date actualizado;
	
	@Column(name = "Activo")
	@ColumnDefault("1")
	private Boolean activo;
	
	@Column(name = "CodigoProducto", length = 6)
	private String codigoProducto;
	
	@OneToMany(mappedBy = "producto")
	private List<Asignaciones> asignaciones;

}
