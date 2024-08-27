package com.Auth_Server_Spring_Boot.businesslogic.database;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    User findByUsername(String username);

    void save(User user);
}
