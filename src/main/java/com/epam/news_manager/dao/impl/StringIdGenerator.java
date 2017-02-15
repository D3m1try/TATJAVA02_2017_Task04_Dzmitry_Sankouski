package com.epam.news_manager.dao.impl;


import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Keys;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public class StringIdGenerator {
    private static StringIdGenerator instance = new StringIdGenerator();

    private StringIdGenerator(){

    }

    public static StringIdGenerator getInstance() {
        return instance;
    }

    public String generateId(Object object){

        if (object instanceof Keys){
            return "0.keys";
        }

        return  (BeanFactory.getInstance().getKeys().getSize() + 1) + "." + object.getClass().getSimpleName();
    }

    public Class getTypeById(String id) throws ClassNotFoundException {
        return Class.forName(id.substring(2));
    }

}
