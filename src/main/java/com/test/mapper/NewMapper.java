package com.test.mapper;

import com.test.model.NewModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMapper implements RowMapper<NewModel> {
    @Override
    public NewModel mapRow(ResultSet rs) {
        try {
            NewModel newModel = new NewModel();
            newModel.setTitle(rs.getString("title"));
            // place-holder

            return newModel;
        } catch (SQLException ignored) {
            return null;
        }

    }
}
