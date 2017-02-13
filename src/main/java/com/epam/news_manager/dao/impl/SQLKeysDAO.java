package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Keys;
import com.epam.news_manager.dao.exception.DAOException;

import java.sql.*;


/**
 * Created by Dzmitry_Sankouski on 10-Feb-17.
 */
public class SQLKeysDAO extends SQLGenericDAOimpl{
    String[] readKeysQueries = {
            "select id from (books)",
            "select id from (disks);",
            "select id from (movies)"
    };
    Connection connection;
    int affectedRows;
    private final String CON_NAME = "jdbc:mysql://127.0.0.1/";
    private final String USER = "root";
    private final String PASS = "123456";
    private final String DB_NAME = "News";

    public Keys read() throws DAOException {
        Keys result = null;

        try {
            ResultSet resultSet;
            connection = DriverManager.getConnection(CON_NAME,USER,PASS);
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(readKeysQueries[1]);
            while (resultSet.next()){
                result.getBookIDs().add(resultSet.getString("id"));
            }


        } catch (SQLException e) {
            e.printStackTrace();// TODO EXCeption
        }


        return result;
    }

}
