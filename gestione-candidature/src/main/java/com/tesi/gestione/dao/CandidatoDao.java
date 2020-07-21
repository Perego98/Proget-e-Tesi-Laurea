package com.tesi.gestione.dao;

import java.sql.Blob;
import java.util.List;

import com.tesi.gestione.entity.Candidato;


public interface CandidatoDao {

	public Candidato findCandidatoByCF(String codFiscale);
	
	public List<Candidato> getCandidati();
	
	void save(Candidato candidato);
	
	void deleteCandidato(String codFiscale);
	
	Blob dowloadCurriculum(String codFiscale);

	void triggerActionOnDeleteUser(String username);
	
	List<Candidato> search(String theSearchName);
	
    boolean CVpresente(String codiceFiscale);
    
    int getPeriodoPreavviso(String codiceFiscale);
	
}
