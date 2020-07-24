package com.tesi.gestione.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.tesi.gestione.crm.CrmCandidato;
import com.tesi.gestione.crm.CrmCandidatoUpdate;
import com.tesi.gestione.crm.CrmSchedaValutazione;
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.dao.CandidatoDao;
import com.tesi.gestione.dao.SchedaDiValutazioneDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.dao.UserDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

@Service
public class SchedaVAlutazioneServiceImpl implements SchedaValutazioneService {

	@Autowired
	private SchedaDiValutazioneDao schedaDiValutazioneDao;

	@Autowired
	private UserService userService;

	@Autowired
	private CandidatoService candidatoService;

	@Autowired
	private SedeDao sedeDao;

	@Override
	@Transactional
	public Schedavalutazione findByCodiceFiscaleAndUsername(String codFiscale, String userUsername) {

		return schedaDiValutazioneDao.findByCodiceFiscaleAndUsername(codFiscale, userUsername);
	}

	@Override
	@Transactional
	public void save(CrmSchedaValutazione crmSchedaValutazione, String codFiscale, String userUsername) {

		// devo creare una Scheda di valutazione
		Schedavalutazione tempSDV = new Schedavalutazione();
		tempSDV.setUtenteRelatore(userService.findByUserName(userUsername));
		tempSDV.setCandidatoRelativo(candidatoService.findByCodiceFiscale(codFiscale));

		if (crmSchedaValutazione.getIdSedePreferita() != null)
			tempSDV.setSedePreferita(sedeDao.findSedeByCityID(crmSchedaValutazione.getIdSedePreferita()));

		tempSDV.setDispSpostamentiTrasferimenti(crmSchedaValutazione.getDispSpostamentiTrasferimenti());
		tempSDV.setDinamicita(crmSchedaValutazione.getDinamicita());
		tempSDV.setComunicativita(crmSchedaValutazione.getComunicativita());
		tempSDV.setComunicativita(crmSchedaValutazione.getComunicativita());
		tempSDV.setPresenza(crmSchedaValutazione.getPresenza());
		tempSDV.setEsperienzePosizione(crmSchedaValutazione.getEsperienzePosizione());
		tempSDV.setEsperienzeGenerali(crmSchedaValutazione.getEsperienzeGenerali());
		tempSDV.setMotivazioneCambiamento(crmSchedaValutazione.getMotivazioneCambiamento());
		tempSDV.setMotivazioneProfessionale(crmSchedaValutazione.getMotivazioneProfessionale());
		tempSDV.setNoteSpostamenti(crmSchedaValutazione.getNoteSpostamenti());
		tempSDV.setLingue(crmSchedaValutazione.getLingue());
		tempSDV.setCompetenza(crmSchedaValutazione.getCompetenza());
		tempSDV.setNote(crmSchedaValutazione.getNote());
		tempSDV.setRetribuzioneAttuale(crmSchedaValutazione.getRetribuzioneAttuale());
		tempSDV.setRetribuzioneRichiesta(crmSchedaValutazione.getRetribuzioneRichiesta());
		tempSDV.setInquadramentoAttuale(crmSchedaValutazione.getInquadramentoAttuale());
		tempSDV.setInquadramentoRichiesto(crmSchedaValutazione.getInquadramentoRichiesto());

		// Setto la data
		// recupero la data in forma String

		String data = crmSchedaValutazione.getDataColloquio();

		String giorno = null, mese = null, anno = null;
		if (data.contains("/")) {
			// Estraggo i giorni
			giorno = data.substring(0, data.indexOf("/"));
			mese = data.substring(data.indexOf("/") + 1, data.lastIndexOf("/"));
			anno = data.substring(data.lastIndexOf("/") + 1, data.length());
		} else if (data.contains("-")) {
			anno = data.substring(0, data.indexOf("-"));
			mese = data.substring(data.indexOf("-") + 1, data.lastIndexOf("-"));
			giorno = data.substring(data.lastIndexOf("-") + 1, data.length());
		}

		if (giorno != null && mese != null && anno != null) {
			int d = Integer.parseInt(giorno);
			int m = Integer.parseInt(mese);
			int a = Integer.parseInt(anno);

			// creo la data
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, d);
			calendar.set(Calendar.MONTH, m - 1);
			calendar.set(Calendar.YEAR, a);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 0);

			tempSDV.setDataColloquio(calendar);
		}

		// cerco con candiadteoService se ho gia un CV allegato
		if (candidatoService.CVpresente(codFiscale)) {
			tempSDV.setCVAllegato("si");
		} else {
			tempSDV.setCVAllegato("no");
		}

		// recupero il periodo di preavviso da candidato con candidatoService
		tempSDV.setPeriodoPreavviso(candidatoService.getPeriodoPreavviso(codFiscale));

		schedaDiValutazioneDao.save(tempSDV);
	}

	@Override
	@Transactional
	public List<Schedavalutazione> findByCodiceFiscale(String codFiscale) {
		return schedaDiValutazioneDao.findByCodiceFiscale(codFiscale);
	}

	@Override
	@Transactional
	public List<Schedavalutazione> findByUsername(String userUsername) {
		return schedaDiValutazioneDao.findByUsername(userUsername);
	}

	@Override
	@Transactional
	public void deleteScheda(String idScheda) {
		schedaDiValutazioneDao.deleteScheda(idScheda);
	}

}
