package com.epam.news_manager.dao.impl;

import com.epam.news_manager.bean.Keys;
import com.epam.news_manager.dao.exception.DAOException;
import java.io.*;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
@Deprecated
public class KeysDAO extends FileGenericDAOImpl<Keys> {

    public Keys read() throws DAOException {
        FileInputStream fis = null;
//        ObjectInputStream oin = null;
        Keys result = null;

        try {
            fis = new FileInputStream(new File(Keys.selfId));
        } catch (FileNotFoundException e) {
            throw new DAOException("File " + Keys.selfId + " was not found");
        }

        try(ObjectInputStream oin = new ObjectInputStream(fis)) {
            result = (Keys) oin.readObject();
        } catch (IOException e) {
            throw new DAOException("IO Exception while reading " + Keys.selfId);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Class " + result.getClass().getSimpleName() + " was not found");
        }
        return result;
    }
}
