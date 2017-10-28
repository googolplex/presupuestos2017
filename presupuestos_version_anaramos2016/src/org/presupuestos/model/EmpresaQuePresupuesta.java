package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;
//import org.presupuestos.validadores.*;

@Tab(properties="empNumero,ruc, empNombre,direccion,telefono, celular,email")
@View(members= 
"Informacion [" + 
"empNumero," +
"ruc," + 
"empNombre;" +
"direccion;" +
"pais," +
"ciudad;" +
"];" +
"Contacto [" + 
"telefono," + 
"email;" + 
"celular;" +
"]")

@Entity
@Table(name="PR_EMPRESAQUEPRESUPUESTA")
public class EmpresaQuePresupuesta extends SuperClaseFeliz{
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="EMPR_NUMERO",unique=true)
	private Long empNumero = 0L; // numero de empresa
	
	 /*@Required
	// @PropertyValidator(value=ValidadorNumEmpr.class,message="numero de empresa duplicado",onlyOnCreate=true)     
	 @Column(length=7,nullable=false,name="EMPR_NUMERO", unique=true)
	 private Long empNumero ;*/
	 
	 @Required
	 //@PropertyValidator(value=ValidadorNameEmpr.class,message="nombre de empresa duplicado")                       
	 @Column(length=200,nullable=false,name="EMPR_NOMBRE", unique=true)        
	 private String empNombre ;
	       
	 //@PropertyValidator(value=ValidadorRucEmpr.class,message="RUC duplicado")
	 @Column(length=20,nullable=false,name="RUC",unique=true)        
	 private String ruc ;   
	 
	 @Stereotype("MEMO")
	 @Column(length=500,nullable=false,name="DIRECCION")      
	 private String direccion ;      
	      
	 @Column(length=40,nullable=false,name="TELEFONO")       
	 private String telefono ;  
	 
	 @Column(length=40,nullable=false,name="CELULAR")       
	 private String celular ;
	 
	 @DescriptionsList(descriptionProperties="pais")
	 @ManyToOne(fetch=FetchType.LAZY,optional=false) 
	 @JoinColumn(name="PAIS_ID")
	 private PaisesCiudades pais ;
		
	 @DescriptionsList(descriptionProperties="ciudad")
	 @ManyToOne(fetch=FetchType.LAZY,optional=false) 
	 @JoinColumn(name="CIUDAD_ID")
	 private PaisesCiudades ciudad ;
	       
	 
	 
	 public PaisesCiudades getPais() {
		return pais;
	}

	public void setPais(PaisesCiudades pais) {
		this.pais = pais;
	}

	public PaisesCiudades getCiudad() {
		return ciudad;
	}

	public void setCiudad(PaisesCiudades ciudad) {
		this.ciudad = ciudad;
	}

	@Stereotype("email")    
	 @Column(length=40,nullable=false,name="EMAIL")  
	 private String email ;  
	       
	 public Long getEmpNumero() {
		return empNumero;
	}
	 
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEmpNumero(Long empNumero) {
		this.empNumero = empNumero;
	}

	public String getEmpNombre() {
		return empNombre.toUpperCase().trim();
	}

	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre.toUpperCase().trim();
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
	
	public String getDireccion() {
		return direccion.toUpperCase().trim();
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion.toUpperCase().trim();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email.toLowerCase().trim();
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase().trim();
	}

	private Long sgteNroEmpresa() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(empNumero) as numerazo from EmpresaQuePresupuesta");         
		ultimoNumero =  (Long) w.getSingleResult();
		if(ultimoNumero == null){
			nuevoNro = 1L;
		}else{
			nuevoNro = ultimoNumero + 1;
		}     
		return nuevoNro ;            
	}
	
	@PrePersist
	private void antesDeGrabar() {
		this.setEmpNumero(this.sgteNroEmpresa()) ;
	}
	
	@PreUpdate
	 private void ultimoPaso() {
		 Date mifechora = new java.util.Date() ;
		 super.setModificadoPor(Users.getCurrent()) ;
		 super.setFchUltMod(mifechora)  ;
	 }
}