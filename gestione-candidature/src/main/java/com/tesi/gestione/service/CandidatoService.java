package com.tesi.gestione.service;

import java.util.List;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.user.CrmCandidato;


public interface CandidatoService {

    Candidato findByCodiceFiscale(String codiceFiscale);

    void save(CrmCandidato crmCandidato);
    
    List<Candidato> getCandidati();
    
    void deleteCandidato(String codFiscale);
}
