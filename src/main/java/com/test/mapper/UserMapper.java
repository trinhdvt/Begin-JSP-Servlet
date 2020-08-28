package com.test.mapper;

import com.test.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs) {
        UserModel user = new UserModel();
        try {
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("full_name"));
            user.setStatus(rs.getInt("status"));
            user.setRoleId(rs.getLong("role_id"));

            user.getRoleModel().setCode(rs.getString("code"));
            user.getRoleModel().setName(rs.getString("name"));
            user.getRoleModel().setId(rs.getLong("id"));

            return user;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
