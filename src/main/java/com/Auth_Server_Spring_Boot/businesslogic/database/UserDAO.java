package com.Auth_Server_Spring_Boot.businesslogic.database;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    User findByUsername(String username);

    User findById(int id);

    void save(User user);

    void update(User user);

    void delete(int userId);
}
