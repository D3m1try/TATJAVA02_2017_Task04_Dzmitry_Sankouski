package com.epam.news_manager.service.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Book;
import com.epam.news_manager.bean.Books;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.DAOFactory;
import com.epam.news_manager.service.exception.ServiceException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class BooksCatalog implements com.epam.news_manager.service.Catalog<Book> {
    private static BooksCatalog instance = new BooksCatalog();

    private BooksCatalog() {

    }

    private void initBooks() {
        if (BeanFactory.getInstance().getKeys().getBookIDs().size() == 0) {

        } else {
            for (String id :
                    BeanFactory.getInstance().getKeys().getBookIDs()) {
                try {
                    Books.getInstance().getBooks().add(DAOFactory.getInstance().getBookDAO().read(id));
                } catch (DAOException e) {
                    //TODO logging
                }
            }
        }
    } // retrieving books

    @Override
    public void add(String request) throws ServiceException {
        Book newBook = new Book();
        fillBook(newBook, request);

        Books.getInstance().getBooks().add(newBook);
        try {
            BeanFactory.getInstance().getKeys().getBookIDs().add(
                    DAOFactory.getInstance().getBookDAO().create(newBook));
            DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void edit(String request) {

    }

    public void fillBook(Book book, String request) throws ServiceException {
        Pattern pattern = Pattern.compile("(\\s\\-[^\\s]+\\s+)([^\\s]+)");
        Matcher matcher = pattern.matcher(request);

        while (matcher.find()) {
            if (matcher.group(1).toUpperCase().contains("D")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    book.setDat(format.parse(matcher.group(2)));
                } catch (ParseException e) {
                    throw new ServiceException("Wrong date format");
                }
            }
            if (matcher.group(1).toUpperCase().contains("P")) {
                try {
                    book.setPageCount(Integer.valueOf(matcher.group(2)));
                } catch (NumberFormatException e) {
                    throw new ServiceException("Page count should be Integer");
                }
            }
            if (matcher.group(1).toUpperCase().contains("ISBN")) {
                book.setISBN(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("T")) {
                book.setTitle(matcher.group(2));
            }

            if (matcher.group(1).toUpperCase().contains("M")) {
                book.setMessage(matcher.group(2));
            }
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Book> find(String request) throws ServiceException {
        Pattern pattern = Pattern.compile("(\\-p)?\\s?(\\w+)\\s+(.+)");
        Matcher matcher = pattern.matcher(request);

        if (!matcher.find()) {
            throw new ServiceException("Illegal arguments");
        }
        if (matcher.group(1) != null) {
            return DAOFactory.getInstance().getBookDAO().find(matcher.group(2), matcher.group(3), true);
        } else {
            return DAOFactory.getInstance().getBookDAO().find(matcher.group(2), matcher.group(3), false);
        }
    }

    @Override
    public void save() throws ServiceException {
        for (Book book :
                Books.getInstance().getBooks()) {
            try {
                DAOFactory.getInstance().getBookDAO().create(book);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage());
            }
            Books.getInstance().getSavedBooks().add(book);
        }

        try {
            DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
            DAOFactory.getInstance().getBooksDAO().update(BeanFactory.getInstance().getBooks());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    public static BooksCatalog getInstance() {
        return instance;
    }
}
