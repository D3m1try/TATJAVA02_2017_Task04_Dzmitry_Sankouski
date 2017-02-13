package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.Bean;
import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Identifiable;
import com.epam.news_manager.dao.Findable;
import com.epam.news_manager.dao.GenericDAO;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.ultil.BeanTinker;
import com.epam.news_manager.dao.ultil.SQLQueryCreator;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 09-Feb-17.
 */
public class SQLGenericDAOimpl<T extends Serializable & Identifiable<String>> implements GenericDAO<T, String> {
    private Class<T> type;
    private final String CON_NAME = "jdbc:mysql://127.0.0.1/News";
    private final String USER = "root";
    private final String PASS = "123456";
    private final String DB_NAME = "News";
    private final String EXISTS_QUERY = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + DB_NAME + "'";
    private final String CREATE_QUERY = "INSERT INTO 'books' VALUES id = '2' ";

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
            id = FileIdGenerator.getInstance().generateId(newInstance);
            newInstance.setId(id);
            //assigning id

            connection = DriverManager.getConnection(CON_NAME, USER, PASS);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SHOW TABLES LIKE '" + type.getSimpleName().toLowerCase() + "'");
            if(!rs.next()){
                statement.executeUpdate(SQLQueryCreator.getInstance().getCreateTable((Bean) newInstance));
            }


            affectedRows = statement.executeUpdate(SQLQueryCreator.getInstance().getInsert((Bean) newInstance));

            return id;
        } catch (SQLException e) {
//            Pattern pattern = Pattern.compile("Table '.+' doesn't exist");
//            Matcher m = pattern.matcher(e.getMessage());
//            if (m.matches()){
//
//                statement.executeUpdate(SQLQueryCreator.getInstance().getInsert((Bean) newInstance))
//            }

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
        Map<String, Class> fields = null;
        String query = SQLQueryCreator.getInstance().getSelect(id, type);

        try {
            connection = DriverManager.getConnection(CON_NAME, USER, PASS);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            // retrieving data from database

            fields = BeanTinker.getFields(type);
            T result = (T) BeanFactory.getInstance().getBean(type);
            // creating object to fill

            BeanTinker.setFields(result, resultSet);
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

    public void update(Serializable transientObject) throws DAOException {

    }

    public void delete(Serializable persistentObject) {

    }

    public List<T> find(String fieldName, String value, boolean isPureSearch) {
        return null;
    }

}
