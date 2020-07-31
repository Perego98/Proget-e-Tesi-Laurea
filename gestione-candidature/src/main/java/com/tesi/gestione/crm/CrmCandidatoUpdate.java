package com.tesi.gestione.crm;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidData;
import com.tesi.gestione.validation.ValidEmail;

public class CrmCandidatoUpdate {

	private String statoCandidatura;

	@NotNull(message = "è richiesto")
	@Size(min = 1, message = "è richiesto")
	private String nome;

	@NotNull(message = "è richiesto")
	@Size(min = 1, message = "è richiesto")
	private String cognome;

	@ValidEmail
	@NotNull(message = "è richiesto")
	@Size(min = 1, message = "è richiesto")
	private String email;

	@NotNull(message = "è richiesto")
	@Size(min = 10, max = 10, message = "è richiesto")
	private String telephone;

//	@NotNull(message = "è richiesto")
//	@ValidData
//	private String dataNascita;
	private String dataFormatted;

	@NotNull(message = "è richiesto")
	private String tipoContratto;

	@NotNull(message = "è richiesto")
	private float ral;

	@NotNull(message = "è richiesto")
	private int preavviso;

	@NotNull(message = "è richiesto")
	private String offerta;

	@NotNull(message = "è richiesto")
	@Size(max = 150, message = "Lunghezza massima 150 caratteri")
	private String proveninenza;

	@Size(max = 100, message = "Lunghezza massima 100 caratteri")
	private String aspettative;

	@Size(max = 65535, message = "Lunghezza massima 65535 caratteri")
	private String note;

	public CrmCandidatoUpdate() {

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

	
	
//	public String getDataNascita() {
//		return dataNascita;
//	}
//
//	public void setDataNascita(String dataNascita) {
//		this.dataNascita = dataNascita;
//	}

	public String getDataFormatted() {
		return dataFormatted;
	}

	public void setDataFormatted(String dataFormatted) {
		this.dataFormatted = dataFormatted;
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

}
