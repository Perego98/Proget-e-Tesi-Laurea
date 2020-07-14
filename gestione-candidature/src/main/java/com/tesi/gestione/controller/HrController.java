package com.tesi.gestione.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.tesi.gestione.dao.RoleDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.CandidatoService;
import com.tesi.gestione.service.UserService;
import com.tesi.gestione.user.CrmCandidato;
import com.tesi.gestione.user.CrmCandidatoUpdate;
import com.tesi.gestione.user.CrmStato;
import com.tesi.gestione.user.CrmUser;

@Controller
@RequestMapping("/hr")
public class HrController {
	

	
    @Autowired
    private CandidatoService candidatoService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showListCandidati")
	public String showMyListCandidati(Model theModel) {
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = candidatoService.getCandidati();

		
		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		
		return "list-candidati";		
	}
	
	@GetMapping("/deleteCandidato")
	public String deleteCandidato(@RequestParam("codFiscale") String codFiscale) {
		
		System.out.println("!!!!!! ******** Hr Controller DELETE " + codFiscale);
		
		candidatoService.deleteCandidato(codFiscale);
		
		return "redirect:/hr/showListCandidati";		
	}
	
//	@GetMapping("/downloadCurriculum")
//	public String downloadCurriculum(@RequestParam("codFiscale") String codFiscale) {
//		
//		System.out.println("!!!!!! *C*C*C*C*C*C*C* Hr Controller Download Curriculum " + codFiscale);
//		
//		candidatoService.dowloadCurriculum(codFiscale);
//		
//		return "redirect:/hr/showListCandidati";		
//	}
	
	@GetMapping("/showMoreInfoCandidato")
	public String showMoreInfoCandidato(@RequestParam("codFiscale") String codFiscale, 
										Model theModel) {
		
		
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		theModel.addAttribute("candidato", theCandidato);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		

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
		
		logger.info("Successfully created user: " + codFiscale);
		        
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = candidatoService.getCandidati();

		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		theModel.addAttribute("registrationSucces", "Candidato registrato con successo.");
		
        return "list-candidati";		
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
		
		logger.info("Successfully created user: " + codFiscale);
		        
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = candidatoService.getCandidati();

		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		theModel.addAttribute("registrationSucces", "Candidato registrato con successo.");
		
        return "list-candidati";		
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

		        
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = candidatoService.getCandidati();

		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		theModel.addAttribute("registrationSucces", "Candidato registrato con successo.");
		
        return "list-candidati";		
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/downloadCurriculum")
	public String downloadCurriculum(@RequestParam("codFiscale") String codFiscale,
									Model theModel) {
		
		// call serivce
		
		candidatoService.dowloadCurriculum(codFiscale);
		
		Candidato theCandidato = candidatoService.findByCodiceFiscale(codFiscale);
		theModel.addAttribute("candidato", theCandidato);
		
		return "info-candidato";
	}
	
	
	
}
