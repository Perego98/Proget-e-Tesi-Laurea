package com.tesi.gestione.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedavalutazione")
public class SchedaValutazione {

	@Id
	@Column(name = "utente_relatore")
	private User relatore;
	
	@Id
	@Column(name = "candidato_relativo")
	private Candidato candidato;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sede_preferita")
	private Sede sedePreferita;
	
	@Column(name = "dataColloquio")
	private Calendar dataColloquio;
	
	@Column(name = "CVAllegato")
	private String cvAllegato; // enum, si / no
	
	@Column(name = "preiodoPreavviso")
	private int preavviso;
	
	@Column(name = "inquadramentoRichiesto")
	private String inquadramentoRichiesto;
	
	@Column(name = "inquadramentoAttuale")
	private String inquadramentoAttuale;
	
	@Column(name = "retribuzioneRichiesta")
	private int retribuzioneRichiesta;
	
	@Column(name = "retribuzioneAttuale")
	private int retribuzioneAttuale;

	@Column(name = "note", columnDefinition="TEXT")
	private String note;
	
	@Column(name = "competenza")
	private String competenza;
	
	@Column(name = "lingue")
	private String lingue;
	
	@Column(name = "noteSpostamenti")
	private String noteSpostamenti;
	
	@Column(name = "motivazioneProfessionale")
	private String motivazioneProfessionale;
	
	@Column(name = "motivazioneCambiamento")
	private String motivazioneCambiamento;
	
	@Column(name = "esperienzeGenerali")
	private String esperienzeGenerali;
	
	@Column(name = "esperienzePosizione")
	private String esperienzePosizione;
	
	@Column(name = "presenza")
	private String presenza; // ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo')
	
	@Column(name = "comunicativit‡")
	private String comunicativit‡; // ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo')
	
	@Column(name = "dinamicit‡")
	private String dinamicit‡; // ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo')
	
	@Column(name = "disponibilit‡SpostamentiTrasferimenti")
	private String disponibilit‡SpostamentiTrasferimenti; // enum, si / no

	public SchedaValutazione() {
		
	}

	public User getRelatore() {
		return relatore;
	}

	public void setRelatore(User relatore) {
		this.relatore = relatore;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Sede getSedePreferita() {
		return sedePreferita;
	}

	public void setSedePreferita(Sede sedePreferita) {
		this.sedePreferita = sedePreferita;
	}

	public Calendar getDataColloquio() {
		return dataColloquio;
	}

	public void setDataColloquio(Calendar dataColloquio) {
		this.dataColloquio = dataColloquio;
	}

	public String getCvAllegato() {
		return cvAllegato;
	}

	public void setCvAllegato(String cvAllegato) {
		this.cvAllegato = cvAllegato;
	}

	public int getPreavviso() {
		return preavviso;
	}

	public void setPreavviso(int preavviso) {
		this.preavviso = preavviso;
	}

	public String getInquadramentoRichiesto() {
		return inquadramentoRichiesto;
	}

	public void setInquadramentoRichiesto(String inquadramentoRichiesto) {
		this.inquadramentoRichiesto = inquadramentoRichiesto;
	}

	public String getInquadramentoAttuale() {
		return inquadramentoAttuale;
	}

	public void setInquadramentoAttuale(String inquadramentoAttuale) {
		this.inquadramentoAttuale = inquadramentoAttuale;
	}

	public int getRetribuzioneRichiesta() {
		return retribuzioneRichiesta;
	}

	public void setRetribuzioneRichiesta(int retribuzioneRichiesta) {
		this.retribuzioneRichiesta = retribuzioneRichiesta;
	}

	public int getRetribuzioneAttuale() {
		return retribuzioneAttuale;
	}

	public void setRetribuzioneAttuale(int retribuzioneAttuale) {
		this.retribuzioneAttuale = retribuzioneAttuale;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCompetenza() {
		return competenza;
	}

	public void setCompetenza(String competenza) {
		this.competenza = competenza;
	}

	public String getLingue() {
		return lingue;
	}

	public void setLingue(String lingue) {
		this.lingue = lingue;
	}

	public String getNoteSpostamenti() {
		return noteSpostamenti;
	}

	public void setNoteSpostamenti(String noteSpostamenti) {
		this.noteSpostamenti = noteSpostamenti;
	}

	public String getMotivazioneProfessionale() {
		return motivazioneProfessionale;
	}

	public void setMotivazioneProfessionale(String motivazioneProfessionale) {
		this.motivazioneProfessionale = motivazioneProfessionale;
	}

	public String getMotivazioneCambiamento() {
		return motivazioneCambiamento;
	}

	public void setMotivazioneCambiamento(String motivazioneCambiamento) {
		this.motivazioneCambiamento = motivazioneCambiamento;
	}

	public String getEsperienzeGenerali() {
		return esperienzeGenerali;
	}

	public void setEsperienzeGenerali(String esperienzeGenerali) {
		this.esperienzeGenerali = esperienzeGenerali;
	}

	public String getEsperienzePosizione() {
		return esperienzePosizione;
	}

	public void setEsperienzePosizione(String esperienzePosizione) {
		this.esperienzePosizione = esperienzePosizione;
	}

	public String getPresenza() {
		return presenza;
	}

	public void setPresenza(String presenza) {
		this.presenza = presenza;
	}

	public String getComunicativit‡() {
		return comunicativit‡;
	}

	public void setComunicativit‡(String comunicativit‡) {
		this.comunicativit‡ = comunicativit‡;
	}

	public String getDinamicit‡() {
		return dinamicit‡;
	}

	public void setDinamicit‡(String dinamicit‡) {
		this.dinamicit‡ = dinamicit‡;
	}

	public String getDisponibilit‡SpostamentiTrasferimenti() {
		return disponibilit‡SpostamentiTrasferimenti;
	}

	public void setDisponibilit‡SpostamentiTrasferimenti(String disponibilit‡SpostamentiTrasferimenti) {
		this.disponibilit‡SpostamentiTrasferimenti = disponibilit‡SpostamentiTrasferimenti;
	}
	
	
}
