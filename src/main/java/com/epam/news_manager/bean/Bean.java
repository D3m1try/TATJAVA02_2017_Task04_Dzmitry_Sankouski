package com.epam.news_manager.bean;

import com.epam.news_manager.dao.impl.FileIdGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public abstract class Bean implements Identifiable<String> , Serializable{
    String id = null;
    private String title ;
    private Date date;
    private String message;

    public Bean(){

    }

    public Bean(String id){
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o){
        boolean result = true;
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }
        result = (result) && this.title.equals(title);
        result = (result) && this.date.equals(date);
        result = (result) && this.message.equals(message);

        return result;
    }


}
