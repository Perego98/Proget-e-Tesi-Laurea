package com.tesi.gestione.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save user
		currentSession.saveOrUpdate(theUser);
	}

	
	@Override
	@Transactional
	public List<User> getUsers() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<User> theQuery = 
				currentSession.createQuery("from User order by username", User.class);
		
//		Query<User> theQuery = 
//				currentSession.createQuery("from User join Users_roles on User.username=Users_roles.user_username join Role on Users_roles.role_id=Role.id order by  Role.id, username ", User.class);



		
		
		// execute query and get result list
		List<User> users = theQuery.getResultList();

		// return the result
		return users;
	}

	@Override
	public void deleteUser(String username) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the obj with PK
		Query theQuery = currentSession.createQuery("delete from User where username=:theUsername");
		
		theQuery.setParameter("theUsername", username);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public void changeState(boolean newState, String username) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the obj with PK
		Query theQuery = currentSession.createQuery("update User set qualified=:newState where username=:theUsername");
		
		theQuery.setParameter("newState", newState);
		theQuery.setParameter("theUsername", username);
		
		theQuery.executeUpdate();
		
	}

//	@Override
//	@Transactional
//	public List<User> getManager() {
//		// get the current hibernate session
//		Session currentSession = sessionFactory.getCurrentSession();
//		
//		// create a query
//		Query<User> theQuery = 
//				currentSession.createQuery("from User join Users_roles join Role where name = 'ROLE_MANAGER' group by username", User.class);
//		
//
//		// execute query and get result list
//		List<User> users = theQuery.getResultList();
//
//		// return the result
//		return users;
//	}
	
	@Override
	@Transactional
	public List<User> getManager() {
		
		List<User> users = getUsers();
		

		// execute query and get result list
		List<User> managers = new ArrayList<>();
		
		for(User tempUser : users) {
			for(Role tempRole : tempUser.getRoles()) {
				if(tempRole.getName().contains("MANAGER")) {
					managers.add(tempUser);
				}
			}			
		}

		// return the result
		return managers;
	}

	 @Override
	 public List<User> search(String theSearchName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        

        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from User where lower(first_name) " + 
            			"like :theName or lower(last_name) like :theName "
            			+ "or lower(userName) like :theName", User.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all users
            theQuery =currentSession.createQuery("from User", User.class);            
        }
        
        // execute query and get result list
        List<User> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
    	}

	@Override
	@Transactional
	public int totUser() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using CF
		Query<Long> theQuery = currentSession.createQuery("SELECT COUNT(*) FROM User", Long.class);
		
		long numCandidati = 0l;
		
		try {
			numCandidati = theQuery.getSingleResult();
		} catch (Exception e) {
			numCandidati = 0l;
		}
		
		return (int) numCandidati;
		
	}

	@Override
	@Transactional
	public List<User> getUsers(int firstResult, int maxResult) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<User> theQuery = 
				currentSession.createQuery("from User order by username", User.class);
		
		// set max e min result number
		theQuery.setFirstResult(firstResult);
		theQuery.setMaxResults(maxResult);
		
		// execute query and get result list
		List<User> users = theQuery.getResultList();

		// return the result
		return users;
	}

}
