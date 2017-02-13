package com.epam.test_news_manger.services;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Book;
import com.epam.news_manager.service.exception.ServiceException;
import com.epam.news_manager.service.impl.BooksCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dzmitry_Sankouski on 03-Feb-17.
 */
public class BookCatalogTests {

    @Test
    public void addTest() throws ParseException,ServiceException {
        Book testBook;

        String ISBN = "klsdfhgla";
        String TITLE = "lasdjkfhg;alg";
        String MESSAGE = "KJDSFGKGFRA";
        String DATE_STR = "2013-03-03";
        int COUNT = 231;
        Date date;

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        date = format.parse(DATE_STR);


        Book book = new Book();

        book.setISBN(ISBN);
        book.setTitle(TITLE);
        book.setPageCount(COUNT);
        book.setPageCount(COUNT);
        book.setDate(date);
        book.setMessage(MESSAGE);

        BooksCatalog.getInstance().add("-ISBN " + ISBN + " -d " + DATE_STR + " -t " + TITLE + " -m " + MESSAGE + " -p " + COUNT);
        testBook = BeanFactory.getInstance().getBooks().getListOfBooks().get(0);

        Assert.assertEquals(book,testBook,"not equals");


    }
}
