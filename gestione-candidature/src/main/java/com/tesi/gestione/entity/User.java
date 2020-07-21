package com.tesi.gestione.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Column(name = "numeroTel")
	private String telephone;
	
	@Column(name = "qualified")
	private boolean qualified;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_username"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sedeAssegnamento")
	private Sede sedeAssegnamento;

	@OneToMany(mappedBy="utenteRelatore", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
					, fetch = FetchType.LAZY)
	private List<Schedavalutazione> schedeValutazione;
	
	public User() {
	}

	public User(String userName, String password, String firstName, String lastName, String email, String telephone, boolean qualified) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.qualified = qualified;
	}

	public User(String userName, String password, String firstName, String lastName, String email, String telephone, boolean qualified, 
			Collection<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.qualified = qualified;
		this.roles = roles;
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

	public Collection<Role> getRoles() {
		
		return roles;
	}
	

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	
	public Sede getSedeAssegnamento() {
		return sedeAssegnamento;
	}

	public void setSedeAssegnamento(Sede sedeAssegnamento) {
		this.sedeAssegnamento = sedeAssegnamento;
	}
	
	

	public boolean isQualified() {
		return qualified;
	}

	public void setQualified(boolean qualified) {
		this.qualified = qualified;
	}

	public List<Schedavalutazione> getSchedeValutazione() {
		return schedeValutazione;
	}

	public void setSchedeValutazione(List<Schedavalutazione> schedeValutazione) {
		this.schedeValutazione = schedeValutazione;
	}

//	@Override
//	public String toString() {
//		return "User [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
//				+ lastName + ", email=" + email + ", telephone=" + telephone + ", qualified=" + qualified + ", roles="
//				+ roles + ", sedeAssegnamento=" + sedeAssegnamento + ", schedeValutazione=" + schedeValutazione + "]";
//	}

	
	





	
 
}
