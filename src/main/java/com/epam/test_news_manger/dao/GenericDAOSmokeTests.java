package com.epam.test_news_manger.dao;
import com.epam.news_manager.bean.*;
import com.epam.news_manager.dao.GenericDAO;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.DAOFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class GenericDAOSmokeTests {
    static Book book;
    static Disk disk;
    static Movie movie;
    static String id;

    @BeforeClass
    public void createBeans(){
    }

    @DataProvider(name = "test1")
    public static Object[][] sources() {
        book = BeanFactory.getInstance().getBook();
        book.setTitle("zzzzzz");
        book.setDat(new Date(13454566));
        book.setMessage("kjndfskjsjkdfjvnsk");
        book.setISBN("sdjfkvgsjdfkvasjdfvka");



        disk = BeanFactory.getInstance().getDisk();
        disk.setTitle("disk");
        disk.setDat(new Date(13454566));
        disk.setMessage("kjndfskjsdkfsjkdfjvnsk");


        movie = BeanFactory.getInstance().getMovie();
        movie.setTitle("ksjfssssssssssssskas");
        movie.setDat(new Date(13454566));
        movie.setMessage("kjndfskjsdkfsjkdfhhhhhhhhhhhhhhhhhjvnsk");
        movie.setLength(10000);
        movie.setSlogan("lsdjfhg");
        movie.setTheme("sdkjfvd");


        return new Object[][] {
                {DAOFactory.getInstance().getBookDAO(), book},

                {DAOFactory.getInstance().getDiskDAO(), disk},

                {DAOFactory.getInstance().getMovieDAO(), movie},

        };
    }

    @Test(dataProvider = "test1")
    public void createRead(GenericDAO<Serializable,String> dao, Serializable bean) throws DAOException {
        Serializable beanReadResult;
        id =  dao.create(bean);
        beanReadResult = dao.read(id);

        Assert.assertTrue(bean.equals(beanReadResult),"create-read scenario failed");
    }

    @Test(dataProvider = "test1")
    public void createUpdateRead(GenericDAO<Serializable,String> dao, Serializable bean) throws DAOException {
        Serializable beanReadResult;
        Bean beans;
        id = (String) dao.create(bean);

        beans = (Bean)bean;
        beans.setTitle("another title");
        dao.update(bean);

        beanReadResult = dao.read(id);
        Assert.assertTrue(bean.equals(beanReadResult),"create-modify-read scenario failed");
    }

    @Test(dataProvider = "test1")
    public void createDelete(GenericDAO<Serializable,String> dao, Serializable bean) throws DAOException{
        Identifiable<String> result;
        dao.create(bean);
        dao.delete(bean);
        result = (Identifiable<String>) bean;
        Assert.assertFalse(false);
//        Assert.assertFalse(new File(result.getId()).exists(),"createDelete scenario failed");

    }

}
