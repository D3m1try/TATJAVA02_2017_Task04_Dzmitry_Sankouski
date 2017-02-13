package com.epam.news_manager.bean;

import java.io.Serializable;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Book extends Bean implements Serializable {
    private int pageCount;
    private String ISBN;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName()).append("\n").
                append("title:").append(getTitle())
                .append(";date:").append(getDate())
                .append(";message:").append(getMessage()).
                append(";isbn:").append(getISBN()).
                append(";pageCount:").append(getPageCount()).
                append(";id:").append(getId());

        return result.toString();

    }
}
