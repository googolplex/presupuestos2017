package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

@Tab(properties="tfact, plazodegarantia")
@View(members= 
"Plazo de garantia [" + 
"tfact;" + 
"plazodegarantia;" + 
"];")

@Entity
@Table(name="PR_PLAZODEGARANTIA")
public class PlazoDeGarantia extends SuperClaseFeliz {
	/*@Required
	@Column(nullable=false,name="FCHGARANTIA")  
	private Date fchgarantia ; // fecha de garantia*/
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="TFACT",unique=true)
	private Long tfact = 0L; // numero de plazo de garantia
	
	@Required
	@Column(length=500,nullable=false,name="PLAZODEGARANTIA",unique=true)        
	private String plazodegarantia ;

	
	public Long getTfact() {
		return tfact;
	}

	public void setTfact(Long tfact) {
		this.tfact = tfact;
	}

	public String getPlazodegarantia() {
		return plazodegarantia.toUpperCase().trim();
	}

	public void setPlazodegarantia(String plazodegarantia) {
		this.plazodegarantia = plazodegarantia.toUpperCase().trim();
	}
	
	private Long sgteNroGarantia() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(tfact) as numerazo from PlazoDeGarantia");         
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
		this.setTfact(this.sgteNroGarantia()) ;
	} 
	@PreUpdate
	private void ultimoPaso() {
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent());
		super.setFchUltMod(mifechora)  ;
	}
}
