package com.tesi.gestione.service;

import com.tesi.gestione.crm.CrmRole;
import com.tesi.gestione.crm.CrmSede;
import com.tesi.gestione.crm.CrmUser;
import com.tesi.gestione.crm.CrmUserUpdate;
import com.tesi.gestione.dao.CandidatoDao;
import com.tesi.gestione.dao.RoleDao;
import com.tesi.gestione.dao.SedeDao;
import com.tesi.gestione.dao.UserDao;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private CandidatoDao candidatoDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private SedeDao sedeDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		// assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		user.setTelephone(crmUser.getTelephone());

		// set default qualidfied
		user.setQualified(true);

		// set the role
		user.setRoles(Arrays.asList(roleDao.findRoleByName(crmUser.getIdRole())));

		// set the Sede
		int sedeId = crmUser.getSedeid();
		Sede tmpSede = sedeDao.findSedeByCityID(sedeId);

		if (tmpSede != null)
			user.setSedeAssegnamento(tmpSede);

		// save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<User> getUsers() {

		return userDao.getUsers();
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		// devo cercare tutti i candidati a lui assegnati
		// poi devo settare per tutti loro lo stato a new
		candidatoDao.triggerActionOnDeleteUser(username);

		// poi cancello l'username
		userDao.deleteUser(username);

	}

	@Override
	@Transactional
	public void update(String userUsername, CrmUserUpdate crmUser) {
		User user = new User();
		user = userDao.findByUserName(userUsername);
		user.setUserName(userUsername);
		user.setEmail(crmUser.getEmail());
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setTelephone(crmUser.getTelephone());

		userDao.save(user);

	}

	@Override
	@Transactional
	public void activateUser(String username) {
		userDao.changeState(true, username);
	}

	@Override
	@Transactional
	public void deactivateUser(String username) {
		userDao.changeState(false, username);
	}

	@Override
	@Transactional
	public void changeSede(CrmSede crmSede, User theUser) {
		theUser.setSedeAssegnamento(sedeDao.findSedeByCityID(crmSede.getSedeid()));
		userDao.save(theUser);
	}

	@Override
	@Transactional
	public void changeRuolo(CrmRole newRole, User theUser) {
		// recupero il ruolo
		if (newRole.getIdRole() != null) {
			Collection<Role> temp = new ArrayList<>();
			temp.add(roleDao.findRoleByName(newRole.getIdRole()));

			theUser.setRoles(temp);
		}

		userDao.save(theUser);
	}

	@Override
	public List<User> getManager() {
		return userDao.getManager();
	}

	@Override
	@Transactional
	public List<User> search(String theSearchName) {
		return userDao.search(theSearchName);
	}

	@Override
	public int totUser() {
		return userDao.totUser();
	}

	@Override
	@Transactional
	public List<User> getUsers(int firstResult, int maxResult) {

		return userDao.getUsers(firstResult, maxResult);
	}

	@Override
	@Transactional
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	@Override
	@Transactional
	public List<Sede> getSedi() {
		return sedeDao.getSedi();
	}

}
