package com.epam.news_manager.controller;

import com.epam.news_manager.controller.exception.ControllerException;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public interface Command {
    public String execute(String request) throws ControllerException;
}
