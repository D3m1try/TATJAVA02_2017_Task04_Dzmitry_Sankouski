package com.epam.news_manager.controller.impl;

import com.epam.news_manager.controller.Command;
import com.epam.news_manager.controller.exception.ControllerException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public final class Controller {
    private final CommandProvider provider = new CommandProvider();
    public String executeTask(String request) throws ControllerException {
        Pattern pattern = Pattern.compile("(\\w+)\\s?(.*)");
        Matcher matcher = pattern.matcher(request);
        matcher.find();

        String commandName;
        Command executionCommand;
        commandName = matcher.group(1);
        request = matcher.group(2);
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }
}