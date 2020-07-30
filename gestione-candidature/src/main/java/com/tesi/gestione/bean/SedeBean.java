package com.tesi.gestione.bean;

import java.util.ArrayList;
import java.util.List;

import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

public class SedeBean {

	private Long id;

	private String nameCity;

	private String via;

	private String cap;

	private String civicNumber;

	private List<User> user;
	
//	private List<UserBean> user;

	public SedeBean() {
	}



//	public SedeBean(Sede theSede) {
//		this.id = theSede.getId();
//		this.nameCity = theSede.getNameCity();
//		this.via = theSede.getVia();
//		this.cap = theSede.getCap();
//		this.civicNumber = theSede.getCivicNumber();
//		this.user = processUser(theSede.getUser());
//	}
	
	public SedeBean(Sede theSede) {
		this.id = theSede.getId();
		this.nameCity = theSede.getNameCity();
		this.via = theSede.getVia();
		this.cap = theSede.getCap();
		this.civicNumber = theSede.getCivicNumber();
		this.user = theSede.getUser();
	}



	private List<UserBean> processUser(List<User> paramUser) {
		
		List<UserBean> tempArray = new ArrayList<>();
		
		for(User temp : paramUser) {
			tempArray.add(new UserBean(temp));
		}
		
		return tempArray;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCivicNumber() {
		return civicNumber;
	}

	public void setCivicNumber(String civicNumber) {
		this.civicNumber = civicNumber;
	}

	
	
//	public List<UserBean> getUser() {
//		return user;
//	}
//
//	public void setUser(List<UserBean> user) {
//		this.user = user;
//	}

	public List<User> getUser() {
		return user;
	}



	public void setUser(List<User> user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "città: " + nameCity + ", via: " + via + ",\n numero civico: " + civicNumber + ", cap: " + cap;
	}

}
