package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;
//import org.presupuestos.validadores.*;

@Tab(properties="tfact, formadepago")
@View(members= 
"Forma de pago [" + 
"tfact;" + 
"formadepago;" + 
"];")

@Entity
@Table(name="PR_FORMADEPAGO")
public class FormaDePago extends SuperClaseFeliz {
	/*@Required
	//@PropertyValidator(value=Validador2014p.class,message="FormaDePago duplicado",onlyOnCreate=true)
	@Column(length=30,nullable=false,name="TFACT",unique=true)
	private String tfact ;*/
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="TFACT",unique=true)
	private Long tfact = 0L; // numero de forma de pago

	@Required
	//@PropertyValidator(value=Validador2014q.class,message="Nombre duplicado")
	@Column(length=500,nullable=false,name="FORMADEPAGO",unique=true)        
	private String formadepago ;

	

	public Long getTfact() {
		return tfact;
	}

	public void setTfact(Long tfact) {
		this.tfact = tfact;
	}

	
	
	public String getFormadepago() {
		return formadepago.toUpperCase().trim();
	}

	public void setFormadepago(String formadepago) {
		this.formadepago = formadepago.toUpperCase().trim();
	}

	private Long sgteNroPago() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(tfact) as numerazo from FormaDePago");         
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
		this.setTfact(this.sgteNroPago()) ;
	} 

	@PreUpdate
	private void ultimoPaso() {
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent()) ;
		super.setFchUltMod(mifechora)  ;
	}       
}
