package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

@Tab(properties="ivaNro,tipoIVA")
@View(members= 
"IVA [" + 
"ivaNro;" + 
"tipoIVA;" +
"]")
@Entity
@Table(name="PR_TIPOIVA")
public class TipoIVA extends SuperClaseFeliz{
	@ReadOnly       
	@Column(length=19,nullable=false,name="IVA_NUMERO",unique=true)
	private Long ivaNro = 0L; // numero de iva
	
	@Required                     
	@Column(length=80,nullable=false,name="TIPOIVA", unique=true)        
	private String tipoIVA ;
	
	
	public Long getIvaNro() {
		return ivaNro;
	}

	public void setIvaNro(Long ivaNro) {
		this.ivaNro = ivaNro;
	}

	
	public String getTipoIVA() {
		return tipoIVA.toUpperCase().trim();
	}

	public void setTipoIVA(String tipoIVA) {
		this.tipoIVA = tipoIVA.toUpperCase().trim();
	}

	private Long sgteNroIVA() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(ivaNro) as numerazo from TipoIVA");         
		ultimoNumero =  (Long) w.getSingleResult();
		if (ultimoNumero == null){
			nuevoNro = 1L;
		}else{
			nuevoNro = ultimoNumero + 1;
		}	     
		return nuevoNro;            
	}
	
	@PrePersist
	private void antesDeGrabar() {
		this.setIvaNro(this.sgteNroIVA()) ;
	} 

	@PreUpdate
	private void ultimoPaso() {
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent()) ;
		super.setFchUltMod(mifechora)  ;
	}
}
