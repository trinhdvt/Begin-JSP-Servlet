package com.test.service;

import com.test.model.UserModel;

public interface IUserService {
    UserModel findUser(String username, String password, int status);
}
