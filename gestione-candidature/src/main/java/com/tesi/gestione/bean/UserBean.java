package com.tesi.gestione.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

public class UserBean {

	private String userName;

	private String password;

	private String firstName;

	private String lastName;

	private String email;

	private String telephone;

	private boolean qualified;
	
	private String ruoli;

	private Collection<Role> roles;

	private Sede sedeAssegnamento;

	private List<Schedavalutazione> schedeValutazione;
	
//	private Collection<RoleBean> roles;
//
	private SedeBean sedeAssegnamentoBean;
//
//	private List<SchedavalutazioneBean> schedeValutazione;

	public UserBean() {
	}

//	public UserBean(User theUser) {
//		this.userName = theUser.getUserName();
//		this.password = theUser.getPassword();
//		this.firstName = theUser.getFirstName();
//		this.lastName = theUser.getLastName();
//		this.email = theUser.getEmail();
//		this.telephone = theUser.getTelephone();
//		this.qualified = theUser.isQualified();
//		this.roles = processRole(theUser.getRoles());
//		this.sedeAssegnamento = processSede(theUser.getSedeAssegnamento());
//		this.schedeValutazione = processScheda(theUser.getSchedeValutazione());
//	}
	
	public UserBean(User theUser) {
		this.userName = theUser.getUserName();
		this.password = theUser.getPassword();
		this.firstName = theUser.getFirstName();
		this.lastName = theUser.getLastName();
		this.email = theUser.getEmail();
		this.telephone = theUser.getTelephone();
		this.qualified = theUser.isQualified();
		this.roles = theUser.getRoles();
		this.sedeAssegnamento = theUser.getSedeAssegnamento();
		this.sedeAssegnamentoBean = new SedeBean(theUser.getSedeAssegnamento());
		this.schedeValutazione = theUser.getSchedeValutazione();
		this.ruoli = processRole(theUser.getRoles());
	}
	
	private String processRole(Collection<Role> paramRole) {
		
		String ruoli = "";
		for (Role temp : paramRole) {
			ruoli += temp.toString();
			
			if(paramRole.size()>1) {
				ruoli += " - ";
			}
		}

		return ruoli;
	}

	public String getRuoli() {
		return ruoli;
	}

	public void setRuoli(String ruoli) {
		this.ruoli = ruoli;
	}
	
	private List<SchedavalutazioneBean> processScheda(List<Schedavalutazione> paramSchede) {
		List<SchedavalutazioneBean> tempArray = new ArrayList<>();

		for (Schedavalutazione temp : paramSchede) {
			tempArray.add(new SchedavalutazioneBean(temp));
		}

		return tempArray;
	}

	private SedeBean processSede(Sede paramSede) {
		return new SedeBean(paramSede);
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isQualified() {
		return qualified;
	}

	public void setQualified(boolean qualified) {
		this.qualified = qualified;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Sede getSedeAssegnamento() {
		return sedeAssegnamento;
	}

	public void setSedeAssegnamento(Sede sedeAssegnamento) {
		this.sedeAssegnamento = sedeAssegnamento;
	}

	public List<Schedavalutazione> getSchedeValutazione() {
		return schedeValutazione;
	}

	public void setSchedeValutazione(List<Schedavalutazione> schedeValutazione) {
		this.schedeValutazione = schedeValutazione;
	}

	public SedeBean getSedeAssegnamentoBean() {
		return sedeAssegnamentoBean;
	}

	public void setSedeAssegnamentoBean(SedeBean sedeAssegnamentoBean) {
		this.sedeAssegnamentoBean = sedeAssegnamentoBean;
	}

	
	
//	public Collection<RoleBean> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<RoleBean> roles) {
//		this.roles = roles;
//	}
//
//	public SedeBean getSedeAssegnamento() {
//		return sedeAssegnamento;
//	}
//
//	public void setSedeAssegnamento(SedeBean sedeAssegnamento) {
//		this.sedeAssegnamento = sedeAssegnamento;
//	}
//
//	public List<SchedavalutazioneBean> getSchedeValutazione() {
//		return schedeValutazione;
//	}
//
//	public void setSchedeValutazione(List<SchedavalutazioneBean> schedeValutazione) {
//		this.schedeValutazione = schedeValutazione;
//	}

}
