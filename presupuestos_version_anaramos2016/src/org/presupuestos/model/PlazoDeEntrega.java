package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

@Tab(properties="tfact, plazodeentrega")
@View(members= 
"Plazo de entrega [" + 
"tfact;" + 
"plazodeentrega;" + 
"];")

@Entity
@Table(name="PR_PLAZODEENTREGA")
public class PlazoDeEntrega extends SuperClaseFeliz{
	/*@Required
	@Column(nullable=false,name="FCHENTREGA")  
	private Date fchentrega ; // fecha de entrega*/
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="TFACT",unique=true)
	private Long tfact = 0L; // numero de plazo de entrega
	
	@Required
	@Column(length=500,nullable=false,name="PLAZODEENTREGA",unique=true)        
	private String plazodeentrega ;

	

	public Long getTfact() {
		return tfact;
	}

	public void setTfact(Long tfact) {
		this.tfact = tfact;
	}

	
	
	
	public String getPlazodeentrega() {
		return plazodeentrega.toUpperCase().trim();
	}

	public void setPlazodeentrega(String plazodeentrega) {
		this.plazodeentrega = plazodeentrega.toUpperCase().trim();
	}

	private Long sgteNroEntrega() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(tfact) as numerazo from PlazoDeEntrega");         
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
		this.setTfact(this.sgteNroEntrega()) ;
	} 
	
	@PreUpdate
	private void ultimoPaso() {
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent());
		super.setFchUltMod(mifechora)  ;
	}
}
