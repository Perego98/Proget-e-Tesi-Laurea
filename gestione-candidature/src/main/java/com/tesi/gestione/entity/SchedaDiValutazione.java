package com.tesi.gestione.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedavalutazione")
public class SchedaDiValutazione {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;	
	
	@ManyToOne
	@JoinColumn(name="utente_relatore")
	private User utenteRelatore;
	

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="candidato_relativo")
	private Candidato candidatoRelativo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sede_preferita")
	private Sede sedePreferita;
	
	@JoinColumn(name="disponibilitāSpostamentiTrasferimenti")
	private String dispSpostamentiTrasferimenti;
	
	@JoinColumn(name="dinamicitā")
	private String dinamicita;
	
	@JoinColumn(name="comunicativitā")
	private String comunicativita;
	
	@JoinColumn(name="presenza")
	private String presenza;
	
	@JoinColumn(name="esperienzePosizione")
	private String esperienzePosizione;
	
	@JoinColumn(name="esperienzeGenerali")
	private String esperienzeGenerali;
	
	@JoinColumn(name="motivazioneCambiamento")
	private String motivazioneCambiamento;
	
	@JoinColumn(name="motivazioneProfessionale")
	private String motivazioneProfessionale;
	
	@JoinColumn(name="noteSpostamenti")
	private String noteSpostamenti;
	
	@JoinColumn(name="lingue")
	private String lingue;
	
	@JoinColumn(name="competenza")
	private String competenza;
	
	@JoinColumn(name="note")
	private String note;
	
	@JoinColumn(name="retribuzioneAttuale")
	private Float retribuzioneAttuale;
	
	@JoinColumn(name="retribuzioneRichiesta")
	private Float retribuzioneRichiesta;
	
	@JoinColumn(name="inquadramentoAttuale")
	private String inquadramentoAttuale;
	
	@JoinColumn(name="inquadramentoRichiesto")
	private String inquadramentoRichiesto;
	
	@JoinColumn(name="periodoPreavviso")
	private int periodoPreavviso;
	
	@JoinColumn(name="CVAllegato")
	private String CVAllegato;
	
	@JoinColumn(name="dataColloquio")
	private Calendar dataColloquio;
	
	
	
}
