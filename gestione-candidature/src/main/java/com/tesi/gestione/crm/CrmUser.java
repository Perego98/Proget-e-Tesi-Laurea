package com.tesi.gestione.crm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidEmail;
import com.tesi.gestione.validation.ValidUsername;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match") })
public class CrmUser {

	@NotNull(message = "� richiesto")
	@Size(min = 1, message = "� richiesto")
	@ValidUsername
	private String userName;

	@NotNull(message = "� richiesto")
	@Size(min = 1, message = "� richiesto")
	private String password;

	@NotNull(message = "� richiesto")
	@Size(min = 1, message = "� richiesto")
	private String matchingPassword;

	@NotNull(message = "� richiesto")
	@Size(min = 1, message = "� richiesto")
	private String firstName;

	@NotNull(message = "� richiesto")
	@Size(min = 1, message = "� richiesto")
	private String lastName;

	@ValidEmail
	@NotNull(message = "� richiesto")
	@Size(min = 1, message = "� richiesto")
	private String email;

	@NotNull(message = "� richiesto")
	@Size(min = 10, max = 10, message = "Devono essere 10 cifre")
	private String telephone;

	@NotNull(message = "� richiesto")
	@Size(min = 1, message = "� richiesto")
	private String idRole;

	@NotNull(message = "� richiesto")
	private int Sedeid;

	public CrmUser() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public int getSedeid() {
		return Sedeid;
	}

	public void setSedeid(int sedeid) {
		Sedeid = sedeid;
	}

}
