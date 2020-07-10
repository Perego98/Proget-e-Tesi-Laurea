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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tesi.gestione.dao.CandidatoDao;
import com.tesi.gestione.dao.UserDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.user.CrmCandidato;

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
		candidato.setStatoCandidatura("new");
		
		// recupero la data in forma String
		String data = crmCandidato.getDataNascita();
		
		//Estraggo i giorni
		String giorno, mese, anno;
		giorno = data.substring(0, data.indexOf("/"));
		mese = data.substring(data.indexOf("/")+1, data.lastIndexOf("/"));
		anno = data.substring(data.lastIndexOf("/")+1, data.length());	
		
		int d = Integer.parseInt(giorno);
		int m = Integer.parseInt(mese);
		int a = Integer.parseInt(anno);
		 
		// creo la data
		Calendar calendar = GregorianCalendar.getInstance();
	    calendar.set(Calendar.DAY_OF_MONTH, d);
	    calendar.set(Calendar.MONTH, m-1);
	    calendar.set(Calendar.YEAR, a);
	    calendar.set(Calendar.MILLISECOND, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);    

		// salvo la data
		candidato.setDataNascita(calendar);
		
		// salvo curriculum
		Blob blob = null;
		try {
			blob = new javax.sql.rowset.serial.SerialBlob(crmCandidato.getCurriculum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (blob != null)
			candidato.setCurriculum(blob);

		
		// salvo l'hr che ha creato il candidato
				// di default viene assegnato a lui
		//	Recupero l'username
		User theHr = userDao.findByUserName(crmCandidato.getHrId());
		
		// se esiste lo collego
		if(theHr != null)
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

       
       

//		String fileName = "curriculum_"+codFiscale+".txt";
//		File file = new File(fileName);
		
//		if(!file.exists()) {
//			
//			try {
//				file.createNewFile();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		
//			 System.out.println("File created: " + file);
//		}else {
//			System.out.println("File gia esistente: " + file);
//		}
        
        
        
		
		// save in download
		InputStream in = null;
//		FileOutputStream  out = null;
		
		try {
			in = theBlob.getBinaryStream();
//			out = new FileOutputStream(fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		
		
		byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
		int len = 0;
		
		// scrivo su file
		try {
			System.out.println("*********** START READ Blob:"+ in.toString());
			while ((len = in.read(buff)) != -1) {
//			    out.write(buff, 0, len);
				System.out.println(buff);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
