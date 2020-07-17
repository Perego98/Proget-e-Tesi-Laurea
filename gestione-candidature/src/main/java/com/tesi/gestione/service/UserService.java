package com.tesi.gestione.service;

import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.user.CrmRole;
import com.tesi.gestione.user.CrmSede;
import com.tesi.gestione.user.CrmUser;
import com.tesi.gestione.user.CrmUserUpdate;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    
    List<User> getUsers();
    
    List<User> getManager();
    
    void deleteUser(String username);

    void update(String userUsername, CrmUserUpdate crmUser);
    
    void activateUser(String username);
    
    void deactivateUser(String username);
    
    void save(CrmUser crmUser);
    
    void changeSede(CrmSede crmSede, User theUser);
    
    void changeRuolo(CrmRole newRole,  User theUser);
    
    List<User> search(String theSearchName);
}
