package com.epam.news_manager.controller.commands;

import com.epam.news_manager.controller.Command;
import com.epam.news_manager.service.exception.ServiceException;
import com.epam.news_manager.service.impl.BooksCatalog;
import com.epam.news_manager.service.impl.DisksCatalog;
import com.epam.news_manager.service.impl.MoviesCatalog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class Add implements Command {
    @Override
    public String execute(String request) {
        Pattern pattern = Pattern.compile("^(\\s*)([\\w]+)");
        Matcher matcher = pattern.matcher(request);
        matcher.find();

        try {
            if (matcher.group(2).toLowerCase().equals("book")){
                BooksCatalog.getInstance().add(request);
            }
            if (matcher.group(2).toLowerCase().equals("disk")){
                DisksCatalog.getInstance().add(request);
            }
            if (matcher.group(2).toLowerCase().equals("movie")){
                MoviesCatalog.getInstance().add(request);
            }
        } catch (ServiceException e) {
            return e.getMessage();
        }

        return "";
    }
}
