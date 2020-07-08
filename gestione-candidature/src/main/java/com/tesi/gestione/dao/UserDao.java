package com.tesi.gestione.dao;

import java.util.List;

import com.tesi.gestione.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    List<User> getUsers();
    
    void save(User user);
    
}
