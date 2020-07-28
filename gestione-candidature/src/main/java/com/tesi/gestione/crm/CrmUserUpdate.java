package com.tesi.gestione.crm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidEmail;

public class CrmUserUpdate {

	private String userName;

	@NotNull(message = "è richiesto")
	@Size(min = 1, message = "è richiesto")
	private String firstName;

	@NotNull(message = "è richiesto")
	@Size(min = 1, message = "è richiesto")
	private String lastName;

	@ValidEmail
	@NotNull(message = "è richiesto")
	@Size(min = 1, message = "è richiesto")
	private String email;

	@NotNull(message = "è richiesto")
	@Size(min = 10, max = 10, message = "Devono essere 10 cifre")
	private String telephone;

	public CrmUserUpdate() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
