package com.epam.test_news_manger.dao;
import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Keys;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.impl.DAOFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.Assert.*;

import java.io.File;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public class KeysDAOSmokeTest {
    private Keys keys = null;

    @BeforeClass
    public void createKeys(){
        keys = BeanFactory.getInstance().getKeys();
        keys.getBookIDs().add("sdfgs");
        keys.getBookIDs().add("sddsds");
    }

    @Test
    public void createRead() throws DAOException {
        Keys keysReadResult;

        DAOFactory.getInstance().getKeysDAO().create(keys);
        keysReadResult = DAOFactory.getInstance().getKeysDAO().read();
        Assert.assertTrue(keys.getBookIDs().equals(keysReadResult.getBookIDs()),"create-read scenario failed");
    }

    @Test
    public void createUpdateRead() throws DAOException {
        Keys keysReadResult;

        DAOFactory.getInstance().getKeysDAO().create(keys);
        keys.getBookIDs().add("kjasdfka");
        DAOFactory.getInstance().getKeysDAO().update(keys);
        keysReadResult = DAOFactory.getInstance().getKeysDAO().read();
        Assert.assertTrue(keys.getBookIDs().equals(keysReadResult.getBookIDs()),"create-modify-read scenario failed");
    }

    @Test
    public void createDelete() throws DAOException {
        DAOFactory.getInstance().getKeysDAO().create(keys);
        DAOFactory.getInstance().getKeysDAO().delete(keys);

        Assert.assertFalse(new File(keys.getId()).exists(),"createDelete scenario failed");
    }

//    @AfterTest
//    public void cleanUp(){
//        new File(keys.getId()).delete();
//    }


}
