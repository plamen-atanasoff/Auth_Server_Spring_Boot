package com.Auth_Server_Spring_Boot.businesslogic.service;

import com.Auth_Server_Spring_Boot.businesslogic.UserInfo;
import com.Auth_Server_Spring_Boot.businesslogic.database.User;

public interface UserService {
    User registerUser(UserInfo userInfo);

    User getUser(String username);
}
