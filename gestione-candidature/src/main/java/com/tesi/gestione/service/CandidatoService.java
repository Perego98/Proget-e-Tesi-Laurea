package com.tesi.gestione.service;

import java.sql.Blob;
import java.util.List;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.user.CrmCandidato;


public interface CandidatoService {

    Candidato findByCodiceFiscale(String codiceFiscale);

    void save(CrmCandidato crmCandidato);
    
    List<Candidato> getCandidati();
    
    void dowloadCurriculum(String codFiscale);
    
    void deleteCandidato(String codFiscale);
}
