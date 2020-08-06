package com.test.dao.implement;

import com.test.dao.INewDAO;
import com.test.mapper.NewMapper;
import com.test.model.NewModel;

import javax.annotation.ManagedBean;
import java.sql.*;
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
        Long id = null;
        Connection cnn = getConnection();
        if (cnn != null) {
            try {
                cnn.setAutoCommit(false);

                String sql = "insert into news(title, content, category_id) values (?,?,?)";
                try (PreparedStatement stm = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stm.setString(1, newModel.getTitle());
                    stm.setString(2, newModel.getContent());
                    stm.setLong(3, newModel.getCategoryId());

                    stm.executeUpdate();
                    cnn.commit();
                    try (ResultSet rs = stm.getGeneratedKeys()) {
                        id = rs.getLong(1);
                    }
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                try {
                    cnn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } finally {
                try {
                    cnn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }
}
