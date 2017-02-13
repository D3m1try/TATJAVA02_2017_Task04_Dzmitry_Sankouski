package com.epam.news_manager.view;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Book;
import com.epam.news_manager.dao.impl.FileIdGenerator;
import com.epam.news_manager.dao.ultil.SQLQueryCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        book.setDate(new Date(13454566));
        book.setMessage("kjndfskjsjkdfjvnsk");
        book.setISBN("sdjfkvgsjdfkvasjdfvka");
        book.setId("0.book");

        System.out.println(SQLQueryCreator.getInstance().getCreateTable(book));
        System.out.println(SQLQueryCreator.getInstance().getInsert(book));


    }
}
