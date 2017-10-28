package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

@Tab(properties="tfact, estado")
@View(members= 
"Estados presupuesto [" + 
"tfact;" + 
"estado;" + 
"];")

@Entity
@Table(name="PR_ESTADOSPRESUPUESTO")
public class EstadosPresupuesto extends SuperClaseFeliz {
	/*@Required
	@Column(length=7,nullable=false,name="TFACT",unique=true)
	private String tfact ;*/
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="TFACT",unique=true)
	private Long tfact = 0L; // numero de estado de presupuesto

	@Required
	@Column(length=80,nullable=false,name="ESTADO",unique=true)	
	private String estado ;


	public Long getTfact() {
		return tfact;
	}

	public void setTfact(Long tfact) {
		this.tfact = tfact;
	}

	
	
	public String getEstado() {
		return estado.toUpperCase().trim();
	}

	public void setEstado(String estado) {
		this.estado = estado.toUpperCase().trim();
	}

	private Long sgteNroEstado() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(tfact) as numerazo from EstadosPresupuesto");         
		ultimoNumero =  (Long) w.getSingleResult();
		if (ultimoNumero == null){
			nuevoNro = 1L;
		}
		else{
			nuevoNro = ultimoNumero + 1;
		}     
		return nuevoNro ;            
	}
	
	@PrePersist
	private void antesDeGrabar() {
		this.setTfact(this.sgteNroEstado()) ;
	} 
	
	
	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}