package com.epam.news_manager.bean;

import java.io.Serializable;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Disk extends Bean implements Serializable {


    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName()).append("\n").
                append("title:").append(getTitle())
                .append(";date:").append(getDate())
                .append(";message:").append(getMessage()).
                append(";id:").append(getId());

        return result.toString();

    }

}
