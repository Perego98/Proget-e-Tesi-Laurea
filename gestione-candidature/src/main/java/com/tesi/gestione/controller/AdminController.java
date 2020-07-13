package com.tesi.gestione.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.UserService;
import com.tesi.gestione.user.CrmRole;
import com.tesi.gestione.user.CrmSede;
import com.tesi.gestione.user.CrmUser;
import com.tesi.gestione.user.CrmUserUpdate;

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

	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		
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
		
		
		return "registration-user-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		List<Role> theRoles = roleDao.getRoles();
		List<Sede> theSedi = sedeDao.getSedi();
		
		theModel.addAttribute("roles", theRoles);
		theModel.addAttribute("sedi", theSedi);
	
		
		String userName = theCrmUser.getUserName();
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-user-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "Username gia presente.");

			logger.warning("User name already exists.");
        	return "registration-user-form";
        }
     // create user account        						
        userService.save(theCrmUser);
        
        logger.info("Successfully created user: " + userName);
        
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<User> theUsers = userService.getUsers();

		// devo aggiungerli al model
		theModel.addAttribute("users", theUsers);
        
		theModel.addAttribute("registrationSucces", "Utente registrato con successo.");
		
        return "list-users";		
	}
	
	@GetMapping("/showFormForUpdateUser")
	public String showFormForUpdateUser(
				@RequestParam("userUsername") String theUsername,
				Model theModel) {

		System.out.println("********* AdminController --------- Dentro showFormForUpdateUser ->  Start");
		theModel.addAttribute("crmUser", new CrmUserUpdate());
		
		User theUser = userService.findByUserName(theUsername);
		List<Role> theRoles = roleDao.getRoles();
		List<Sede> theSedi = sedeDao.getSedi();
		
		theModel.addAttribute("roles", theRoles);
		theModel.addAttribute("sedi", theSedi);
		theModel.addAttribute("user", theUser);
        
        return "update-user";		
	}
	
	@PostMapping("/processUpdateUserForm")
	public String processUpdateUserForm(
				@RequestParam("userUsername") String theUsername,
				@Valid @ModelAttribute("crmUser") CrmUserUpdate theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		List<Role> theRoles = roleDao.getRoles();
		List<Sede> theSedi = sedeDao.getSedi();
		
		theModel.addAttribute("roles", theRoles);
		theModel.addAttribute("sedi", theSedi);
	
		System.out.println("********* AdminController --------- Dentro processUpdateUserForm ->  Start");
		
		// form validation
		if (theBindingResult.hasErrors()) {
			System.out.println("********* AdminController --------- Dentro processUpdateUserForm ->  theBindingResult.hasErrors()");
			
			User theUser = userService.findByUserName(theUsername);
			
			theModel.addAttribute("user", theUser);
			
			theModel.addAttribute("registrationError", "Uno o più parametri non sono corretti!");
			
			return "update-user";
		}
		
		// create user account
		userService.update(theUsername, theCrmUser);
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<User> theUsers = userService.getUsers();
		
		// devo aggiungerli al model
		theModel.addAttribute("users", theUsers);
		
		theModel.addAttribute("registrationSucces", "Utente aggiornato con successo.");
		
		System.out.println("********* AdminController --------- Dentro processUpdateUserForm ->  list-users");
		
		return "list-users";	
	}
	
	@GetMapping("/activateUser")
	public String activateUser(@RequestParam("userUsername") String theUsername) {
		
		userService.activateUser(theUsername);
		
		return "redirect:/admin/showListUsers";	
	}
	
	@GetMapping("/deactivateUser")
	public String deactivateUser(@RequestParam("userUsername") String theUsername) {
		
		userService.deactivateUser(theUsername);
		
		return "redirect:/admin/showListUsers";	
	}
	
	// mostra la pagina per aggiornare la sede
	@GetMapping("/showFormForUpdateUserSede")
	public String showFormForUpdateUserSede(
				@RequestParam("userUsername") String theUsername,
				Model theModel) {
		
		
		List<Sede> theSedi = sedeDao.getSedi();
//		
		theModel.addAttribute("sedi", theSedi);
		
		// recupero la sede attuale
		theModel.addAttribute("sedeAttuale", userService.findByUserName(theUsername).getSedeAssegnamento());
		
		theModel.addAttribute("crmSede", new CrmSede());		
		
		theModel.addAttribute("userUsername", theUsername);
		
//		User theUser = userService.findByUserName(theUsername);
//		
//		theModel.addAttribute("user", theUser);
        
        return "update-user-sede";		
	}
	
	// processa la pagina per salvare la nuova sede
	@PostMapping("/processUpdateUserSedeForm")
	public String processUpdateUserSedeForm(
				@RequestParam("userUsername") String theUsername,
				@Valid @ModelAttribute("crmUser") CrmSede theSede, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		List<Sede> theSedi = sedeDao.getSedi();
		
		theModel.addAttribute("sedi", theSedi);
	
		// create user account
		User theUser = userService.findByUserName(theUsername);
		
		userService.changeSede(theSede, theUser);
		
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<User> theUsers = userService.getUsers();
				
		// devo aggiungerli al model
		theModel.addAttribute("users", theUsers);
		
		theModel.addAttribute("registrationSucces", "Sede aggiornata con successo.");
		
		return "list-users";	
	}
		
	
	@GetMapping("/showFormForUpdateUserRole")
	public String showFormForUpdateUserRole(
				@RequestParam("userUsername") String theUsername,
				Model theModel) {
		
		
		List<Role> theRoles = roleDao.getRoles();
//		
		theModel.addAttribute("roles", theRoles);
		
		// recupero la sede attuale
		theModel.addAttribute("ruoloAttuale", userService.findByUserName(theUsername).getRoles());
		
		theModel.addAttribute("crmRole", new CrmRole());		
		
		theModel.addAttribute("userUsername", theUsername);
		
//		User theUser = userService.findByUserName(theUsername);
//		
//		theModel.addAttribute("user", theUser);
        
        return "update-user-role";		
	}
	
	@PostMapping("/processUpdateUserRoleForm")
	public String processUpdateUserRoleForm(
				@RequestParam("userUsername") String theUsername,
				@Valid @ModelAttribute("crmUser") CrmRole theRole, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		List<Role> theSedi = roleDao.getRoles();
		
		theModel.addAttribute("roles", theSedi);
	
		// create user account
		User theUser = userService.findByUserName(theUsername);
		
		userService.changeRuolo(theRole, theUser);
		
		
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<User> theUsers = userService.getUsers();
				
		// devo aggiungerli al model
		theModel.addAttribute("users", theUsers);
		
		theModel.addAttribute("registrationSucces", "Ruolo aggiornato con successo.");
		
		return "list-users";	
	}
	
	
	
	
}
