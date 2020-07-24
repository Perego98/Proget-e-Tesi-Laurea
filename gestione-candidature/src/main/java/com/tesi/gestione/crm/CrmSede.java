package com.tesi.gestione.crm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidEmail;

public class CrmSede {

	@NotNull(message = "is required")
	private int Sedeid;

	public CrmSede() {

	}

	public int getSedeid() {
		return Sedeid;
	}

	public void setSedeid(int sedeid) {
		Sedeid = sedeid;
	}

}
