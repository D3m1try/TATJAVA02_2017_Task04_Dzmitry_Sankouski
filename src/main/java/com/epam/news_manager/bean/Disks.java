package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Disks extends Bean implements Serializable {
    List<Disk> listOfDisks = new ArrayList<>();

    public List<Disk> getListOfDisks() {
        return listOfDisks;
    }
}
