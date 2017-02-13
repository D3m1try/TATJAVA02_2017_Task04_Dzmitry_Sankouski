package com.epam.news_manager.dao.exception;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class DAOException extends Exception{

    public DAOException(String message){
        super(message);
    }

    public DAOException(Exception e){
        super(e);
    }
}
