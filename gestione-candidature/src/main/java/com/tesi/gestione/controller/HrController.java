package com.tesi.gestione.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tesi.gestione.crm.CrmCandidato;
import com.tesi.gestione.crm.CrmCandidatoUpdate;
import com.tesi.gestione.crm.CrmSchedaValutazione;
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.crm.CrmSupervisore;
import com.tesi.gestione.crm.CrmUser;
import com.tesi.gestione.dao.RoleDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.CandidatoService;
import com.tesi.gestione.service.SchedaValutazioneService;
import com.tesi.gestione.service.UserService;

@Controller
@RequestMapping("/hr")
public class HrController {
	
	private static int FixCandidatiPerPagina = 10;
	
    @Autowired
    private CandidatoService candidatoService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SchedaValutazioneService schedaValutazioneService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showListCandidati")
	public String showMyListCandidati(Model theModel) {
		
//		// devo chiedere a UserService (UserDao) l'elenco degli user
//		List<Candidato> theCandidati = candidatoService.getCandidati();
//
//		
//		// devo aggiungerli al model
//		theModel.addAttribute("candidati", theCandidati);
//		
//		
//		return "list-candidati";		
		
		return "redirect:/hr/showListCandidatiPagination";
	}
	
	@GetMapping("/showListCandidatiPagination")
	public String showMyListCandidatiPagination(Model theModel,
			@RequestParam(value = "registrationSucces", required = false) String registrationSucces, 
			@RequestParam(value = "registrationError", required = false) String registrationError) {
		// numero di utenti per pagina
		int candidatiPerPagina = FixCandidatiPerPagina;
		
		// numero di candidati totali
		int maxSize = candidatoService.totCandidati();
		
		// pagine necessarie
		int numPagine = maxSize / candidatiPerPagina;
		
		// se ho un resto aggiungo una pagina
		if(maxSize % candidatiPerPagina != 0) {
			numPagine++;
		}
	
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = null;
		if(numPagine > 1)
			theCandidati = candidatoService.getCandidati(0, candidatiPerPagina);
		else
			theCandidati = candidatoService.getCandidati(0, maxSize);
		
		
		List<Integer> numeroPagine = new ArrayList<>();

		for(int i = 0; i<numPagine; i++) {
			numeroPagine.add(i+1);
		}
		
		
		// aggiungo le info di chi è loggato
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		theModel.addAttribute("adminAccount", currentPrincipalName);
		theModel.addAttribute("candidati", theCandidati);
		theModel.addAttribute("numeroPagineList", numeroPagine);
		theModel.addAttribute("firstPage", true);
		theModel.addAttribute("pageNumber", 1);
		theModel.addAttribute("candidatiPerPagina", candidatiPerPagina);
		theModel.addAttribute("registrationSucces", registrationSucces);
		theModel.addAttribute("registrationError", registrationError);
		
		return "list-candidati";		
	}
	
	@GetMapping("/showListCandidatiMinMax")
	public String showMyListCandidatiPaginationMinMax(
			Model theModel,
			@RequestParam("firstPage") String firstPage,
			@RequestParam("maxPage") String maxPage) {
		// numero di utenti per pagina
		int candidatiPerPagina = FixCandidatiPerPagina;
		
		// numero di candidati totali
		int maxSize = candidatoService.totCandidati();
		
		// pagine necessarie
		int numPagine = maxSize / candidatiPerPagina;
		
		// se ho un resto aggiungo una pagina
		if(maxSize % candidatiPerPagina != 0) {
			numPagine++;
		}
		
		int pageNumber = (Integer.parseInt(firstPage) / candidatiPerPagina) + 1;
		
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = candidatoService.getCandidati(Integer.parseInt(firstPage), Integer.parseInt(maxPage));
		
		
		
		
		// aggiungo le info di chi è loggato
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		
		List<Integer> numeroPagine = new ArrayList<>();

		for(int i = 0; i<numPagine; i++) {
			numeroPagine.add(i+1);
		}
		
		theModel.addAttribute("candidati", theCandidati);
		theModel.addAttribute("adminAccount", currentPrincipalName);
		theModel.addAttribute("numeroPagineList", numeroPagine);
		theModel.addAttribute("firstPage", false);
		theModel.addAttribute("pageNumber", pageNumber);
		theModel.addAttribute("candidatiPerPagina", candidatiPerPagina);
		
		return "list-candidati";		
	}

	
	
	@GetMapping("/deleteCandidato")
	public String deleteCandidato(@RequestParam("codFiscale") String codFiscale) {
		
		System.out.println("!!!!!! ******** Hr Controller DELETE " + codFiscale);
		
		candidatoService.deleteCandidato(codFiscale);
		
		return "redirect:/hr/showListCandidati";		
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
		
		return "info-candidato";		
	}

