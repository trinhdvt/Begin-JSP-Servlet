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
    public Long save(NewModel newModel) {
        String sql = "insert into news(title, content, category_id) values (?,?,?)";
        return insert(sql, newModel.getTitle(), newModel.getContent(), newModel.getCategoryId());
    }

    @Override
    public NewModel findOne(Long id) {
        String sql = "select * from news where id=?";
        var res = query(sql, new NewMapper(), id);
        if (res == null || res.isEmpty())
            return null;
        else return res.get(0);
    }

    @Override
    public void updateModel(NewModel newModel) {
        String sql = "update from news" +
                " set category_id=?,title=?,thumbnail=?," +
                " short_description=?, content=?, created_date=?," +
                " modified_date=?, created_by=?, modified_by=? " +
                " where id=?";
        update(sql, newModel.getCategoryId(), newModel.getTitle(),
                newModel.getThumbnail(), newModel.getShortDescription(),
                newModel.getContent(), newModel.getCreatedDate(),
                newModel.getModifiedDate(), newModel.getCreatedBy(),
                newModel.getModifiedBy(), newModel.getId());

    }

    @Override
    public void deleteModel(Long id) {
        String sql = "delete from news where id=?";
        update(sql, id);
    }

    @Override
    public List<NewModel> findAll() {
        String sql = "select * from news";
        return query(sql, new NewMapper());
    }
}
