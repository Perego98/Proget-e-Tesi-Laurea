package com.tesi.gestione.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tesi.gestione.crm.CrmCandidatoUpdate;
import com.tesi.gestione.crm.CrmRole;
import com.tesi.gestione.crm.CrmSchedaValutazione;
import com.tesi.gestione.crm.CrmSede;
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.crm.CrmUser;
import com.tesi.gestione.crm.CrmUserUpdate;
import com.tesi.gestione.dao.RoleDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.CandidatoService;
import com.tesi.gestione.service.ManagerService;
import com.tesi.gestione.service.SchedaValutazioneService;
import com.tesi.gestione.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	

	@Autowired
	private ManagerService managerService;

	@Autowired
	private CandidatoService candidatoService;

	@Autowired
	private UserService userService;

	@Autowired
	private SchedaValutazioneService schedaValutazioneService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	
	
	@GetMapping("/showListCandidati")
	public String showMyListCandidati(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		List<Candidato> theCandidati = managerService.getCandidatiAssociati(currentPrincipalName);

		
		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		
		return "list-candidati-manager";		
	}
	
	@GetMapping("/showMoreInfoCandidato")
	public String showMoreInfoCandidato(@RequestParam("codFiscale") String codFiscale, 
										Model theModel) {
		
		
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		theModel.addAttribute("candidato", theCandidato);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		List<Schedavalutazione> theSchede = schedaValutazioneService.findByCodiceFiscale(codFiscale);		
		

		if(theSchede.isEmpty()) {
			theModel.addAttribute("schedaVal", null);
		}else {
			theModel.addAttribute("schedaVal", theSchede);
		}
		

		String formatted = format1.format(theCandidato.getDataNascita().getTime());
		theModel.addAttribute("dataN", formatted);
		
		return "info-candidato-manager";		
	}

	@GetMapping("/showCandidatoUpdateStatoForm")
	public String showMyCandidatoUpdateStatoPage(@RequestParam("codFiscale") String codFiscale, 
											Model theModel) {

		theModel.addAttribute("candidato", candidatoService.findByCodiceFiscale(codFiscale));
		return "update-stato-candidato-manager";
	}
	
	@PostMapping("/processUpdateStatoCandidatoForm")
	public String processUpdateStatoCandidatoForm(
				@RequestParam("codFiscale") String codFiscale, 
				@Valid @ModelAttribute("crmCandidato") CrmStato CrmStato, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		
		// chiamo serviceCAndidato
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		
		candidatoService.changeStato(CrmStato, theCandidato);

		        
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
	
		List<Candidato> theCandidati = managerService.getCandidatiAssociati(currentPrincipalName);

		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		theModel.addAttribute("registrationSucces", "Stato del candidato aggiornato con successo.");
		
        return "list-candidati-manager";		
	}
	
	@GetMapping("/downloadCurriculum")
	public String downloadCurriculum(@RequestParam("codFiscale") String codFiscale,
									Model theModel) {
		
		// call serivce
		
		candidatoService.dowloadCurriculum(codFiscale);
		
		List<Schedavalutazione> theSchede = schedaValutazioneService.findByCodiceFiscale(codFiscale);		
		

		if(theSchede.isEmpty()) {
			theModel.addAttribute("schedaVal", null);
		}else {
			theModel.addAttribute("schedaVal", theSchede);
		}
		
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		theModel.addAttribute("candidato", theCandidato);
		
		return "info-candidato-manager";
	}
	
	@GetMapping("/showCompilazioneSchedaValutazioneForm")
	public String showMyCompilazioneSchedaValutazionePage(@RequestParam("codFiscale") String codFiscale,
			Model theModel) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		theModel.addAttribute("crmSchedaValutazione", new CrmSchedaValutazione());
		theModel.addAttribute("candidato", candidatoService.findByCodiceFiscale(codFiscale));
		theModel.addAttribute("user", userService.findByUserName(currentPrincipalName));
		theModel.addAttribute("sedi", candidatoService.getSedi());

		return "compilazione-scheda-valutazione-manager";
	}
		
	@PostMapping("/addSchedaValutazione")
	public String addSchedaValutazioneForm(
			@Valid @ModelAttribute("crmSchedaValutazione") CrmSchedaValutazione crmSchedaValutazione,
			BindingResult theBindingResult, Model theModel, @RequestParam("codFiscale") String codFiscale,
			@RequestParam("userUsername") String userUsername) {

		System.out.println(" ********** RegistrationController -> dentro processRegistrationCandidatoForm()");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		// form validation
		if (theBindingResult.hasErrors()) {

			theModel.addAttribute("sedi", candidatoService.getSedi());
			theModel.addAttribute("candidato", candidatoService.findByCodiceFiscale(codFiscale));
			theModel.addAttribute("user", userService.findByUserName(currentPrincipalName));

			return "compilazione-scheda-valutazione";
		}

		if (codFiscale != null && userUsername != null) {
			schedaValutazioneService.save(crmSchedaValutazione, codFiscale, userUsername);
		} else {
			// devo chiedere a UserService (UserDao) l'elenco degli user
			List<Candidato> theCandidati = candidatoService.getCandidati();

			// devo aggiungerli al model
			theModel.addAttribute("candidati", theCandidati);

			if (codFiscale != null) {
				theModel.addAttribute("registrationError", "Codice fiscale non presente.");
			} else {
				theModel.addAttribute("registrationError", "Username non  presente.");
			}
			return "list-candidati-manager";
		}
		

		List<Candidato> theCandidati = managerService.getCandidatiAssociati(currentPrincipalName);

		

		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		theModel.addAttribute("registrationSucces", "Scheda di valutazione caricata con successo.");
		
        return "list-candidati-manager";		
	}
	
		
	@GetMapping("/showSchedeValutazione")
	public String showMySchedeValutazione(@RequestParam("codFiscale") String codFiscale, Model theModel) {

		List<Schedavalutazione> theSchede = schedaValutazioneService.findByCodiceFiscale(codFiscale);

		if (theSchede.isEmpty()) {
			theModel.addAttribute("schede", null);
		} else {
			theModel.addAttribute("schede", theSchede);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			for (Schedavalutazione temp : theSchede) {
				String formatted = format1.format(temp.getDataColloquio().getTime());
				theModel.addAttribute("data" + temp.getId(), formatted);
			}

		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		theModel.addAttribute("accountAttuale", currentPrincipalName);

		return "list-schede-manager";
	}

		@GetMapping("/deleteScheda")
	public String deleteScheda(@RequestParam("codScheda") String codScheda, Model theModel) {
		
		
		schedaValutazioneService.deleteScheda(codScheda);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		List<Candidato> theCandidati = managerService.getCandidatiAssociati(currentPrincipalName);

		
		theModel.addAttribute("candidati", theCandidati);
		
		theModel.addAttribute("registrationSucces", "Scheda di valutazione cancellata con successo.");
		
        return "list-candidati-manager";	
	}

}