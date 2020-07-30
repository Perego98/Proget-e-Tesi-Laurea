package com.tesi.gestione.copy;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

@Entity
@Table(name = "schedavalutazione")
public class SchedavalutazioneCopy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "utente_relatore")
	private User utenteRelatore;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "candidato_relativo")
	private Candidato candidatoRelativo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sede_preferita")
	private Sede sedePreferita;

	@JoinColumn(name = "dispSpostamentiTrasferimenti")
	private String dispSpostamentiTrasferimenti;

	@JoinColumn(name = "dinamicita")
	private String dinamicita;

	@JoinColumn(name = "comunicativita")
	private String comunicativita;

	@JoinColumn(name = "presenza")
	private String presenza;

	@JoinColumn(name = "esperienzePosizione")
	private String esperienzePosizione;

	@JoinColumn(name = "esperienzeGenerali")
	private String esperienzeGenerali;

	@JoinColumn(name = "motivazioneCambiamento")
	private String motivazioneCambiamento;

	@JoinColumn(name = "motivazioneProfessionale")
	private String motivazioneProfessionale;

	@JoinColumn(name = "noteSpostamenti")
	private String noteSpostamenti;

	@JoinColumn(name = "lingue")
	private String lingue;

	@JoinColumn(name = "competenza")
	private String competenza;

	@JoinColumn(name = "note")
	private String note;

	@JoinColumn(name = "retribuzioneAttuale")
	private Float retribuzioneAttuale;

	@JoinColumn(name = "retribuzioneRichiesta")
	private Float retribuzioneRichiesta;

	@JoinColumn(name = "inquadramentoAttuale")
	private String inquadramentoAttuale;

	@JoinColumn(name = "inquadramentoRichiesto")
	private String inquadramentoRichiesto;

	@JoinColumn(name = "periodoPreavviso")
	private int periodoPreavviso;

	@JoinColumn(name = "CVAllegato")
	private String CVAllegato;

	@JoinColumn(name = "dataColloquio")
	private Calendar dataColloquio;

	private String dataFormatted;
	
	


	public SchedavalutazioneCopy() {
	}
	
	
	public SchedavalutazioneCopy(Schedavalutazione scheda) {
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

	private String formattedData(Calendar  data) {
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
