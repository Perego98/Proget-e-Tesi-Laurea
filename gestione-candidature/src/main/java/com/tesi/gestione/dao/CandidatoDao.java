package com.tesi.gestione.dao;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.User;


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

	public List<Candidato> getCandidatiAssociati(String userUsername);
	
	public List<Candidato> getCandidatiAssociati(String userUsername, int firstResult, int maxResult);
	
	long getCandidatiStatus(String stato);
	
	List<Candidato> getCandidati(int firstResult, int maxResult);

	public int totCandidati();
	
	int totCandidatiAssociati(String userUsername);
	

	
	
}
