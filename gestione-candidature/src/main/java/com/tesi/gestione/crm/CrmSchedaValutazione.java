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

	// Radio button
	private String idSedePreferita;

	// radio button (si, no)
	private String dispSpostamentiTrasferimenti;

	// ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo')
	private String dinamicita;

	// ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo')
	private String comunicativita;

	// ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo')
	private String presenza;

	@Size(max = 245, message = "Lunghezza massima 245 caratteri")
	private String esperienzePosizione;

	@Size(max = 245, message = "Lunghezza massima 245 caratteri")
	private String esperienzeGenerali;

	@Size(max = 145, message = "Lunghezza massima 145 caratteri")
	private String motivazioneCambiamento;

	@Size(max = 145, message = "Lunghezza massima 145 caratteri")
	private String motivazioneProfessionale;

	@Size(max = 145, message = "Lunghezza massima 145 caratteri")
	private String noteSpostamenti;

	@Size(max = 200, message = "Lunghezza massima 200 caratteri")
	private String lingue;

	@Size(max = 16777215, message = "Lunghezza massima 16777215 caratteri")
	private String competenza;

	@Size(max = 75, message = "Lunghezza massima 75 caratteri")
	private String note;

	private Float retribuzioneAttuale;

	private Float retribuzioneRichiesta;

	@Size(max = 75, message = "Lunghezza massima 75 caratteri")
	private String inquadramentoAttuale;

	@Size(max = 75, message = "Lunghezza massima 75 caratteri")
	private String inquadramentoRichiesto;

	private int periodoPreavviso;

	// radio button (si, no)
	private String CVAllegato;

	@ValidData
	private String dataColloquio;

	public CrmSchedaValutazione() {
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

}
