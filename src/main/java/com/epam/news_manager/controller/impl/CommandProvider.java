package com.epam.news_manager.controller.impl;

import com.epam.news_manager.controller.Command;
import com.epam.news_manager.controller.commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    CommandProvider(){
        repository.put(CommandName.ADD, new Add());
        repository.put(CommandName.FIND, new Find());
        repository.put(CommandName.NON_EXISTING, new WrongRequest());
        repository.put(CommandName.EXIT, new Exit());
        repository.put(CommandName.HELP, new Help());

    }

    Command getCommand(String name){
        CommandName commandName =null;
        Command command = null;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
//write log
            command = repository.get(CommandName.NON_EXISTING);
        }
        return command;
    }
}