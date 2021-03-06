package org.presupuestos.validadores;

import javax.persistence.*;

import org.openxava.jpa.*;
import org.openxava.util.*;
import org.openxava.validators.*;

public class ValidadorNameEmpr implements IPropertyValidator, IWithMessage{
	/**
	* controlo registros duplicados (nombre de la empresa )
	*/
	private static final long serialVersionUID = 1L;

	private String message ;

	public void setMessage(String message) {
		this.message = message ;
	}       

	public void validate(Messages errors, Object value, String propertyName,
			String modelName) throws Exception {
		Query w = XPersistence.getManager().createQuery("select count(*) as q1 from EmpresaQuePresupuesta k where upper(trim(empNombre)) = :minombre");
		w.setParameter("minombre",((String)value).toUpperCase().trim()) ;               
		Long q1 =  (Long) w.getSingleResult();
             
		if ( q1 > 0 ) {
			errors.add(message); 
		}
	}
}
