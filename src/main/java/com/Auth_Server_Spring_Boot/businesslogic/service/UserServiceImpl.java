package com.Auth_Server_Spring_Boot.businesslogic.service;

import com.Auth_Server_Spring_Boot.businesslogic.UserInfo;
import com.Auth_Server_Spring_Boot.businesslogic.database.User;
import com.Auth_Server_Spring_Boot.businesslogic.database.UserDAO;
import com.Auth_Server_Spring_Boot.businesslogic.password.PasswordHasher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User registerUser(UserInfo userInfo) {
        byte[] salt = PasswordHasher.generateSalt();
        String passwordHash;
        try {
            passwordHash = PasswordHasher.generateHash(userInfo.password(), salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Password hash couldn't be created", e);
        }

        User user = new User(userInfo.username(), passwordHash, salt, userInfo.firstName(), userInfo.lastName(),
            userInfo.email());

        return userDAO.save(user);
    }

    @Override
    public User getUser(String username) {
        return userDAO.findByUsername(username);
    }
}
