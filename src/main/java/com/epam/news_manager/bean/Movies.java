package com.epam.news_manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 27-Feb-17.
 */
public class Movies extends Bean implements Serializable {
    private static Movies instance = new Movies();
    List<Movie> movies = new ArrayList<>();
    List<Movie> savedMovies = new ArrayList<>();

    public static Movies getInstance() {
        return instance;
    }

    public List<Movie> getSavedMovies() {
        return savedMovies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}

