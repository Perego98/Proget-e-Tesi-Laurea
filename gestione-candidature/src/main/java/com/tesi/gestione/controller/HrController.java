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
import com.tesi.gestione.crm.CrmStato;
import com.tesi.gestione.crm.CrmSupervisore;
import com.tesi.gestione.crm.CrmUser;
import com.tesi.gestione.dao.RoleDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.CandidatoService;
import com.tesi.gestione.service.UserService;

@Controller
@RequestMapping("/hr")
public class HrController {
	

	
    @Autowired
    private CandidatoService candidatoService;
    
    @Autowired
    private UserService userService;
    
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

		        
		// devo chiedere a UserService (UserDao) l'elenco degli user
		List<Candidato> theCandidati = candidatoService.getCandidati();

		// devo aggiungerli al model
		theModel.addAttribute("candidati", theCandidati);
		
		theModel.addAttribute("registrationSucces", "Supervisore " + userUsername + " assegnato con successo a " + theCandidato.getNome() + " CF: " + theCandidato.getCodiceFiscale());
		
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
	
	
	
	
	
	
	// FILE FILE FILE FILE
	
	// Mostra pagine per aggiungere documento
//	@RequestMapping(value = { "/showUploadCV" }, method = RequestMethod.GET)
//    public String addDocuments(@RequestParam("codFiscale") String codFiscale, ModelMap model) {
//		Candidato candidato = candidatoService.findByCodiceFiscale(codFiscale);
//        model.addAttribute("candidato", candidato);
// 
//        FileBucket fileModel = new FileBucket();
//        model.addAttribute("fileBucket", fileModel);
// 
//         
//        return "upload-cv";
//    }
     
	
	// Scarica documento
//    @RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
//    public String downloadDocument(@PathVariable int userId, @PathVariable int docId, HttpServletResponse response) throws IOException {
//        UserDocument document = userDocumentService.findById(docId);
//        response.setContentType(document.getType());
//        response.setContentLength(document.getContent().length);
//        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
//  
//        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
//  
//        return "redirect:/add-document-"+userId;
//    }
 
	// Cancella documento
//    @RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
//    public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
//        userDocumentService.deleteById(docId);
//        return "redirect:/add-document-"+userId;
//    }
 
	// carica documento 
//    @RequestMapping(value = { "/uploadCV" }, method = RequestMethod.POST)
//    public String uploadDocument(FileBucket fileBucket, BindingResult result, ModelMap model, @RequestParam("codFiscale") String codFiscale) 
//    				throws IOException{
//         
//        
//             
//            System.out.println("Fetching file");
//             
//            Candidato candidato = candidatoService.findByCodiceFiscale(codFiscale);
// 
//            candidatoService.uploadCV(fileBucket, candidato);
//           
//            
//         // devo chiedere a UserService (UserDao) l'elenco degli user
//    		List<Candidato> theCandidati = candidatoService.getCandidati();
//
//    		// devo aggiungerli al model
//    		model.addAttribute("candidati", theCandidati);
//    		
//    		model.addAttribute("registrationSucces", "Curriculum Uploaded");
//    		
//            return "list-candidati";
//        }
    
     
//    private void saveDocument(FileBucket fileBucket, Candidato candidato) throws IOException{
//         
//    	
//         
//        MultipartFile multipartFile = fileBucket.getFile();
//        
//        
//	    byte[] bytes = multipartFile.getBytes();
//	    Blob blob;
//		try {
//			blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//		} catch (SerialException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        candidato.setCurriculum(blob);
//        
//        
//    }
	
    // MOVED 
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
	        
//	        try {
//
//	        	 byte[] bytes = file.getBytes();
//	        	 Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//	        
//	        	 
//	        	 System.out.println("FILE CREATO !!!!!!!");
//	        	 
//	        	 
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } catch (SerialException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}

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
	
	
	
}
