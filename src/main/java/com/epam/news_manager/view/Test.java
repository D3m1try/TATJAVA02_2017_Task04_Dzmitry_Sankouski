package com.epam.news_manager.view;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Book;
import com.epam.news_manager.bean.Disk;
import com.epam.news_manager.bean.Movie;
import com.epam.news_manager.dao.ultil.SQLQueryCreator;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Date;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        DriverManager manager;
        Connection con;
        int i = 0;

//        Pattern p = Pattern.compile("\\s\\-ISBN+\\s+[^\\s]+\\s");
//        Matcher m = p.matcher(" -ISBN  asjk ");
//
//        Pattern pattern = Pattern.compile("^(\\s*)(\\w+)(.+)");
//        Matcher matcher = pattern.matcher("   book  -sdjkfjslsl l sda lhkl  ");
//
//
//        while (matcher.find()) {
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//            i++;
//        }
// reg exps

//        try {
////            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/", "root", "123456");
//        System.out.println("con est !!");
//        con.close();
        Book book;
        book = BeanFactory.getInstance().getBook();
        book.setTitle("zzzzzz");
        book.setDat(new Date(13454566));
        book.setMessage("kjndfskjsjkdfjvnsk");
        book.setISBN("sdjfkvgsjdfkvasjdfvka");
//        book.setId("0.book");

        Movie movie;
        movie = BeanFactory.getInstance().getMovie();
        movie.setTitle("zzzzzz");
        movie.setDat(new Date(13454566));
        movie.setMessage("kjndfskjsjkdfjvnsk");
        movie.setId("0.movie");

        movie = BeanFactory.getInstance().getMovie();
        movie.setTitle("ksjfssssssssssssskas");
        movie.setDat(new Date(13454566));
        movie.setMessage("kjndfskjsdkfsjkdfhhhhhhhhhhhhhhhhhjvnsk");

        System.out.println(SQLQueryCreator.getInstance().getCreateTable(book));
        System.out.println(SQLQueryCreator.getInstance().getInsert(book));
        System.out.println(SQLQueryCreator.getInstance().getSelect("0.book", book.getClass()));
        System.out.println(SQLQueryCreator.getInstance().getCreateTable(movie));


    }
}
