package com.test.dao;

import com.test.mapper.RowMapper;

import java.util.List;

public interface GenericDAO<T> {
    List<T> select(String sql, RowMapper<T> rowMapper, Object... params);

    void update(String sql, Object... params);

    Long insert(String sql, Object... params);

    int count(String sql, Object... params);
}
