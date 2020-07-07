package com.tesi.gestione.service;

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
		
		// set default statoCandidatura
		candidato.setStatoCandidatura("new");
		
		
		
		// save user in the database
		candidatoDao.save(candidato);

	}

}
