package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;
import org.openxava.util.*;


@Tab(properties="inventarioNro, fechainventario, cliente.cliNombre")
@View(members= 
"Numero - Fecha [" + 
"inventarioNro;" + 
"fechainventario;" + 
"];" +
"Cliente [" + 
"cliente;" +
"];" + 
"Detalles [ " +
"atencion;" +
"detalle;" +
"];")
@Entity
@Table(name="PR_INVENTARIO")
public class Inventario extends SuperClaseFeliz {
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="inventarionro",unique=true)
	private Long inventarioNro = 0L; // numero de inventario
	
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	@Column(nullable=false,name="FCHINVENTARIO")  
	private Date fechainventario ; // fecha de inventario
	
	@DescriptionsList(descriptionProperties="cliNombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="CLIENTE_ID")
	private Clientes cliente ; 
	
	@DescriptionsList(descriptionProperties="atencion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="ATENCION_ID")
	private Presupuestos atencion ; 
	
	@DescriptionsList(descriptionProperties="detalle")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="DETALLE_ID")
	private Presupuestos detalle ;
	
	
	
	public Presupuestos getAtencion() {
		return atencion;
	}

	public void setAtencion(Presupuestos atencion) {
		this.atencion = atencion;
	}

	public Presupuestos getDetalle() {
		return detalle;
	}

	public void setDetalle(Presupuestos detalle) {
		this.detalle = detalle;
	}

	public Long getInventarioNro() {
		return inventarioNro;
	}

	public void setInventarioNro(Long inventarioNro) {
		this.inventarioNro = inventarioNro;
	}

	public Date getFechainventario() {
		return fechainventario;
	}

	public void setFechainventario(Date fechainventario) {
		this.fechainventario = fechainventario;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	private Long sgteNroInventario() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(inventarioNro) as numerazo from Inventario");         
		ultimoNumero =  (Long) w.getSingleResult();
		if (ultimoNumero == null){
			nuevoNro = 1L;
		}
		else{
			nuevoNro = ultimoNumero + 1;
		}     
		return nuevoNro ;            
	}
	
	/*antes de grabar calcula el sgte nro*/
	
	@PrePersist
	private void antesDeGrabar() {
		this.setInventarioNro(this.sgteNroInventario()) ;
	}  
	
	@PreUpdate
	public void ultimoPaso() {                      
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent()) ;
		super.setFchUltMod(mifechora);
	}

}