	// save candidato
	@PostMapping("/processRegistrationCandidatoForm")
	public String processRegistrationCandidatoForm(
				@Valid @ModelAttribute("crmCandidato") CrmCandidato CrmCandidato, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		System.out.println(" ********** RegistrationController -> dentro processRegistrationCandidatoForm()");
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		CrmCandidato.setHrId(currentPrincipalName);
		
		System.out.println(" ********** RegistrationController -> dentro processRegistrationCandidatoForm -> hrId: " + CrmCandidato.getHrId());
		
		// form validation
	 	if (theBindingResult.hasErrors()){
	 		return "registration-candidato-form";
        }

	 	String codFiscale = CrmCandidato.getCodiceFiscale();
		// check the database if user already exists
        Candidato existing = candidatoService.findByCodiceFiscale(codFiscale);
        if (existing != null){
        	theModel.addAttribute("crmCandidato", new CrmCandidato());
			theModel.addAttribute("registrationError", "Candidato already exists.");

			logger.warning("Candidato already exists.");
        	return "registration-candidato-form";
        }
		// create user account   
        
		candidatoService.save(CrmCandidato);
		

		return "redirect:/hr/showListCandidatiPagination?registrationSucces=Candidato registrato con successo.";
	
	}
	
	
	@GetMapping("/showCandidatoRegistrationForm")
	public String showMyCandidatoRegistrationPage(Model theModel) {
		
		System.out.println(" ********** RegistrationController -> dentro showMyCandidatoRegistrationPage()");
		
		theModel.addAttribute("crmCandidato", new CrmCandidato());
		
		return "registration-candidato-form";
	}
	
	
	@GetMapping("/showCandidatoUpdateForm")
	public String showMyCandidatoUpdatePage(@RequestParam("codFiscale") String codFiscale, 
											Model theModel) {
		
		System.out.println(" ********** RegistrationController -> dentro showMyCandidatoRegistrationPage()");
		
		

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		theModel.addAttribute("crmCandidato", new CrmCandidatoUpdate());
	
		


		String formatted = format1.format(theCandidato.getDataNascita().getTime());
		theModel.addAttribute("dataDiNascita", formatted);
		theCandidato.setDataNascita(null);
		theModel.addAttribute("candidato", theCandidato);
		
		return "update-candidato";
	}
	
	
	// update candidato
	@PostMapping("/processUpdateCandidatoForm")
	public String processUpdateCandidatoForm(
				@RequestParam("codFiscale") String codFiscale, 
				@Valid @ModelAttribute("crmCandidato") CrmCandidatoUpdate CrmCandidato, 
				BindingResult theBindingResult, 
				Model theModel) {
		

		
		
		// form validation
	 	if (theBindingResult.hasErrors()){
	 		
	 		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			
			Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
			theModel.addAttribute("crmCandidato", new CrmCandidatoUpdate());
		
			String formatted = format1.format(theCandidato.getDataNascita().getTime());
			theModel.addAttribute("dataDiNascita", formatted);
			theCandidato.setDataNascita(null);
			theModel.addAttribute("candidato", theCandidato);
	 		return "update-candidato";
        }

	 	
		// check the database if user already exists
        Candidato existing = candidatoService.findByCodiceFiscale(codFiscale);
        
        
        
		candidatoService.update(codFiscale, CrmCandidato);
		
		return "redirect:/hr/showListCandidatiPagination?registrationSucces=Candidato aggiornato con successo.";
	
	}
	
	
	@GetMapping("/showCandidatoUpdateStatoForm")
	public String showMyCandidatoUpdateStatoPage(@RequestParam("codFiscale") String codFiscale, 
											Model theModel) {

		theModel.addAttribute("candidato", candidatoService.findByCodiceFiscale(codFiscale));
		return "update-stato-candidato";
	}
	
	// update candidato
	@PostMapping("/processUpdateStatoCandidatoForm")
	public String processUpdateStatoCandidatoForm(
				@RequestParam("codFiscale") String codFiscale, 
				@Valid @ModelAttribute("crmCandidato") CrmStato CrmStato, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		
		// chiamo serviceCAndidato
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		
		candidatoService.changeStato(CrmStato, theCandidato);

		return "redirect:/hr/showListCandidatiPagination?registrationSucces=Stato del candidato aggiornato con successo.";
		        
	}
	
	
	@GetMapping("/showCandidatoSetManagerForm")
	public String showMyCandidatoSetManagerPage(@RequestParam("codFiscale") String codFiscale, 
											Model theModel) {

		theModel.addAttribute("candidato", candidatoService.findByCodiceFiscale(codFiscale));
		theModel.addAttribute("managers", userService.getManager());
		return "update-set-manager";
	}
	
	// update Supervisore
	@GetMapping("/processUpdateSupervisoreCandidatoForm")
	public String processUpdateSupervisoreCandidatoForm(
				@RequestParam("codFiscale") String codFiscale,
				@RequestParam("userUsername") String userUsername,
				Model theModel) {
		
		
		// chiamo serviceCAndidato
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		
		candidatoService.changeSupervisore(userUsername, theCandidato);

		return "redirect:/hr/showListCandidatiPagination?registrationSucces=" + "Supervisore " + userUsername + 
				" assegnato con successo a " + theCandidato.getNome() + " CF: " + theCandidato.getCodiceFiscale();		
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
		
		return "info-candidato";
	}
	

