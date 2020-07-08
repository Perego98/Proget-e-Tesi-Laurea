package com.tesi.gestione.controller;

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
import com.tesi.gestione.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private CandidatoService candidatoService;
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private SedeDao sedeDao;
    
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
    
    
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		
		System.out.println(" ********** RegistrationController -> Entrato in showMyLoginPage().");
		
		// get Role from dao
		List<Role> theRoles = roleDao.getRoles();
		
		// fet Sedi from dao
		List<Sede> theSedi = sedeDao.getSedi();
		
		
		System.out.println(" ********** RegistrationController -> Recuperati Ruoli: " + theRoles.toString());
		System.out.println(" ********** RegistrationController -> Recuperati Sedi: " + theSedi.toString());
		
		// add the role to the model
		theModel.addAttribute("roles", theRoles);
		
		// add the sedi to the model
		theModel.addAttribute("sedi", theSedi);
		
		System.out.println(" ********** RegistrationController -> Aggiunti al modello: " + theModel.toString());
		
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		
		System.out.println(" ********** RegistrationController -> Entrato in showMyLoginPage().");
		
		// get Role from dao
		List<Role> theRoles = roleDao.getRoles();
		
		// fet Sedi from dao
		List<Sede> theSedi = sedeDao.getSedi();
		
		
		System.out.println(" ********** RegistrationController -> Recuperati Ruoli: " + theRoles.toString());
		System.out.println(" ********** RegistrationController -> Recuperati Sedi: " + theSedi.toString());
		
		// add the role to the model
		theModel.addAttribute("roles", theRoles);
		
		// add the sedi to the model
		theModel.addAttribute("sedi", theSedi);
		
		System.out.println(" ********** RegistrationController -> Aggiunti al modello: " + theModel.toString());
		
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
     // create user account        						
        userService.save(theCrmUser);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
	
	// candidato 
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
	 		return "registra-candidato";
        }

	 	String codFiscale = CrmCandidato.getCodiceFiscale();
		// check the database if user already exists
        Candidato existing = candidatoService.findByCodiceFiscale(codFiscale);
        if (existing != null){
        	theModel.addAttribute("crmCandidato", new CrmCandidato());
			theModel.addAttribute("registrationError", "Candidato already exists.");

			logger.warning("Candidato already exists.");
        	return "registra-candidato";
        }
		// create user account   
        
		candidatoService.save(CrmCandidato);
		
		logger.info("Successfully created user: " + codFiscale);
		        
        
        return "registration-candidato-confirmation";		
	}
	
	
	@GetMapping("/showCandidatoRegistrationForm")
	public String showMyCandidatoRegistrationPage(Model theModel) {
		
		System.out.println(" ********** RegistrationController -> dentro showMyCandidatoRegistrationPage()");
		
		theModel.addAttribute("crmCandidato", new CrmCandidato());
		
		return "registra-candidato";
	}
	
}
