package com.epam.news_manager.dao.ultil;

import com.epam.news_manager.bean.Bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dzmitry_Sankouski on 13-Feb-17.
 */
public class BeanSurgeon {
    public static Map<String,Object> getFields(Bean bean) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<String,Object> result = new HashMap<>();
        Class target = bean.getClass();

        BeanInfo info = Introspector.getBeanInfo(target);
        Object value;
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() ) {
            value = pd.getReadMethod().invoke(bean);

            result.put(pd.getName(), value);
        }

        result.remove("class");

        return  result;
    }

    public static Map<String,Class> getFields(Class target) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<String,Class> result = new HashMap<>();

        BeanInfo info = Introspector.getBeanInfo(target);
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() ) {
            result.put(pd.getName(),pd.getPropertyType());
        }

        result.remove("class");

        return  result;
    }

    public static void setFields(Object target, ResultSet set) throws IntrospectionException, SQLException, InvocationTargetException, IllegalAccessException {
        BeanInfo info = Introspector.getBeanInfo(target.getClass());
        Object value;
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() ) {

            if (!pd.getName().equalsIgnoreCase("class")){
            pd.getWriteMethod().invoke(target, set.getObject(set.findColumn(pd.getName())));
            }

        }
    }
}
