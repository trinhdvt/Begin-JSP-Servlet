package com.test.dao.implement;

import com.test.dao.GenericDAO;
import com.test.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDAO<T> implements GenericDAO<T> {
    //    private static final String DB_PATH = "db/JSP_Servlet.db";
    private final ResourceBundle resource = ResourceBundle.getBundle("db");

    public Connection getConnection() {
        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://localhost;database=JSP_Servlet";
//           return DriverManager.getConnection(url, "sa", "illusion");
//            Class.forName("org.sqlite.JDBC");
//            String url = "jdbc:sqlite:" + DB_PATH;
//            return DriverManager.getConnection(url);
            Class.forName(resource.getString("DRIVER_NAME"));
            return DriverManager.getConnection(resource.getString("DB_PATH"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> select(String sql, RowMapper<T> rowMapper, Object... params) {
        Connection cnn = getConnection();
        List<T> result;
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (cnn != null) {
            result = new ArrayList<>();
            try {
                stm = cnn.prepareStatement(sql);

                setParameters(stm, params);

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

    @Override
    public void update(String sql, Object... params) {
        Connection cnn = getConnection();
        if (cnn != null) {
            try {
                cnn.setAutoCommit(false);
                try (PreparedStatement stm = cnn.prepareStatement(sql)) {
                    setParameters(stm, params);
                    stm.executeUpdate();
                    cnn.commit();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
                try {
                    cnn.rollback();
                } catch (SQLException ignored) {
                }
            } finally {
                try {
                    closeResource(cnn, null, null);
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public Long insert(String sql, Object... params) {
        Connection cnn = getConnection();
        Long id = null;
        if (cnn != null) {
            try {
                cnn.setAutoCommit(false);
                try (PreparedStatement stm = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    setParameters(stm, params);
                    stm.executeUpdate();

                    cnn.commit();

                    try (ResultSet rs = stm.getGeneratedKeys()) {
                        if (rs.next())
                            id = rs.getLong(1);
                    }

                    return id;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
                try {
                    cnn.rollback();
                } catch (SQLException ignored) {
                }
            } finally {
                try {
                    closeResource(cnn, null, null);
                } catch (SQLException ignored) {
                }
            }
        }
        return id;
    }

    @Override
    public int count(String sql, Object... params) {
        var cnn = getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (cnn != null) {
            try {
                stm = cnn.prepareStatement(sql);
                setParameters(stm, params);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                return 0;
            } finally {
                try {
                    closeResource(cnn, stm, rs);
                } catch (SQLException ignored) {
                }
            }

        }
        return 0;
    }

    private void setParameters(PreparedStatement stm, Object[] params) {
        try {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Long)
                    stm.setLong(i + 1, (Long) params[i]);
                else if (params[i] instanceof String)
                    stm.setString(i + 1, params[i] + "");
                else if (params[i] instanceof Integer)
                    stm.setInt(i + 1, (Integer) params[i]);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
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
