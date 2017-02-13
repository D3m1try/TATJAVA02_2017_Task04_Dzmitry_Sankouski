package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.Bean;
import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Keys;
import com.epam.news_manager.dao.exception.DAOException;

import java.sql.*;


/**
 * Created by Dzmitry_Sankouski on 10-Feb-17.
 */
public class SQLKeysDAO extends SQLGenericDAOimpl{
    String[] readKeysQueries = {
            "select id from book",
            "select id from disk",
            "select id from movie"
    };
    Connection connection;
    int affectedRows;
//    private final String CON_NAME = "jdbc:mysql://127.0.0.1/News";
//    private final String USER = "root";
//    private final String PASS = "123456";
//    private final String DB_NAME = "News";

    public Keys read() throws DAOException {
        Keys result = null;

        try {
            ResultSet resultSet;
            connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(readKeysQueries[1]);
            while (resultSet.next()){
                result.getBookIDs().add(resultSet.getString("id"));
            }

            resultSet = statement.executeQuery(readKeysQueries[2]);
            while (resultSet.next()){
                result.getDiskIDs().add(resultSet.getString("id"));
            }

            resultSet = statement.executeQuery(readKeysQueries[3]);
            while (resultSet.next()){
                result.getMovieIDs().add(resultSet.getString("id"));
            }

            ConnectionPool.getInstance().returnConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();// TODO EXCeption
        }


        return result;
    }

}
