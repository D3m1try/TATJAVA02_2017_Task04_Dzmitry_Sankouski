package com.epam.news_manager.bean;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public interface Identifiable<T> {
    public T getId();

    public void setId(T id);
}
