package com.test.service;

import com.test.model.NewModel;
import com.test.paging.Pageable;

import java.util.List;

public interface INewService {
    List<NewModel> findByCategoryId(long categoryId);

    Long saveModel(NewModel newModel);

    NewModel updateModel(NewModel updateObj);

    void deleteModel(Long... ids);

    List<NewModel> findAll(Pageable pageable);

    int getTotalItems();
}
