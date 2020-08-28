package com.test.service.implement;

import com.test.dao.implement.UserDAO;
import com.test.model.UserModel;
import com.test.service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {

    @Inject
    UserDAO userDAO;

    @Override
    public UserModel findUser(String username, String password, int status) {
        return userDAO.findUser(username, password, status);
    }
}
