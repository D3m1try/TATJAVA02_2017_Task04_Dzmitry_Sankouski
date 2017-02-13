package com.epam.news_manager.controller.commands;

import com.epam.news_manager.controller.Command;
import com.epam.news_manager.controller.exception.ControllerException;
import com.epam.news_manager.controller.exception.ExitException;

/**
 * Created by Dzmitry_Sankouski on 05-Feb-17.
 */
public class Exit implements Command {

    @Override
    public String execute(String request) throws ControllerException {
        throw new ExitException();
    }
}
