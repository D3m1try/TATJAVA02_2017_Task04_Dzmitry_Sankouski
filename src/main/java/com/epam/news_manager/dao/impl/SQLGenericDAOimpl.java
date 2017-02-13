package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.Identifiable;
import com.epam.news_manager.dao.Findable;
import com.epam.news_manager.dao.GenericDAO;
import com.epam.news_manager.dao.exception.DAOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 09-Feb-17.
 */
public class SQLGenericDAOimpl<T extends Serializable & Identifiable<String>> implements GenericDAO<T, String> {
    private Class<T> type;
    private final String CON_NAME = "jdbc:mysql://127.0.0.1/";
    private final String USER = "root";
    private final String PASS = "123456";
    private final String DB_NAME = "News";
    private final String EXISTS_QUERY = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + DB_NAME + "'";
    private final String CREATE_QUERY = "INSERT INTO 'books' VALUES id = '2' ";

    public SQLGenericDAOimpl(Class<T> type){
        this.type = type;
    }

    public SQLGenericDAOimpl(){

    }


    public String create(Serializable newInstance) throws DAOException {
        Connection connection;
        int affectedRows;


        try {
            connection = DriverManager.getConnection(CON_NAME,USER,PASS);
            Statement statement = connection.createStatement();

            affectedRows = statement.executeUpdate(CREATE_QUERY);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    public T read(String id) throws DAOException {
        return null;
    }

    public void update(Serializable transientObject) throws DAOException {

    }

    public void delete(Serializable persistentObject) {

    }

    public List<T> find(String fieldName, String value, boolean isPureSearch) {
        return null;
    }

}
