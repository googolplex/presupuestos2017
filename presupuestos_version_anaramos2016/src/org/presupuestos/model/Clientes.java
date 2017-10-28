package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;
//import org.presupuestos.validadores.*;

@Tab(properties="cliNumero,ruc,cliNombre,direccion,telefono, celular,email")
@View(members= 
"Informacion [" + 
"cliNumero," + 
"ruc;" +
"cliNombre;" +
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
@Table(name="PR_CLIENTES")
public class Clientes extends SuperClaseFeliz {

	@ReadOnly       
	@Column(length=19,nullable=false,name="CLI_NUMERO",unique=true)
	private Long cliNumero = 0L; // numero de cliente
	
	@Required
	@Column(length=80,nullable=false,name="CLI_NOMBRE", unique=true)        
	private String cliNombre ;
	
	@Required
	@Column(length=20,nullable=false,name="RUC",unique=true)        
	private String ruc ;    
    
	@Stereotype("MEMO")
	@Column(length=500,name="DIRECCION")
	private String direccion ;      
       
	@Column(length=40,name="TELEFONO")      
	private String telefono ;   
	
	@Column(length=40,name="CELULAR")      
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Stereotype("email")    
	@Column(length=40,name="EMAIL") 
	private String email ;  
       
	public Long getCliNumero() {
		return cliNumero;
	}

	public void setCliNumero(Long cliNumero) {
		this.cliNumero = cliNumero;
	}

	public String getCliNombre() {
		return  cliNombre.toUpperCase().trim();
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre.toUpperCase().trim();
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
	
	private Long sgteNroCliente() {
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select max(cliNumero) as numerazo from Clientes");         
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
		this.setCliNumero(this.sgteNroCliente()) ;
	} 

	@PreUpdate
	private void ultimoPaso() {
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent()) ;
		super.setFchUltMod(mifechora)  ;
	}
}
