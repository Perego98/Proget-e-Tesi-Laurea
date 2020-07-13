package com.tesi.gestione.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidEmail;

//@FieldMatch.List({
//    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
//})
public class CrmUserUpdate {

//	@NotNull(message = "is required")
//	@Size(min = 1, message = "is required")
	private String userName;
//
//	@NotNull(message = "is required")
//	@Size(min = 1, message = "is required")
//	private String password;
//	
//	@NotNull(message = "is required")
//	@Size(min = 1, message = "is required")
//	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	@NotNull(message = "is required")
	@Size(min = 10, max=10, message = "Devono essere 10 cifre")
	private String telephone;
	
//	@NotNull(message = "is required")
//	@Size(min = 1, message = "is required")
//	private String idRole;
//	
//	@NotNull(message = "is required")
//	private int Sedeid;

	public CrmUserUpdate() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getMatchingPassword() {
//		return matchingPassword;
//	}
//
//	public void setMatchingPassword(String matchingPassword) {
//		this.matchingPassword = matchingPassword;
//	}

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

//	public String getIdRole() {
//		return idRole;
//	}
//
//	public void setIdRole(String idRole) {
//		this.idRole = idRole;
//	}
//
//	public int getSedeid() {
//		return Sedeid;
//	}
//
//	public void setSedeid(int sedeid) {
//		Sedeid = sedeid;
//	}
	
	


	
	
}
