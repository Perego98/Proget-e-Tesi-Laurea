package com.tesi.gestione.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tesi.gestione.dao.RoleDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.model.FileBucket;
import com.tesi.gestione.service.CandidatoService;
import com.tesi.gestione.service.UserService;
import com.tesi.gestione.user.CrmCandidato;
import com.tesi.gestione.user.CrmCandidatoUpdate;
import com.tesi.gestione.user.CrmStato;
import com.tesi.gestione.user.CrmSupervisore;
import com.tesi.gestione.user.CrmUser;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {
	

	
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
		
	
	
	// FILE FILE FILE FILE
	
	// Mostra pagine per aggiungere documento
	@RequestMapping(value = { "/showUploadCV" }, method = RequestMethod.GET)
    public String addDocuments(@RequestParam("codFiscale") String codFiscale, ModelMap model) {
		
		Candidato candidato = candidatoService.findByCodiceFiscale(codFiscale);
        model.addAttribute("candidato", candidato);
 
//        FileBucket fileModel = new FileBucket();
//        model.addAttribute("fileBucket", fileModel);
        
//        model.addAttribute("multipartFile", new MultipartFile);
 
         
        return "upload-cv";
    }
     
	
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
 
	// carica documento // ModelMap model,
//    @RequestMapping(value = { "/uploadCV" }, method = RequestMethod.GET)
//	@PostMapping("/uploadCV")
//    public String uploadDocument(@RequestParam("uploadFile") MultipartFile file) 
//    				throws IOException{
//         
//        
//             
//            System.out.println("Fetching file SONO DENTRO");
//             
////            Candidato candidato = candidatoService.findByCodiceFiscale(codFiscale);
// 
////            candidatoService.uploadCV(fileBucket, candidato);
//           
//            
//         // devo chiedere a UserService (UserDao) l'elenco degli user
////    		List<Candidato> theCandidati = candidatoService.getCandidati();
//
//    		// devo aggiungerli al model
////    		model.addAttribute("candidati", theCandidati);
//    		
////    		model.addAttribute("registrationSucces", "Curriculum Uploaded");
//    		
//    		return "redirect:/hr/showListCandidati";
//        }
	 @PostMapping("/uploadCV") //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
        	// Errore reindirizzamento pagina
           System.out.println("FILE VUOTO");
        }

        try {

        	 byte[] bytes = file.getBytes();
        	 Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        
        	 System.out.println("FILE CREATO !!!!!!!");
        	 
        	 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return "redirect:/hr/showListCandidati";
    }
     
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
	
	
	
	
}
