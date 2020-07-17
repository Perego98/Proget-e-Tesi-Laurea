package com.tesi.gestione.crm;


import java.sql.Blob;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.validation.FieldMatch;
import com.tesi.gestione.validation.ValidData;
import com.tesi.gestione.validation.ValidEmail;

public class CrmSchedaValutazione {

	

	// user
	@NotNull(message = "is required")
	private String idUtenteRelatore;
	
	//candidato
	@NotNull(message = "is required")
	private String idCandidatoRelativo;
	
	
	private String idSedePreferita;
	
	private String dispSpostamentiTrasferimenti;
	
	private String dinamicita;
	
	private String comunicativita;
	
	private String presenza;
	
	@Size(max = 245, message = "Maximum length 245 characters")
	private String esperienzePosizione;
	
	@Size(max = 245, message = "Maximum length 245 characters")
	private String esperienzeGenerali;
	
	@Size(max = 145, message = "Maximum length 145 characters")
	private String motivazioneCambiamento;
	
	@Size(max = 145, message = "Maximum length 145 characters")
	private String motivazioneProfessionale;
	
	@Size(max = 145, message = "Maximum length 145 characters")
	private String noteSpostamenti;
	
	@Size(max = 200, message = "Maximum length 200 characters")
	private String lingue;
	
	@Size(max = 16777215, message = "Maximum length 16777215 characters")
	private String competenza;
	
	@Size(max = 75, message = "Maximum length 75 characters")
	private String note;
	
	private Float retribuzioneAttuale;
	
	private Float retribuzioneRichiesta;
	
	@Size(max = 75, message = "Maximum length 75 characters")
	private String inquadramentoAttuale;
	
	@Size(max = 75, message = "Maximum length 75 characters")
	private String inquadramentoRichiesto;
	
	private int periodoPreavviso;
	
	private String CVAllegato;
	
	@ValidData
	private String dataColloquio;

	public CrmSchedaValutazione() {
	}

	public String getIdUtenteRelatore() {
		return idUtenteRelatore;
	}

	public void setIdUtenteRelatore(String idUtenteRelatore) {
		this.idUtenteRelatore = idUtenteRelatore;
	}

	public String getIdCandidatoRelativo() {
		return idCandidatoRelativo;
	}

	public void setIdCandidatoRelativo(String idCandidatoRelativo) {
		this.idCandidatoRelativo = idCandidatoRelativo;
	}

	public String getIdSedePreferita() {
		return idSedePreferita;
	}

	public void setIdSedePreferita(String idSedePreferita) {
		this.idSedePreferita = idSedePreferita;
	}

	public String getDispSpostamentiTrasferimenti() {
		return dispSpostamentiTrasferimenti;
	}

	public void setDispSpostamentiTrasferimenti(String dispSpostamentiTrasferimenti) {
		this.dispSpostamentiTrasferimenti = dispSpostamentiTrasferimenti;
	}

	public String getDinamicita() {
		return dinamicita;
	}

	public void setDinamicita(String dinamicita) {
		this.dinamicita = dinamicita;
	}

	public String getComunicativita() {
		return comunicativita;
	}

	public void setComunicativita(String comunicativita) {
		this.comunicativita = comunicativita;
	}

	public String getPresenza() {
		return presenza;
	}

	public void setPresenza(String presenza) {
		this.presenza = presenza;
	}

	public String getEsperienzePosizione() {
		return esperienzePosizione;
	}

	public void setEsperienzePosizione(String esperienzePosizione) {
		this.esperienzePosizione = esperienzePosizione;
	}

	public String getEsperienzeGenerali() {
		return esperienzeGenerali;
	}

	public void setEsperienzeGenerali(String esperienzeGenerali) {
		this.esperienzeGenerali = esperienzeGenerali;
	}

	public String getMotivazioneCambiamento() {
		return motivazioneCambiamento;
	}

	public void setMotivazioneCambiamento(String motivazioneCambiamento) {
		this.motivazioneCambiamento = motivazioneCambiamento;
	}

	public String getMotivazioneProfessionale() {
		return motivazioneProfessionale;
	}

	public void setMotivazioneProfessionale(String motivazioneProfessionale) {
		this.motivazioneProfessionale = motivazioneProfessionale;
	}

	public String getNoteSpostamenti() {
		return noteSpostamenti;
	}

	public void setNoteSpostamenti(String noteSpostamenti) {
		this.noteSpostamenti = noteSpostamenti;
	}

	public String getLingue() {
		return lingue;
	}

	public void setLingue(String lingue) {
		this.lingue = lingue;
	}

	public String getCompetenza() {
		return competenza;
	}

	public void setCompetenza(String competenza) {
		this.competenza = competenza;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Float getRetribuzioneAttuale() {
		return retribuzioneAttuale;
	}

	public void setRetribuzioneAttuale(Float retribuzioneAttuale) {
		this.retribuzioneAttuale = retribuzioneAttuale;
	}

	public Float getRetribuzioneRichiesta() {
		return retribuzioneRichiesta;
	}

	public void setRetribuzioneRichiesta(Float retribuzioneRichiesta) {
		this.retribuzioneRichiesta = retribuzioneRichiesta;
	}

	public String getInquadramentoAttuale() {
		return inquadramentoAttuale;
	}

	public void setInquadramentoAttuale(String inquadramentoAttuale) {
		this.inquadramentoAttuale = inquadramentoAttuale;
	}

	public String getInquadramentoRichiesto() {
		return inquadramentoRichiesto;
	}

	public void setInquadramentoRichiesto(String inquadramentoRichiesto) {
		this.inquadramentoRichiesto = inquadramentoRichiesto;
	}

	public int getPeriodoPreavviso() {
		return periodoPreavviso;
	}

	public void setPeriodoPreavviso(int periodoPreavviso) {
		this.periodoPreavviso = periodoPreavviso;
	}

	public String getCVAllegato() {
		return CVAllegato;
	}

	public void setCVAllegato(String cVAllegato) {
		CVAllegato = cVAllegato;
	}

	public String getDataColloquio() {
		return dataColloquio;
	}

	public void setDataColloquio(String dataColloquio) {
		this.dataColloquio = dataColloquio;
	}

	@Override
	public String toString() {
		return "CrmSchedaValutazione [idUtenteRelatore=" + idUtenteRelatore + ", idCandidatoRelativo="
				+ idCandidatoRelativo + ", idSedePreferita=" + idSedePreferita + ", dispSpostamentiTrasferimenti="
				+ dispSpostamentiTrasferimenti + ", dinamicita=" + dinamicita + ", comunicativita=" + comunicativita
				+ ", presenza=" + presenza + ", esperienzePosizione=" + esperienzePosizione + ", esperienzeGenerali="
				+ esperienzeGenerali + ", motivazioneCambiamento=" + motivazioneCambiamento
				+ ", motivazioneProfessionale=" + motivazioneProfessionale + ", noteSpostamenti=" + noteSpostamenti
				+ ", lingue=" + lingue + ", competenza=" + competenza + ", note=" + note + ", retribuzioneAttuale="
				+ retribuzioneAttuale + ", retribuzioneRichiesta=" + retribuzioneRichiesta + ", inquadramentoAttuale="
				+ inquadramentoAttuale + ", inquadramentoRichiesto=" + inquadramentoRichiesto + ", periodoPreavviso="
				+ periodoPreavviso + ", CVAllegato=" + CVAllegato + ", dataColloquio=" + dataColloquio + "]";
	}

	
	
	

	


	
	
}
