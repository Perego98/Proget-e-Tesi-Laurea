package com.tesi.gestione.validation;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.crypto.Data;

public class DataValidator implements ConstraintValidator<ValidData, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String DATA_PATTERN = "^[0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean isValid(final String data, final ConstraintValidatorContext context) {
		
		if (data == null) {
			return false;
		}


		
		if(data.contains("/") || data.contains("-")) {
			
			
			// controlo anche nel caso l'inserimento fosse non solo gg/mm/aaaa ma anche g/mm/aaaa o g/m/aaaa o gg/m/aaaa
			if(data.length() == 10 || data.length() == 9 || data.length() == 8) {
 
				String giorno = null, mese = null, anno = null;
				if(data.contains("/")) {
					giorno = data.substring(0, data.indexOf("/"));
					mese = data.substring(data.indexOf("/")+1, data.lastIndexOf("/"));
					anno = data.substring(data.lastIndexOf("/")+1, data.length());		
				} else if(data.contains("-")) {
					anno = data.substring(0, data.indexOf("-"));
					mese = data.substring(data.indexOf("-")+1, data.lastIndexOf("-"));
					giorno = data.substring(data.lastIndexOf("-")+1, data.length());		
				}
				
				// se uno di questi è a null ritorno false
				if(anno == null || mese == null || giorno == null)
					return false;
				
				// controllo a parte la lunghezza dell'anno, perchè può essere superata dal controllo precedente
				if(anno.length() != 4)
					return false;
				
				// provo a convertirli in numeri
				if(isNumeric(giorno) && isNumeric(mese) && isNumeric(anno)) {
					// controllo che le date esistano
					 int d = Integer.parseInt(giorno);
					 int m = Integer.parseInt(mese);
					 // TODO: sistemare controllando anche l'anno bisestile
					 // se il mese esiste
					 if(m<=12) {
						 switch(m) {
						 
						 case 1:
						 case 3:
						 case 5:
						 case 7:
						 case 8:
						 case 10:
						 case 12:{
							 if(d > 31)
								 return false;
							 break;
						 	}
						 case 2:
						 case 4:
						 case 6:
						 case 9:
						 case 11:{
							 if(d > 30)
								 return false;
							 break;
						 	}
						 }
					 }
					 else {return false;}
					
				}
				else {return false;}
				
			}
			else {return false;}
		}
		else {return false;}
		
		return true;
	}
	
	private static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}