	// Mostra pagine per aggiungere documento
	@RequestMapping(value = { "/showUploadCV" }, method = RequestMethod.GET)
    public String addDocuments(@RequestParam("codFiscale") String codFiscale, ModelMap model) {
		
		Candidato candidato = candidatoService.findByCodiceFiscale(codFiscale);
        model.addAttribute("candidato", candidato);
 
         
        return "upload-cv";
    }
	
	 @PostMapping("/uploadCV") //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file, 
	    		@RequestParam("codFiscale") String codFiscale) {

	        if (file.isEmpty()) {
	        	// Errore reindirizzamento pagina
	           System.out.println("FILE VUOTO");
	        }
	        Candidato candidato = candidatoService.findByCodiceFiscale(codFiscale);
	        
	        candidatoService.uploadCV(file, candidato);
	        

	        return "redirect:/hr/showListCandidati";
	    }
	 
		@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {

			// search customers from the service
			List<Candidato> theCandidati = candidatoService.search(theSearchName);

			// aggiungo al model
			theModel.addAttribute("candidati", theCandidati);

			return "list-candidati";
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

		return "compilazione-scheda-valutazione";
	}
		
		@PostMapping("/addSchedaValutazione")
	public String addSchedaValutazioneForm(
				@Valid @ModelAttribute("crmSchedaValutazione") CrmSchedaValutazione crmSchedaValutazione, 
				BindingResult theBindingResult, 
				Model theModel,
				@RequestParam("codFiscale") String codFiscale, 
				@RequestParam("userUsername") String userUsername) {
		
		System.out.println(" ********** RegistrationController -> dentro processRegistrationCandidatoForm()");
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		
		// form validation
	 	if (theBindingResult.hasErrors()){
	 						
	 		theModel.addAttribute("sedi", candidatoService.getSedi());
			theModel.addAttribute("candidato", candidatoService.findByCodiceFiscale(codFiscale));
			theModel.addAttribute("user", userService.findByUserName(currentPrincipalName));
	 		
	 		return "compilazione-scheda-valutazione";
        }

	 	
		// check the database if user already exists
//	        Schedavalutazione existing = schedaValutazioneService.findByCodiceFiscaleAndUsername(codFiscale, userUsername);
//	        if (existing != null){
//	        	// devo chiedere a UserService (UserDao) l'elenco degli user
//				List<Candidato> theCandidati = candidatoService.getCandidati();
//
//				// devo aggiungerli al model
//				theModel.addAttribute("candidati", theCandidati);
//				
//				theModel.addAttribute("registrationError", "La scheda di valutazione è già presente.");
//
//				logger.warning("Scheda Valutazione already exists.");
//	        	return "list-candidati";
//	        }
		// create user account   
        
        if(codFiscale != null && userUsername!= null) {
        	schedaValutazioneService.save(crmSchedaValutazione, codFiscale, userUsername);
        }
        else {
        	// devo chiedere a UserService (UserDao) l'elenco degli user
			List<Candidato> theCandidati = candidatoService.getCandidati();

			// devo aggiungerli al model
			theModel.addAttribute("candidati", theCandidati);

        	if(codFiscale != null) {
        		return "redirect:/hr/showListCandidatiPagination?registrationError=Codice fiscale non presente.";
        	}
        	else {
        		return "redirect:/hr/showListCandidatiPagination?registrationError=Username non  presente.";
        	}    		
        }
        
		
    	return "redirect:/hr/showListCandidatiPagination?registrationSucces=Scheda di valutazione caricata con successo.";
			
	}

	
	@GetMapping("/showSchedeValutazione")
	public String showMySchedeValutazione(@RequestParam("codFiscale") String codFiscale,
			Model theModel) {
		
		
		List<Schedavalutazione> theSchede = schedaValutazioneService.findByCodiceFiscale(codFiscale);

		if(theSchede.isEmpty()) {
			theModel.addAttribute("schede", null);
		}else {
			theModel.addAttribute("schede", theSchede);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			for(Schedavalutazione temp : theSchede) {
				String formatted = format1.format(temp.getDataColloquio().getTime());
				theModel.addAttribute("data" + temp.getId(), formatted);
			}
			
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		theModel.addAttribute("accountAttuale", currentPrincipalName);
		
		
		
		return "list-schede";		
	}
	
	@GetMapping("/deleteScheda")
	public String deleteScheda(@RequestParam("codScheda") String codScheda, Model theModel) {
		
		
		schedaValutazioneService.deleteScheda(codScheda);
		
		return "redirect:/hr/showListCandidatiPagination";
	}
	
	
}
