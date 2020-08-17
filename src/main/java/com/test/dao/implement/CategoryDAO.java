package com.test.dao.implement;

import com.test.dao.ICategoryDAO;
import com.test.mapper.CategoryMapper;
import com.test.model.CategoryModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

    @Override
    public List<CategoryModel> findAll() {
        String sql = "select * from category";
        return select(sql, new CategoryMapper());
    }
}
