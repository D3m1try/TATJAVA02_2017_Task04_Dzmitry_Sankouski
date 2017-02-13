package com.epam.news_manager.bean;

import com.epam.news_manager.dao.impl.DAOFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class Keys implements Serializable,Identifiable<String>{
    public static String selfId = "0.keys";

    public Keys(){

    }

    List<String> booksIDs = new ArrayList<>();
    List<String> bookIDs = new ArrayList<>();
    List<String> disksIDs = new ArrayList<>();
    List<String> diskIDs = new ArrayList<>();
    List<String> moviesIDs = new ArrayList<>();
    List<String> movieIDs = new ArrayList<>();
    List<String> otherIDs = new ArrayList<>();

    public List<String> getBooksIDs() {
        return booksIDs;
    }

    public List<String> getBookIDs() {
        return bookIDs;
    }

    public List<String> getDisksIDs() {
        return disksIDs;
    }

    public List<String> getDiskIDs() {
        return diskIDs;
    }

    public List<String> getMoviesIDs() {
        return moviesIDs;
    }

    public List<String> getMovieIDs() {
        return movieIDs;
    }

    public int getSize(){
        return bookIDs.size() + booksIDs.size() + diskIDs.size() + disksIDs.size() + movieIDs.size() + moviesIDs.size() +
                + otherIDs.size();
    }

    public List<String> getAll(){
        List<String> result = new ArrayList<>();
        result.addAll(bookIDs);
        result.addAll(booksIDs);
        result.addAll(diskIDs);
        result.addAll(disksIDs);
        result.addAll(movieIDs);
        result.addAll(moviesIDs);
        result.addAll(otherIDs);
        return result;
    }

    @Override
    public String getId() {
        return selfId;
    }

    @Override
    public void setId(String id) {
        //dont need it
    }
}
