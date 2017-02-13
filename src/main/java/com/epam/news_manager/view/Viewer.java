package com.epam.news_manager.view;

import com.epam.news_manager.bean.Bean;

import java.util.List;

/**
 * Created by Dzmitry_Sankouski on 04-Feb-17.
 */
public class Viewer {

    public static String beansToString(List<? extends Bean> beans) {
        StringBuilder result = new StringBuilder();
        if (beans.isEmpty()){
            return "nothing";
        }
        for (Bean bean :
                beans) {
            result.append(bean.getClass().getSimpleName()).append(":\n").
                append("Id: ").append(bean.getId()).append("\n").
                append("Title: ").append(bean.getTitle()).append("\n");
        }
        return result.toString();
    }
}
