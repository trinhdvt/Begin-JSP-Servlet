package com.test.dao.implement;

import com.test.dao.IUserDAO;
import com.test.mapper.UserMapper;
import com.test.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

    @Override
    public UserModel findUser(String userName, String password, int status) {
        String sqlQuery = "select user.*, name, code from user inner join role on user.role_id = role.id " +
                "where username=?, password=?, status=?";
        var users = select(sqlQuery, new UserMapper(), userName, password, status);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
