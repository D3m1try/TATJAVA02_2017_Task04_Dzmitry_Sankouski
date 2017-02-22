package com.epam.news_manager.dao;

import com.epam.news_manager.bean.*;
import com.epam.news_manager.dao.impl.FileGenericDAOImpl;
import com.epam.news_manager.dao.impl.KeysDAO;
import com.epam.news_manager.dao.impl.SQLGenericDAOimpl;

/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private KeysDAO keysDAO = new KeysDAO();
    private SQLGenericDAOimpl<Book> bookDAO = new SQLGenericDAOimpl(Book.class);
    private SQLGenericDAOimpl<Disk> diskDAO = new SQLGenericDAOimpl<Disk>(Disk.class);
    private SQLGenericDAOimpl<Movie> movieDAO = new SQLGenericDAOimpl<Movie>(Movie.class);
    private SQLGenericDAOimpl<Books> booksDAO = new SQLGenericDAOimpl<Books>(Books.class);


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

    public GenericDAO<Disk,String> getDiskDAO() {
        return diskDAO;
    }

    public GenericDAO<Movie,String> getMovieDAO() {
        return movieDAO;
    }

    public GenericDAO<Books,String> getBooksDAO() {
        return booksDAO;
    }


}
