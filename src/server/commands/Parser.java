package server.commands;

import server.Exeptions.BadRequestException;
import server.Utils.CommandUtil;


public record Parser(String command) {

    public String parseCommand() throws BadRequestException {
        String[] newCommand = command.split(" ");
        if (newCommand.length < 3) throw new BadRequestException("Wrong command.");
        try {
            CommandPattern commandPattern = CommandPattern.valueOf(newCommand[0].toUpperCase());
            Command command = new Command(commandPattern);
            String[] keyValues = CommandUtil.removeFirstElementOfStringArray(newCommand);
            return command.execCommand(keyValues);
        } catch (IllegalArgumentException e) {

            throw new BadRequestException("Command " + newCommand[0].toUpperCase() + " doesn't exist.");
        }
    }
}