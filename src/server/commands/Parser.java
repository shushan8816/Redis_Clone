package server.commands;

import server.Exeptions.BadRequestException;
import server.Utils.CommandUtil;

public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public String parseCommand() throws BadRequestException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length < 2) throw new BadRequestException("Wrong command.");
        try {
            CommandPattern commandStrategy = CommandPattern.valueOf(splitCommand[0].toUpperCase());
            Command command = new Command(commandStrategy);
            String[] keyValues = CommandUtil.removeFirstElementOfStringArray(splitCommand);
            return command.execCommand(keyValues);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Command " + splitCommand[0].toUpperCase() + " doesn't exist.");
        }
    }
}