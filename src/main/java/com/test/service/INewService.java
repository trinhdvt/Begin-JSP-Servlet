package com.test.service;

import com.test.model.NewModel;

import java.util.List;

public interface INewService {
    List<NewModel> findByCategoryId(long categoryId);

    Long saveNewModel(NewModel newModel);
}
