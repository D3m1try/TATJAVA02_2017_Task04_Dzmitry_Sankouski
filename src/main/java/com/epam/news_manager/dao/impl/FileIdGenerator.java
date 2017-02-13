package com.epam.news_manager.dao.impl;


import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Keys;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public class FileIdGenerator {
    private static FileIdGenerator instance = new FileIdGenerator();

    private FileIdGenerator(){

    }

    public static FileIdGenerator getInstance() {
        return instance;
    }

    public String generateId(Object object){

        if (object instanceof Keys){
            return "0.keys";
        }

        return  (BeanFactory.getInstance().getKeys().getSize() + 1) + "." + object.getClass().getSimpleName();
    }

}
