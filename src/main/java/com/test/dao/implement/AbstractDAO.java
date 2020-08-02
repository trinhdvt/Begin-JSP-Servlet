package com.test.dao.implement;

import com.test.dao.GenericDAO;
import com.test.mapper.RowMapper;
import com.test.model.AbstractModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T extends AbstractModel> implements GenericDAO<T> {

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;database=JSP_Servlet";
            return DriverManager.getConnection(url, "sa", "illusion");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        Connection cnn = getConnection();
        List<T> result;
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (cnn != null) {
            result = new ArrayList<>();
            try {
                stm = cnn.prepareStatement(sql);

                // set parameter

                rs = stm.executeQuery();
                while (rs.next()) {
                    result.add(rowMapper.mapRow(rs));
                }

                return result;
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                return null;
            } finally {
                try {
                    closeResource(cnn, stm, rs);
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return null;
    }

    public void closeResource(Connection cnn, PreparedStatement stm, ResultSet rs) throws SQLException {
        if (cnn != null)
            cnn.close();
        if (stm != null)
            stm.close();
        if (rs != null)
            rs.close();
    }
}
