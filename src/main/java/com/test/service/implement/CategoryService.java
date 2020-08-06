package com.test.service.implement;

import com.test.dao.ICategoryDAO;
import com.test.model.CategoryModel;
import com.test.service.ICategoryService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
public class CategoryService implements ICategoryService {

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }
}
