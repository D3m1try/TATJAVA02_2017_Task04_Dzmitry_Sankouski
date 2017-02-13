package com.epam.news_manager.controller.commands;

import com.epam.news_manager.controller.Command;
import com.epam.news_manager.controller.exception.ControllerException;
import com.epam.news_manager.controller.impl.CommandName;

/**
 * Created by Dzmitry_Sankouski on 05-Feb-17.
 */
public class Help implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        String helpMessage = "";

        try {
            helpMessage = CommandName.valueOf(request.toUpperCase()).getUsage();
        } catch (IllegalArgumentException e) {
            helpMessage = CommandName.HELP.getUsage();
        }

        return helpMessage;
    }
}
