package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Books extends Bean implements Serializable {
    private static Books instance = new Books();
    List<Book> books = new ArrayList<>();
    List<Book> savedBooks = new ArrayList<>();

    public static Books getInstance() {
        return instance;
    }

    public List<Book> getSavedBooks() {
        return savedBooks;
    }

    public List<Book> getBooks() {
        return books;
    }
}
