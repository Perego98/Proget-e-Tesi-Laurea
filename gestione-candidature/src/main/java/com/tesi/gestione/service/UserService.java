package com.tesi.gestione.service;

import com.tesi.gestione.entity.User;
import com.tesi.gestione.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
