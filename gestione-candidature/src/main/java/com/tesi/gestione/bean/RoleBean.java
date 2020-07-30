package com.tesi.gestione.bean;

import com.tesi.gestione.entity.Role;

public class RoleBean {

	
	private Long id;

	private String name;
	
	private String nameFormatted;

	public RoleBean() {
	}

	
	
	public RoleBean(Role theRole) {
		this.id = theRole.getId();
		this.name = theRole.getName();
		this.nameFormatted = formatName(theRole.getName());
	}

	private String formatName(String theRole) {
		String temp=theRole;
		
		temp = temp.replace("ROLE_", "");
		
		return temp;
	}

	public RoleBean(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getNameFormatted() {
		return nameFormatted;
	}


	public void setNameFormatted(String nameFormatted) {
		this.nameFormatted = nameFormatted;
	}



	@Override
	public String toString() {

		String temp = name;
		temp = temp.replace("ROLE_", "");

		return temp;
	}
}
