package com.tesi.gestione.dao;

import com.tesi.gestione.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
