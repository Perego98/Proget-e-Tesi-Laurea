package com.tesi.gestione.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tesi.gestione.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role findRoleByName(String theRoleName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
		
		Role theRole = null;
		
		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		
		return theRole;
	}

	@Override
	@Transactional
	public List<Role> getRoles() {
		System.out.println(" ********** RoleDaoImpl -> Entrato in getRoles().");
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Role> theQuery = 
				currentSession.createQuery("from Role", Role.class);
		
		// execute query and get result list
		List<Role> roles = theQuery.getResultList();
		
		System.out.println(" ********** RoelDaoImpl -> roles: " + roles.toString());
		
		// return the result
		return roles;
	}

	@Override
	public Role findRoleById(String theRoleId) {
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

				// now retrieve/read from database using name
				Query<Role> theQuery = currentSession.createQuery("from Role where id=:theRoleId", Role.class);
				theQuery.setParameter("theRoleId", theRoleId);
				
				Role theRole = null;
				
				try {
					theRole = theQuery.getSingleResult();
				} catch (Exception e) {
					theRole = null;
				}
				
				return theRole;
	}
}
