package com.tesi.gestione.service;

import java.sql.Blob;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tesi.gestione.crm.CrmCandidato;
import com.tesi.gestione.crm.CrmCandidatoUpdate;
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Sede;

public interface CandidatoService {

	Candidato findByCodiceFiscale(String codiceFiscale);

	void save(CrmCandidato crmCandidato);

	List<Candidato> getCandidati();

	void uploadCV(MultipartFile multipartFile, Candidato candidato);

	boolean dowloadCurriculum(String codFiscale);

	void deleteCandidato(String codFiscale);

	void update(String codFiscale, CrmCandidatoUpdate crmCandidatoUpdate);

	void changeStato(CrmStato crmStato, Candidato theCandidato);

	void changeSupervisore(String userUsername, Candidato theCandidato);

	List<Candidato> search(String theSearchName);

	List<Sede> getSedi();

	boolean CVpresente(String codiceFiscale);

	int getPeriodoPreavviso(String codiceFiscale);

	int getCandidatiStatus(String stato);

	List<Candidato> getCandidati(int firstResult, int maxResult);

	int totCandidati();

	int totCandidatiCollegati(String userUsername);

}
