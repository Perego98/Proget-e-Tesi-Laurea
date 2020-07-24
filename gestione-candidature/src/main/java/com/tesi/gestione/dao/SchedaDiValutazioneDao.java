package com.tesi.gestione.dao;

import java.sql.Blob;
import java.util.List;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Schedavalutazione;

public interface SchedaDiValutazioneDao {

	void save(Schedavalutazione schedaDiValutazione);

	Schedavalutazione findByCodiceFiscaleAndUsername(String codFiscale, String userUsername);

	List<Schedavalutazione> findByCodiceFiscale(String codFiscale);

	List<Schedavalutazione> findByUsername(String userUsername);

	void deleteScheda(String idScheda);
}
