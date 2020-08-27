package com.tesi.gestione.controller;

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

import com.tesi.gestione.bean.RoleBean;
import com.tesi.gestione.bean.SedeBean;
import com.tesi.gestione.bean.UserBean;
import com.tesi.gestione.crm.CrmRole;
import com.tesi.gestione.crm.CrmSede;
import com.tesi.gestione.crm.CrmUser;
import com.tesi.gestione.crm.CrmUserUpdate;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static int FixUserPerPagina = 10;

	@Autowired
	private UserService userService;

	
	private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showListUsers")
	public String showMyListUsers(Model theModel) {


		return "redirect:/admin/showListUsersPagination";
	}

	@GetMapping("/showListUsersPagination")
	public String showMyListUsersPagination(Model theModel,
			@RequestParam(value = "registrationSucces", required = false) String registrationSucces) {
		// numero di utenti per pagina
		int userPerPagina = FixUserPerPagina;

		// numero di utenti totali
		int maxSize = userService.totUser();

		// pagine necessarie
		int numPagine = maxSize / userPerPagina;

		// se ho un resto aggiungo una pagina
		if (maxSize % userPerPagina != 0) {
			numPagine++;
		}

		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<User> theUsers = null;
		if (numPagine > 1)
			theUsers = userService.getUsers(0, userPerPagina);
		else
			theUsers = userService.getUsers(0, maxSize);

		
		List<UserBean> theUsersBean = new ArrayList<>();
		
		for(User temp : theUsers){
			theUsersBean.add(new UserBean(temp));
		}
		
		List<Integer> numeroPagine = new ArrayList<>();

		for (int i = 0; i < numPagine; i++) {
			numeroPagine.add(i + 1);
		}

		// aggiungo le info di chi è loggato
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		theModel.addAttribute("adminAccount", currentPrincipalName);
//		theModel.addAttribute("users", theUsers);
		theModel.addAttribute("users", theUsersBean);
		theModel.addAttribute("numeroPagineList", numeroPagine);
		theModel.addAttribute("firstPage", true);
		theModel.addAttribute("pageNumber", 1);
		theModel.addAttribute("userPerPagina", userPerPagina);
		theModel.addAttribute("registrationSucces", registrationSucces);

		return "list-users";
	}

	@GetMapping("/showListUsersMinMax")
	public String showMyListUsersPaginationMinMax(Model theModel, @RequestParam("firstPage") String firstPage,
			@RequestParam("maxPage") String maxPage) {
		// numero di utenti per pagina
		int userPerPagina = FixUserPerPagina;

		// numero di utenti totali
		int maxSize = userService.totUser();

		// pagine necessarie
		int numPagine = maxSize / userPerPagina;

		// se ho un resto aggiungo una pagina
		if (maxSize % userPerPagina != 0) {
			numPagine++;
		}

		int pageNumber = (Integer.parseInt(firstPage) / userPerPagina) + 1;

		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<User> theUsers = userService.getUsers(Integer.parseInt(firstPage), Integer.parseInt(maxPage));

		List<UserBean> theUsersBean = new ArrayList<>();
		
		for(User temp : theUsers){
			theUsersBean.add(new UserBean(temp));
		}
		
		// aggiungo le info di chi è loggato
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		List<Integer> numeroPagine = new ArrayList<>();

		for (int i = 0; i < numPagine; i++) {
			numeroPagine.add(i + 1);
		}

//		theModel.addAttribute("users", theUsers);
		theModel.addAttribute("users", theUsersBean);
		theModel.addAttribute("adminAccount", currentPrincipalName);
		theModel.addAttribute("numeroPagineList", numeroPagine);
		theModel.addAttribute("firstPage", false);
		theModel.addAttribute("pageNumber", pageNumber);
		theModel.addAttribute("userPerPagina", userPerPagina);

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

		// add the role to the model
		theModel.addAttribute("roles", getRolesBean());

		// add the sedi to the model
		theModel.addAttribute("sedi", getSediBean());

		return "registration-user-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
			BindingResult theBindingResult, Model theModel) {

//		List<Role> theRoles = userService.getRoles();
//		List<Sede> theSedi = userService.getSedi();

		theModel.addAttribute("roles", getRolesBean());
		theModel.addAttribute("sedi", getSediBean());

		String userName = theCrmUser.getUserName();

		// form validation
		if (theBindingResult.hasErrors()) {
			return "registration-user-form";
		}

		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null) {
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "Username gia presente.");

			logger.warning("User name already exists.");
			return "registration-user-form";
		}
		// create user account
		userService.save(theCrmUser);

		return "redirect:/admin/showListUsersPagination?registrationSucces=Utente registrato con successo.";
	}

	// mostra la pagina per inserire i dati da aggiornare
	@GetMapping("/showFormForUpdateUser")
	public String showFormForUpdateUser(@RequestParam("userUsername") String theUsername, Model theModel) {

		System.out.println("********* AdminController --------- Dentro showFormForUpdateUser ->  Start");
		theModel.addAttribute("crmUser", new CrmUserUpdate());

//		User theUser = userService.findByUserName(theUsername);
//		List<Role> theRoles = userService.getRoles();
//		List<Sede> theSedi = userService.getSedi();

//		theModel.addAttribute("roles", getRolesBean());
//		theModel.addAttribute("sedi", getSediBean());
		theModel.addAttribute("user", findUserByUsernameBean(theUsername));

		return "update-user";
	}

	// processa i dati da aggiornare
	@PostMapping("/processUpdateUserForm")
	public String processUpdateUserForm(@RequestParam("userUsername") String theUsername,
			@Valid @ModelAttribute("crmUser") CrmUserUpdate theCrmUser, BindingResult theBindingResult,
			Model theModel) {

//		List<Role> theRoles = userService.getRoles();
//		List<Sede> theSedi = userService.getSedi();

//		theModel.addAttribute("roles", getRolesBean());
//		theModel.addAttribute("sedi", getSediBean());

		System.out.println("********* AdminController --------- Dentro processUpdateUserForm ->  Start");

		// form validation
		if (theBindingResult.hasErrors()) {
			System.out.println(
					"********* AdminController --------- Dentro processUpdateUserForm ->  theBindingResult.hasErrors()");

//			User theUser = userService.findByUserName(theUsername);

			theModel.addAttribute("user", findUserByUsernameBean(theUsername));

			theModel.addAttribute("registrationError", "Uno o più parametri non sono corretti!");

			return "update-user";
		}

		// create user account
		userService.update(theUsername, theCrmUser);

		return "redirect:/admin/showListUsersPagination?registrationSucces=Utente aggiornato con successo.";

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
	public String showFormForUpdateUserSede(@RequestParam("userUsername") String theUsername, Model theModel) {

//		List<Sede> theSedi = userService.getSedi();
//		
		theModel.addAttribute("sedi", getSediBean());

		// recupero la sede attuale
		SedeBean sedeAttuale = new SedeBean(userService.findByUserName(theUsername).getSedeAssegnamento());
		theModel.addAttribute("sedeAttuale", sedeAttuale);

		theModel.addAttribute("crmSede", new CrmSede());

		theModel.addAttribute("userUsername", findUserByUsernameBean(theUsername).getUserName());

//		User theUser = userService.findByUserName(theUsername);
//		
//		theModel.addAttribute("user", theUser);

		return "update-user-sede";
	}

	// processa la pagina per salvare la nuova sede
	@PostMapping("/processUpdateUserSedeForm")
	public String processUpdateUserSedeForm(@RequestParam("userUsername") String theUsername,
			@Valid @ModelAttribute("crmUser") CrmSede theSede, BindingResult theBindingResult, Model theModel) {

//		List<Sede> theSedi = userService.getSedi();

//		theModel.addAttribute("sedi", getSediBean());
		User theUser = userService.findByUserName(theUsername);
		
		// create user account
		userService.changeSede(theSede, theUser);

		return "redirect:/admin/showListUsersPagination?registrationSucces=Sede aggiornata con successo.";

	}

	@GetMapping("/showFormForUpdateUserRole")
	public String showFormForUpdateUserRole(@RequestParam("userUsername") String theUsername, Model theModel) {

//		List<Role> theRoles = userService.getRoles();
//		
		theModel.addAttribute("roles", getRolesBean());

		// recupero la sede attuale
		
		
		theModel.addAttribute("ruoloAttuale", userService.findByUserName(theUsername).getRoles());

		theModel.addAttribute("crmRole", new CrmRole());

		theModel.addAttribute("userUsername", findUserByUsernameBean(theUsername).getUserName());

//		User theUser = userService.findByUserName(theUsername);
//		
//		theModel.addAttribute("user", theUser);

		return "update-user-role";
	}

	@PostMapping("/processUpdateUserRoleForm")
	public String processUpdateUserRoleForm(@RequestParam("userUsername") String theUsername,
			@Valid @ModelAttribute("crmUser") CrmRole theRole, BindingResult theBindingResult, Model theModel) {

//		List<Role> theSedi = userService.getRoles();

//		theModel.addAttribute("roles", getRolesBean());

		// create user account

		userService.changeRuolo(theRole, userService.findByUserName(theUsername));

		return "redirect:/admin/showListUsersPagination?registrationSucces=Ruolo aggiornato con successo.";

	}

	
	// private method helper
	private UserBean findUserByUsernameBean(String username) {
		return new UserBean(userService.findByUserName(username));
	}
	
	private List<RoleBean> getRolesBean(){
		// get Role from dao
		List<Role> theRoles = userService.getRoles();

	
		
		List<RoleBean> theRolesBean = new ArrayList<>();
		
		
		for(Role temp : theRoles){
			theRolesBean.add(new RoleBean(temp));
		}
		
		return theRolesBean;
		
	}
	
	private List<SedeBean> getSediBean(){
		// get Sedi from dao
		List<Sede> theSedi = userService.getSedi();
		
		List<SedeBean> theSediBean = new ArrayList<>();
		
		for(Sede temp : theSedi){
			theSediBean.add(new SedeBean(temp));
		}
		
		return theSediBean;
	}

}
