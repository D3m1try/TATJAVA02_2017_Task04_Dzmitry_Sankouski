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
                .append(";date:").append(getDat())
                .append(";message:").append(getMessage()).
                append(";isbn:").append(getISBN()).
                append(";pageCount:").append(getPageCount()).
                append(";id:").append(getId());

        return result.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (pageCount != book.pageCount) return false;
        return ISBN != null ? ISBN.equals(book.ISBN) : book.ISBN == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + pageCount;
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        return result;
    }
}
