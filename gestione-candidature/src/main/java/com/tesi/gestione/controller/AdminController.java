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
@RequestMapping("/admin")
public class AdminController {
	

	
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
	
	@GetMapping("/showListUsers")
	public String showMyListUsers(Model theModel) {
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<User> theUsers = userService.getUsers();
		
		// devo aggiungerli al model
		theModel.addAttribute("users", theUsers);
		
		
		return "list-users";		
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userUsername") String theUsername) {
		
		System.out.println("!!!!!! ******** Admin COntroller DELETE " + theUsername);
		
		userService.deleteUser(theUsername);
		
		return "redirect:/admin/showListUsers";		
	}

	
	
}
