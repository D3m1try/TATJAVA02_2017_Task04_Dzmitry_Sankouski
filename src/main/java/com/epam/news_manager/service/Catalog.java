package com.epam.news_manager.service;


import com.epam.news_manager.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 30-Jan-17.
 */
public interface Catalog<T> {

    public void add(String request) throws ServiceException;

    public void delete();

    public List<T> find(String request) throws ServiceException;

    public void save() throws ServiceException;
}
