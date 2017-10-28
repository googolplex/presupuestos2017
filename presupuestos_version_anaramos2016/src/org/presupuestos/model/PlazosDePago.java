package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

@Tab(properties="tfact, plazodepago")
@View(members= 
"Plazos de pago [" + 
"tfact;" + 
"plazodepago;" + 
"];")

@Entity
@Table(name="PR_PLAZOSDEPAGO")
public class PlazosDePago extends SuperClaseFeliz {
	/*@Required
	@Column(nullable=false,name="FCHDEPAGO")  
	private Date fchdepago ; // fecha de pago*/
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="TFACT",unique=true)
	private Long tfact = 0L; // numero de plazo de garantia
	
	@Required
	@Column(length=500,nullable=false,name="PLAZODEPAGO",unique=true)        
	private String plazodepago ;

	

	public Long getTfact() {
		return tfact;
	}

	public void setTfact(Long tfact) {
		this.tfact = tfact;
	}

	
	
	public String getPlazodepago() {
		return plazodepago.toUpperCase().trim();
	}

	public void setPlazodepago(String plazodepago) {
		this.plazodepago = plazodepago.toUpperCase().trim();
	}

	private Long sgteNroPPago() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(tfact) as numerazo from PlazosDePago");         
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
		this.setTfact(this.sgteNroPPago()) ;
	} 

	@PreUpdate
	private void ultimoPaso() {
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent());
		super.setFchUltMod(mifechora)  ;
	}
}
