package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public abstract class Bean implements Identifiable<String> , Serializable{
    String id = null;
    private String title ;
    private Date dat;
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

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
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
        result = (result) && this.dat.equals(dat);
        result = (result) && this.message.equals(message);

        return result;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dat != null ? dat.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
