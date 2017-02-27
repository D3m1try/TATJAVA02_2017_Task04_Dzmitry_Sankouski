package com.epam.news_manager.service.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Movie;
import com.epam.news_manager.bean.Movies;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.DAOFactory;
import com.epam.news_manager.service.exception.ServiceException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class MoviesCatalog implements com.epam.news_manager.service.Catalog<Movie> {
    private static MoviesCatalog instance = new MoviesCatalog();
    private boolean isMoviessUp = false;

    private MoviesCatalog() {

    }

    private void initBooks() throws ServiceException {
        if (BeanFactory.getInstance().getKeys().getBookIDs().size() == 0) {

        } else {
            for (String id :
                    BeanFactory.getInstance().getKeys().getBookIDs()) {
                try {
                    Movies.getInstance().getSavedMovies().add(DAOFactory.getInstance().getMovieDAO().read(id));
                } catch (DAOException e) {
                    throw new ServiceException(e.getMessage());
                }
            }
        }
    }

    @Override
    public void add(String request) throws ServiceException {
        Movie newMovie = new Movie();
        fillMovie(newMovie, request);

        Movies.getInstance().getMovies().add(newMovie);
        try {
            BeanFactory.getInstance().getKeys().getMovieIDs().add(
                    DAOFactory.getInstance().getMovieDAO().create(newMovie));
            DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void edit(String request) {

    }

    public void fillMovie(Movie movie, String request) throws ServiceException {
        Pattern pattern = Pattern.compile("\\s(\\-[^\\s]+)\\s+([^\\s]+)");
        Matcher matcher = pattern.matcher(request);

        while (matcher.find()) {
            if (matcher.group(1).toUpperCase().equals("-TH")) {
                movie.setTheme(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().equals("-T")) {
                movie.setTitle(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("D")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    movie.setDat(format.parse(matcher.group(2)));
                } catch (ParseException e) {
                    throw new ServiceException("Wrong date format");
                }
            }

            if (matcher.group(1).toUpperCase().contains("M")) {
                movie.setMessage(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("S")) {
                movie.setSlogan(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("L")) {
                try {
                    movie.setLength(Integer.valueOf(matcher.group(2)));
                } catch (NumberFormatException e) {
                    throw new ServiceException("Length should be Integer");
                }
            }
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Movie> find(String request) throws ServiceException {
        Pattern pattern = Pattern.compile("(\\-p)?\\s?(\\w+)\\s+(.+)");
        Matcher matcher = pattern.matcher(request);

        if (!matcher.find()) {
            throw new ServiceException("Illegal arguments");
        }
        if (matcher.group(1) != null) {
            return DAOFactory.getInstance().getMovieDAO().find(matcher.group(2), matcher.group(3), true);
        } else {
            return DAOFactory.getInstance().getMovieDAO().find(matcher.group(2), matcher.group(3), false);
        }
    }

    @Override
    public void save() throws ServiceException {
        for (Movie movie :
                Movies.getInstance().getMovies()) {
            try {
                DAOFactory.getInstance().getMovieDAO().create(movie);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage());
            }
            Movies.getInstance().getSavedMovies().add(movie);
        }

        try {
            DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
            DAOFactory.getInstance().getMovieDAO().update(BeanFactory.getInstance().getMovie());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public static MoviesCatalog getInstance() {
        return instance;
    }
}
