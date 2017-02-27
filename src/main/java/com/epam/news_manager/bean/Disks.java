package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 27-Feb-17.
 */
public class Disks extends Bean implements Serializable {
    private static Disks instance = new Disks();
    List<Disk> disks = new ArrayList<>();
    List<Disk> savedDisks = new ArrayList<>();

    public static Disks getInstance() {
        return instance;
    }

    public List<Disk> getSavedDisks() {
        return savedDisks;
    }

    public List<Disk> getDisks() {
        return disks;
    }
}

