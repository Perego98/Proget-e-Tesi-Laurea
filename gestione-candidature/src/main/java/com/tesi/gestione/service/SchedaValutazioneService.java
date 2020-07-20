package com.tesi.gestione.service;


import com.tesi.gestione.crm.CrmSchedaValutazione;
import com.tesi.gestione.entity.SchedaDiValutazione;

public interface SchedaValutazioneService {


    void save(CrmSchedaValutazione crmSchedaValutazione);

	SchedaDiValutazione findByCodiceFiscaleAndUsername(String codFiscale, String userUsername);
    
   
}
