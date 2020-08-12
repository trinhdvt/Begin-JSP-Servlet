package com.test.dao;

import com.test.mapper.RowMapper;
import com.test.model.AbstractModel;

import java.util.List;

public interface GenericDAO<T extends AbstractModel> {
    List<T> query(String sql, RowMapper<T> rowMapper, Object... params);

    void update(String sql, Object... params);

    Long insert(String sql, Object... params);
}
