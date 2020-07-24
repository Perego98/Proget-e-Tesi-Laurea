package com.tesi.gestione.service;

import java.sql.Blob;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tesi.gestione.crm.CrmCandidato;
import com.tesi.gestione.crm.CrmCandidatoUpdate;
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Sede;

public interface ManagerService {

	List<Candidato> getCandidatiAssociati(String userUsername);

	List<Candidato> getCandidatiAssociati(String userUsername, int firstResult, int maxResult);

	int totCandidatiAssociati(String userUsername);

}
