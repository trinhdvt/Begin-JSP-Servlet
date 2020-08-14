package com.test.service.implement;

import com.test.dao.INewDAO;
import com.test.model.NewModel;
import com.test.service.INewService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.time.LocalDate;
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
    public Long saveModel(NewModel newModel) {
        return newDAO.save(newModel);
    }

    @Override
    public NewModel updateModel(NewModel updateObj) {
        NewModel oldObj = newDAO.findOne(updateObj.getId());
        updateObj.setModifiedDate(LocalDate.now().toString());
        updateObj.setModifiedBy("");
        updateObj.setCreatedDate(oldObj.getCreatedDate());
        updateObj.setCreatedBy(oldObj.getCreatedBy());
        newDAO.updateModel(updateObj);
        return updateObj;
//        return newDAO.findOne(updateObj.getId());
    }

    @Override
    public void deleteModel(Long... ids) {
        for (var id : ids) {
            newDAO.deleteModel(id);
        }
    }

    @Override
    public List<NewModel> findAll() {
        return newDAO.findAll();
    }
}
