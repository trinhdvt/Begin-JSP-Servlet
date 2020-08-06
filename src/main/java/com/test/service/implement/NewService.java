package com.test.service.implement;

import com.test.dao.INewDAO;
import com.test.model.NewModel;
import com.test.service.INewService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
public class NewService implements INewService {
    @Inject
    private INewDAO newDAO;

    @Override
    public List<NewModel> findByCategoryId(long categoryId) {
        return newDAO.findByCategoryId(categoryId);
    }

    @Override
    public Long saveNewModel(NewModel newModel) {
        return newDAO.saveNewModel(newModel);
    }
}
