package com.epam.news_manager.bean;

import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.DAOFactory;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class BeanFactory {

    private enum TYPES {
        BOOK,
        DISK,
        MOVIE
    }

    private Books books = new Books();
    private Keys keys;

    private static BeanFactory instance = new BeanFactory();

    private BeanFactory(){
        try {
            keys = DAOFactory.getInstance().getKeysDAO().read();
        } catch (DAOException e) {
            // TODO what TODO
        }finally {
            if (keys == null) {
                keys = new Keys();
            }
        }
    }

    //--------------getters


    public Bean getBean(Class type) throws ClassNotFoundException {
        TYPES t = TYPES.valueOf(type.getSimpleName().toUpperCase());

        if (t == TYPES.BOOK){
            return getBook();
        } if (t == TYPES.DISK){
            return getDisk();
        } if (t == TYPES.MOVIE){
            return getMovie();
        }
        throw new ClassNotFoundException();

    }

    public Keys getKeys() {
        return keys;
    }

    public Book getBook() {
        return new Book();
    }


    public Disk getDisk() {
        return new Disk();
    }

    public Movie getMovie() {
        return new Movie();
    }


    public Books getBooks() {
        return books;
    }





    public static BeanFactory getInstance() {
        return instance;
    }
}
