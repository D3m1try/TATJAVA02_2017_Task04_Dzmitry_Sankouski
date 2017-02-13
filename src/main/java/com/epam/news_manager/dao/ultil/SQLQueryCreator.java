package com.epam.news_manager.dao.ultil;

import com.epam.news_manager.bean.Bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dzmitry_Sankouski on 10-Feb-17.
 */
public class SQLQueryCreator {
    private static SQLQueryCreator instance = new SQLQueryCreator();
    private final String CREATE_TABLE_BASE = "create table if not exists ";
    private final String INSERT_BASE = "insert into ";
    private final String PROBEL = " ";
    private final String LEFT_PARENSIS = "(";
    private final String RIGHT_PARENSIS = ")";
    private final String SEMICOLON = ";";
    private final String COMMA = ",";


    private SQLQueryCreator(){

    }

    public static SQLQueryCreator getInstance() {
        return instance;
    }

    //    private final String CREATE_TABLE_BASE = "create table if not exists ";



    public String getCreateTable(Bean bean) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        StringBuilder query = new StringBuilder(CREATE_TABLE_BASE);
        query.append(LEFT_PARENSIS);
        for (Map.Entry<String,Object> field :
                getFields(bean).entrySet()) {
            query.append(field.getKey()).append(PROBEL)
                    .append(typeConvert(field.getValue())).append(COMMA);
        }

        query.insert(query.length() - 2 , RIGHT_PARENSIS);
        return query.substring(0,query.length()-1); //TODO remove magic nums
    }

    public String getInsert(Bean bean) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        StringBuilder query = new StringBuilder(INSERT_BASE);
        StringBuilder values = new StringBuilder(LEFT_PARENSIS);
        StringBuilder cols = new StringBuilder(LEFT_PARENSIS);

        query.append(bean.getClass().getSimpleName()).append(PROBEL);

        for (Map.Entry<String,Object> field :
                getFields(bean).entrySet()) {
            cols.append(field.getKey()).append(COMMA).append(PROBEL);
            values.append(field.getValue()).append(COMMA).append(PROBEL);
        }

        cols.insert(cols.length() - 2 , RIGHT_PARENSIS); //inserting in  last iteration comma place to erase it
        values.insert(cols.length() - 2 , RIGHT_PARENSIS);
        query.append(cols).append(" values ").append(values);

        return query.toString();
    }



    private Map<String,Object> getFields(Bean bean) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<String,Object> result = new HashMap<>();
        Class target = bean.getClass();

        BeanInfo info = Introspector.getBeanInfo(target);
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() ) {
            result.put(pd.getName(),pd.getReadMethod().invoke(bean));
        }

        result.remove("class");

        return  result;
    }

    private String typeConvert(Object target){
        if (target == null){
            return null;
        } if (target instanceof String){
            return "varchar(500)";
        } if (target instanceof Integer){
            return "int";
        } if (target instanceof Date){
            return "date";
        }
        return target.getClass().getSimpleName();
    }

}
