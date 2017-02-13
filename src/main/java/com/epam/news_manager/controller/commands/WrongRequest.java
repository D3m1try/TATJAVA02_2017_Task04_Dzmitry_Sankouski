package com.epam.news_manager.controller.commands;

import com.epam.news_manager.controller.exception.UnknownCommand;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class WrongRequest implements com.epam.news_manager.controller.Command {

    @Override
    public String execute(String request) throws  UnknownCommand {
        throw new UnknownCommand();

    }
}
