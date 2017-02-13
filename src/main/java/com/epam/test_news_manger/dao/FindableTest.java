package com.epam.test_news_manger.dao;

import com.epam.news_manager.bean.*;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.impl.DAOFactory;
import com.epam.news_manager.dao.impl.FileGenericDAOImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dzmitry_Sankouski on 04-Feb-17.
 */
public class FindableTest {
    static Book book;
    static Books books;
    static Book disk;
    static Disks disks;
    static Movie movie;
    static Movies movies;
    static String id;


    @DataProvider(name = "test1")
    public static Object[][] sources() {
        book = BeanFactory.getInstance().getBook();
        book.setTitle("zzzzzz");
        book.setDate(new Date(13454566));
        book.setMessage("kjndfskjsjkdfjvnsk");
        book.setISBN("sdjfkvgsjdfkvasjdfvka");

        books = BeanFactory.getInstance().getBooks();
        books.setTitle("ksjfkas");
        books.setDate(new Date(13454566));
        books.setMessage("kjndfskjsdkfsjkdfjvnsk");
        for (int i = 0; i < 3; i++) {
            books.getListOfBooks().add(book);
        }

        disk = BeanFactory.getInstance().getDisk();
        disk.setTitle("disk");
        disk.setDate(new Date(13454566));
        disk.setMessage("kjndfskjsdkfsjkdfjvnsk");

        disks = BeanFactory.getInstance().getDisks();
        disks.setTitle("disks");
        disks.setDate(new Date(13454566));
        disks.setMessage("kjn--------------dfskjsdkfsjkdfjvnsk");
        for (int i = 0; i < 3; i++) {
            disks.getListOfDisks().add(disk);
        }

        movie = BeanFactory.getInstance().getMovie();
        movie.setTitle("ksjfssssssssssssskas");
        movie.setDate(new Date(13454566));
        movie.setMessage("kjndfskjsdkfsjkdfhhhhhhhhhhhhhhhhhjvnsk");

        movies = BeanFactory.getInstance().getMovies();
        movies.setTitle("ksjqqq22222fkas");
        movies.setDate(new Date(13454566));
        movies.setMessage("kjndfskjs33333333333dkfsjkdfjvnsk");
        for (int i = 0; i < 3; i++) {
            movies.getListOfMovies().add(movie);
        }

        return new Object[][] {
                {DAOFactory.getInstance().getBookDAO(), book},
                {DAOFactory.getInstance().getBooksDAO(), books},
                {DAOFactory.getInstance().getDiskDAO(), disk},
                {DAOFactory.getInstance().getDisksDAO(), disks},
                {DAOFactory.getInstance().getMovieDAO(), movie},
                {DAOFactory.getInstance().getMoviesDAO(), movies}
        };
    }

    @Test(dataProvider = "test1")
    public void findTest(FileGenericDAOImpl dao, Serializable bean) throws DAOException{
        BeanFactory.getInstance().getKeys().getBookIDs().add(dao.create(bean));
        Assert.assertEquals(bean,dao.find("date",new Date(13454566).toString(), false).get(0),"find fails");
    }
}
