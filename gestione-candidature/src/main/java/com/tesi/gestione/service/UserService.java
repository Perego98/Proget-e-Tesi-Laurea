package com.tesi.gestione.service;

import com.tesi.gestione.entity.User;
import com.tesi.gestione.user.CrmUser;
import com.tesi.gestione.user.CrmUserUpdate;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    
    List<User> getUsers();
    
    void deleteUser(String username);

    void update(String userUsername, CrmUserUpdate crmUser);
    
    void save(CrmUser crmUser);
}
