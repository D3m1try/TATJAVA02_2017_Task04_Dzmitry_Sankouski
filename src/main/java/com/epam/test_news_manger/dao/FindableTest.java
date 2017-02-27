package com.epam.test_news_manger.dao;

import com.epam.news_manager.bean.*;
import com.epam.news_manager.dao.GenericDAO;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.DAOFactory;
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
    static Disk disk;

    static Movie movie;

    static String id;


    @DataProvider(name = "test1")
    public static Object[][] sources() {
        book = BeanFactory.getInstance().getBook();
        book.setTitle("zzzzzz");
        book.setDat(new Date(13454566));
        book.setMessage("kjndfskjsjkdfjvnsk");
        book.setISBN("sdjfkvgsjdfkvasjdfvka");

        books = BeanFactory.getInstance().getBooks();
        books.setTitle("ksjfkas");
        books.setDat(new Date(13454566));
        books.setMessage("kjndfskjsdkfsjkdfjvnsk");
        for (int i = 0; i < 3; i++) {
            books.getBooks().add(book);
        }

        disk = BeanFactory.getInstance().getDisk();
        disk.setTitle("disk");
        disk.setDat(new Date(13454566));
        disk.setMessage("kjndfskjsdkfsjkdfjvnsk");


        movie = BeanFactory.getInstance().getMovie();
        movie.setTitle("ksjfssssssssssssskas");
        movie.setDat(new Date(13454566));
        movie.setMessage("kjndfskjsdkfsjkdfhhhhhhhhhhhhhhhhhjvnsk");


        return new Object[][] {
                {DAOFactory.getInstance().getBookDAO(), book},
                {DAOFactory.getInstance().getDiskDAO(), disk},
                {DAOFactory.getInstance().getMovieDAO(), movie},
        };
    }

    @Test(dataProvider = "test1")
    public void findTest(GenericDAO<Serializable, String> dao, Serializable bean) throws DAOException {
        BeanFactory.getInstance().getKeys().getBookIDs().add(dao.create(bean));
        Assert.assertEquals(bean,dao.find("date",new Date(13454566).toString(), false).get(0),"find fails");
    }
}
