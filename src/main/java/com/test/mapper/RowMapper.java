package com.test.mapper;

import com.test.model.AbstractModel;

import java.sql.ResultSet;

public interface RowMapper<T extends AbstractModel> {
    T mapRow(ResultSet rs);
}
