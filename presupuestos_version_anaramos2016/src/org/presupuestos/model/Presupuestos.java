package org.presupuestos.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;
import org.openxava.util.*;


@Tab(properties="presupuestoNro,fechapresupuesto, cliente.cliNombre, empresa.empNombre, estado.estado,monto, atencion, cargoAtencion, firmadoPor, cargoFirmante")
@View(members= 
"Numero - Fecha [" + 
"presupuestoNro," + 
"fechapresupuesto;" + 
"];" +
"Cliente - Empresa - Estado [" + 
"cliente," + 
"estado;" + 
"empresa;" + 
"];" + 
"Plazos [ " +
"formadepago," +
"plazoentrega;" +
"plazogarantia," +
"plazopago;" +
"];" +
"Detalles [ " +
"monto," +
"tipoIVA;" + 
"atencion," +
"cargoAtencion;" +
"firmadoPor," +
"cargoFirmante;" + 
"detalle;" +
"condicionesGenerales;" + 
"];")

@Entity
@Table(name="PR_PRESUPUESTOS")
public class Presupuestos extends SuperClaseFeliz{
	
	@ReadOnly       
	@Column(length=19,nullable=false,name="presupuestonro",unique=true)
	private Long presupuestoNro = 0L; // numero de presupuesto
	
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	@Column(nullable=false,name="FCHPRESUPUESTO")  
	private Date fechapresupuesto ; // fecha de presupuesto
	
	@Required
	@Column(length=80,nullable=false,name="ATENCION",unique=false)        
	private String atencion ;
	
	@Required
	@Stereotype("MEMO")
	@Column(length=500,nullable=false,name="DETALLE",unique=false)        
	private String detalle ;
	
	@Required
	@Column(length=19,nullable=false,name="MONTO",unique=false)        
	private Long monto ;
	
	@Required
	@Column(length=80,nullable=false,name="FIRMADOPOR",unique=false)        
	private String firmadoPor ;
	
	@Required
	@Column(length=80,nullable=false,name="CARGOATENCION",unique=false)        
	private String cargoAtencion ;
	
	@Required
	@Column(length=80,nullable=false,name="CARGOFIRMANTE",unique=false)        
	private String cargoFirmante ;
	
	@Required
	@Stereotype("MEMO")
	@Column(length=500, nullable=false, name="CONDICIONES_GENERALES", unique=false)
	private String condicionesGenerales;

	@DescriptionsList(descriptionProperties="cliNombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="CLIENTE_ID")
	private Clientes cliente ;                   
    
	@DescriptionsList(descriptionProperties="empNombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="EMPRESA_ID")
	private EmpresaQuePresupuesta empresa ;         
	
	@DescriptionsList(descriptionProperties="estado")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="ESTADO_ID")
	private EstadosPresupuesto estado ;
	
	@DescriptionsList(descriptionProperties="formadepago")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="FORMADEPAGO_ID")
	private FormaDePago formadepago ;

