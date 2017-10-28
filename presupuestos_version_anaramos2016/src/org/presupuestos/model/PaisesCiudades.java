package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

@Tab(properties="paisCiudadNro,pais,ciudad")
@View(members= 
"Pais -Ciudad [" + 
"paisCiudadNro;" +
"pais;" + 
"ciudad;" +
"]")
@Entity
@Table(name="PR_PAISESCIUDADES")
public class PaisesCiudades extends SuperClaseFeliz{
	@ReadOnly       
	@Column(length=19,nullable=false,name="paisCiudadNro",unique=true)
	private Long paisCiudadNro = 0L; // numero de pais y ciudad
	
	@Required
	@Column(length=80,nullable=false,name="PAIS", unique=false)        
	private String pais ;
	
	@Required
	@Column(length=80,nullable=false,name="CIUDAD", unique=true)        
	private String ciudad ;
	
	public String getPais() {
		return pais.toUpperCase().trim();
	}

	public void setPais(String pais) {
		this.pais = pais.toUpperCase().trim();
	}

	public String getCiudad() {
		return ciudad.toUpperCase().trim();
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad.toUpperCase().trim();
	}

	public Long getPaisCiudadNro() {
		return paisCiudadNro;
	}

	public void setPaisCiudadNro(Long paisCiudadNro) {
		this.paisCiudadNro = paisCiudadNro;
	}
	

	private Long sgteNroPaisCiudad() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(paisCiudadNro) as numerazo from PaisesCiudades");         
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
		this.setPaisCiudadNro(this.sgteNroPaisCiudad()) ;
	} 

	@PreUpdate
	private void ultimoPaso() {
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent()) ;
		super.setFchUltMod(mifechora)  ;
	}
}
