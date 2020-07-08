package com.tesi.gestione.dao;

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

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);
	}

	
	@Override
	@Transactional
	public List<User> getUsers() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<User> theQuery = 
				currentSession.createQuery("from User", User.class);
		
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

}
