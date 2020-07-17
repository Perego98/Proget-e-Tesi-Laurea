package com.tesi.gestione.crm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidEmail;

//@FieldMatch.List({
//    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
//})
public class CrmRole {


	@NotNull(message = "is required")
	private String idRole;

	public CrmRole() {

	}

	
	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	


	
	
}
