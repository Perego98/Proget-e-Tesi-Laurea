package com.tesi.gestione.dao;

import java.util.List;

import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Sede;
import com.tesi.gestione.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    List<User> getUsers();
    
    List<User> getUsers(int firstResult, int maxResult);
    
    List<User> getManager();
    
    void save(User user);
    
    void deleteUser(String username);
    
    void changeState(boolean newState, String username);
    
    List<User> search(String theSearchName);

    int totUser();
    
}
