package com.openclassroom.payMyBuddy.service;

import com.openclassroom.payMyBuddy.model.User;

public interface IUserService {

    User saveUser(User user);

    User findUserByEmail(String email);

}
