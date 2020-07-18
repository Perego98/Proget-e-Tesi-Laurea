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
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.dao.CandidatoDao;
import com.tesi.gestione.dao.UserDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.User;

@Service
public class CandidatoServiceImpl implements CandidatoService {

	@Autowired
	private CandidatoDao candidatoDao;

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Candidato findByCodiceFiscale(String codiceFiscale) {

		// using DAO
		return candidatoDao.findCandidatoByCF(codiceFiscale);
	}

	@Override
	@Transactional
	public void save(CrmCandidato crmCandidato) {

		Candidato candidato = new Candidato();

		// set the value
		candidato.setCodiceFiscale(crmCandidato.getCodiceFiscale());
		candidato.setNome(crmCandidato.getNome());
		candidato.setCognome(crmCandidato.getCognome());
		candidato.setTelephone(crmCandidato.getTelephone());
		candidato.setEmail(crmCandidato.getEmail());
		candidato.setTipoContratto(crmCandidato.getTipoContratto());
		candidato.setRal(crmCandidato.getRal());
		candidato.setPreavviso(crmCandidato.getPreavviso());
		candidato.setOfferta(crmCandidato.getOfferta());
		candidato.setProveninenza(crmCandidato.getProveninenza());
		candidato.setAspettative(crmCandidato.getAspettative());
		candidato.setNote(crmCandidato.getNote());

		// set default statoCandidatura
		candidato.setStatoCandidatura("assegnato_hr");

		// recupero la data in forma String

		String data = crmCandidato.getDataNascita();
		
		String giorno = null , mese = null, anno = null;
		if(data.contains("/")) {
			// Estraggo i giorni
			giorno = data.substring(0, data.indexOf("/"));
			mese = data.substring(data.indexOf("/") + 1, data.lastIndexOf("/"));
			anno = data.substring(data.lastIndexOf("/") + 1, data.length());
		}
		else if(data.contains("-")){
			anno = data.substring(0, data.indexOf("-"));
			mese = data.substring(data.indexOf("-") + 1, data.lastIndexOf("-"));
			giorno = data.substring(data.lastIndexOf("-") + 1, data.length());
		}
		
		if(giorno != null && mese != null && anno != null) {
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

			candidato.setDataNascita(calendar);
		}
		

		User theHr = userDao.findByUserName(crmCandidato.getHrId());

		// se esiste lo collego
		if (theHr != null)
			candidato.setSupervisore(theHr);

		// save user in the database
		candidatoDao.save(candidato);

	}

	@Override
	public List<Candidato> getCandidati() {
		return candidatoDao.getCandidati();
	}

	@Override
	@Transactional
	public void deleteCandidato(String codFiscale) {
		candidatoDao.deleteCandidato(codFiscale);
	}

	@Override
	@Transactional
	@CrossOrigin
	public void dowloadCurriculum(String codFiscale) {

		// Retrive curriculum from database
		Blob theBlob = candidatoDao.dowloadCurriculum(codFiscale);

		System.out.println("Blob: " + theBlob.toString());

		String fileName = "curriculum_" + codFiscale + ".pdf";
		File file = new File(fileName);

		if (!file.exists()) {

			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("File created: " + file);
		} else {
			System.out.println("File gia esistente: " + file);
		}

		// save in download
		InputStream in = null;
		FileOutputStream out = null;

		try {
			in = theBlob.getBinaryStream();
			out = new FileOutputStream(fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		byte[] buff = new byte[4096]; // how much of the blob to read/write at a time
		int len = 0;

		// scrivo su file
		try {
			System.out.println("*********** START READ Blob:" + in.toString());
			while ((len = in.read(buff)) != -1) {
				out.write(buff, 0, len);
//				System.out.println(buff);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Update

	@Override
	@Transactional
	public void update(String codFiscale, CrmCandidatoUpdate crmCandidatoUpdate) {

		Candidato candidato = candidatoDao.findCandidatoByCF(codFiscale);

		// set the value
		candidato.setNome(crmCandidatoUpdate.getNome());
		candidato.setCognome(crmCandidatoUpdate.getCognome());
		candidato.setTelephone(crmCandidatoUpdate.getTelephone());
		candidato.setEmail(crmCandidatoUpdate.getEmail());
		candidato.setRal(crmCandidatoUpdate.getRal());
		candidato.setPreavviso(crmCandidatoUpdate.getPreavviso());
		candidato.setProveninenza(crmCandidatoUpdate.getProveninenza());
		candidato.setAspettative(crmCandidatoUpdate.getAspettative());
		candidato.setNote(crmCandidatoUpdate.getNote());

		if (crmCandidatoUpdate.getTipoContratto() != null) {
			candidato.setTipoContratto(crmCandidatoUpdate.getTipoContratto());
		}

		if (crmCandidatoUpdate.getOfferta() != null) {
			candidato.setOfferta(crmCandidatoUpdate.getOfferta());
		}

		// recupero la data in forma String
		if (crmCandidatoUpdate.getDataNascita() != null) {
			String data = crmCandidatoUpdate.getDataNascita();

			// Estraggo i giorni
			String giorno = data, mese, anno;
			giorno = data.substring(0, data.indexOf("/"));
			mese = data.substring(data.indexOf("/") + 1, data.lastIndexOf("/"));
			anno = data.substring(data.lastIndexOf("/") + 1, data.length());

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
//			calendar.setTime(data);

			// salvo la data
//			candidato.setDataNascita(calendar.getTime());

			candidato.setDataNascita(calendar);

		}

//		if(crmCandidatoUpdate.getCurriculum() != null) {
//			// salvo curriculum
//			Blob blob = null;
//			try {
//				blob = new javax.sql.rowset.serial.SerialBlob(crmCandidatoUpdate.getCurriculum());
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (blob != null)
//				candidato.setCurriculum(blob);
//		}
//		

		// save user in the database
		candidatoDao.save(candidato);

	}

	@Override
	@Transactional
	public void changeStato(CrmStato crmStato, Candidato theCandidato) {

		if (crmStato.getStatoCandidatura() != null) {
			theCandidato.setStatoCandidatura(crmStato.getStatoCandidatura());
			candidatoDao.save(theCandidato);
		}

	}

	@Override
	@Transactional
	public void changeSupervisore(String userUsername, Candidato theCandidato) {
		theCandidato.setSupervisore(userDao.findByUserName(userUsername));
		
		// solo se il candidato era a new lo cambio con assegnato_manager
		// in caso contrario o ha già un manager/ hr o è in uno stato che non va cambiato in questo modo
		// perchè è assunto, in valutazione o rigettato
		if(theCandidato.getStatoCandidatura().contains("new")) {
			theCandidato.setStatoCandidatura("assegnato_manager");
		}
			
		candidatoDao.save(theCandidato);
	}

	@Override
	@Transactional
	public void uploadCV(MultipartFile multipartFile, Candidato candidato) {

		byte[] bytes;
		Blob blob = null;
		try {
			bytes = multipartFile.getBytes();
			blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (blob != null) {
			candidato.setCurriculum(blob);

			// save candidato
			candidatoDao.save(candidato);
		}

	}
	
	@Override
    @Transactional
    public List<Candidato> search(String theSearchName) {

        return candidatoDao.search(theSearchName);
    }

}
