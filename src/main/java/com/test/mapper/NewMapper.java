package com.test.mapper;

import com.test.model.NewModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMapper implements RowMapper<NewModel> {
    @Override
    public NewModel mapRow(ResultSet rs) {
        try {
            NewModel newModel = new NewModel();
            newModel.setId(rs.getLong("id"));
            newModel.setTitle(rs.getString("title"));
            newModel.setCategoryId(rs.getLong("category_id"));
            newModel.setThumbnail(rs.getString("thumbnail"));
            newModel.setShortDescription(rs.getString("short_description"));
            newModel.setContent(rs.getString("content"));
            newModel.setCreatedDate(rs.getString("created_date"));
            newModel.setModifiedDate(rs.getString("modified_date"));
            newModel.setCreatedBy(rs.getString("created_by"));
            newModel.setModifiedBy(rs.getString("modified_by"));
            return newModel;
        } catch (SQLException ignored) {
            return null;
        }

    }
}
