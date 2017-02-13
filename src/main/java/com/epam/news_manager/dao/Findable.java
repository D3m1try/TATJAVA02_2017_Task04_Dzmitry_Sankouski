package com.epam.news_manager.dao;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public interface Findable<T> {

    List<T> find(String fieldName, String value, boolean isPureSearch);
}
