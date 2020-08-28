package com.test.dao;

import com.test.model.UserModel;

public interface IUserDAO {
    UserModel findUser(String userName, String password, int status);
}
