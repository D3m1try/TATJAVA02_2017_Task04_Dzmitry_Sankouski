package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Disks extends Bean implements Serializable {
    List<Book> listOfDisks = new ArrayList<>();

    public List<Book> getListOfDisks() {
        return listOfDisks;
    }
}
