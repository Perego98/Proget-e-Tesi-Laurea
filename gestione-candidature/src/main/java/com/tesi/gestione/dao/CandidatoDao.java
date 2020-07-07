package com.tesi.gestione.dao;

import java.util.List;

import com.tesi.gestione.entity.Candidato;


public interface CandidatoDao {

	public Candidato findCandidatoByCF(String codFiscale);
	
	public List<Candidato> getCandidati();
	
	void save(Candidato candidato);
	
}
