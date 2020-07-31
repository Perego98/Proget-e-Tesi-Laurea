package com.tesi.gestione.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

public class SchedavalutazioneBean {

	private int id;

	private User utenteRelatore;

	private Candidato candidatoRelativo;

	private Sede sedePreferita;

	private String dispSpostamentiTrasferimenti;

	private String dinamicita;

	private String comunicativita;

	private String presenza;

	private String esperienzePosizione;

	private String esperienzeGenerali;

	private String motivazioneCambiamento;

	private String motivazioneProfessionale;

	private String noteSpostamenti;

	private String lingue;

	private String competenza;

	private String note;

	private Float retribuzioneAttuale;

	private Float retribuzioneRichiesta;

	private String inquadramentoAttuale;

	private String inquadramentoRichiesto;

	private int periodoPreavviso;

	private String CVAllegato;

	private Calendar dataColloquio;

	private String dataFormatted;

	public SchedavalutazioneBean() {
	}

	public SchedavalutazioneBean(Schedavalutazione scheda) {
		this.id = scheda.getId();
		this.utenteRelatore = scheda.getUtenteRelatore();
		this.candidatoRelativo = scheda.getCandidatoRelativo();
		this.sedePreferita = scheda.getSedePreferita();
		this.dispSpostamentiTrasferimenti = scheda.getDispSpostamentiTrasferimenti();
		this.dinamicita = scheda.getDinamicita();
		this.comunicativita = scheda.getComunicativita();
		this.presenza = scheda.getPresenza();
		this.esperienzePosizione = scheda.getEsperienzePosizione();
		this.esperienzeGenerali = scheda.getEsperienzeGenerali();
		this.motivazioneCambiamento = scheda.getMotivazioneCambiamento();
		this.motivazioneProfessionale = scheda.getMotivazioneProfessionale();
		this.noteSpostamenti = scheda.getNoteSpostamenti();
		this.lingue = scheda.getLingue();
		this.competenza = scheda.getCompetenza();
		this.note = scheda.getNote();
		this.retribuzioneAttuale = scheda.getRetribuzioneAttuale();
		this.retribuzioneRichiesta = scheda.getRetribuzioneRichiesta();
		this.inquadramentoAttuale = scheda.getInquadramentoAttuale();
		this.inquadramentoRichiesto = scheda.getInquadramentoRichiesto();
		this.periodoPreavviso = scheda.getPeriodoPreavviso();
		this.CVAllegato = scheda.getCVAllegato();
		this.dataColloquio = scheda.getDataColloquio();
		this.dataFormatted = formattedData(scheda.getDataColloquio());
	}

	private String formattedData(Calendar data) {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String formatted = format1.format(data.getTime());

		return formatted;
	}
	
	private String formattedData2(Calendar data) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(data.getTime());

		return formatted;
	}

	public String getDataFormatted() {
		return dataFormatted;
	}

	public void setDataFormatted(String dataFormatted) {
		this.dataFormatted = dataFormatted;
	}

	public User getUtenteRelatore() {
		return utenteRelatore;
	}

	public void setUtenteRelatore(User utenteRelatore) {
		this.utenteRelatore = utenteRelatore;
	}

	public Candidato getCandidatoRelativo() {
		return candidatoRelativo;
	}

	public void setCandidatoRelativo(Candidato candidatoRelativo) {
		this.candidatoRelativo = candidatoRelativo;
	}

	public Sede getSedePreferita() {
		return sedePreferita;
	}

	public void setSedePreferita(Sede sedePreferita) {
		this.sedePreferita = sedePreferita;
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

	public Calendar getDataColloquio() {
		return dataColloquio;
	}

	public void setDataColloquio(Calendar dataColloquio) {
		this.dataColloquio = dataColloquio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