	@DescriptionsList(descriptionProperties="plazodeentrega")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="PLAZODEENTREGA_ID")
	private PlazoDeEntrega plazoentrega ;
	
	@DescriptionsList(descriptionProperties="plazodegarantia")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="PLAZODEGARANTIA_ID")
	private PlazoDeGarantia plazogarantia ;
	
	@DescriptionsList(descriptionProperties="plazodepago")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="PLAZOSDEPAGO_ID")
	private PlazosDePago plazopago;
	
	@DescriptionsList(descriptionProperties="tipoIVA")
	@ManyToOne(fetch=FetchType.LAZY,optional=false) 
	@JoinColumn(name="TIPOIVA_ID")
	private TipoIVA tipoIVA;
	
	
	/*getters and setters*/
	
	public TipoIVA getTipoIVA() {
		return tipoIVA;
	}

	public void setTipoIVA(TipoIVA tipoIVA) {
		this.tipoIVA = tipoIVA;
	}

	public String getFirmadoPor() {
		return firmadoPor.toUpperCase().trim();
	}

	public void setFirmadoPor(String firmadoPor) {
		this.firmadoPor = firmadoPor.toUpperCase().trim();
	}

	
	
	public String getCargoAtencion() {
		return cargoAtencion.toUpperCase().trim();
	}

	public void setCargoAtencion(String cargoAtencion) {
		this.cargoAtencion = cargoAtencion.toUpperCase().trim();
	}

	public String getCargoFirmante() {
		return cargoFirmante.toUpperCase().trim();
	}

	public void setCargoFirmante(String cargoFirmante) {
		this.cargoFirmante = cargoFirmante.toUpperCase().trim();
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}
	
	public Long getPresupuestoNro() {
		return presupuestoNro;
	}

	public void setPresupuestoNro(Long presupuestoNro) {
		this.presupuestoNro = presupuestoNro;
	}

	public Date getFechapresupuesto() {
		return fechapresupuesto;
	}

	public void setFechapresupuesto(Date fechapresupuesto) {
		this.fechapresupuesto = fechapresupuesto;
	}
	
	public String getAtencion() {
		return atencion.toUpperCase().trim();
	}

	public void setAtencion(String atencion) {
		this.atencion = atencion.toUpperCase().trim();
	}

	public String getDetalle() {
		return detalle.toUpperCase().trim();
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle.toUpperCase().trim();
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public EmpresaQuePresupuesta getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaQuePresupuesta empresa) {
		this.empresa = empresa;
	}

	public EstadosPresupuesto getEstado() {
		return estado;
	}

	public void setEstado(EstadosPresupuesto estado) {
		this.estado = estado;
	}

	public FormaDePago getFormadepago() {
		return formadepago;
	}

	public void setFormadepago(FormaDePago formadepago) {
		this.formadepago = formadepago;
	}

	public PlazoDeEntrega getPlazoentrega() {
		return plazoentrega;
	}

	public void setPlazoentrega(PlazoDeEntrega plazoentrega) {
		this.plazoentrega = plazoentrega;
	}

	public PlazoDeGarantia getPlazogarantia() {
		return plazogarantia;
	}

	public void setPlazogarantia(PlazoDeGarantia plazogarantia) {
		this.plazogarantia = plazogarantia;
	}

	public PlazosDePago getPlazopago() {
		return plazopago;
	}

	public void setPlazopago(PlazosDePago plazopago) {
		this.plazopago = plazopago;
	}
	

	public String getCondicionesGenerales() {
		return condicionesGenerales;
	}

	public void setCondicionesGenerales(String condicionesGenerales) {
		this.condicionesGenerales = condicionesGenerales;
	}

	/*calcula sgte nro de presupuesto*/
	
	private Long sgteNroPresupuesto() {
		Date ahora = new Date() ;
		Calendar micalendario = Calendar.getInstance() ;
		Integer anholargo  = 0 ;        
		Integer meslargo  = 0 ;
		Long presupuestos = 0L;
		Long ultimoNumero = 0L;
		Long nuevoNro = 0L ;
           
		Query w = XPersistence.getManager().createQuery("select count(*) as numerazo from Presupuestos i where year(i.fechapresupuesto) = :anhofeliz and month(i.fechapresupuesto) = :mesfeliz");
		micalendario.setTime(ahora);            
		anholargo = micalendario.get(Calendar.YEAR) ;
		meslargo  = micalendario.get(Calendar.MONTH) + 1;
		w.setParameter("anhofeliz",anholargo) ;
		w.setParameter("mesfeliz",meslargo) ;           
		ultimoNumero =  (Long) w.getSingleResult();
		if(ultimoNumero == null){
			nuevoNro = 1L;
		}
		else{
			nuevoNro = ultimoNumero + 1;
		}
		presupuestos =  (Long.valueOf(anholargo) * 1000000L) + (Long.valueOf(meslargo) * 10000L) + nuevoNro ;        
		return presupuestos ;            
	}
	
	/*antes de grabar calcula el sgte nro*/
	
	@PrePersist
	private void antesDeGrabar() {
		this.setPresupuestoNro(this.sgteNroPresupuesto()) ;
	}  
	
	@PreUpdate
	public void ultimoPaso() {                      
		Date mifechora = new java.util.Date() ;
		super.setModificadoPor(Users.getCurrent()) ;
		super.setFchUltMod(mifechora);
	} 
}
