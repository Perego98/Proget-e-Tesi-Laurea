package com.tesi.gestione.service;


import java.util.List;

import com.tesi.gestione.crm.CrmSchedaValutazione;
import com.tesi.gestione.entity.Schedavalutazione;

public interface SchedaValutazioneService {


    void save(CrmSchedaValutazione crmSchedaValutazione, String codFiscale, String userUsername);

	Schedavalutazione findByCodiceFiscaleAndUsername(String codFiscale, String userUsername);
   
	List<Schedavalutazione> findByCodiceFiscale(String codFiscale);

	List<Schedavalutazione> findByUsername(String userUsername);
	
	void deleteScheda(String idScheda);
   
}
