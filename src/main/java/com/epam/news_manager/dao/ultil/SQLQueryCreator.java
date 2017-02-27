package com.epam.news_manager.dao.ultil;

import com.epam.news_manager.bean.Bean;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * Created by Dzmitry_Sankouski on 10-Feb-17.
 */
public class SQLQueryCreator {
    private static SQLQueryCreator instance = new SQLQueryCreator();
    private final String CREATE_TABLE_BASE = "CREATE TABLE IF NOT EXISTS ";
    private final String INSERT_BASE = "INSERT INTO ";
    private final String SELECT_BASE = "SELECT * FROM ";
    private final String GAP = " ";
    private final String LEFT_PARENSIS = "(";
    private final String RIGHT_PARENSIS = ")";
    private final String SEMICOLON = ";";
    private final String COMMA = ",";
    private final String APOSTROPHE = "'";


    private SQLQueryCreator(){

    }

    public static SQLQueryCreator getInstance() {
        return instance;
    }

    public String getCreateTable(Bean bean) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        StringBuilder query = new StringBuilder(CREATE_TABLE_BASE);
        query.append(bean.getClass().getSimpleName()).append(GAP).append(LEFT_PARENSIS);

        for (Map.Entry<String,Object> field :
                BeanSurgeon.getFields(bean).entrySet()) {
            query.append(field.getKey())
                    .append(GAP)
                    .append(typeConvert(field.getValue()))
                    .append(COMMA)
                    .append(GAP);
        }

        query.replace(query.length() - 2, query.length() - 1 , RIGHT_PARENSIS);//TODO remove magic nums
        // erasing gap & comma from last cycle iteration
        return query.toString();
    }

    public String getInsert(Bean bean) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        StringBuilder query = new StringBuilder(INSERT_BASE);
        StringBuilder values = new StringBuilder(LEFT_PARENSIS);
        StringBuilder cols = new StringBuilder(LEFT_PARENSIS);

        query.append(bean.getClass().getSimpleName()).append(GAP);

        for (Map.Entry<String,Object> field :
                BeanSurgeon.getFields(bean).entrySet()) {

            if (field.getValue() instanceof Date){
                field.setValue(new java.sql.Date(((Date) field.getValue()).getTime()));
            } // converting utils.date to sql.date

            cols.append(field.getKey()).append(COMMA).append(GAP);
            values.append(APOSTROPHE).append(field.getValue()).append(APOSTROPHE).append(COMMA).append(GAP);
        }

        cols.replace(cols.length() - 2, cols.length() - 1 , RIGHT_PARENSIS);
        values.replace(values.length() - 2, values.length() - 1, RIGHT_PARENSIS);

        query.append(cols).append(" values ").append(values);

        return query.toString();
    }

    // objects of different classes stored in different tables
    public String getSelect(Class type, String id){
        StringBuilder result = new StringBuilder();
        result.append(SELECT_BASE).append(type.getSimpleName().toLowerCase())
                .append(" where id = ")
                .append(APOSTROPHE).append(id).append(APOSTROPHE);
        return  result.toString();
    }


    public String getSelect(Class type, String field, String value){
        StringBuilder result = new StringBuilder();
        result.append(SELECT_BASE).append(type.getSimpleName().toLowerCase())
                .append(" where ").append(field).append(" = ")
                .append(APOSTROPHE).append(value).append(APOSTROPHE);
        return  result.toString();
    }


    public String getFind(String field, String value){
        StringBuilder result = new StringBuilder();

        return  result.toString();
    }


    private String typeConvert(Object target){

        if (target instanceof String){
            return "varchar(500)";
        } if (target instanceof Integer){
            return "int";
        } if (target instanceof Date){
            return "date";
        }
        return target.getClass().getSimpleName();
    }

}
