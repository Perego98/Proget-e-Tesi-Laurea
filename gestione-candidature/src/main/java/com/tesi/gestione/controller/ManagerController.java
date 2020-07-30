package com.tesi.gestione.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.tesi.gestione.copy.SchedavalutazioneCopy;
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

	private static int FixCandidatiPerPagina = 10;

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

//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//
//		List<Candidato> theCandidati = managerService.getCandidatiAssociati(currentPrincipalName);
//
//		
//		// devo aggiungerli al model
//		theModel.addAttribute("candidati", theCandidati);
//		
//		
//		return "list-candidati-manager";	

		return "redirect:/manager/showListCandidatiPagination";
	}

	@GetMapping("/showListCandidatiPagination")
	public String showMyListCandidatiPagination(Model theModel,
			@RequestParam(value = "registrationSucces", required = false) String registrationSucces,
			@RequestParam(value = "registrationError", required = false) String registrationError) {

		// aggiungo le info di chi è loggato
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		// numero di utenti per pagina
		int candidatiPerPagina = FixCandidatiPerPagina;

		// numero di candidati totali
		int maxSize = managerService.totCandidatiAssociati(currentPrincipalName);

		// pagine necessarie
		int numPagine = maxSize / candidatiPerPagina;

		// se ho un resto aggiungo una pagina
		if (maxSize % candidatiPerPagina != 0) {
			numPagine++;
		}

		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = null;
		if (numPagine > 1)
			theCandidati = managerService.getCandidatiAssociati(currentPrincipalName, 0, candidatiPerPagina);
		else
			theCandidati = managerService.getCandidatiAssociati(currentPrincipalName, 0, maxSize);

		List<Integer> numeroPagine = new ArrayList<>();

		for (int i = 0; i < numPagine; i++) {
			numeroPagine.add(i + 1);
		}

		theModel.addAttribute("adminAccount", currentPrincipalName);
		theModel.addAttribute("candidati", theCandidati);
		theModel.addAttribute("numeroPagineList", numeroPagine);
		theModel.addAttribute("firstPage", true);
		theModel.addAttribute("pageNumber", 1);
		theModel.addAttribute("candidatiPerPagina", candidatiPerPagina);
		theModel.addAttribute("registrationSucces", registrationSucces);
		theModel.addAttribute("registrationError", registrationError);

		return "list-candidati-manager";
	}

	@GetMapping("/showListCandidatiMinMax")
	public String showMyListCandidatiPaginationMinMax(Model theModel, @RequestParam("firstPage") String firstPage,
			@RequestParam("maxPage") String maxPage) {

		// aggiungo le info di chi è loggato
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		// numero di utenti per pagina
		int candidatiPerPagina = FixCandidatiPerPagina;

		// numero di candidati totali
		int maxSize = managerService.totCandidatiAssociati(currentPrincipalName);

		// pagine necessarie
		int numPagine = maxSize / candidatiPerPagina;

		// se ho un resto aggiungo una pagina
		if (maxSize % candidatiPerPagina != 0) {
			numPagine++;
		}

		int pageNumber = (Integer.parseInt(firstPage) / candidatiPerPagina) + 1;

		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = managerService.getCandidatiAssociati(currentPrincipalName,
				Integer.parseInt(firstPage), Integer.parseInt(maxPage));

		List<Integer> numeroPagine = new ArrayList<>();

		for (int i = 0; i < numPagine; i++) {
			numeroPagine.add(i + 1);
		}

		theModel.addAttribute("candidati", theCandidati);
		theModel.addAttribute("adminAccount", currentPrincipalName);
		theModel.addAttribute("numeroPagineList", numeroPagine);
		theModel.addAttribute("firstPage", false);
		theModel.addAttribute("pageNumber", pageNumber);
		theModel.addAttribute("candidatiPerPagina", candidatiPerPagina);

		return "list-candidati-manager";
	}

	@GetMapping("/showMoreInfoCandidato")
	public String showMoreInfoCandidato(@RequestParam("codFiscale") String codFiscale, Model theModel) {

		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		theModel.addAttribute("candidato", theCandidato);

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		List<Schedavalutazione> theSchede = schedaValutazioneService.findByCodiceFiscale(codFiscale);

		if (theSchede.isEmpty()) {
			theModel.addAttribute("schedaVal", null);
		} else {
			theModel.addAttribute("schedaVal", theSchede);
		}

		String formatted = format1.format(theCandidato.getDataNascita().getTime());
		theModel.addAttribute("dataN", formatted);

		return "info-candidato-manager";
	}

	@GetMapping("/showCandidatoUpdateStatoForm")
	public String showMyCandidatoUpdateStatoPage(@RequestParam("codFiscale") String codFiscale, Model theModel) {

		theModel.addAttribute("candidato", candidatoService.findByCodiceFiscale(codFiscale));
		return "update-stato-candidato-manager";
	}

	@PostMapping("/processUpdateStatoCandidatoForm")
	public String processUpdateStatoCandidatoForm(@RequestParam("codFiscale") String codFiscale,
			@Valid @ModelAttribute("crmCandidato") CrmStato CrmStato, BindingResult theBindingResult, Model theModel) {

		// chiamo serviceCAndidato
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);

		candidatoService.changeStato(CrmStato, theCandidato);

		return "redirect:/manager/showListCandidatiPagination?registrationSucces=Stato del candidato aggiornato con successo.";
	}

	@GetMapping("/downloadCurriculum")
	public String downloadCurriculum(@RequestParam("codFiscale") String codFiscale, Model theModel) {

		// call serivce

		candidatoService.dowloadCurriculum(codFiscale);

		List<Schedavalutazione> theSchede = schedaValutazioneService.findByCodiceFiscale(codFiscale);

		if (theSchede.isEmpty()) {
			theModel.addAttribute("schedaVal", null);
		} else {
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
				return "redirect:/manager/showListCandidatiPagination?registrationError=Codice fiscale non presente.";
			} else {
				return "redirect:/manager/showListCandidatiPagination?registrationError=Username non  presente.";
			}
		}

		return "redirect:/manager/showListCandidatiPagination?registrationSucces=Scheda di valutazione caricata con successo.";
	}

	@GetMapping("/showSchedeValutazione")
	public String showMySchedeValutazione(@RequestParam("codFiscale") String codFiscale,
			@RequestParam(value = "registrationSucces", required = false) String registrationSucces,
			@RequestParam(value = "registrationError", required = false) String registrationError, Model theModel) {

		List<Schedavalutazione> theSchede = schedaValutazioneService.findByCodiceFiscale(codFiscale);
		List<SchedavalutazioneCopy> theSchedeCopy = new ArrayList<>();
		
		
		if (theSchede.isEmpty()) {
			theModel.addAttribute("schede", null);
		} else {
			for (Schedavalutazione temp : theSchede) {
				theSchedeCopy.add(new SchedavalutazioneCopy(temp));
			}
			
			// aggiungo le schede con data formattata al model
			theModel.addAttribute("schede", theSchedeCopy);
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		theModel.addAttribute("accountAttuale", currentPrincipalName);
		theModel.addAttribute("codiceFiscale", codFiscale);
		theModel.addAttribute("registrationSucces", registrationSucces);
		theModel.addAttribute("registrationError", registrationError);

		return "list-schede-manager";
	}

	@GetMapping("/deleteScheda")
	public String deleteScheda(@RequestParam("codScheda") String codScheda,
			@RequestParam("codFiscale") String codFiscale, Model theModel) {

		schedaValutazioneService.deleteScheda(codScheda);

		return "redirect:/manager/showSchedeValutazione?codFiscale=" + codFiscale
				+ "&registrationSucces=Scheda di valutazione cancellata con successo.";
	}

}
