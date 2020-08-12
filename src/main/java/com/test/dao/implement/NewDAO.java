package com.test.dao.implement;

import com.test.dao.INewDAO;
import com.test.mapper.NewMapper;
import com.test.model.NewModel;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

    @Override
    public List<NewModel> findByCategoryId(long categoryId) {
        String query = "select * from news where category_id=?";
        return query(query, new NewMapper(), categoryId);
    }

    @Override
    public Long saveNewModel(NewModel newModel) {
        String sql = "insert into news(title, content, category_id) values (?,?,?)";
        return insert(sql, newModel.getTitle(), newModel.getContent(), newModel.getCategoryId());
    }
}
