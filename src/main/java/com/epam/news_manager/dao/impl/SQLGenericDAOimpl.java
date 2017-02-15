package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.Bean;
import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Identifiable;
import com.epam.news_manager.dao.Findable;
import com.epam.news_manager.dao.GenericDAO;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.ultil.BeanSurgeon;
import com.epam.news_manager.dao.ultil.SQLQueryCreator;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 09-Feb-17.
 */
public class SQLGenericDAOimpl<T extends Serializable & Identifiable<String>> implements GenericDAO<T, String>, Findable {
    private Class<T> type;



    public SQLGenericDAOimpl(Class<T> type) {
        this.type = type;
    }

    public SQLGenericDAOimpl() {

    }

    @Override
    public String create(T newInstance) throws DAOException {
        Connection connection;
        int affectedRows;
        String id;
        Statement statement;

        try {
            id = StringIdGenerator.getInstance().generateId(newInstance);
            newInstance.setId(id);
            //assigning id

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SHOW TABLES LIKE '" + type.getSimpleName().toLowerCase() + "'");
            if(!rs.next()){
                statement.executeUpdate(SQLQueryCreator.getInstance().getCreateTable((Bean) newInstance));
            }


            affectedRows = statement.executeUpdate(SQLQueryCreator.getInstance().getInsert((Bean) newInstance));

            ConnectionPool.getInstance().returnConnection(connection);

            return id;
        } catch (SQLException e) {

            throw new DAOException(e);
        } catch (IllegalAccessException e) {
            throw new DAOException(e);
        } catch (IntrospectionException e) {
            throw new DAOException(e);
        } catch (InvocationTargetException e) {
            throw new DAOException(e);
        }

    }


    @Override
    public T read(String id) throws DAOException {
        Connection connection;
        String query = SQLQueryCreator.getInstance().getSelect(type, id);

        try {
            connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            // retrieving data from database

            T result = (T) BeanFactory.getInstance().getBean(type);
            // creating object to fill

            BeanSurgeon.setFields(result, resultSet);

            ConnectionPool.getInstance().returnConnection(connection);

            return result;
        } catch (InvocationTargetException e1) {
            throw new DAOException(e1);
        } catch (IntrospectionException e1) {
            throw new DAOException(e1);
        } catch (SQLException e1) {
            throw new DAOException(e1);
        } catch (IllegalAccessException e1) {
            throw new DAOException(e1);
        } catch (ClassNotFoundException e1) {
            throw new DAOException(e1);
        }

    }

    @Override
    public void update(Serializable transientObject) throws DAOException {

    }

    @Override
    public void delete(Serializable persistentObject) {

    }

    @Override
    public List<T> find(String fieldName, String value, boolean isPureSearch) {
        Connection connection;
        Statement statement;
        String query = "";
        List<T> result = new ArrayList<>();
        List<String> resultIds = new ArrayList<>();
        if (isPureSearch){

            query = SQLQueryCreator.getInstance().getSelect(type, fieldName, value);

        } else {

//            query = SQLQueryCreator.getInstance().getSelect(type, fieldName, value);
            //TODO query for not pure search
        }


        try {
            T bean = (T) BeanFactory.getInstance().getBean(type);
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();

            // retrieving data from database
            ResultSet resultSet = statement.executeQuery(query);

            // fillling bean with data & adding it to result
            while (resultSet.next()){
                BeanSurgeon.setFields(bean,resultSet);
                result.add(bean);
            }

            ConnectionPool.getInstance().returnConnection(connection);
        } catch (InvocationTargetException e1) {
            //TODO logging
        } catch (IntrospectionException e1) {
            //TODO logging
        } catch (SQLException e1) {
            //TODO logging
        } catch (IllegalAccessException e1) {
            //TODO logging
        } catch (ClassNotFoundException e1) {
            //TODO logging
        }
        return result;
    }
}
