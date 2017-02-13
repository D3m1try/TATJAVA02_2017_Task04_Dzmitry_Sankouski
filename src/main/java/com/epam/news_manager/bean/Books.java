package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Books extends Bean implements Serializable {

    List<Book> listOfBooks = new ArrayList<>();

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }
}
