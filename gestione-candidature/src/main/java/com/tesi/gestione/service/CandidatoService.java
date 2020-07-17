package com.tesi.gestione.service;

import java.sql.Blob;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.user.CrmCandidato;
import com.tesi.gestione.user.CrmCandidatoUpdate;
import com.tesi.gestione.user.CrmStato;


public interface CandidatoService {

    Candidato findByCodiceFiscale(String codiceFiscale);

    void save(CrmCandidato crmCandidato);
    
    List<Candidato> getCandidati();
    
    void uploadCV(MultipartFile multipartFile, Candidato candidato);
    
    void dowloadCurriculum(String codFiscale);
    
    void deleteCandidato(String codFiscale);
    
    void update(String codFiscale, CrmCandidatoUpdate crmCandidatoUpdate);
    
    void changeStato(CrmStato crmStato, Candidato theCandidato);
    
    void changeSupervisore(String userUsername,  Candidato theCandidato);
    
    List<Candidato> search(String theSearchName);
}
