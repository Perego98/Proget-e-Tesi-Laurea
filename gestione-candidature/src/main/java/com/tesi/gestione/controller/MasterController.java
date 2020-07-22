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

import com.tesi.gestione.crm.CrmRole;
import com.tesi.gestione.crm.CrmSede;
import com.tesi.gestione.crm.CrmUser;
import com.tesi.gestione.crm.CrmUserUpdate;
import com.tesi.gestione.dao.RoleDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.CandidatoService;
import com.tesi.gestione.service.MasterService;
import com.tesi.gestione.service.UserService;

@Controller
@RequestMapping("/master")
public class MasterController {
	

	 @Autowired
	 private MasterService masterService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	
	@GetMapping("/showListCandidati")
	public String showMyListCandidati(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = masterService.getCandidatiAssociati(currentPrincipalName);

		
		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		
		return "list-candidati-hr";		
	}
	
}
