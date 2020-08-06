package com.test.dao;

import com.test.model.NewModel;

import java.util.List;

public interface INewDAO {
    List<NewModel> findByCategoryId(long categoryId);

    Long saveNewModel(NewModel newModel);
}
