package com.tesi.gestione.service;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.user.CrmCandidato;


public interface CandidatoService {

    Candidato findByCodiceFiscale(String codiceFiscale);

    void save(CrmCandidato crmCandidato);
}
