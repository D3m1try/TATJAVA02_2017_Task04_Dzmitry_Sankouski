package com.epam.news_manager.dao;

import com.epam.news_manager.dao.exception.DAOException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public interface GenericDAO <T extends Serializable, PK> extends Findable {

    /** Сохранить объект newInstance в базе данных */
    PK create(T newInstance) throws DAOException;

    /** Извлечь объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    T read(PK id) throws DAOException;

    /** Сохранить изменения, сделанные в объекте.  */
    void update(T transientObject) throws DAOException;

    /** Удалить объект из базы данных */
    void delete(T persistentObject);
}