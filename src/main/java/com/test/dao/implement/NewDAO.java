package com.test.dao.implement;

import com.test.dao.INewDAO;
import com.test.mapper.NewMapper;
import com.test.model.NewModel;
import com.test.paging.Pageable;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

    @Override
    public List<NewModel> findByCategoryId(long categoryId) {
        String query = "select * from news where category_id=?";
        return select(query, new NewMapper(), categoryId);
    }

    @Override
    public Long save(NewModel newModel) {
        String sql = "insert into news(title, content, category_id) values (?,?,?)";
        return insert(sql, newModel.getTitle(), newModel.getContent(), newModel.getCategoryId());
    }

    @Override
    public NewModel findOne(Long id) {
        String sql = "select * from news where id=?";
        var res = select(sql, new NewMapper(), id);
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
    public List<NewModel> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("select * from news");

        var sorter = pageable.getSorter();
        var sortBy = sorter.getSortBy();
        var sortOrder = sorter.getSortOrder();
        if (sortBy != null && sortOrder != null) {
            sql.append(" order by ").append(sortBy).append(" ").append(sortOrder);
        }

        var offset = pageable.getOffset();
        var limit = pageable.getLimit();
        if (offset != null && limit != null) {
            sql.append(" limit ").append(" ?,?");
            return select(sql.toString(), new NewMapper(), offset, limit);
        }

        return select(sql.toString(), new NewMapper());
    }

    @Override
    public int getTotalItems() {
        String sql = "select count(*) from news";
        return count(sql);
    }
}
