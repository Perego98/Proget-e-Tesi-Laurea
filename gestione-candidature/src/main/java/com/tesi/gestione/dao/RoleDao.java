package com.tesi.gestione.dao;

import com.tesi.gestione.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
