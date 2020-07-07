package com.tesi.gestione.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesi.gestione.dao.CandidatoDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.user.CrmCandidato;

@Service
public class CandidatoServiceImpl implements CandidatoService {
	
	@Autowired
	private CandidatoDao candidatoDao;
	
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
		candidato.setEmail(crmCandidato.getTelephone());
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
		Date theData =  new Date(d, m, a);
		
		// salvo la data
		candidato.setDataNascita(theData);
		
		

		
		
		
		// save user in the database
		candidatoDao.save(candidato);

	}

}
