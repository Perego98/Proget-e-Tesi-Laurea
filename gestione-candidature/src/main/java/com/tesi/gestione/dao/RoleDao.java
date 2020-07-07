package com.tesi.gestione.dao;

import java.util.List;

import com.tesi.gestione.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
	
	public List<Role> getRoles();
	
	
	
}
