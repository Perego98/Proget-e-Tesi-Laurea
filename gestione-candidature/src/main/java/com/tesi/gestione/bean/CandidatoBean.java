package com.tesi.gestione.bean;

import java.util.List;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.User;

public class CandidatoBean {

	private String codiceFiscale;

	private String statoCandidatura;

	private String nome;

	private String cognome;

	private String telephone;

	private String email;

	private Calendar dataNascita;

	private String tipoContratto;

	private float ral;

	private int preavviso;

	private String offerta;
	
	private String proveninenza;

	private String aspettative;

	private String note;

	private Blob curriculum;

	private User supervisore;

	private String dataFormatted;

	private List<Schedavalutazione> schedeValutazione;
	
//	private List<SchedavalutazioneBean> schedeValutazione;

	public CandidatoBean() {
	}

//	public CandidatoBean(Candidato candidato) {
//		this.codiceFiscale = candidato.getCodiceFiscale();
//		this.statoCandidatura = candidato.getStatoCandidatura();
//		this.nome = candidato.getNome();
//		this.cognome = candidato.getCognome();
//		this.telephone = candidato.getTelephone();
//		this.email = candidato.getEmail();
//		this.dataNascita = candidato.getDataNascita();
//		this.tipoContratto = candidato.getTipoContratto();
//		this.ral = candidato.getRal();
//		this.preavviso = candidato.getPreavviso();
//		this.offerta = candidato.getOfferta();
//		this.proveninenza = candidato.getProveninenza();
//		this.aspettative = candidato.getAspettative();
//		this.note = candidato.getNote();
//		this.curriculum = candidato.getCurriculum();
//		this.supervisore = candidato.getSupervisore();
//		this.schedeValutazione = processSchede(candidato.getSchedeValutazione());
//	}
	
	public CandidatoBean(Candidato candidato) {
		this.codiceFiscale = candidato.getCodiceFiscale();
		this.statoCandidatura = candidato.getStatoCandidatura();
		this.nome = candidato.getNome();
		this.cognome = candidato.getCognome();
		this.telephone = candidato.getTelephone();
		this.email = candidato.getEmail();
		this.dataNascita = candidato.getDataNascita();
		this.tipoContratto = candidato.getTipoContratto();
		this.ral = candidato.getRal();
		this.preavviso = candidato.getPreavviso();
		this.offerta = candidato.getOfferta();
		this.proveninenza = candidato.getProveninenza();
		this.aspettative = candidato.getAspettative();
		this.note = candidato.getNote();
		this.curriculum = candidato.getCurriculum();
		this.supervisore = candidato.getSupervisore();
		this.schedeValutazione = candidato.getSchedeValutazione();
		this.dataFormatted = formattedData(candidato.getDataNascita());
	}

	private String formattedData(Calendar data) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(data.getTime());

		return formatted;
	}

	private List<SchedavalutazioneBean> processSchede(List<Schedavalutazione> paramSchede) {
		
		List<SchedavalutazioneBean> tempArray = new ArrayList<>();
		
		for(Schedavalutazione temp : paramSchede) {
			tempArray.add(new SchedavalutazioneBean(temp));
		}
		
		return tempArray;
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

	public Calendar getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Calendar dataNascita) {
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

	public User getSupervisore() {
		return supervisore;
	}

	public void setSupervisore(User supervisore) {
		this.supervisore = supervisore;
	}

	public List<Schedavalutazione> getSchedeValutazione() {
		return schedeValutazione;
	}

	public void setSchedeValutazione(List<Schedavalutazione> schedeValutazione) {
		this.schedeValutazione = schedeValutazione;
	}

	public String getDataFormatted() {
		return dataFormatted;
	}

	public void setDataFormatted(String dataFormatted) {
		this.dataFormatted = dataFormatted;
	}

	
	
	
//	public List<SchedavalutazioneBean> getSchedeValutazione() {
//		return schedeValutazione;
//	}
//
//	public void setSchedeValutazione(List<SchedavalutazioneBean> schedeValutazione) {
//		this.schedeValutazione = schedeValutazione;
//	}



}
