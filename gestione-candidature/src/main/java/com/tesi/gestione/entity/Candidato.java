package com.tesi.gestione.entity;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "candidato")
public class Candidato {
	
	@Id
	@Column(name = "codiceFiscale")
	private String codiceFiscale;
	
	@Column(name = "stato_candidatura")
	private String statoCandidatura;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "telefono")
	private String telephone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dataNascita")
	private Date dataNascita;
	
	@Column(name = "tipoContratto")
	private String tipoContratto;
	
	@Column(name = "ral")
	private float ral;
	
	@Column(name = "tempo_preavviso_giorni")
	private int preavviso;
	
	@Column(name = "tipoOfferta")
	private String offerta;
	
	@Column(name = "canale_provenienza", length = 150)
	private String proveninenza;
	
	// not required
	@Column(name = "aspettative", length = 100)
	private String aspettative;
	
	// not required
	@Column(name = "note", columnDefinition="TEXT")
	private String note;
	
	@Column(name = "curriculum")
	private byte[] curriculum;

	
	
	public Candidato() {
	}

	public Candidato(String codiceFiscale, String statoCandidatura, String nome, String cognome, String telephone,
			String email, Date dataNascita, String tipoContratto, float ral, int preavviso, String offerta,
			String proveninenza, byte[] curriculum) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.statoCandidatura = statoCandidatura;
		this.nome = nome;
		this.cognome = cognome;
		this.telephone = telephone;
		this.email = email;
		this.dataNascita = dataNascita;
		this.tipoContratto = tipoContratto;
		this.ral = ral;
		this.preavviso = preavviso;
		this.offerta = offerta;
		this.proveninenza = proveninenza;
		this.curriculum = curriculum;
	}

	

	public Candidato(String codiceFiscale, String statoCandidatura, String nome, String cognome, String telephone,
			String email, Date dataNascita, String tipoContratto, float ral, int preavviso, String offerta,
			String proveninenza, String aspettative, String note, byte[] curriculum) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.statoCandidatura = statoCandidatura;
		this.nome = nome;
		this.cognome = cognome;
		this.telephone = telephone;
		this.email = email;
		this.dataNascita = dataNascita;
		this.tipoContratto = tipoContratto;
		this.ral = ral;
		this.preavviso = preavviso;
		this.offerta = offerta;
		this.proveninenza = proveninenza;
		this.aspettative = aspettative;
		this.note = note;
		this.curriculum = curriculum;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public byte[] getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(byte[] curriculum) {
		this.curriculum = curriculum;
	}

	@Override
	public String toString() {
		return "Candidato [codiceFiscale=" + codiceFiscale + ", statoCandidatura=" + statoCandidatura + ", nome=" + nome
				+ ", cognome=" + cognome + ", telephone=" + telephone + ", email=" + email + ", dataNascita="
				+ dataNascita + ", tipoContratto=" + tipoContratto + ", ral=" + ral + ", preavviso=" + preavviso
				+ ", offerta=" + offerta + ", proveninenza=" + proveninenza + ", aspettative=" + aspettative + ", note="
				+ note + ", curriculum=" + Arrays.toString(curriculum) + "]";
	}



	
	
	
	
}