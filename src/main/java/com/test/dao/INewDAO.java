package com.test.dao;

import com.test.model.NewModel;

import java.util.List;

public interface INewDAO {
    List<NewModel> findByCategoryId(long categoryId);

    Long save(NewModel newModel);

    NewModel findOne(Long id);

    void updateModel(NewModel newModel);

    void deleteModel(Long id);

    List<NewModel> findAll(Integer offset, Integer limit);

    int getTotalItems();
}
