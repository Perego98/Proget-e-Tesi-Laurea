package com.tesi.gestione.user;

import java.sql.Blob;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidEmail;

public class CrmCandidato {

	@NotNull(message = "is required")
	@Size(min = 16, max = 16, message = "must be 16 digits")
	private String codiceFiscale;

	//@NotNull(message = "is required")
	private String statoCandidatura;
	

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String nome;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String cognome;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	@NotNull(message = "is required")
	@Size(min = 10, message = "is required")
	private String telephone;
	
	//@NotNull(message = "is required")
	private Date dataNascita;
	
	@NotNull(message = "is required")
	private String tipoContratto;
	
	@NotNull(message = "is required")
	private float ral;
	
	@NotNull(message = "is required")
	private int preavviso;
	
	@NotNull(message = "is required")
	private String offerta;
	
	@NotNull(message = "is required")
	@Size(max = 150, message = "Maximum length 150 characters")
	private String proveninenza;
	
	@Size(max = 100, message = "Maximum length 100 characters")
	private String aspettative;
	
	@Size(max = 65535, message = "Maximum length 65535 characters")
	private String note;
	
	//@NotNull(message = "is required")
	private Blob curriculum;

	public CrmCandidato() {
		
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getStatoCandidatura() {
		return statoCandidatura;
	}

	public void setStatoCandidatura(String statoCandidatura) {
		this.statoCandidatura = statoCandidatura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getTipoContratto() {
		return tipoContratto;
	}

	public void setTipoContratto(String tipoContratto) {
		this.tipoContratto = tipoContratto;
	}

	public float getRal() {
		return ral;
	}

	public void setRal(float ral) {
		this.ral = ral;
	}

	public int getPreavviso() {
		return preavviso;
	}

	public void setPreavviso(int preavviso) {
		this.preavviso = preavviso;
	}

	public String getOfferta() {
		return offerta;
	}

	public void setOfferta(String offerta) {
		this.offerta = offerta;
	}

	public String getProveninenza() {
		return proveninenza;
	}

	public void setProveninenza(String proveninenza) {
		this.proveninenza = proveninenza;
	}

	public String getAspettative() {
		return aspettative;
	}

	public void setAspettative(String aspettative) {
		this.aspettative = aspettative;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Blob getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Blob curriculum) {
		this.curriculum = curriculum;
	}
	
	
	
	
	

	


	
	
}
