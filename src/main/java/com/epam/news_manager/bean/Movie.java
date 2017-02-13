package com.epam.news_manager.bean;

import java.io.Serializable;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Movie extends Bean implements Serializable {
    private int length;
    private String theme;
    private String slogan;

//    ---------setters & getters


    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName()).append("\n").
                append("title:").append(getTitle()).
                append(";date:").append(getDate()).
                append(";message:").append(getMessage()).
                append(";theme:").append(getTheme()).
                append(";slogan:").append(getSlogan()).
                append(";length:").append(getLength()).
                append(";id:").append(getId());

        return result.toString();

    }
}
