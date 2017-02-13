package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.*;
import com.epam.news_manager.dao.GenericDAO;

/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private KeysDAO keysDAO = new KeysDAO();
    private SQLGenericDAOimpl<Book> bookDAO = new SQLGenericDAOimpl(Book.class);
    private FileGenericDAOImpl<Disk> diskDAO = new FileGenericDAOImpl<>(Disk.class);
    private FileGenericDAOImpl<Movie> movieDAO = new FileGenericDAOImpl<Movie>(Movie.class);
    private FileGenericDAOImpl<Books> booksDAO = new FileGenericDAOImpl<Books>(Books.class);
    private FileGenericDAOImpl<Disks> disksDAO = new FileGenericDAOImpl<Disks>(Disks.class);
    private FileGenericDAOImpl<Movies> moviesDAO = new FileGenericDAOImpl<Movies>(Movies.class);

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public KeysDAO getKeysDAO() {
        return keysDAO;
    }

    public GenericDAO<Book,String> getBookDAO() {
        return bookDAO;
    }

    public FileGenericDAOImpl<Disk> getDiskDAO() {
        return diskDAO;
    }

    public FileGenericDAOImpl<Movie> getMovieDAO() {
        return movieDAO;
    }

    public FileGenericDAOImpl<Books> getBooksDAO() {
        return booksDAO;
    }

    public FileGenericDAOImpl<Disks> getDisksDAO() {
        return disksDAO;
    }

    public FileGenericDAOImpl<Movies> getMoviesDAO() {
        return moviesDAO;
    }
}
